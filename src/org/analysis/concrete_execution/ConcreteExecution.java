package org.analysis.concrete_execution;

import org.analysis.api_stub.APIStub;
import org.analysis.cfg.Condition;
import org.analysis.cfg.IntraCFG;
import org.analysis.concolic_testing.TestCaseValue;
import org.analysis.formula.FormulaSet;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.*;
import org.jakstab.loader.StubProvider;
import org.jakstab.loader.Win32StubLibrary;

import v2.org.analysis.cfg.AddressList;
import v2.org.analysis.structure.BitVector;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.system.SystemHandle;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ConcreteExecution {
	public static int MAX_NUM_RUN = 1000;
	private static int MAX_NUM_LOOP_EACH = 1500;
	private static int MAX_NUM_LOOP_TOTAL = 200000;
	private int loopTotal = 0, loopEach = 0;
	private long loopAddr = Long.MIN_VALUE;
	private AddressList addrTraceList;
	private Map<AbsoluteAddress, Instruction> assemblyMap;
	private ConcreteFlag concreteFlag;
	private ConcreteValueMemoryOperand concreteValueMemoryOperand;
	private ConcreteStack concreteStack;
	private ConcreteValueRegister concreteValueRegister;
	private ConcreteValueRegisterPart concreteValueRegisterPart;
	private ConcreteValueSegment concreteValueSegment;
	private long evaluateValue;
	private FormulaSet formulaList;
	private Map<AbsoluteAddress, AbsoluteAddress> neg;
	private boolean run = false;
	private AbsoluteAddress targetTemp, targetIndirect, retAPIAddr = new AbsoluteAddress(0);
	private TestCaseValue var;
	private StubProvider stubLibrary;
	private String funcName = "";
	private Program program;
	private SystemHandle system;
	private boolean running = true;
	private boolean stop = false;
	private IntraCFG cfg;

	public Map<AbsoluteAddress, AbsoluteAddress> getNeg() {
		return neg;
	}

	public void setNeg(Map<AbsoluteAddress, AbsoluteAddress> neg) {
		this.neg = neg;
	}

	public ConcreteFlag getConcreteFlag() {
		return concreteFlag;
	}

	public void setConcreteFlag(ConcreteFlag concreteFlag) {
		this.concreteFlag = concreteFlag;
	}

	public ConcreteValueMemoryOperand getConcreteValueMemoryOperand() {
		return concreteValueMemoryOperand;
	}

	public void setConcreteValueMemoryOperand(ConcreteValueMemoryOperand concreteValueMemoryOperand) {
		this.concreteValueMemoryOperand = concreteValueMemoryOperand;
	}

	public ConcreteStack getConcreteStack() {
		return concreteStack;
	}

	public void setConcreteStack(ConcreteStack concreteStack) {
		this.concreteStack = concreteStack;
	}

	public ConcreteValueRegister getConcreteValueRegister() {
		return concreteValueRegister;
	}

	public void setConcreteValueRegister(ConcreteValueRegister concreteValueRegister) {
		this.concreteValueRegister = concreteValueRegister;
	}

	public ConcreteValueRegisterPart getConcreteValueRegisterPart() {
		return concreteValueRegisterPart;
	}

	public void setConcreteValueRegisterPart(ConcreteValueRegisterPart concreteValueRegisterPart) {
		this.concreteValueRegisterPart = concreteValueRegisterPart;
	}

	public ConcreteValueSegment getConcreteValueSegment() {
		return concreteValueSegment;
	}

	public void setConcreteValueSegment(ConcreteValueSegment concreteValueSegment) {
		this.concreteValueSegment = concreteValueSegment;
	}

	public StubProvider getStubLibrary() {
		return stubLibrary;
	}

	public void setStubLibrary(StubProvider stubLibrary) {
		this.stubLibrary = stubLibrary;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public SystemHandle getSystem() {
		return system;
	}

	public void setSystem(SystemHandle system) {
		this.system = system;
	}

	public ConcreteExecution(AbsoluteAddress target, AddressList addrTraceList,
			Map<AbsoluteAddress, AbsoluteAddress> neg, Map<AbsoluteAddress, Instruction> assemblyMap, TestCaseValue var) {
		super();
		this.targetTemp = target;
		this.targetIndirect = target;
		this.addrTraceList = addrTraceList;
		// this.traceList = addrTraceList.clone();
		this.assemblyMap = assemblyMap;
		// setFormulas(new SymbolicFormulas());
		formulaList = new FormulaSet();
		concreteValueRegister = new ConcreteValueRegister(var);
		concreteValueRegisterPart = new ConcreteValueRegisterPart(var);
		this.concreteValueRegister.setConcreteValueRegisterPart(this.concreteValueRegisterPart);
		this.concreteValueRegisterPart.setConcreteValueRegister(this.concreteValueRegister);
		concreteValueSegment = new ConcreteValueSegment(var);
		concreteValueMemoryOperand = new ConcreteValueMemoryOperand(var, Program.getProgram(), concreteValueRegister,
				concreteValueRegisterPart, concreteValueSegment, var);
		this.neg = neg;
		this.var = var;
		evaluateValue = Long.MIN_VALUE;
		concreteFlag = new ConcreteFlag();
		concreteStack = new ConcreteStack();
		this.concreteValueRegister.setConcreteFlag(concreteFlag);
	}

	public ConcreteExecution(AbsoluteAddress indirectTarget, AddressList trace,
			Map<AbsoluteAddress, AbsoluteAddress> negConditionList, Map<AbsoluteAddress, Instruction> assemblyMap2,
			TestCaseValue sv, StubProvider stubLibrary2) {
		// TODO Auto-generated constructor stub
		this(indirectTarget, trace, negConditionList, assemblyMap2, sv);
		stubLibrary = stubLibrary2;
	}

	private boolean contain(Map<AbsoluteAddress, String> stub, AbsoluteAddress address) {
		// TODO Auto-generated method stub
		if (address == null || stub == null)
			return false;
		Iterator<Entry<AbsoluteAddress, String>> it = stub.entrySet().iterator();
		long t = 0xFFFFFFFFL & address.getValue();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry pairs = (Map.Entry) it.next();
			// if
			// (((String)pairs.getValueOperand()).contains("GetModuleHandleA"))
			// System.out.println("Debug");
			AbsoluteAddress addr = ((AbsoluteAddress) pairs.getKey());
			if (addr.getValue() == t) {
				funcName = (String) pairs.getValue();
				return true;
			}
		}
		return false;
	}

	public ConcreteExecution(AbsoluteAddress target, AddressList addrTraceList,
			Map<AbsoluteAddress, Instruction> assemblyMap) {
		super();
		this.targetTemp = target;
		this.targetIndirect = target;
		this.addrTraceList = addrTraceList;
		// this.traceList = addrTraceList.clone();
		this.assemblyMap = assemblyMap;
		// setFormulas(new SymbolicFormulas());
		formulaList = new FormulaSet();
		concreteValueRegister = new ConcreteValueRegister();
		concreteValueRegisterPart = new ConcreteValueRegisterPart();
		this.concreteValueRegister.setConcreteValueRegisterPart(this.concreteValueRegisterPart);
		this.concreteValueRegisterPart.setConcreteValueRegister(this.concreteValueRegister);
		concreteValueSegment = new ConcreteValueSegment();
		concreteValueMemoryOperand = new ConcreteValueMemoryOperand();
		neg = new TreeMap<AbsoluteAddress, AbsoluteAddress>();
		var = new TestCaseValue();
		evaluateValue = Long.MIN_VALUE;
		concreteFlag = new ConcreteFlag();
		concreteStack = new ConcreteStack();
		this.concreteValueRegister.setConcreteFlag(concreteFlag);
	}

	public ConcreteExecution(AbsoluteAddress indirectTarget, AddressList concreteTrace,
			Map<AbsoluteAddress, AbsoluteAddress> negConditionList, TestCaseValue sv, Program program) {
		// TODO Auto-generated constructor stub
		this(indirectTarget, concreteTrace, negConditionList, program.getAssemblyMap(), sv, program.getStubLibrary());
		this.program = program;
	}

	public ConcreteExecution(AbsoluteAddress indirectTarget, AddressList concreteTrace,
			Map<AbsoluteAddress, AbsoluteAddress> negConditionList, TestCaseValue sv, Program program,
			SystemHandle system) {
		// TODO Auto-generated constructor stub
		this(indirectTarget, concreteTrace, negConditionList, program.getAssemblyMap(), sv, program.getStubLibrary());
		this.program = program;
		this.system = system;
	}

	public ConcreteExecution(AbsoluteAddress indirectTarget, AddressList concreteTrace, IntraCFG cfg, TestCaseValue sv,
			Program program2, SystemHandle system2) {
		// TODO Auto-generated constructor stub
		this(indirectTarget, concreteTrace, cfg.getNegConditionList(), program2.getAssemblyMap(), sv, program2
				.getStubLibrary());
		this.program = program2;
		this.system = system2;
		this.cfg = cfg;

	}

	private boolean checkArithmeticTargetExp(String type, String destName) {
		// return (getTargetExp(type, destName) instanceof long);
		return true;
	}

	private boolean checkSatPC() {
		this.writeZ3Input("z3Input.smt");
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start /wait run_z3.bat");
			System.out.println("Running script Z3...");
			int exitCode = p.waitFor();

			System.out.println("Done script. ExitCode:" + exitCode);
		} catch (Throwable e) {
			System.out.flush();
			e.printStackTrace();
			// Runtime.getRuntime().removeShutdownHook(shutdownThread);
			// Kills eclipse shutdown thread
			System.exit(1);
		}

		InputStream fis;
		BufferedReader br;

		try {

			fis = new FileInputStream("z3Output.txt");
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			// String line = "";
			if (br.readLine().equals("sat"))
				return true;
			br.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		// Done with the file
		// System.out.println(result.substring(8));

		br = null;
		fis = null;
		return false;
	}

	public boolean evaluate() {
		// boolean result = false;
		Instruction ins = assemblyMap.get(targetIndirect);
		String type = "", destName = "";
		if (ins.getOperand(0).getClass().getSimpleName().equals("X86MemoryOperand")) {
			type = "X86MemoryOperand";
			destName = ins.getOperand(0).toString();
		}

		if (ins.getOperand(0).getClass().getSimpleName().equals("X86Register")) {
			type = "X86Register";
			destName = ins.getOperand(0).toString();
		}

		if (ins.getOperand(0).getClass().getSimpleName().equals("X86RegisterPart")) {
			type = "X86RegisterPart";
			destName = ins.getOperand(0).toString();
		}

		if (ins.getOperand(0).getClass().getSimpleName().equals("X86SegmentRegister")) {
			type = "X86SegmentRegister";
			destName = ins.getOperand(0).toString();
		}
		int numL = 0;

		while (checkArithmeticTargetExp(type, destName)) {
			numL++;
			long vTarget = getTargetExp(type, destName);
			AbsoluteAddress ad = new AbsoluteAddress(vTarget);
			if (!addrTraceList.contain(ad)) {
				if (!executeZ3())
					return false;
				this.evaluateValue = vTarget;
				return true;
			} else {
				AddressList addrL = addrTraceList.clone();
				addrTraceList = addrTraceList.cut(ad);
				while (true) {
					targetTemp = addrTraceList.pop();
					if (targetTemp.getValue() == targetIndirect.getValue())
						// System.out.println("End of Symbolic Execution");
						// formulas.printInfo();
						break;

					ins = assemblyMap.get(targetTemp);
					for (int i = 0; i < ins.getOperandCount(); i++)
						if (ins.getOperand(i).getClass().getSimpleName().equals("X86Register"))
							var.add(ins.getOperand(i).toString(), 0);
						else if (ins.getOperand(i).getClass().getSimpleName().equals("X86RegisterPart"))
							var.add(ins.getOperand(i).toString(), 0);
						else if (ins.getOperand(i).getClass().getSimpleName().equals("X86SegmentRegister"))
							var.add(ins.getOperand(i).toString(), 0);
						else if (ins.getOperand(i).getClass().getSimpleName().equals("X86MemoryOperand"))
							var.add(((X86MemoryOperand) ins.getOperand(i)).toString(), 0);

					if (ins instanceof X86ArithmeticInstruction)
						this.X86ArithmeticInterprete((X86ArithmeticInstruction) ins);
					else if (ins instanceof X86CallInstruction)
						this.X86CallInterprete((X86CallInstruction) ins);
					else if (ins instanceof X86CondJmpInstruction)
						this.X86ConditionalJumpInterprete((X86CondJmpInstruction) ins);
					else if (ins instanceof X86JmpInstruction)
						this.X86JumpInterprete((X86JmpInstruction) ins);
					else if (ins instanceof X86MoveInstruction)
						this.X86MoveInterprete((X86MoveInstruction) ins);
					else if (ins instanceof X86RetInstruction)
						this.X86ReturnInterprete((X86RetInstruction) ins);
					else if (ins instanceof X86Instruction)
						this.X86InstructionInterprete((X86Instruction) ins);
				}
				this.addrTraceList = addrL;
			}

			if (!checkSatPC())
				return false;

			// Exit after 100 times running
			if (numL > 2) {
				this.evaluateValue = vTarget;
				break;
			}
		}
		return false;
	}

	public AbsoluteAddress execute() {
		// this.printInfor();
		// AbsoluteAddress result = null;
		AbsoluteAddress result = null;
		AddressList addrL = addrTraceList.clone();
		long t = addrL.get(0).getValue();
		int num_run = 0;
		run = true;
		// long returnValue = 4276551680L;
		// long returnValue = (long) (Math.random() * Math.pow(2, 31));
		// long returnValue = 0;
		concreteStack.push(this.system.getKernel().getReturnRandomValue());
		concreteValueRegister.mov("%edx", t);
		while (running) {
			if (!run)
				break;
			if (stop)
				break;

			targetTemp = addrTraceList.pop();

			if (targetTemp == null)
				break;
			Instruction ins = assemblyMap.get(targetTemp);

			if (targetTemp.getValue() == this.targetIndirect.getValue() && addrTraceList.isEmpty()) {
				// System.out.println("End of Symbolic Execution");
				// formulas.printInfo();
				if (ins.getName().equals("jmp")) {
					if (stubLibrary != null
							&& contain(((Win32StubLibrary) stubLibrary).getAddressStubMap(), targetTemp)) {
						// result = new AbsoluteAddress
						System.out.println("Address:" + targetTemp.toString());
						if (APIStub.executeConcrete(funcName, this, assemblyMap.get(targetTemp)))
							funcName = "";

						result = retAPIAddr;
						break;
					}
				}
				result = getTargetValue();

				if (result != null && !addrL.contain(result))
					break;

				num_run++;
				if (num_run >= MAX_NUM_RUN)
					break;

				addrTraceList = addrL.clone();
				targetTemp = addrTraceList.popAddr(result);
			}

			/*
			 * if (targetTemp.toString().equals("0x00401119"))
			 * System.out.println("Debug");
			 * 
			 * if (targetTemp.toString().equals("0x0040111f"))
			 * System.out.println("Debug");
			 */

			/*
			 * for (int i = 0; i < ins.getOperandCount(); i++) if
			 * (ins.getOperand(i).getClass().getSimpleName()
			 * .equals("X86Register")) var.add(ins.getOperand(i).toString(), 0);
			 * else if (ins.getOperand(i).getClass().getSimpleName()
			 * .equals("X86MemoryOperand")) var.add(((X86MemoryOperand)
			 * ins.getOperand(i)).toString(), 0);
			 */

			if (ins instanceof X86ArithmeticInstruction)
				this.X86ArithmeticInterprete((X86ArithmeticInstruction) ins);
			else if (ins instanceof X86CallInstruction)
				this.X86CallInterprete((X86CallInstruction) ins);
			else if (ins instanceof X86CondJmpInstruction)
				this.X86ConditionalJumpInterprete((X86CondJmpInstruction) ins);
			else if (ins instanceof X86JmpInstruction)
				this.X86JumpInterprete((X86JmpInstruction) ins);
			else if (ins instanceof X86MoveInstruction)
				this.X86MoveInterprete((X86MoveInstruction) ins);
			else if (ins instanceof X86RetInstruction)
				this.X86ReturnInterprete((X86RetInstruction) ins);
			else if (ins instanceof X86Instruction)
				this.X86InstructionInterprete((X86Instruction) ins);
		}
		// this.addrTraceList = addrL;
		// if (!checkSatPC())
		// return null;
		// return evaluate() || executeZ3();
		// return executeZ3();
		if (result == null && this.evaluateValue != Long.MIN_VALUE)
			result = new AbsoluteAddress(this.evaluateValue);

		return result;
	}

	public boolean executeZ3() {
		this.writeZ3Input("z3Input.smt");
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start /wait run_z3.bat");
			System.out.println("Running script Z3...");
			int exitCode = p.waitFor();

			System.out.println("Done script. ExitCode:" + exitCode);
		} catch (Throwable e) {
			System.out.flush();
			e.printStackTrace();
			// Runtime.getRuntime().removeShutdownHook(shutdownThread);
			// Kills eclipse shutdown thread
			System.exit(1);
		}
		return this.readZ3Output("z3Output.txt");
	}

	public Condition generateCondProver() {
		Condition cond = new Condition();
		return cond;
	}

	public AddressList getAddrTraceList() {
		return addrTraceList;
	}

	public Map<AbsoluteAddress, Instruction> getAssemblyMap() {
		return assemblyMap;
	}

	private long getBitVecValue(long value) {
		if (value < 0)
			return (long) (Math.pow(2, 32) + value);
		else
			return value;
	}

	public long getEvaluateValue() {
		return evaluateValue;
	}

	public FormulaSet getFormulas() {
		return formulaList;
	}

	/*
	 * private void X86ConditionalJumpInterprete(X86CondJmpInstruction inst) {
	 * Operand dest = inst.getOperand1();
	 * 
	 * // long returnAddress = 0; if
	 * (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) { //
	 * returnAddress = ((X86AbsoluteAddress) dest).getValueOperand(); } else if
	 * (dest.getClass().getSimpleName() .equals("X86PCRelativeAddress")) { //
	 * returnAddress = ((X86PCRelativeAddress) dest) //
	 * .getEffectiveValue(target.getValueOperand()); } else if
	 * (dest.getClass().getSimpleName().equals("X86Register")) { //
	 * returnAddress = prevState.getRegValue(((X86Register) //
	 * dest).toString()).clone(); }
	 * 
	 * // System.out.println("Instruction: " + inst.getName());
	 * 
	 * // structure: create new work associated with condition-false case //
	 * jump to condition-true case // add state condition into each trace //
	 * SymbolicCondition compareStatus = prevState.getCompareStatus(); // we
	 * accept loop not to be constrained by compareStatus if
	 * (inst.getName().equals("loop")) { // if (compareStatus == null) { //
	 * logger.error("Only accept conditional statement after CMP"); //
	 * halt_status = FAILED; //
	 * SymbolicExecution.programTrace.setVisited(startAddress, null, //
	 * inst.toString(startAddress, symFinder)); // return null; // } //
	 * FormulaSet f = resolveLoop(inst); FormulaSet farkas = new
	 * FarkasAlgorithm(targetTemp, assemblyMap, traceList).resolve();
	 * farkas.add(new Formula(new VarExp("ecx"), new ConstantExp(0), "="));
	 * //farkas.printInfo(); this.formulas.add(farkas);
	 * System.out.println("Process Loop with Destination:" + dest.toString());
	 * return; } // SymbolicCondition positiveCond = new SymbolicCondition(); //
	 * SymbolicCondition negativeCond = new SymbolicCondition(); // int
	 * positiveConnector, negativeConnector; if
	 * (neg.get(targetTemp).getValueOperand() == this.addrTraceList.get(0)
	 * .getValueOperand()) this.formulas.getTop().setOperator(
	 * CondJump.getReserCondJump((inst.getName()))); else
	 * this.formulas.getTop().setOperator(
	 * CondJump.getCondJump((inst.getName()))); // // if
	 * (inst.getName().equals("ja") || inst.getName().equals("jg")) { // // //
	 * positiveConnector = SymbolicCondition.B_OP_GREATER; // // //
	 * negativeConnector = SymbolicCondition.B_OP_NOT_GREATER; // // // // }
	 * else if (inst.getName().equals("jae") || // inst.getName().equals("jge"))
	 * { // // // positiveConnector = SymbolicCondition.B_OP_NOT_LESS; // // //
	 * negativeConnector = SymbolicCondition.B_OP_LESS; // // if
	 * (neg.get(target).getValueOperand() == //
	 * this.addrTraceList.get(0).getValueOperand()) // //
	 * this.formulas.getTop().setOperator(3); // // else // //
	 * this.formulas.getTop().setOperator(6); // // } else if
	 * (inst.getName().equals("jb") || // inst.getName().equals("jl")) { // //
	 * // positiveConnector = SymbolicCondition.B_OP_LESS; // // //
	 * negativeConnector = SymbolicCondition.B_OP_NOT_LESS; // // if
	 * (neg.get(target).getValueOperand() == //
	 * this.addrTraceList.get(0).getValueOperand()) // //
	 * this.formulas.getTop().setOperator(6); // // else // //
	 * this.formulas.getTop().setOperator(3); // // } else if
	 * (inst.getName().equals("jbe") || // inst.getName().equals("jle")) { // //
	 * // positiveConnector = SymbolicCondition.B_OP_NOT_GREATER; // // //
	 * negativeConnector = SymbolicCondition.B_OP_GREATER; // // if
	 * (neg.get(target).getValueOperand() == //
	 * this.addrTraceList.get(0).getValueOperand()) // //
	 * this.formulas.getTop().setOperator(3); // // else // //
	 * this.formulas.getTop().setOperator(5); // // } else if
	 * (inst.getName().equals("je")) { // // // positiveConnector =
	 * SymbolicCondition.B_OP_EQUAL; // // // negativeConnector =
	 * SymbolicCondition.B_OP_NOT_EQUAL; // // if
	 * (neg.get(target).getValueOperand() == //
	 * this.addrTraceList.get(0).getValueOperand()) // //
	 * this.formulas.getTop().setOperator(4); // // else // //
	 * this.formulas.getTop().setOperator(1); // // } else if
	 * (inst.getName().equals("jne")) { // // // positiveConnector =
	 * SymbolicCondition.B_OP_NOT_EQUAL; // // // negativeConnector =
	 * SymbolicCondition.B_OP_EQUAL; // // if (neg.get(target).getValueOperand()
	 * == // this.addrTraceList.get(0).getValueOperand()) // //
	 * this.formulas.getTop().setOperator(1); // // else // //
	 * this.formulas.getTop().setOperator(4); // // } else if
	 * (inst.getName().equals("jnl")) { // // // positiveConnector =
	 * SymbolicCondition.B_OP_NOT_LESS; // // // negativeConnector =
	 * SymbolicCondition.B_OP_LESS; // // if (neg.get(target).getValueOperand()
	 * == // this.addrTraceList.get(0).getValueOperand()) // //
	 * this.formulas.getTop().setOperator(3); // // else // //
	 * this.formulas.getTop().setOperator(6); // } else if
	 * (inst.getName().equals("loop")) { // // decrease ecx, check if it is
	 * equal to 0 // // SymbolicValue ecx = prevState.getRegValue("%ecx"); // //
	 * SymbolicValue newDest = new SymbolicValue(ecx); // //
	 * newDest.addExprValue(SymbolicValue.SUB_EXPR, new // // SymbolicValue(1));
	 * // // prevState.setRegValue("%ecx", newDest); // // set condition // //
	 * compareStatus = new SymbolicCondition(); // //
	 * compareStatus.addCondition(prevState.getRegValue("%ecx").clone(), // //
	 * new SymbolicValue(0), SymbolicCondition.B_OP_UNDEFINED); // //
	 * positiveConnector = SymbolicCondition.B_OP_NOT_EQUAL; // //
	 * negativeConnector = SymbolicCondition.B_OP_EQUAL; // } else { // //
	 * logger.error("Unsupported instruction: " + inst.getName()); // //
	 * halt_status = FAILED; // //
	 * SymbolicExecution.programTrace.setVisited(startAddress, null, // //
	 * inst.toString(startAddress, symFinder)); // // return null; // } }
	 */

	private String getNameZ3Result(String line) {
		// TODO Auto-generated method stub
		String result[] = line.split(" ");
		return result[0];
	}

	public AbsoluteAddress getTarget() {
		return targetTemp;
	}

	private long getTargetExp(String type, String destName) {
		long v = 0;
		if (type.equals("X86Register"))
			v = this.concreteValueRegister.getRegVal(destName);
		else if (type.equals("X86RegisterPart"))
			v = this.concreteValueRegisterPart.getRegVal(destName);
		else if (type.equals("X86SegmentRegister"))
			v = this.concreteValueSegment.getRegVal(destName);
		else if (type.equals("X86MemoryOperand"))
			v = this.concreteValueMemoryOperand.getMemoryOperandVal(destName);

		return v;
	}

	private AbsoluteAddress getTargetValue() {
		// TODO Auto-generated method stub
		long a = 0;
		Instruction ins = assemblyMap.get(targetIndirect);
		if (ins.getName().startsWith("ret")) {
			a = concreteStack.pop();
		} else if (ins.getName().startsWith("call")) {
			AbsoluteAddress returnAddress = new AbsoluteAddress(targetTemp.getValue() + ins.getSize());

			if (ins.getOperand(0).getClass().getSimpleName().equals("X86Register"))
				a = concreteValueRegister.getRegVal(ins.getOperand(0).toString());
			else if (ins.getOperand(0).getClass().getSimpleName().equals("X86RegisterPart"))
				a = concreteValueRegisterPart.getRegVal(ins.getOperand(0).toString());
			else if (ins.getOperand(0).getClass().getSimpleName().equals("X86SegmentRegister"))
				a = concreteValueSegment.getRegVal(ins.getOperand(0).toString());
			else if (ins.getOperand(0).getClass().getSimpleName().equals("X86MemoryOperand")) {
				// a =
				// this.calculateValueMemoryOperand((X86MemoryOperand)ins.getOperand(i),
				// ins);
				// a = concreteValueMemoryOperand
				// .getMemoryOperandVal(((X86MemoryOperand)
				// ins.getOperand(0)).toString());
				a = this.calculateValueMemoryOperand(((X86MemoryOperand) ins.getOperand(0)), ins);
				if (stubLibrary != null
						&& contain(((Win32StubLibrary) stubLibrary).getAddressStubMap(), new AbsoluteAddress(a))) {
					// result = new AbsoluteAddress
					System.out.println("Address:" + targetTemp.toString());
					if (APIStub.executeConcrete(funcName, this, assemblyMap.get(targetTemp)))
						funcName = "";

					a = returnAddress.getValue();
					// result = retAPIAddr;
					// break;
				}
			}

			this.evaluateValue = a;

			concreteValueRegister.sub("%esp", 4);
			concreteStack.push(returnAddress.getValue());

		} else {
			for (int i = 0; i < ins.getOperandCount(); i++)
				if (ins.getOperand(i).getClass().getSimpleName().equals("X86Register"))
					a = concreteValueRegister.getRegVal(ins.getOperand(i).toString());
				else if (ins.getOperand(i).getClass().getSimpleName().equals("X86RegisterPart"))
					a = concreteValueRegisterPart.getRegVal(ins.getOperand(i).toString());
				else if (ins.getOperand(i).getClass().getSimpleName().equals("X86SegmentRegister"))
					a = concreteValueSegment.getRegVal(ins.getOperand(i).toString());
				else if (ins.getOperand(i).getClass().getSimpleName().equals("X86MemoryOperand"))
					// a =
					// this.calculateValueMemoryOperand((X86MemoryOperand)ins.getOperand(i),
					// ins);
					a = concreteValueMemoryOperand.getMemoryOperandVal(((X86MemoryOperand) ins.getOperand(i))
							.toString());
		}
		if (a != Long.MIN_VALUE)
			return new AbsoluteAddress(a);
		else
			return null;
	}

	private long getValueZ3Result(String line) {
		// TODO Auto-generated method stub
		long val = 0;
		String result[] = line.split(" ");
		if (result[2].startsWith("(")) {
			val = Long.parseLong(result[3].substring(0, result[3].length() - 1));
			// if (val > 100000) val = 0;
			if (result[2].substring(1).equals("-"))
				val = -val;
		} else
			val = Long.parseLong(result[2]);
		return val;
	}

	public void printInfor() {
		System.out.println();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Apply the method of Symbolic Execution:");
		System.out.print("Trace List of Address: ");
		this.addrTraceList.printInfo();

	}

	public void printResult() {
		// TODO Auto-generated method stub
		System.out.println("Result of Symbolic Execution:");
		// printResult();
		this.formulaList.printInfo();
		this.concreteValueRegister.printInfo();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println();

	}

	public boolean readZ3Output(String fileName) {
		// String result = "";
		InputStream fis;
		BufferedReader br;
		String line;
		try {
			fis = new FileInputStream(fileName);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));

			if ((line = br.readLine()).equals("sat")) {
				while ((line = br.readLine()) != null) {
					String name = getNameZ3Result(line);
					long val = getValueZ3Result(line);
					var.add(name, val);
				}
				br.close();
				return true;
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		// Done with the file
		// System.out.println(result.substring(8));

		br = null;
		fis = null;
		return false;
	}

	public String reverseConditionJump(String instName) {
		// TODO Auto-generated method stub
		String result = "";
		if (instName.equals("ja") || instName.equals("jnbe")) {
			// if
			// ((CF) = 0) or ((ZF) = 0)
			// Not finished
			result = "jbe";
		} else if (instName.equals("jae") || instName.equals("jnb")) {
			// if
			// ((CF) = 0)
			result = "jb";
		} else if (instName.equals("jb") || instName.equals("jnae")) {
			// if
			// ((CF) = 1)
			result = "jae";
		} else if (instName.equals("jbe") || instName.equals("jna")) {
			// if
			// ((CF) = 1) or ((ZF) = 1)
			// Not finished
			result = "ja";
		} else if (instName.equals("jc")) {
			// if
			// ((CF) = 1)
			result = "jnc";
		} else if (instName.equals("jcxz")) {
			// if
			// ((CF) = 0)
			result = "jcxz";
		} else if (instName.equals("je") || instName.equals("jz")) {
			// if
			// ((ZF) = 1)
			result = "jne";
		} else if (instName.equals("jg") || instName.equals("jnle")) {
			// if
			// ((SF) = (OF)) and ((ZF) = 0)
			result = "jle";
		} else if (instName.equals("jge") || instName.equals("jnl")) {
			// if
			// (SF) = (OF)
			result = "jl";
		} else if (instName.equals("jl") || instName.equals("jnge")) {
			// if
			// (SF) ≠ (OF)
			result = "jge";
		} else if (instName.equals("jle") || instName.equals("jng")) {
			// if
			// ((SF) ≠ (OF)) or ((ZF) = 1)
			// not finished
			result = "jg";
		} else if (instName.equals("jnc")) {
			// if
			// ((CF) = 0)
			result = "jc";
		} else if (instName.equals("jne") || instName.equals("jnz")) {
			// if
			// ((ZF) = 0)
			result = "je";
		} else if (instName.equals("jno")) {
			// if
			// ((OF) = 0)
			result = "jo";
		} else if (instName.equals("jns")) {
			// if
			// ((SF) = 0)
			result = "js";
		} else if (instName.equals("jnp") || instName.equals("jpo")) {
			// if
			// ((PF) = 0)
			result = "jp";
		} else if (instName.equals("jo")) {
			// if
			// ((OF) = 0)
			result = "jno";
		} else if (instName.equals("jp") || instName.equals("jpe")) {
			// if
			// ((PF) = 1)
			result = "jnp";
		} else if (instName.equals("js")) {
			// if
			// ((SF) = 1)
			result = "jns";
		}
		return result;
	}

	public void setAddrTraceList(AddressList addrTraceList) {
		this.addrTraceList = addrTraceList;
	}

	public void setAssemblyMap(Map<AbsoluteAddress, Instruction> assemblyMap) {
		this.assemblyMap = assemblyMap;
	}

	public void setEvaluateValue(long evaluateValue) {
		this.evaluateValue = evaluateValue;
	}

	public void setFormulas(FormulaSet formulas) {
		this.formulaList = formulas;
	}

	public void setTarget(AbsoluteAddress target) {
		this.targetTemp = target;
	}

	public void solver() {

	}

	public void writeZ3Input(String fileName) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
			writer.println("(benchmark example");
			writer.println(" :status sat");
			writer.println(" :logic QF_LIA");
			writer.println(":extrafuns (" + var.getStringZ3() + ")");
			writer.println(":formula" + formulaList.toStringPrefix());
			writer.println(")");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// add, inc, and, sub, or
	private void X86ArithmeticInterprete(X86ArithmeticInstruction ins) {

		switch (ins.getOperation()) {
		case UMUL:
			if (ins.getName().startsWith("unsignedMul")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				long op = 0;
				if (src.getClass().getSimpleName().equals("X86Register")) {
					op = concreteValueRegister.getRegVal(src.toString());
					// symbolValueRegister.setSymbolRegisterValue(dest.toString(),
					// new ValueLongExp(0));
					// symbolValueRegister.set
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					op = concreteValueRegisterPart.getRegVal(src.toString());
					// symbolValueRegisterPart.setSymbolRegisterPartValue(
					// src.toString(), new ValueLongExp(0));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					op = concreteValueSegment.getRegVal(src.toString());
					// symbolValueSegment.setSymbolSegmentValue(src.toString(),
					// new ValueLongExp(0));
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					op = concreteValueMemoryOperand.getMemoryOperandVal(src.toString());
					// symbolValueMemoryOperand.setSymbolMemoryOperandValue(
					// (X86MemoryOperand) src, new ValueLongExp(0));
				}

				// Exp eax = symbolValueRegister.getRegVal(src.toString());
				if (dest.getClass().getSimpleName().equals("X86Register")) {
					concreteValueRegister.mul(dest.toString(), op);
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					concreteValueRegisterPart.mul(dest.toString(), op);
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					concreteValueSegment.mul(dest.toString(), op);
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					concreteValueMemoryOperand.mul((X86MemoryOperand) dest, op);
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			}
			break;

		case ADD:
			// Statement supported: add, inc
			if (ins.getName().startsWith("add")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueRegister.add(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueRegister.add(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegister.add(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegister.add(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						long t = calculateValueMemoryOperand((X86MemoryOperand) src, ins);
						concreteValueRegister.add(dest.toString(), t);
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueRegisterPart.add(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueRegisterPart.add(dest.toString(),
								concreteValueRegisterPart.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegisterPart.add(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegisterPart.add(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						concreteValueRegisterPart.add(dest.toString(),
								concreteValueMemoryOperand.getMemoryOperandVal((X86MemoryOperand) src));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueSegment.add(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueSegment.add(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueSegment.add(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueSegment.add(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						concreteValueSegment.add(dest.toString(),
								concreteValueMemoryOperand.getMemoryOperandVal((X86MemoryOperand) src));
					}
				}
				// X86MemoryOperand
				else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueMemoryOperand.add((X86MemoryOperand) dest,
								concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueMemoryOperand.add((X86MemoryOperand) dest,
								concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueMemoryOperand.add((X86MemoryOperand) dest,
								concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueMemoryOperand.add((X86MemoryOperand) dest, getBitVecValue(((Immediate) src)
								.getNumber().intValue()));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						concreteValueMemoryOperand.add((X86MemoryOperand) dest,
								concreteValueMemoryOperand.getMemoryOperandVal((X86MemoryOperand) src));
					}
				}

				// newDest.addExprValue(SymbolicValue.ADD_EXPR, result);
			} else if (ins.getName().startsWith("inc")) {
				Operand dest = ins.getOperand1();
				if (dest.getClass().getSimpleName().equals("X86Register")) {
					concreteValueRegister.add(dest.toString(), 1);
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					concreteValueRegisterPart.add(dest.toString(), 1);
					// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister"))
					concreteValueSegment.add(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// System.out.println("Information of this X86MemoryOperand");
					// System.out.println(((X86MemoryOperand)dest).toString());
					// System.out.println(((X86MemoryOperand)dest).getDisplacement());
					// System.out.println(((X86MemoryOperand)dest).getScale());
					// System.out.println(((X86MemoryOperand)dest).);
					this.concreteValueMemoryOperand.add((X86MemoryOperand) dest, 1);
				}

				// result = new SymbolicValue(1);
				// newDest.addExprValue(SymbolicValue.ADD_EXPR, result);
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// ins.toString(startAddress, symFinder));
				// return null;
			}
			break;
		case ADDC:
			// Statement supported: add, inc
			if (ins.getName().startsWith("adc")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						if (concreteFlag.getcFlag())
							concreteValueRegister.add(dest.toString(),
									concreteValueRegister.getRegVal(src.toString()) + 1);
						else
							concreteValueRegister.add(dest.toString(), concreteValueRegister.getRegVal(src.toString()));

						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						if (concreteFlag.getcFlag())
							concreteValueRegister.add(dest.toString(),
									concreteValueRegisterPart.getRegVal(src.toString()) + 1);
						else
							concreteValueRegister.add(dest.toString(),
									concreteValueRegisterPart.getRegVal(src.toString()));

						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						if (concreteFlag.getcFlag())
							concreteValueRegister.add(dest.toString(),
									concreteValueSegment.getRegVal(src.toString()) + 1);
						else
							concreteValueRegister.add(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						if (concreteFlag.getcFlag())
							concreteValueRegister.add(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
									.intValue()) + 1);
						else
							concreteValueRegister.add(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
									.intValue()));

						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						if (concreteFlag.getcFlag())
							concreteValueRegisterPart.add(dest.toString(),
									concreteValueRegister.getRegVal(src.toString()) + 1);
						else
							concreteValueRegisterPart.add(dest.toString(),
									concreteValueRegister.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						if (concreteFlag.getcFlag())
							concreteValueRegisterPart.add(dest.toString(),
									concreteValueRegisterPart.getRegVal(src.toString()) + 1);
						else
							concreteValueRegisterPart.add(dest.toString(),
									concreteValueRegisterPart.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						if (concreteFlag.getcFlag())
							concreteValueRegisterPart.add(dest.toString(),
									concreteValueSegment.getRegVal(src.toString()) + 1);
						else
							concreteValueRegisterPart.add(dest.toString(),
									concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						if (concreteFlag.getcFlag())
							concreteValueRegisterPart.add(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
									.intValue()) + 1);
						else
							concreteValueRegisterPart.add(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
									.intValue()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						if (concreteFlag.getcFlag())
							concreteValueSegment.add(dest.toString(),
									concreteValueRegister.getRegVal(src.toString()) + 1);
						else
							concreteValueSegment.add(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						if (concreteFlag.getcFlag())
							concreteValueSegment.add(dest.toString(),
									concreteValueRegisterPart.getRegVal(src.toString()) + 1);
						else
							concreteValueSegment.add(dest.toString(),
									concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						if (concreteFlag.getcFlag())
							concreteValueSegment.add(dest.toString(),
									concreteValueSegment.getRegVal(src.toString()) + 1);
						else
							concreteValueSegment.add(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						if (concreteFlag.getcFlag())
							concreteValueSegment.add(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
									.intValue()) + 1);
						else
							concreteValueSegment.add(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
									.intValue()));
					}
				}
				// X86MemoryOperand
				else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						if (concreteFlag.getcFlag())
							concreteValueMemoryOperand.add((X86MemoryOperand) dest,
									concreteValueRegister.getRegVal(src.toString()) + 1);
						else
							concreteValueMemoryOperand.add((X86MemoryOperand) dest,
									concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						if (concreteFlag.getcFlag())
							concreteValueMemoryOperand.add((X86MemoryOperand) dest,
									concreteValueRegisterPart.getRegVal(src.toString()) + 1);
						else
							concreteValueMemoryOperand.add((X86MemoryOperand) dest,
									concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						if (concreteFlag.getcFlag())
							concreteValueMemoryOperand.add((X86MemoryOperand) dest,
									concreteValueSegment.getRegVal(src.toString()) + 1);
						else
							concreteValueMemoryOperand.add((X86MemoryOperand) dest,
									concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						if (concreteFlag.getcFlag())
							concreteValueMemoryOperand.add((X86MemoryOperand) dest, getBitVecValue(((Immediate) src)
									.getNumber().intValue()) + 1);
						else
							concreteValueMemoryOperand.add((X86MemoryOperand) dest, getBitVecValue(((Immediate) src)
									.getNumber().intValue()));
					}
				}

				// newDest.addExprValue(SymbolicValue.ADD_EXPR, result);
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// ins.toString(startAddress, symFinder));
				// return null;
			}
			break;
		case AND:
			// Statement supported: and
			if (ins.getName().startsWith("and")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueRegister.and(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueRegister.and(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegister.and(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegister.and(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueRegisterPart.and(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueRegisterPart.and(dest.toString(),
								concreteValueRegisterPart.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegisterPart.and(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegisterPart.and(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueSegment.and(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueSegment.and(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueSegment.and(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueSegment.and(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueMemoryOperand.and((X86MemoryOperand) dest,
								concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueMemoryOperand.and((X86MemoryOperand) dest,
								concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueMemoryOperand.and((X86MemoryOperand) dest,
								concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueMemoryOperand.and((X86MemoryOperand) dest, getBitVecValue(((Immediate) src)
								.getNumber().intValue()));
					}
				}

			} else {
				// logger.error("Instruction not supported: " + ins.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// ins.toString(startAddress, symFinder));
				// return null;
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;

		case XOR:
			// Statement supported: and
			if (ins.getName().startsWith("xor")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					X86MemoryOperand t = calculateMemoryOperand((X86MemoryOperand) dest);
					long tVal = calculateValueMemoryOperand((X86MemoryOperand) dest, ins);

					if (src.getClass().getSimpleName().equals("X86Register")) {
						long val = concreteValueRegister.getRegVal(src.toString());
						// This is 32 bits so we have to divide it into 4 parts
						// and
						// assigns to 4 new memory operands
						// if (val instanceof ValueLongExp && t.getBase() ==
						// null && tVal instanceof ValueLongExp) {
						// long v = ((ValueLongExp) val).getValueOperand();
						val = BitVector.xor(val, tVal);
						String x = Convert.longToHex(val, 32);
						int l = x.length();
						if (l < 8)
							return;
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(t,
								Convert.hexToLong(x.substring(l - 2, l)));
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(
								new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 1),
								Convert.hexToLong(x.substring(l - 4, l - 2)));
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(
								new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 2),
								Convert.hexToLong(x.substring(l - 6, l - 4)));
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(
								new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 3),
								Convert.hexToLong(x.substring(l - 8, l - 6)));

					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueMemoryOperand.xor((X86MemoryOperand) dest,
								concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueMemoryOperand.xor((X86MemoryOperand) dest,
								concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long x1 = ((Immediate) src).getNumber().intValue();
						// if (tVal instanceof ValueLongExp) {
						// long v = ((ValueLongExp) tVal).getValueOperand();
						long v = BitVector.xor(tVal, x1);
						String x = Convert.longToHex(v, 32);
						int l = x.length();
						if (l < 8)
							return;
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(t,
								Convert.hexToLong(x.substring(l - 2, l)));
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(
								new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 1),
								Convert.hexToLong(x.substring(l - 4, l - 2)));
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(
								new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 2),
								Convert.hexToLong(x.substring(l - 6, l - 4)));
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(
								new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 3),
								Convert.hexToLong(x.substring(l - 8, l - 6)));

					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						concreteValueMemoryOperand.xor((X86MemoryOperand) dest,
								concreteValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}

				} else if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueRegister.xor(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueRegister.xor(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegister.xor(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegister.xor(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueRegisterPart.xor(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueRegisterPart.xor(dest.toString(),
								concreteValueRegisterPart.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegisterPart.xor(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegisterPart.xor(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueSegment.xor(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueSegment.xor(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueSegment.xor(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueSegment.xor(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueMemoryOperand.xor((X86MemoryOperand) dest,
								concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueMemoryOperand.xor((X86MemoryOperand) dest,
								concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueMemoryOperand.xor((X86MemoryOperand) dest,
								concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueMemoryOperand.xor((X86MemoryOperand) dest, getBitVecValue(((Immediate) src)
								.getNumber().intValue()));
					}
				}

			} else {
				// logger.error("Instruction not supported: " + ins.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// ins.toString(startAddress, symFinder));
				// return null;
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;

		case SUB:
			/* Statement supported: sub, dec */
			if (ins.getName().startsWith("sub")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueRegister.sub(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueRegister.sub(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegister.sub(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegister.sub(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueRegisterPart.sub(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueRegisterPart.sub(dest.toString(),
								concreteValueRegisterPart.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegisterPart.sub(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegisterPart.sub(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueSegment.sub(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueSegment.sub(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueSegment.sub(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueSegment.sub(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueMemoryOperand.sub((X86MemoryOperand) dest,
								concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueMemoryOperand.sub((X86MemoryOperand) dest,
								concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueMemoryOperand.sub((X86MemoryOperand) dest,
								concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueMemoryOperand.sub((X86MemoryOperand) dest, getBitVecValue(((Immediate) src)
								.getNumber().intValue()));
					}
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			} else if (ins.getName().startsWith("dec")) {
				// result = new SymbolicValue(1);
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
				Operand dest = ins.getOperand1();
				if (dest.getClass().getSimpleName().equals("X86Register")) {
					concreteValueRegister.sub(dest.toString(), 1);
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					concreteValueRegisterPart.sub(dest.toString(), 1);
					// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister"))
					concreteValueSegment.sub(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86MemoryOperand"))
					concreteValueMemoryOperand.sub((X86MemoryOperand) dest, 1);
			} else {
				// logger.error("Instruction not supported: " + ins.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// ins.toString(startAddress, symFinder));
				// return null;
			}
			break;
		case OR:
			// Statement supported: or
			if (ins.getName().startsWith("or")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueRegister.or(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueRegister.or(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegister.or(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegister.or(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueRegisterPart.or(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueRegisterPart.or(dest.toString(),
								concreteValueRegisterPart.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegisterPart.or(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegisterPart.or(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueSegment.or(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueSegment.or(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueSegment.or(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueSegment.or(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueMemoryOperand.or((X86MemoryOperand) dest,
								concreteValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueMemoryOperand.or((X86MemoryOperand) dest,
								concreteValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueMemoryOperand.or((X86MemoryOperand) dest,
								concreteValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueMemoryOperand.or((X86MemoryOperand) dest, getBitVecValue(((Immediate) src)
								.getNumber().intValue()));
					}
				}
			} else {
				// logger.error("Instruction not supported: " + ins.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// ins.toString(startAddress, symFinder));
				// return null;
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case SRL:
			if (ins.getName().startsWith("shr")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegister.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueRegister.divide(dest.toString(), x);
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegisterPart.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueRegister.divide(dest.toString(), x);
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueSegment.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueRegister.divide(dest.toString(), x);
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long y = getBitVecValue(((Immediate) src).getNumber().intValue());
						long x = (long) Math.pow(2, y);
						concreteValueRegister.divide(dest.toString(), x);
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src == null)
						return;
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegister.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueRegisterPart.divide(dest.toString(), x);
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegisterPart.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueRegisterPart.divide(dest.toString(), x);
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueSegment.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueRegisterPart.divide(dest.toString(), x);
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long y = getBitVecValue(((Immediate) src).getNumber().intValue());
						long x = (long) Math.pow(2, y);
						concreteValueRegisterPart.divide(dest.toString(), x);
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegister.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueSegment.divide(dest.toString(), x);
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegisterPart.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueSegment.divide(dest.toString(), x);
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueSegment.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueSegment.divide(dest.toString(), x);
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long y = getBitVecValue(((Immediate) src).getNumber().intValue());
						long x = (long) Math.pow(2, y);
						concreteValueSegment.divide(dest.toString(), x);
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegister.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.div((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegisterPart.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.div((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueSegment.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.div((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long y = getBitVecValue(((Immediate) src).getNumber().intValue());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.div((X86MemoryOperand) dest, x);
					}
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			}
			break;
		case SLL:
			if (ins.getName().startsWith("shl")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src == null) {
						concreteValueRegister.mul(dest.toString(), (long) Math.pow(2, 1));
					} else if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegister.mul(dest.toString(),
								(long) Math.pow(2, getBitVecValue(((Immediate) src).getNumber().intValue())));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegisterPart.mul(dest.toString(),
								(long) Math.pow(2, getBitVecValue(((Immediate) src).getNumber().intValue())));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueSegment.mul(dest.toString(),
								(long) Math.pow(2, getBitVecValue(((Immediate) src).getNumber().intValue())));
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegister.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.mul((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegisterPart.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.mul((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueSegment.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.mul((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long y = getBitVecValue(((Immediate) src).getNumber().intValue());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.mul((X86MemoryOperand) dest, x);
					}
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			}
			break;
		case SMUL:
			if (ins.getName().startsWith("imul")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				long eax = 0;

				if (src.getClass().getSimpleName().equals("X86Register")) {
					eax = concreteValueRegister.getRegVal(src.toString());
					concreteValueRegister.movS(src.toString(), 0);
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, src.toString());
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					eax = concreteValueRegisterPart.getRegVal(src.toString());
					concreteValueRegisterPart.setRegVal(src.toString(), 0);
					// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
					// concreteValueRegister, src.toString());

				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					eax = concreteValueSegment.getRegVal(src.toString());
					concreteValueSegment.setSymbolRegisterValue(src.toString(), 0);
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					eax = concreteValueMemoryOperand.getRegVal((X86MemoryOperand) src);
					concreteValueMemoryOperand.setSymbolMemoryOperandValue((X86MemoryOperand) src, 0);
				}

				// concreteValueRegister.getRegVal(src.toString());
				if (dest.getClass().getSimpleName().equals("X86Register")) {
					concreteValueRegister.mul(dest.toString(), eax);
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					concreteValueRegisterPart.mul(dest.toString(), eax);
					// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());

				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					concreteValueSegment.mul(dest.toString(), eax);
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					concreteValueMemoryOperand.mul((X86MemoryOperand) dest, eax);
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			}
			break;
		case RL:
			if (ins.getName().startsWith("rol")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						concreteValueRegister.rl(dest.toString(), y);
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						concreteValueRegisterPart.rl(dest.toString(), y);
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						concreteValueSegment.rl(dest.toString(), y);
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					if (src == null)
						break;
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegister.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.rl((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegisterPart.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.rl((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueSegment.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.rl((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long y = getBitVecValue(((Immediate) src).getNumber().intValue());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.rl((X86MemoryOperand) dest, x);
					}
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			}
			break;

		case RR:
			if (ins.getName().startsWith("ror")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						concreteValueRegister.rr(dest.toString(), y);
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						concreteValueRegisterPart.rr(dest.toString(), y);
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						concreteValueSegment.rr(dest.toString(), y);
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegister.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.rr((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueRegisterPart.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.rr((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
						long y = concreteValueSegment.getRegVal(src.toString());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.rr((X86MemoryOperand) dest, x);
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long y = getBitVecValue(((Immediate) src).getNumber().intValue());
						long x = (long) Math.pow(2, y);
						concreteValueMemoryOperand.rr((X86MemoryOperand) dest, x);
					}
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			}
			break;
		default:
			System.out.println("Arithmetic statement not supported: " + ins.getName());
			// halt_status = FAILED;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// ins.toString(startAddress, symFinder));
			// return null;
		}

	}

	private void X86CallInterprete(X86CallInstruction inst) {
		Operand dest = inst.getOperand1();
		long r = Long.MIN_VALUE;
		AbsoluteAddress returnAddress = new AbsoluteAddress(targetTemp.getValue() + inst.getSize());
		// System.out.println("Instruction: " + inst.getName());

		// call structure: push call next eip to stack, jump to call address
		// first operand
		if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
			// returnAddress = new SymbolicValue(((AbsoluteAddress)
			// dest).getValueOperand());
			r = ((AbsoluteAddress) dest).getValue();
		} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
			r = ((X86PCRelativeAddress) dest).getEffectiveValue(targetTemp.getValue());

		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			// returnAddress = (prevState.getRegValue(((X86Register)
			// dest).toString())).clone();
			r = concreteValueRegister.getRegVal(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// returnAddress = (prevState.getMemoryValue((X86MemoryOperand)
			// dest));
			// if (returnAddress == null) {
			// halt_status = MISSING_MEMORY;
			// memoryAddress = (X86MemoryOperand) dest;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// inst.toString(startAddress, symFinder));
			// return null;

			// returnAddress = returnAddress.clone();
			X86MemoryOperand d = (X86MemoryOperand) dest;
			r = this.calculateValueMemoryOperand(d, inst);
		}

		if (this.targetIndirect.getValue() == this.targetTemp.getValue())
			this.evaluateValue = r;

		if (stubLibrary != null
				&& contain(((Win32StubLibrary) stubLibrary).getAddressStubMap(), new AbsoluteAddress(r))) {
			// String funcName
			// SystemCallStub.executeSymbolic(funcName);
			System.out.println("Address:" + targetTemp.toString());
			if (APIStub.executeConcrete(funcName, this, inst)) {
				funcName = "";
				retAPIAddr = new AbsoluteAddress(targetTemp.getValue() + inst.getSize());
			} else
				System.out.println("Symbolic Execution of System Call failed:" + funcName);

			// if (this.targetTemp.getValueOperand() ==
			// targetIndirect.getValueOperand())
			// this.evaluateValue = returnAddress.getValueOperand();

			// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
			// concreteValueRegister, "%esp");
			return;
		}

		// returnAddress = (prevState.getMemoryValue((X86MemoryOperand)
		// dest));
		// if (returnAddress == null) {
		// halt_status = MISSING_MEMORY;
		// memoryAddress = (X86MemoryOperand) dest;
		// SymbolicExecution.programTrace.setVisited(startAddress, null,
		// inst.toString(startAddress, symFinder));
		// return null;

		// returnAddress = returnAddress.clone();

		// this.evaluateValue = returnAddress.getValueOperand();
		concreteValueRegister.sub("%esp", 4);
		concreteStack.push(returnAddress.getValue());
		// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
		// concreteValueRegister, "%esp");
	}

	private void X86ConditionalJumpInterprete(X86CondJmpInstruction inst) {
		// Operand dest = inst.getOperand1();
		String instName = inst.getName();

		/*
		 * if (neg.get(targetTemp).getValueOperand() ==
		 * this.addrTraceList.get(0) .getValueOperand())
		 * this.formulas.getTop().setOperator(
		 * CondJump.getReserCondJump((inst.getName()))); else
		 * this.formulas.getTop().setOperator(
		 * CondJump.getCondJump((inst.getName())));
		 */
		boolean reverCond = false;
		if (!neg.isEmpty() && neg.containsKey(targetTemp)
				&& neg.get(targetTemp).getValue() == this.addrTraceList.get(0).getValue()) {
			// instName = reverseConditionJump(instName);
			reverCond = true;
			// System.out.println("Concrete Reverse Conditional Jump:" +
			// instName);
		} else
			/*
			 * System.out.println("Concrete Conditional Jump:" + instName + " "
			 * + targetTemp.toString() + " " + this.targetIndirect.toString())
			 */
			;

		// structure: create new work associated with condition-false case
		// jump to condition-true case
		// add state condition into each trace
		// SymbolicCondition compareStatus = prevState.getCompareStatus();
		// we accept loop not to be constrained by compareStatus
		if (inst.getName().equals("loop")) {
			// if (compareStatus == null) {
			// logger.error("Only accept conditional statement after CMP");
			// halt_status = FAILED;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// inst.toString(startAddress, symFinder));
			// return null;
			// }
			// FormulaSet f = resolveLoop(inst);
			/*
			 * FormulaSet farkas = new FarkasAlgorithm(targetTemp, assemblyMap,
			 * traceList).resolve(); farkas.add(new Formula(new VarExp("ecx"),
			 * new ConstantLongExp(0), "=")); // farkas.printInfo();
			 * this.formulaList.add(farkas);
			 */
			/*
			 * System.out.println("Process Loop with Destination:" +
			 * dest.toString());
			 */
			concreteValueRegister.sub("%ecx", 1);
			long ecx = concreteValueRegister.getRegVal("%ecx");

			if (reverCond) {
				if (ecx == 0)
					// System.out.println("Loop Right")
					;
				else {
					// System.out.println("Debug Loop");
					if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
						// this.addrTraceList.pushList(cfg.getCFGVertices()
						// .getVertex(this.targetTemp).getListLoop(0));
						if (loopTotal > MAX_NUM_LOOP_TOTAL) {
							System.out.println("Outside Loop Total: " + loopTotal + " Address: "
									+ targetTemp.toString());
							this.stop = true;
							return;
						}

						if (loopEach > MAX_NUM_LOOP_EACH) {
							System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
							this.stop = true;
							return;
						}

						this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

						loopTotal++;

						if (targetTemp.getValue() == loopAddr)
							loopEach++;
						else {
							loopEach = 1;
							loopAddr = targetTemp.getValue();
						}
					} else
						stop = true;
				}

			} else {
				if (ecx != 0)
					// System.out.println("Loop Right")
					;
				else {
					// System.out.println("Debug Loop");
					if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
						// this.addrTraceList.pushList(cfg.getCFGVertices()
						// .getVertex(this.targetTemp).getListLoop(0));
						if (loopTotal > MAX_NUM_LOOP_TOTAL) {
							System.out.println("Outside Loop Total: " + loopTotal + " Address: "
									+ targetTemp.toString());
							this.stop = true;
							return;
						}

						if (loopEach > MAX_NUM_LOOP_EACH) {
							System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
							this.stop = true;
							return;
						}

						this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

						loopTotal++;

						if (targetTemp.getValue() == loopAddr)
							loopEach++;
						else {
							loopEach = 1;
							loopAddr = targetTemp.getValue();
						}
					} else
						stop = true;
				}
			}
		} else if (instName.equals("ja") || instName.equals("jnbe")) {
			// if
			// ((CF) = 0) or ((ZF) = 0)
			// Not finished
			if (reverCond) {
				if (!(!concreteFlag.getcFlag() | !concreteFlag.getzFlag()))
					;
				// System.out.println("Concrete JA condition is true");
				else {
					System.out.println("Reverse Concrete JA or JNBE condition is false");
					run = false;
				}
			} else if (!concreteFlag.getcFlag() | !concreteFlag.getzFlag())
				;
			// System.out.println("Concrete JA condition is true");
			else {
				System.out.println("Concrete JA or JNBE condition is false");
				run = false;
			}
		} else if (instName.equals("jae") || instName.equals("jnb")) {
			// if
			// ((CF) = 0)
			if (reverCond) {
				if (concreteFlag.getcFlag())
					System.out.println("Concrete " + instName + " condition is true");
				else {
					if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
						// this.addrTraceList.pushList(cfg.getCFGVertices()
						// .getVertex(this.targetTemp).getListLoop(0));
						if (loopTotal > MAX_NUM_LOOP_TOTAL) {
							System.out.println("Outside Loop Total: " + loopTotal + " Address: "
									+ targetTemp.toString());
							this.stop = true;
							return;
						}

						if (loopEach > MAX_NUM_LOOP_EACH) {
							System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
							this.stop = true;
							return;
						}

						this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

						loopTotal++;

						if (targetTemp.getValue() == loopAddr)
							loopEach++;
						else {
							loopEach = 1;
							loopAddr = targetTemp.getValue();
						}
					} else {
						System.out.println("Reverse Concrete " + instName + " condition is false");
						run = false;
					}
				}
			} else if (!concreteFlag.getcFlag())
				System.out.println("Concrete " + instName + " condition is true");
			else {

				if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
				// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
				// MAX_NUM_LOOP_EACH
				) {

					if (loopTotal > MAX_NUM_LOOP_TOTAL) {
						System.out.println("Outside Loop Total: " + loopTotal + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					if (loopEach > MAX_NUM_LOOP_EACH) {
						System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

					loopTotal++;

					if (targetTemp.getValue() == loopAddr)
						loopEach++;
					else {
						loopEach = 1;
						loopAddr = targetTemp.getValue();
					}
				} else {
					run = false;
					System.out.println("Concrete " + instName + " condition is false");
				}
			}
			/*
			 * if (reverCond) { if (concreteFlag.getcFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out
			 * .println("Reverse Concrete JAE or JNB condition is false"); run =
			 * false; } } else if (!concreteFlag.getcFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out.println("Concrete JAE or JNB condition is false"); run
			 * = false; }
			 */
		} else if (instName.equals("jb") || instName.equals("jnae")) {
			// if
			// ((CF) = 1)
			if (reverCond) {
				if (!concreteFlag.getcFlag())
					;
				// System.out.println("Concrete JA condition is true");
				else {
					System.out.println("Reverse Concrete JB or JNAE condition is false");
					run = false;
				}
			} else if (concreteFlag.getcFlag())
				;
			// System.out.println("Concrete JA condition is true");
			else {
				System.out.println("Concrete JB or JNAE condition is false");
				run = false;
			}
		} else if (instName.equals("jbe") || instName.equals("jna")) {
			// if
			// ((CF) = 1) or ((ZF) = 1)

			if (reverCond) {
				if (!(concreteFlag.getcFlag() | concreteFlag.getzFlag()))
					;
				// System.out.println("Concrete JA condition is true");
				else {
					System.out.println("Reverse Concrete JBE or JNA condition is false");
					run = false;
				}
			} else if (concreteFlag.getcFlag() | concreteFlag.getzFlag())
				;
			// System.out.println("Concrete JA condition is true");
			else {
				// System.out.println("Concrete JBE or JNA condition is false");
				run = false;
			}
		} else if (instName.equals("jc")) {
			// if
			// ((CF) = 1)
			if (reverCond) {
				if (!concreteFlag.getcFlag())
					;
				// System.out.println("Concrete JA condition is true");
				else {
					System.out.println("Reverse Concrete JC condition is false");
					run = false;
				}
			} else if (concreteFlag.getcFlag())
				;
			// System.out.println("Concrete JA condition is true");
			else {
				System.out.println("Concrete JC condition is false");
				run = false;
			}
		} else if (instName.equals("jcxz")) {
			// if
			// ((CF) = 0)
			if (reverCond) {
				if (concreteFlag.getcFlag())
					System.out.println("Concrete " + instName + " condition is true");
				else {
					if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
						// this.addrTraceList.pushList(cfg.getCFGVertices()
						// .getVertex(this.targetTemp).getListLoop(0));
						if (loopTotal > MAX_NUM_LOOP_TOTAL) {
							System.out.println("Outside Loop Total: " + loopTotal + " Address: "
									+ targetTemp.toString());
							this.stop = true;
							return;
						}

						if (loopEach > MAX_NUM_LOOP_EACH) {
							System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
							this.stop = true;
							return;
						}

						this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

						loopTotal++;

						if (targetTemp.getValue() == loopAddr)
							loopEach++;
						else {
							loopEach = 1;
							loopAddr = targetTemp.getValue();
						}
					} else {
						System.out.println("Reverse Concrete " + instName + " condition is false");
						run = false;
					}
				}
			} else if (!concreteFlag.getcFlag())
				System.out.println("Concrete " + instName + " condition is true");
			else {

				if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
				// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
				// MAX_NUM_LOOP_EACH
				) {

					if (loopTotal > MAX_NUM_LOOP_TOTAL) {
						System.out.println("Outside Loop Total: " + loopTotal + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					if (loopEach > MAX_NUM_LOOP_EACH) {
						System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

					loopTotal++;

					if (targetTemp.getValue() == loopAddr)
						loopEach++;
					else {
						loopEach = 1;
						loopAddr = targetTemp.getValue();
					}
				} else {
					run = false;
					System.out.println("Concrete " + instName + " condition is false");
				}
			}
			/*
			 * if (reverCond) { if (concreteFlag.getcFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out .println("Reverse Concrete JCXZ condition is false");
			 * run = false; } } else if (!concreteFlag.getcFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out.println("Concrete JCXZ condition is false"); run =
			 * false; }
			 */
		} else if (instName.equals("je") || instName.equals("jz")) {
			// if
			// ((ZF) = 1)
			if (reverCond) {
				if (!concreteFlag.getzFlag())
					;
				// System.out.println("Concrete JA condition is true");
				else {

					if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop())
						this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));
					else {
						System.out.println("Reverse Concrete JE or JZ condition is false");
						run = false;
					}
				}
			} else if (concreteFlag.getzFlag())

				// System.out.println("Concrete JA condition is true")
				;
			else {
				if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop())
					this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));
				else {
					System.out.println("Concrete JE or JZ condition is false");
					run = false;
				}
			}
		} else if (instName.equals("jg") || instName.equals("jnle")) {
			// if
			// ((SF) = (OF)) and ((ZF) = 0)
			if (reverCond) {
				if (!((concreteFlag.getsFlag() == concreteFlag.getoFlag()) & !concreteFlag.getzFlag()))
					;
				// System.out.println("Concrete JA condition is true");
				else {
					System.out.println("Reverse Concrete JG or JNLE condition is false");
					run = false;
				}
			} else if ((concreteFlag.getsFlag() == concreteFlag.getoFlag()) & !concreteFlag.getzFlag())
				;
			// System.out.println("Concrete JA condition is true");
			else {
				System.out.println("Concrete JG or JNLE condition is false");
				run = false;
			}
		} else if (instName.equals("jge") || instName.equals("jnl")) {
			// if
			// (SF) = (OF)
			if (reverCond) {
				if (!(concreteFlag.getsFlag() == concreteFlag.getoFlag()))
					;
				// System.out.println("Concrete JA condition is true");
				else {
					System.out.println("Reverse Concrete JGE or JNL condition is false");
					run = false;
				}
			} else if (concreteFlag.getsFlag() == concreteFlag.getoFlag())
				;
			// System.out.println("Concrete JA condition is true");
			else {
				System.out.println("Concrete JGE or JNL condition is false");
				run = false;
			}
		} else if (instName.equals("jl") || instName.equals("jnge")) {
			// if
			// (SF) !=� (OF)
			if (reverCond) {
				if (concreteFlag.getsFlag() == concreteFlag.getoFlag())
					;
				// System.out.println("Concrete JA condition is true");
				else {
					System.out.println("Reverse Concrete JL or JNGE condition is false");
					run = false;
				}
			} else if (concreteFlag.getsFlag() != concreteFlag.getoFlag())
				;
			// System.out.println("Concrete JA condition is true");
			else {
				System.out.println("Concrete JL or JNGE condition is false");
				run = false;
			}
		} else if (instName.equals("jle") || instName.equals("jng")) {
			// if
			// ((SF) !=� (OF)) or ((ZF) = 1)
			if (reverCond) {
				if (!((concreteFlag.getsFlag() != concreteFlag.getoFlag()) | concreteFlag.getzFlag()))
					;
				// System.out.println("Concrete JA condition is true");
				else {
					System.out.println("Reverse Concrete JLE or JNG condition is false");
					run = false;
				}
			} else if ((concreteFlag.getsFlag() != concreteFlag.getoFlag()) | concreteFlag.getzFlag())
				;
			// System.out.println("Concrete JA condition is true");
			else {
				System.out.println("Concrete JLE or JNG condition is false");
				run = false;
			}
		} else if (instName.equals("jnc")) {
			// if
			// ((CF) = 0)
			if (reverCond) {
				if (concreteFlag.getcFlag())
					System.out.println("Concrete " + instName + " condition is true");
				else {
					if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
						// this.addrTraceList.pushList(cfg.getCFGVertices()
						// .getVertex(this.targetTemp).getListLoop(0));
						if (loopTotal > MAX_NUM_LOOP_TOTAL) {
							System.out.println("Outside Loop Total: " + loopTotal + " Address: "
									+ targetTemp.toString());
							this.stop = true;
							return;
						}

						if (loopEach > MAX_NUM_LOOP_EACH) {
							System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
							this.stop = true;
							return;
						}

						this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

						loopTotal++;

						if (targetTemp.getValue() == loopAddr)
							loopEach++;
						else {
							loopEach = 1;
							loopAddr = targetTemp.getValue();
						}
					} else {
						System.out.println("Reverse Concrete " + instName + " condition is false");
						run = false;
					}
				}
			} else if (!concreteFlag.getcFlag())
				System.out.println("Concrete " + instName + " condition is true");
			else {

				if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
				// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
				// MAX_NUM_LOOP_EACH
				) {

					if (loopTotal > MAX_NUM_LOOP_TOTAL) {
						System.out.println("Outside Loop Total: " + loopTotal + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					if (loopEach > MAX_NUM_LOOP_EACH) {
						System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

					loopTotal++;

					if (targetTemp.getValue() == loopAddr)
						loopEach++;
					else {
						loopEach = 1;
						loopAddr = targetTemp.getValue();
					}
				} else {
					run = false;
					System.out.println("Concrete " + instName + " condition is false");
				}
			}
			/*
			 * if (reverCond) { if (concreteFlag.getcFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out .println("Reverse Concrete JNC condition is false");
			 * run = false; } } else if (!concreteFlag.getcFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out.println("Concrete JNC condition is false"); run =
			 * false; }
			 */
		} else if (instName.equals("jne") || instName.equals("jnz")) {
			// if
			// ((ZF) = 0)
			if (reverCond) {
				if (concreteFlag.getzFlag())
					/*
					 * System.out.println("Concrete " + instName +
					 * " condition is true")
					 */
					;
				else {
					if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
						// this.addrTraceList.pushList(cfg.getCFGVertices()
						// .getVertex(this.targetTemp).getListLoop(0));
						if (loopTotal > MAX_NUM_LOOP_TOTAL) {
							System.out.println("Outside Loop Total: " + loopTotal + " Address: "
									+ targetTemp.toString());
							this.stop = true;
							return;
						}

						if (loopEach > MAX_NUM_LOOP_EACH) {
							System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
							this.stop = true;
							return;
						}

						this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

						loopTotal++;

						if (targetTemp.getValue() == loopAddr)
							loopEach++;
						else {
							loopEach = 1;
							loopAddr = targetTemp.getValue();
						}
					} else {
						System.out.println("Reverse Concrete " + instName + " condition is false");
						run = false;
					}
				}
			} else if (!concreteFlag.getzFlag())
				System.out.println("Concrete " + instName + " condition is true");
			else {

				if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
				// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
				// MAX_NUM_LOOP_EACH
				) {

					if (loopTotal > MAX_NUM_LOOP_TOTAL) {
						System.out.println("Outside Loop Total: " + loopTotal + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					if (loopEach > MAX_NUM_LOOP_EACH) {
						System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

					loopTotal++;

					if (targetTemp.getValue() == loopAddr)
						loopEach++;
					else {
						loopEach = 1;
						loopAddr = targetTemp.getValue();
					}
				} else {
					run = false;
					System.out.println("Concrete " + instName + " condition is false");
				}
			}
		} else if (instName.equals("jno")) {
			// if
			// ((OF) = 0)
			if (reverCond) {
				if (concreteFlag.getoFlag())
					System.out.println("Concrete " + instName + " condition is true");
				else {
					if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
						// this.addrTraceList.pushList(cfg.getCFGVertices()
						// .getVertex(this.targetTemp).getListLoop(0));
						if (loopTotal > MAX_NUM_LOOP_TOTAL) {
							System.out.println("Outside Loop Total: " + loopTotal + " Address: "
									+ targetTemp.toString());
							this.stop = true;
							return;
						}

						if (loopEach > MAX_NUM_LOOP_EACH) {
							System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
							this.stop = true;
							return;
						}

						this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

						loopTotal++;

						if (targetTemp.getValue() == loopAddr)
							loopEach++;
						else {
							loopEach = 1;
							loopAddr = targetTemp.getValue();
						}
					} else {
						System.out.println("Reverse Concrete " + instName + " condition is false");
						run = false;
					}
				}
			} else if (!concreteFlag.getoFlag())
				System.out.println("Concrete " + instName + " condition is true");
			else {

				if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
				// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
				// MAX_NUM_LOOP_EACH
				) {

					if (loopTotal > MAX_NUM_LOOP_TOTAL) {
						System.out.println("Outside Loop Total: " + loopTotal + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					if (loopEach > MAX_NUM_LOOP_EACH) {
						System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

					loopTotal++;

					if (targetTemp.getValue() == loopAddr)
						loopEach++;
					else {
						loopEach = 1;
						loopAddr = targetTemp.getValue();
					}
				} else {
					run = false;
					System.out.println("Concrete " + instName + " condition is false");
				}
			}
			/*
			 * if (reverCond) { if (concreteFlag.getoFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out .println("Reverse Concrete JNO condition is false");
			 * run = false; } } else if (!concreteFlag.getoFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out.println("Concrete JNO condition is false"); run =
			 * false; }
			 */
		} else if (instName.equals("jns")) {
			// if
			// ((SF) = 0)
			if (reverCond) {
				if (concreteFlag.getsFlag())
					System.out.println("Concrete " + instName + " condition is true");
				else {
					if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
						// this.addrTraceList.pushList(cfg.getCFGVertices()
						// .getVertex(this.targetTemp).getListLoop(0));
						if (loopTotal > MAX_NUM_LOOP_TOTAL) {
							System.out.println("Outside Loop Total: " + loopTotal + " Address: "
									+ targetTemp.toString());
							this.stop = true;
							return;
						}

						if (loopEach > MAX_NUM_LOOP_EACH) {
							System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
							this.stop = true;
							return;
						}

						this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

						loopTotal++;

						if (targetTemp.getValue() == loopAddr)
							loopEach++;
						else {
							loopEach = 1;
							loopAddr = targetTemp.getValue();
						}
					} else {
						System.out.println("Reverse Concrete " + instName + " condition is false");
						run = false;
					}
				}
			} else if (!concreteFlag.getsFlag())
				System.out.println("Concrete " + instName + " condition is true");
			else {

				if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
				// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
				// MAX_NUM_LOOP_EACH
				) {

					if (loopTotal > MAX_NUM_LOOP_TOTAL) {
						System.out.println("Outside Loop Total: " + loopTotal + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					if (loopEach > MAX_NUM_LOOP_EACH) {
						System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

					loopTotal++;

					if (targetTemp.getValue() == loopAddr)
						loopEach++;
					else {
						loopEach = 1;
						loopAddr = targetTemp.getValue();
					}
				} else {
					run = false;
					System.out.println("Concrete " + instName + " condition is false");
				}
			}
			/*
			 * if (reverCond) { if (concreteFlag.getsFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out .println("Reverse Concrete JNS condition is false");
			 * run = false; } } else if (!concreteFlag.getsFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out.println("Concrete JNS condition is false"); run =
			 * false; }
			 */
		} else if (instName.equals("jnp") || instName.equals("jpo")) {
			// if
			// ((PF) = 0)
			if (reverCond) {
				if (concreteFlag.getpFlag())
					System.out.println("Concrete " + instName + " condition is true");
				else {
					if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
						// this.addrTraceList.pushList(cfg.getCFGVertices()
						// .getVertex(this.targetTemp).getListLoop(0));
						if (loopTotal > MAX_NUM_LOOP_TOTAL) {
							System.out.println("Outside Loop Total: " + loopTotal + " Address: "
									+ targetTemp.toString());
							this.stop = true;
							return;
						}

						if (loopEach > MAX_NUM_LOOP_EACH) {
							System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
							this.stop = true;
							return;
						}

						this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

						loopTotal++;

						if (targetTemp.getValue() == loopAddr)
							loopEach++;
						else {
							loopEach = 1;
							loopAddr = targetTemp.getValue();
						}
					} else {
						System.out.println("Reverse Concrete " + instName + " condition is false");
						run = false;
					}
				}
			} else if (!concreteFlag.getpFlag())
				System.out.println("Concrete " + instName + " condition is true");
			else {

				if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
				// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
				// MAX_NUM_LOOP_EACH
				) {

					if (loopTotal > MAX_NUM_LOOP_TOTAL) {
						System.out.println("Outside Loop Total: " + loopTotal + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					if (loopEach > MAX_NUM_LOOP_EACH) {
						System.out.println("Outside Loop Each: " + loopEach + " Address: " + targetTemp.toString());
						this.stop = true;
						return;
					}

					this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));

					loopTotal++;

					if (targetTemp.getValue() == loopAddr)
						loopEach++;
					else {
						loopEach = 1;
						loopAddr = targetTemp.getValue();
					}
				} else {
					run = false;
					System.out.println("Concrete " + instName + " condition is false");
				}
			}
			/*
			 * if (reverCond) { if (concreteFlag.getpFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out
			 * .println("Reverse Concrete JNP or JPO condition is false"); run =
			 * false; } } else if (!concreteFlag.getpFlag()) ; //
			 * System.out.println("Concrete JA condition is true"); else {
			 * System.out.println("Concrete JNP or JPO condition is false"); run
			 * = false; }
			 */
		} else if (instName.equals("jo")) {
			// if
			// ((OF) = 1)
			if (reverCond) {
				if (!concreteFlag.getoFlag())
					;
				// System.out.println("Concrete JA condition is true");
				else {
					System.out.println("Reverse Concrete JO condition is false");
					run = false;
				}
			} else if (concreteFlag.getoFlag())
				;
			// System.out.println("Concrete JA condition is true");
			else {
				System.out.println("Concrete JO condition is false");
				// run = false;
			}
		} else if (instName.equals("jp") || instName.equals("jpe")) {
			// if
			// ((PF) = 1)
			if (reverCond) {
				if (!concreteFlag.getpFlag())
					;
				// System.out.println("Concrete JA condition is true");
				else {
					System.out.println("Concrete JP or JPE condition is false");
					run = false;
				}
			} else if (concreteFlag.getpFlag())
				;
			// System.out.println("Concrete JA condition is true");
			else {
				System.out.println("Concrete JP or JPE condition is false");
				run = false;
			}
		} else if (inst.getName().equals("js")) {
			// if
			// ((SF) = 1)
			if (reverCond) {
				if (!concreteFlag.getsFlag())
					;
				// System.out.println("Concrete JA condition is true");
				else {
					System.out.println("Reverse Concrete JS condition is false");
					run = false;
				}
			} else if (concreteFlag.getsFlag())
				;
			// System.out.println("Concrete JA condition is true");
			else {
				System.out.println("Concrete JS condition is false");
				run = false;
			}
		}
	}

	private void X86InstructionInterprete(X86Instruction inst) {
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();
		long d = 0, s = 0;
		// System.out.println("Instruction: " + inst.getName());
		if (inst.getName().startsWith("cmps")) {
			if (inst.hasPrefixREPZ()) {
				// System.out.println("Debug Instruction REPZ" +
				// inst.getName());

				if (dest.getClass().getSimpleName().equals("X86MemoryOperand")
						&& src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					X86MemoryOperand edi = calculateMemoryOperand((X86MemoryOperand) dest);
					X86MemoryOperand esi = calculateMemoryOperand((X86MemoryOperand) src);

					// Comapre between EDI and ESI, if not true, increase ESI
					// and
					// EDI by 1
					long ecx = concreteValueRegister.getRegVal("%ecx");

					// if (ecx instanceof ValueLongExp) {
					// int t = (int) ((ValueLongExp) ecx).getValueOperand();
					for (int i = 0; i < ecx; i++) {
						long ediValue = this.calculateValueMemoryOperand(
								new X86MemoryOperand(edi.getDataType(), edi.getDisplacement() + i), inst);
						long esiValue = this.calculateValueMemoryOperand(
								new X86MemoryOperand(esi.getDataType(), esi.getDisplacement() + i), inst);

						if (ediValue != esiValue) {
							this.concreteFlag.setzFlag(false);
							return;
						}
					}
					this.concreteValueRegister.add("%esi", ecx);
					this.concreteValueRegister.add("%edi", ecx);
					this.concreteFlag.setzFlag(true);
				}
			} else if (inst.hasPrefixREPNZ()) {
				System.out.println("Debug Instruction REPNZ" + inst.getName());
			} else {
				System.out.println("Debug Instruction " + inst.getName());
			}

		} else if (inst.getName().startsWith("std")) {
			concreteFlag.setdFlag(true);
		} else if (inst.getName().startsWith("cld")) {
			// Clear direction flag
			concreteFlag.setdFlag(false);
		} else if (inst.getName().startsWith("scas")) {
			// Load String
			/*
			 * @_1: scasb jnz @_1 sub edi,esi ; EDI = API Name size
			 */
			// Scas is followed by jump condition. This is a loop
			// So we run this loop by symbolic execution with while loop
			// System.out.println("Debug Instruction scas");
			long x = concreteValueRegisterPart.getRegVal(dest.toString());
			long y = concreteValueRegister.getRegVal("%edi");

			long z = program.getByteValueMemory(new AbsoluteAddress(y));
			// Dieu kien bang xay ra
			if (x == z)
				// break;
				concreteFlag.setzFlag(true);
			else
				concreteFlag.setzFlag(false);

			// }

			// symbolValueRegister.mov("%edi", new ValueLongExp(y1 + 1));

			concreteValueRegister.mov("%edi", y + 1);
			// concreteFlag.setzFlag(true);

		} else if (inst.getName().startsWith("stos")) {
			// Load String
			if (inst.hasPrefixREPZ()) {
				// System.out.println("Debug Concrete Instruction REPZ stos");

				long x = 0;
				long ecx = this.concreteValueRegister.getRegVal("%ecx");
				if (src.getClass().getSimpleName().equals("X86Register")) {
					x = concreteValueRegister.getRegVal(src.toString());
					// FlagRelationship.totalChangePart(symbolValueRegisterPart,
					// symbolValueRegister, "%eax");
					if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
						X86MemoryOperand t = this.calculateMemoryOperand((X86MemoryOperand) dest);

						for (int i = 0; i < ecx;) {
							concreteValueMemoryOperand.movDoubleWord(
									new X86MemoryOperand(inst.getDataType(), t.getDisplacement() + i), x);
							i += 4;
						}
						// }

					}
					concreteValueRegister.mov("%ecx", 0);
					concreteValueRegister.add("%edi", ecx);
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.toString().equals("%ax")) {
						x = concreteValueRegisterPart.getRegVal(src.toString());
						// FlagRelationship.partChangeTotal(symbolValueRegisterPart,
						// symbolValueRegister, "%ax");
						if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
							X86MemoryOperand t = this.calculateMemoryOperand((X86MemoryOperand) dest);
							for (int i = 0; i < ecx;) {
								concreteValueMemoryOperand.movWord(
										new X86MemoryOperand(inst.getDataType(), t.getDisplacement() + i), x);
								i += 2;
							}
							// }

						}
						concreteValueRegister.mov("%ecx", 0);
						concreteValueRegister.add("%edi", ecx);
					} else if (src.toString().endsWith("l") || src.toString().endsWith("h")) {
						x = concreteValueRegisterPart.getRegVal(src.toString());
						// FlagRelationship.partChangeTotal(symbolValueRegisterPart,
						// symbolValueRegister, "%ax");
						if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
							X86MemoryOperand t = this.calculateMemoryOperand((X86MemoryOperand) dest);
							for (int i = 0; i < ecx; i++) {
								concreteValueMemoryOperand.movByte(
										new X86MemoryOperand(inst.getDataType(), t.getDisplacement() + i), x);
							}

						}
						concreteValueRegister.mov("%ecx", 0);
						concreteValueRegister.add("%edi", ecx);
					}
				}

			} else if (inst.hasPrefixREPNZ()) {
				System.out.println("Debug Concrete Instruction REPCNZ stos");
			} else {

				// System.out.println("Debug Concrete Instruction stos");

				long x = 0;
				if (src.getClass().getSimpleName().equals("X86Register")) {
					x = concreteValueRegister.getRegVal(src.toString());
					// FlagRelationship.totalChangePart(symbolValueRegisterPart,
					// symbolValueRegister, "%eax");
					if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
						X86MemoryOperand t = this.calculateMemoryOperand((X86MemoryOperand) dest);

						concreteValueMemoryOperand.movDoubleWord(
								new X86MemoryOperand(inst.getDataType(), t.getDisplacement()), x);
						// }

					}

					concreteValueRegister.add("%edi", 4);
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.toString().equals("%ax")) {
						x = concreteValueRegisterPart.getRegVal(src.toString());
						// FlagRelationship.partChangeTotal(symbolValueRegisterPart,
						// symbolValueRegister, "%ax");
						if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
							X86MemoryOperand t = this.calculateMemoryOperand((X86MemoryOperand) dest);

							concreteValueMemoryOperand.movWord(
									new X86MemoryOperand(inst.getDataType(), t.getDisplacement()), x);
							// }

						}

						concreteValueRegister.add("%edi", 2);
					} else if (src.toString().endsWith("l") || src.toString().endsWith("h")) {
						x = concreteValueRegisterPart.getRegVal(src.toString());
						// FlagRelationship.partChangeTotal(symbolValueRegisterPart,
						// symbolValueRegister, "%ax");
						if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
							X86MemoryOperand t = this.calculateMemoryOperand((X86MemoryOperand) dest);

							concreteValueMemoryOperand.movByte(
									new X86MemoryOperand(inst.getDataType(), t.getDisplacement()), x);
							// }

						}

						concreteValueRegister.add("%edi", 1);
					}
				}
			}

		} else if (inst.getName().startsWith("lods")) {
			// Load String
			// System.out.println("Debug Concrete Instruction lods");
			long b1 = 0;

			if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = (X86MemoryOperand) src;

				long disp = t.getDisplacement();
				if (t.getBase() != null) {
					long base = concreteValueRegister.getRegVal(t.getBase().toString());

					if (inst.getName().toString().endsWith("b")) {
						concreteValueRegister.add(t.getBase().toString(), 4);
					} else if (inst.getName().toString().endsWith("s")) {
						concreteValueRegister.add(t.getBase().toString(), 4);
					} else if (inst.getName().toString().endsWith("w")) {
						concreteValueRegister.add(t.getBase().toString(), 4);
					} else if (inst.getName().toString().endsWith("l")) {
						concreteValueRegister.add(t.getBase().toString(), 4);
					}

					// if (base instanceof ValueLongExp) {
					b1 = calculateValueMemoryOperand(new X86MemoryOperand(inst.getDataType(), base + disp), inst);
					// }
				}
			}

			if (dest.getClass().getSimpleName().equals("X86Register")) {
				concreteValueRegister.mov("%eax", b1);
				// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
				// concreteValueRegister, "%eax");
			} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
				concreteValueRegisterPart.mov("%ax", b1);
				// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
				// concreteValueRegister, "%ax");
			}
		} else if (inst.getName().startsWith("movsb")) {
			long val = concreteValueRegister.getRegVal("%ecx");
			concreteValueRegister.add("%esi", val);
			concreteValueRegister.add("%edi", val);
			concreteValueRegister.mov("%ecx", 0);

			// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
			// concreteValueRegister, "%esi");
			// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
			// concreteValueRegister, "%edi");
			// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
			// concreteValueRegister, "%ecx");
		} else if (inst.getName().startsWith("cld")) {
			concreteFlag.setdFlag(false);
		} else if (inst.getName().startsWith("int")) {
			if (dest.getClass().getSimpleName().equals("Immediate")) {
				int x = ((Immediate) dest).getNumber().intValue();
				// Process interrupt 21h
				if (x == 33) {
					// Check if AH = 2A
					// Return: CX = year (1980-2099) DH = month DL = day AL =
					// day of week (00h=Sunday)
					if (concreteValueRegisterPart.getRegVal("%ah") == 42) {
						concreteValueRegisterPart.setConcreteRegisterPartValue("%cx",
								Calendar.getInstance().get(Calendar.YEAR));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, "%cx");
						// int z = Calendar.getInstance().get(Calendar.MONTH);
						concreteValueRegisterPart.setConcreteRegisterPartValue("%dh",
								Calendar.getInstance().get(Calendar.MONTH) + 1);
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, "%dh");
						concreteValueRegisterPart.setConcreteRegisterPartValue("%dl",
								Calendar.getInstance().get(Calendar.DATE));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, "%dl");
						concreteValueRegisterPart.setConcreteRegisterPartValue("%al",
								Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, "%al");
					}
				}

			}
		} else if (inst.getName().startsWith("leave")) {

		} else if (inst.getName().startsWith("lea")) {
			if (dest.getClass().getSimpleName().equals("X86Register")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					concreteValueRegister.mov(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					concreteValueRegister.mov(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					concreteValueRegister.mov(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					X86MemoryOperand t = (X86MemoryOperand) src;
					if (t.getBase() != null) {
						concreteValueRegister.mov(dest.toString(),
								concreteValueRegister.getRegVal(t.getBase().toString()));
						if (t.getDisplacement() != 0)
							concreteValueRegister.add(dest.toString(), t.getDisplacement());
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (t.getDisplacement() != 0) {
						concreteValueRegister.mov(dest.toString(), t.getDisplacement());
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					concreteValueRegister
							.mov(dest.toString(), getBitVecValue(((Immediate) src).getNumber().intValue()));
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				}
			} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					concreteValueRegisterPart.mov(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
					// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					concreteValueRegisterPart.mov(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
					// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					concreteValueRegisterPart.mov(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
					// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					concreteValueRegisterPart.mov(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
							.intValue()));
					// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					X86MemoryOperand t = (X86MemoryOperand) src;
					if (t.getBase() != null) {
						concreteValueRegisterPart.mov(dest.toString(),
								concreteValueRegister.getRegVal(t.getBase().toString()));
						if (t.getDisplacement() != 0)
							concreteValueRegisterPart.add(dest.toString(), t.getDisplacement());

						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				}
			} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					concreteValueSegment.mov(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					concreteValueSegment.mov(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					concreteValueSegment.mov(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					concreteValueSegment.mov(dest.toString(),
							concreteValueMemoryOperand.getRegVal((X86MemoryOperand) src));
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					concreteValueSegment.mov(dest.toString(), getBitVecValue(((Immediate) src).getNumber().intValue()));
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					X86MemoryOperand t = (X86MemoryOperand) src;
					if (t.getBase() != null) {
						concreteValueSegment.mov(dest.toString(),
								concreteValueRegister.getRegVal(t.getBase().toString()));
					}
				}
			}
		} else if (inst.getName().startsWith("cmp") || inst.getName().startsWith("test")) {
			// SymbolicValue lhs = null, rhs = null;

			if (dest.getClass().getSimpleName().equals("X86Register"))
				d = concreteValueRegister.getRegVal(dest.toString());
			else if (dest.getClass().getSimpleName().equals("X86RegisterPart"))
				d = concreteValueRegisterPart.getRegVal(dest.toString());
			else if (dest.getClass().getSimpleName().equals("X86SegmentRegister"))
				d = concreteValueSegment.getRegVal(dest.toString());
			else if (dest.getClass().getSimpleName().equals("Immediate"))
				d = getBitVecValue(((Immediate) dest).getNumber().intValue());
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// d = concreteValueMemoryOperand
				// .getMemoryOperandVal((X86MemoryOperand) dest);
				d = calculateValueMemoryOperand((X86MemoryOperand) dest, inst);
			}

			if (src.getClass().getSimpleName().equals("X86Register"))
				s = concreteValueRegister.getRegVal(src.toString());
			else if (src.getClass().getSimpleName().equals("X86RegisterPart"))
				s = concreteValueRegisterPart.getRegVal(src.toString());
			else if (src.getClass().getSimpleName().equals("X86SegmentRegister"))
				s = concreteValueSegment.getRegVal(src.toString());
			else if (src.getClass().getSimpleName().equals("Immediate"))
				if (inst.getName().endsWith("b")) {
					s = ((Immediate) src).getNumber().intValue() & 0xff;
					// s = new ValueLongExp(t);
				} else
					s = getBitVecValue(((Immediate) src).getNumber().intValue());
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				s = concreteValueMemoryOperand.getMemoryOperandVal((X86MemoryOperand) src);
			}

			// this.formulas.add(new Formula(d.clone(), s.clone()));
			if (inst.getName().startsWith("cmp"))
				concreteFlag.changeFlagWithCMP(concreteValueRegister, concreteValueMemoryOperand, concreteStack, d, s);
			else
				concreteFlag.changeFlagWithTEST(concreteValueRegister, concreteValueMemoryOperand, concreteStack, d, s);
			// set compare status

		} else if (inst.getName().startsWith("pop")) {
			if (dest == null)
				return;

			if (dest.getClass().getSimpleName().equals("X86Register")) {
				if (!concreteValueRegister.setSymbolRegisterValue(dest.toString(), concreteStack.pop()))
					System.out.println("Error");

				// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
				// concreteValueRegister, dest.toString());

				concreteValueRegister.add("%esp", 4);
				// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
				// concreteValueRegister, "%esp");

			} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
				concreteValueRegisterPart.setConcreteRegisterPartValue(dest.toString(), concreteStack.pop());

				// System.out.println("Error");
				// else
				// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
				// concreteValueRegister, dest.toString());

				concreteValueRegister.add("%esp", 2);
				// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
				// concreteValueRegister, "%esp");
			} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				if (!concreteValueSegment.setSymbolRegisterValue(dest.toString(), concreteStack.pop()))
					System.out.println("Error");
			} else if (dest.getClass().getSimpleName().equals("Immediate"))
				// d = new ConstantExp(((Immediate)
				// dest).getNumber().intValue());
				;
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				if (!concreteValueMemoryOperand.setSymbolMemoryOperandValue((X86MemoryOperand) dest,
						concreteStack.pop()))
					System.out.println("Error");
			}

		} else if (inst.getName().startsWith("pusha")) {
			concreteStack.push(concreteValueRegister.getRegVal("%eax"));
			concreteStack.push(concreteValueRegister.getRegVal("%ecx"));
			concreteStack.push(concreteValueRegister.getRegVal("%edx"));
			concreteStack.push(concreteValueRegister.getRegVal("%ebx"));
			concreteStack.push(concreteValueRegister.getRegVal("%esp"));
			concreteStack.push(concreteValueRegister.getRegVal("%ebp"));
			concreteStack.push(concreteValueRegister.getRegVal("%esi"));
			concreteStack.push(concreteValueRegister.getRegVal("%edi"));

		} else if (inst.getName().startsWith("pushf")) {
			concreteStack.push(0);
		} else if (inst.getName().startsWith("push")) {
			if (dest == null)
				return;

			if (dest.getClass().getSimpleName().equals("X86Register"))
				d = concreteValueRegister.getRegVal(dest.toString());
			else if (dest.getClass().getSimpleName().equals("X86RegisterPart"))
				d = concreteValueRegisterPart.getRegVal(dest.toString());
			else if (dest.getClass().getSimpleName().equals("X86SegmentRegister"))
				d = concreteValueRegister.getRegVal(dest.toString());
			else if (dest.getClass().getSimpleName().equals("Immediate"))
				d = getBitVecValue(((Immediate) dest).getNumber().intValue());
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// d = concreteValueMemoryOperand
				// .getMemoryOperandVal((X86MemoryOperand) dest);
				d = this.calculateValueMemoryOperand((X86MemoryOperand) dest, inst);
				/*
				 * X86MemoryOperand t = (X86MemoryOperand) dest; if (t.getBase()
				 * != null) { d =
				 * concreteValueRegister.getRegVal(t.getBase().toString()); if
				 * (t.getDisplacement() != 0) d += t.getDisplacement();
				 * //FlagRelationship.totalChangePart(concreteValueRegisterPart,
				 * // concreteValueRegister, dest.toString()); } else if
				 * (t.getDisplacement() != 0) { d = t.getDisplacement();
				 * //FlagRelationship.totalChangePart(concreteValueRegisterPart,
				 * // concreteValueRegister, dest.toString()); }
				 */
			}

			concreteStack.push(d);
		} else if (inst.getName().startsWith("nop")) {
			// do nothing
		} else {

		}

	}

	private void X86JumpInterprete(X86JmpInstruction inst) {
		Operand dest = inst.getOperand1();
		// System.out.println("Instruction: " + inst.getName());

		// jump structure: jump to call address
		// first operand
		AbsoluteAddress jumpAddress = null;
		// System.out.println("Instruction: " + inst.getName());

		// jump structure: jump to call address
		// first operand
		if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
			// returnAddress = new SymbolicValue(((AbsoluteAddress)
			// dest).getValueOperand());
			jumpAddress = new AbsoluteAddress(((AbsoluteAddress) dest).getValue());
		} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
			jumpAddress = new AbsoluteAddress(((X86PCRelativeAddress) dest).getEffectiveValue(targetTemp.getValue()));
			// System.out.println("RelativeAddress:" + jumpAddress.toString());

		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			// returnAddress = (prevState.getRegValue(((X86Register)
			// dest).toString())).clone();
			jumpAddress = new AbsoluteAddress(concreteValueRegister.getRegVal(dest.toString()));

		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			jumpAddress = new AbsoluteAddress(((X86MemoryOperand) dest).getDisplacement());
			System.out.println("MemoryOperand:" + jumpAddress.toString());
		}

		if (stubLibrary != null && contain(((Win32StubLibrary) stubLibrary).getAddressStubMap(), targetTemp)) {
			// String funcName
			// SystemCallStub.executeConcrete(funcName);
			System.out.println("Address:" + targetTemp.toString());
			if (APIStub.executeConcrete(funcName, this, inst))
				funcName = "";
			else
				System.out.println("Concrete Execution of System Call failed:" + funcName);
			this.evaluateValue = retAPIAddr.getValue();
		} else {
			if (jumpAddress != null && this.targetTemp.getValue() == this.targetIndirect.getValue())
				this.evaluateValue = jumpAddress.getValue();
			else
				this.evaluateValue = Long.MIN_VALUE;

		}

	}

	private void X86MoveInterprete(X86MoveInstruction inst) {
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();

		// System.out.println("Instruction: " + inst.getName());
		// SymbolicValue toMoveVal = null;

		if (src.getClass().getSimpleName().equals("Immediate")) {
			// toMoveVal = new SymbolicValue(((Immediate)
			// src).getNumber().intValue());
		} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// toMoveVal = prevState.getMemoryValue((X86MemoryOperand) src);
			// if (toMoveVal == null) {
			// halt_status = MISSING_MEMORY;
			// memoryAddress = (X86MemoryOperand) src;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// inst.toString(startAddress, symFinder));
			// return null;
			// }
			// toMoveVal = toMoveVal.clone();
		} else if (src.getClass().getSimpleName().equals("X86Register")) {
			// toMoveVal = prevState.getRegValue(((X86Register)
			// src).toString()).clone();
		}

		if (inst.getName().startsWith("mov")) {
			// normal move
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				/*
				 * X86MemoryOperand t = (X86MemoryOperand) dest; long base = 0;
				 * long disp = 0;
				 * 
				 * if (t.getBase() != null) { base =
				 * concreteValueRegister.getRegVal(t.getBase() .toString()); }
				 * if (t.getDisplacement() != 0) { disp = t.getDisplacement(); }
				 * 
				 * t = new X86MemoryOperand(t.getDataType(), disp + base);
				 */
				X86MemoryOperand y = (X86MemoryOperand) dest;
				// Xu li truong hop mov fs:0, esp
				// Khi do se tac dong den SEH
				if (y.getSegmentRegister() != null && y.getSegmentRegister().toString() == "%fs"
						&& y.getDisplacement() == 0) {
					System.out.println("SEH Exploit:" + inst.toString());
					if (src.getClass().getSimpleName().equals("X86Register")) {
						if (((X86Register) src).toString().equals("%esp")) {
							system.getSEHHandler().getStart().setNextSEHRecord(concreteStack.get(0));
							system.getSEHHandler().getStart().setSEHHandler(concreteStack.get(4));
						}
					}
				} else {
					X86MemoryOperand t = calculateMemoryOperand((X86MemoryOperand) dest);

					if (src.getClass().getSimpleName().equals("X86Register")) {
						long v = concreteValueRegister.getRegVal(src.toString());

						String x = Convert.longToHex(v, 32);
						int l = x.length();
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(t,
								Convert.hexToLong(x.substring(l - 2, l)));
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(
								new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 1),
								Convert.hexToLong(x.substring(l - 4, l - 2)));
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(
								new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 2),
								Convert.hexToLong(x.substring(l - 6, l - 4)));
						concreteValueMemoryOperand.setSymbolMemoryOperandValue(
								new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 3),
								Convert.hexToLong(x.substring(l - 8, l - 6)));

					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueMemoryOperand.mov((X86MemoryOperand) dest,
								concreteValueRegisterPart.getRegVal(src.toString()));

					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueMemoryOperand.mov((X86MemoryOperand) dest,
								concreteValueSegment.getRegVal(src.toString()));

					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						concreteValueMemoryOperand.mov((X86MemoryOperand) dest,
								concreteValueMemoryOperand.getRegVal((X86MemoryOperand) src));

					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueMemoryOperand.mov((X86MemoryOperand) dest, getBitVecValue(((Immediate) src)
								.getNumber().intValue()));

					}
				}
			} else if (dest.getClass().getSimpleName().equals("X86Register")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					concreteValueRegister.mov(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					concreteValueRegister.mov(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					concreteValueRegister.mov(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					X86MemoryOperand t = (X86MemoryOperand) src;
					long desp = t.getDisplacement();

					// Xu ly cho van de MemoryOperand la [esp + 24]
					if (t.getBase() != null && t.getBase().toString() == "%esp") {
						concreteValueRegister.mov(dest.toString(), concreteStack.get(desp));
					} else if (t.getBase() != null) {
						// Xy li cho van de MemoryOperand la [eax + 0x401201]
						long v = concreteValueRegister.getRegVal(t.getBase().toString());

						// if (v instanceof ValueLongExp) {
						concreteValueRegister.mov(dest.toString(),
								calculateValueMemoryOperand(new X86MemoryOperand(t.getDataType(), desp + v), inst));
						// }

					} else
						// Xu li cho van de MemoryOperand la 0x401020
						concreteValueRegister.mov(dest.toString(),
								calculateValueMemoryOperand(new X86MemoryOperand(t.getDataType(), desp), inst));

					// concreteValueRegister.mov(dest.toString(),
					// concreteValueMemoryOperand
					// .getRegVal((X86MemoryOperand) src));
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					concreteValueRegister
							.mov(dest.toString(), getBitVecValue(((Immediate) src).getNumber().intValue()));
					// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
					// concreteValueRegister, dest.toString());
				}
			}
		} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
			if (src.getClass().getSimpleName().equals("X86Register")) {
				concreteValueRegisterPart.mov(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
				// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
				// concreteValueRegister, dest.toString());
			} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
				concreteValueRegisterPart.mov(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
				// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
				// concreteValueRegister, dest.toString());
			} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				concreteValueRegisterPart.mov(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
				// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
				// concreteValueRegister, dest.toString());
			} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				concreteValueRegisterPart.mov(dest.toString(),
						concreteValueMemoryOperand.getRegVal((X86MemoryOperand) src));
				// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
				// concreteValueRegister, dest.toString());
			} else if (src.getClass().getSimpleName().equals("Immediate")) {
				concreteValueRegisterPart
						.mov(dest.toString(), getBitVecValue(((Immediate) src).getNumber().intValue()));
				// RegisterRelationship.partChangeTotal(concreteValueRegisterPart,
				// concreteValueRegister, dest.toString());
			}
		} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
			if (src.getClass().getSimpleName().equals("X86Register")) {
				concreteValueSegment.mov(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
			} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
				concreteValueSegment.mov(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
			} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				concreteValueSegment.mov(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
			} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				concreteValueSegment.mov(dest.toString(), concreteValueMemoryOperand.getRegVal((X86MemoryOperand) src));
			} else if (src.getClass().getSimpleName().equals("Immediate")) {
				concreteValueSegment.mov(dest.toString(), getBitVecValue(((Immediate) src).getNumber().intValue()));
			}
		} else if (inst.getName().startsWith("xchg")) {
			// normal move
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = calculateMemoryOperand((X86MemoryOperand) dest);
				System.out.println(t.getDisplacement());

				if (!program.checkAddress(new AbsoluteAddress(t.getDisplacement()))) {
					// SEH Exploit
					evaluateValue = system.getSEHHandler().getStart().getSehHandler();
					running = false;
				}
			} else {
				// prevState.setRegValue(((X86Register) dest).toString(),
				// toMoveVal);

				long t = concreteValueRegister.getRegVal(dest.toString());
				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueRegister.mov(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						concreteValueRegister.mov(src.toString(), t);
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, src.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						/*
						 * concreteValueRegister .mov(dest.toString(),
						 * concreteValueRegisterPart
						 * .getRegVal(src.toString())); FlagRelationship
						 * .totalChangePart(concreteValueRegisterPart,
						 * concreteValueRegister, dest.toString());
						 */
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegister.mov(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						concreteValueSegment.mov(src.toString(), t);
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						concreteValueRegister.mov(dest.toString(),
								concreteValueMemoryOperand.getRegVal((X86MemoryOperand) src));
						concreteValueMemoryOperand.mov((X86MemoryOperand) src, t);
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegister.mov(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.totalChangePart(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						/*
						 * concreteValueRegisterPart.mov(dest.toString(),
						 * concreteValueRegister.getRegVal(src.toString()));
						 * FlagRelationship
						 * .partChangeTotal(concreteValueRegisterPart,
						 * concreteValueRegister, dest.toString());
						 */
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueRegisterPart.mov(dest.toString(),
								concreteValueRegisterPart.getRegVal(src.toString()));
						concreteValueRegisterPart.mov(src.toString(), t);
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueRegisterPart.mov(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						concreteValueSegment.mov(src.toString(), t);
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						concreteValueRegisterPart.mov(dest.toString(),
								concreteValueMemoryOperand.getRegVal((X86MemoryOperand) src));
						concreteValueMemoryOperand.mov((X86MemoryOperand) src, t);
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueRegisterPart.mov(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
						// RegisterRelationship.partChangeTotal(
						// concreteValueRegisterPart,
						// concreteValueRegister, dest.toString());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						concreteValueSegment.mov(dest.toString(), concreteValueRegister.getRegVal(src.toString()));
						concreteValueRegister.mov(src.toString(), t);
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						concreteValueSegment.mov(dest.toString(), concreteValueRegisterPart.getRegVal(src.toString()));
						concreteValueRegisterPart.mov(src.toString(), t);
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						concreteValueSegment.mov(dest.toString(), concreteValueSegment.getRegVal(src.toString()));
						concreteValueSegment.mov(src.toString(), t);
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						concreteValueSegment.mov(dest.toString(),
								concreteValueMemoryOperand.getRegVal((X86MemoryOperand) src));
						concreteValueMemoryOperand.mov((X86MemoryOperand) src, t);
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						concreteValueSegment.mov(dest.toString(), getBitVecValue(((Immediate) src).getNumber()
								.intValue()));
					}
				}

			}
		} else {
			// conditional move
			// structure: create new work associated with condition-false case
			// move in condition-true case
			// remain the same in condition-false case

			// SymbolicCondition compareStatus = prevState.getCompareStatus();
			// if (compareStatus == null) {
			// logger.error("Only accept conditional statement after CMP");
			// halt_status = FAILED;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// inst.toString(startAddress, symFinder));
			// return null;
			// }

			// ignore signs
			// SymbolicCondition positiveCond = new SymbolicCondition();
			// SymbolicCondition negativeCond = new SymbolicCondition();
			// int positiveConnector, negativeConnector;

			if (inst.getName().equals("cmova") || inst.getName().equals("cmovg")) {
				// positiveConnector = SymbolicCondition.B_OP_GREATER;
				// negativeConnector = SymbolicCondition.B_OP_NOT_GREATER;
			} else if (inst.getName().equals("cmovae") || inst.getName().equals("cmovge")) {
				// positiveConnector = SymbolicCondition.B_OP_NOT_LESS;
				// negativeConnector = SymbolicCondition.B_OP_LESS;
			} else if (inst.getName().equals("cmovb") || inst.getName().equals("cmovl")) {
				// positiveConnector = SymbolicCondition.B_OP_LESS;
				// negativeConnector = SymbolicCondition.B_OP_NOT_LESS;
			} else if (inst.getName().equals("cmovbe") || inst.getName().equals("cmovle")) {
				// positiveConnector = SymbolicCondition.B_OP_NOT_GREATER;
				// negativeConnector = SymbolicCondition.B_OP_GREATER;
			} else if (inst.getName().equals("cmove")) {
				// positiveConnector = SymbolicCondition.B_OP_EQUAL;
				// negativeConnector = SymbolicCondition.B_OP_NOT_EQUAL;
			} else if (inst.getName().equals("cmovne")) {
				// positiveConnector = SymbolicCondition.B_OP_NOT_EQUAL;
				// negativeConnector = SymbolicCondition.B_OP_EQUAL;
			} else if (inst.getName().equals("cmovnl")) {
				// positiveConnector = SymbolicCondition.B_OP_NOT_LESS;
				// negativeConnector = SymbolicCondition.B_OP_LESS;
			} else {
				// logger.error("Unsupported instruction: " + inst.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// inst.toString(startAddress, symFinder));
				// return null;
			}
		}

	}

	private void X86ReturnInterprete(X86RetInstruction inst) {
		if (inst.getName().startsWith("ret")) {
			if (this.targetIndirect.getValue() == this.targetTemp.getValue()) {
				this.evaluateValue = concreteStack.pop();
			} else {
				concreteStack.pop();
				concreteValueRegister.add("%esp", 4);
			}

			// RegisterRelationship.totalChangePart(concreteValueRegisterPart,
			// concreteValueRegister, "%esp");
		}
	}

	private long calculateValueMemoryOperand(X86MemoryOperand t, Instruction inst) {
		// TODO Auto-generated method stub
		long d = 0;
		// X86MemoryOperand t = (X86MemoryOperand) dest;
		long desp = t.getDisplacement();
		long base = 0;
		if (t.getBase() != null) {
			if (t.getBase().toString().equals("%esp"))
				base = concreteStack.get(desp);
			else
				base = concreteValueRegister.getRegVal(t.getBase().toString());
		}
		long ad = desp + base;
		// Truy cap dia chi trong kernel32 Address
		if (system.checkAddrInKernel32(ad)) {
			if (inst.getName().toString().endsWith("b")) {
				d = system.getKernel().readByte((int) ad);
			} else if (inst.getName().toString().endsWith("s")) {
				d = system.getKernel().readWord((int) ad);
			} else if (inst.getName().toString().endsWith("w")) {
				d = system.getKernel().readWord((int) ad);
			} else if (inst.getName().toString().endsWith("l")) {
				d = system.getKernel().readDoubleWord((int) ad);
			}
		} else if (system.checkAddrInFile(ad)) {
			if (inst.getName().toString().endsWith("b")) {
				d = system.getFileHandle().readByte((int) ad);
			} else if (inst.getName().toString().endsWith("s")) {
				d = system.getFileHandle().readWord((int) ad);
			} else if (inst.getName().toString().endsWith("w")) {
				d = system.getFileHandle().readWord((int) ad);
			} else if (inst.getName().toString().endsWith("l")) {
				d = system.getFileHandle().readDoubleWord((int) ad);
			}
		} else {
			/*
			 * d = concreteValueMemoryOperand .getMemoryOperandVal(new
			 * X86MemoryOperand(t.getDataType(), desp));
			 */
			if (inst.getName().toString().endsWith("b")) {
				if (inst.getName().toString().startsWith("cmp")
						&& !concreteValueMemoryOperand.contain(new X86MemoryOperand(t.getDataType(), desp))) {
					d = program.getByteValueMemory(new AbsoluteAddress(ad));
				} else

					d = concreteValueMemoryOperand.getMemoryOperandVal(new X86MemoryOperand(t.getDataType(), ad));
			} else if (inst.getName().toString().endsWith("s")) {
				d = concreteValueMemoryOperand.getMemoryOperandValWord(new X86MemoryOperand(t.getDataType(), ad));
			} else if (inst.getName().toString().endsWith("w")) {
				d = concreteValueMemoryOperand.getMemoryOperandValWord(new X86MemoryOperand(t.getDataType(), ad));
			} else if (inst.getName().toString().endsWith("l")) {
				d = concreteValueMemoryOperand.getMemoryOperandValDoubleWord(new X86MemoryOperand(t.getDataType(), ad));
			}
		}

		return d;
	}

	private X86MemoryOperand calculateMemoryOperand(X86MemoryOperand t) {
		// TODO Auto-generated method stub
		long base = 0;
		long disp = 0;

		if (t.getBase() != null) {
			base = concreteValueRegister.getRegVal(t.getBase().toString());
		}
		if (t.getDisplacement() != 0) {
			disp = t.getDisplacement();
		}

		return new X86MemoryOperand(t.getDataType(), disp + base);
	}
}
