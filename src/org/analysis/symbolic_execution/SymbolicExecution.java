package org.analysis.symbolic_execution;

import org.analysis.api_stub.APIStub;
import org.analysis.cfg.Condition;
import org.analysis.cfg.IntraCFG;
import org.analysis.concolic_testing.TestCaseValue;
import org.analysis.formula.*;
import org.analysis.wpds.WConf;
import org.analysis.wpds.weight.IntegerWeight;
import org.analysis.wpds.weight.Weight;
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
import java.util.*;
import java.util.Map.Entry;

public class SymbolicExecution {
	private static int MAX_NUM_LOOP_EACH = 1500;
	private static int MAX_NUM_LOOP_TOTAL = 200000;
	private int loopTotal = 0, loopEach = 0;
	private long loopAddr = Long.MIN_VALUE;

	private AbsoluteAddress targetTemp, targetIndirect, retAPIAddr = new AbsoluteAddress(0);
	// private Instruction oldInst = null;
	// private boolean specialTrace = false;
	private AddressList addrTraceList;
	private Map<AbsoluteAddress, Instruction> assemblyMap;
	long evaluateValue;
	private Map<AbsoluteAddress, AbsoluteAddress> neg;

	public Map<AbsoluteAddress, AbsoluteAddress> getNeg() {
		return neg;
	}

	public void setNeg(Map<AbsoluteAddress, AbsoluteAddress> neg) {
		this.neg = neg;
	}

	private FormulaSet formulaList;
	private TestCaseValue pcValue;

	public TestCaseValue getPcValue() {
		return pcValue;
	}

	public void setPcValue(TestCaseValue pcValue) {
		this.pcValue = pcValue;
	}

	private SymbolValueRegister symbolValueRegister;
	private SymbolValueRegisterPart symbolValueRegisterPart;

	public AbsoluteAddress getTargetTemp() {
		return targetTemp;
	}

	public void setTargetTemp(AbsoluteAddress targetTemp) {
		this.targetTemp = targetTemp;
	}

	public AbsoluteAddress getTargetIndirect() {
		return targetIndirect;
	}

	public void setTargetIndirect(AbsoluteAddress targetIndirect) {
		this.targetIndirect = targetIndirect;
	}

	public SymbolValueRegister getSymbolValueRegister() {
		return symbolValueRegister;
	}

	public void setSymbolValueRegister(SymbolValueRegister symbolValueRegister) {
		this.symbolValueRegister = symbolValueRegister;
	}

	public SymbolValueRegisterPart getSymbolValueRegisterPart() {
		return symbolValueRegisterPart;
	}

	public void setSymbolValueRegisterPart(SymbolValueRegisterPart symbolValueRegisterPart) {
		this.symbolValueRegisterPart = symbolValueRegisterPart;
	}

	public SymbolValueMemoryOperand getSymbolValueMemoryOperand() {
		return symbolValueMemoryOperand;
	}

	public void setSymbolValueMemoryOperand(SymbolValueMemoryOperand symbolValueMemoryOperand) {
		this.symbolValueMemoryOperand = symbolValueMemoryOperand;
	}

	public SymbolValueSegment getSymbolValueSegment() {
		return symbolValueSegment;
	}

	public void setSymbolValueSegment(SymbolValueSegment symbolValueSegment) {
		this.symbolValueSegment = symbolValueSegment;
	}

	public SymbolFlag getSymbolFlag() {
		return symbolFlag;
	}

	public void setSymbolFlag(SymbolFlag symbolFlag) {
		this.symbolFlag = symbolFlag;
	}

	public SymbolStack getSymbolStack() {
		return symbolStack;
	}

	public void setSymbolStack(SymbolStack symbolStack) {
		this.symbolStack = symbolStack;
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

	private SymbolValueMemoryOperand symbolValueMemoryOperand;
	private SymbolValueSegment symbolValueSegment;
	private SymbolFlag symbolFlag;
	private SymbolStack symbolStack;
	private StubProvider stubLibrary;
	private String funcName = "";
	private Program program;
	private SystemHandle system;
	private IntraCFG cfg;
	private List<WConf> weightConf;
	private Weight type;
	private boolean running = true;
	private boolean stop = false;

	public SymbolicExecution(AbsoluteAddress target, AddressList addrTraceList,
			Map<AbsoluteAddress, Instruction> assemblyMap) {
		super();
		this.targetTemp = target;
		this.targetIndirect = target;
		this.addrTraceList = addrTraceList;
		// this.traceList = addrTraceList.clone();
		this.assemblyMap = assemblyMap;
		// setFormulas(new SymbolicFormulas());
		formulaList = new FormulaSet();
		symbolValueRegister = new SymbolValueRegister();
		symbolValueRegisterPart = new SymbolValueRegisterPart();
		symbolValueSegment = new SymbolValueSegment();
		symbolValueMemoryOperand = new SymbolValueMemoryOperand();
		neg = new TreeMap<AbsoluteAddress, AbsoluteAddress>();
		pcValue = new TestCaseValue();
		evaluateValue = Long.MIN_VALUE;
		symbolFlag = new SymbolFlag();
		symbolStack = new SymbolStack();
	}

	public SymbolicExecution(AbsoluteAddress target, AddressList addrTraceList,
			Map<AbsoluteAddress, AbsoluteAddress> neg, Map<AbsoluteAddress, Instruction> assemblyMap, TestCaseValue var) {
		super();
		this.targetTemp = target;
		this.targetIndirect = target;
		this.addrTraceList = addrTraceList;
		// this.traceList = addrTraceList.clone();
		this.assemblyMap = assemblyMap;
		// setFormulas(new SymbolicFormulas());
		formulaList = new FormulaSet();
		symbolValueRegister = new SymbolValueRegister();
		symbolValueRegisterPart = new SymbolValueRegisterPart();
		symbolValueSegment = new SymbolValueSegment();
		symbolValueMemoryOperand = new SymbolValueMemoryOperand();
		this.neg = neg;
		this.pcValue = var;
		evaluateValue = Long.MIN_VALUE;
		symbolFlag = new SymbolFlag();
		symbolStack = new SymbolStack();

		this.symbolValueRegister.setSymbolFlag(symbolFlag);
	}

	public SymbolicExecution(AbsoluteAddress indirectTarget, AddressList trace,
			Map<AbsoluteAddress, AbsoluteAddress> negConditionList, Map<AbsoluteAddress, Instruction> assemblyMap2,
			TestCaseValue sv, StubProvider stubLibrary2) {
		// TODO Auto-generated constructor stub
		this(indirectTarget, trace, negConditionList, assemblyMap2, sv);
		stubLibrary = stubLibrary2;
	}

	public SymbolicExecution(AbsoluteAddress indirectTarget, AddressList trace,
			Map<AbsoluteAddress, AbsoluteAddress> negConditionList, TestCaseValue sv, Program program) {
		// TODO Auto-generated constructor stub
		this(indirectTarget, trace, negConditionList, program.getAssemblyMap(), sv, program.getStubLibrary());
		this.program = program;
	}

	private void addStubKernel32() {
		if (this.stubLibrary == null)
			return;

		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088899403),
				"GetWindowsDirectoryA");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088849274),
				"GetSystemDirectoryA");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088980502),
				"GetCurrentDirectoryA");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088984821),
				"SetCurrentDirectoryA");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap()
				.put(new AbsoluteAddress(2088843369), "FindFirstFileA");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088839186),
				"SetFileAttributesA");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088770088), "CreateFileA");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088801518),
				"CreateFileMappingA");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088803287), "CloseHandle");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088980169), "FindNextFileA");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088810901), "MapViewOfFile");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap()
				.put(new AbsoluteAddress(2088832030), "SetFilePointer");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088968286), "SetEndOfFile");
		((Win32StubLibrary) this.stubLibrary).getAddressStubMap().put(new AbsoluteAddress(2088811012),
				"UnMapViewOfFile");

	}

	public SymbolicExecution(AbsoluteAddress indirectTarget, AddressList trace,
			Map<AbsoluteAddress, AbsoluteAddress> negConditionList, TestCaseValue sv, Program program,
			SystemHandle system) {
		// TODO Auto-generated constructor stub
		this(indirectTarget, trace, negConditionList, program.getAssemblyMap(), sv, program.getStubLibrary());
		this.program = program;
		this.system = system;
	}

	public SymbolicExecution(AbsoluteAddress indirectTarget, AddressList trace, IntraCFG cfg, TestCaseValue sv,
			Program program2, SystemHandle system2) {
		// TODO Auto-generated constructor stub
		this(indirectTarget, trace, cfg.getNegConditionList(), program2.getAssemblyMap(), sv, program2.getStubLibrary());
		this.program = program2;
		this.system = system2;
		this.cfg = cfg;
		this.addStubKernel32();
		this.symbolValueMemoryOperand = new SymbolValueMemoryOperand(sv, program, symbolValueRegister,
				symbolValueRegisterPart, symbolValueSegment);
		this.symbolValueRegister.setSymbolValueRegisterPart(this.symbolValueRegisterPart);
		this.symbolValueRegisterPart.setSymbolValueRegister(this.symbolValueRegister);
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

	public void printInfor() {
		System.out.println();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Apply the method of Symbolic Execution:");
		System.out.print("Trace List of Address: ");
		this.addrTraceList.printInfo();
	}

	public long generateRandom(long Min, long Max) {
		return Min + (int) (Math.random() * ((Max - Min) + 1));
	}

	public boolean execute() {
		// this.printInfor();
		// AbsoluteAddress result = null;
		AddressList addrL = addrTraceList.clone();
		long t = addrL.get(0).getValue();
		// long returnValue = 4276551680L;
		// long returnValue = (long) (Math.random() * Math.pow(2, 31));
		// long returnValue = 0;
		symbolStack.push(new LongValueOld(this.system.getKernel().getReturnRandomValue()));
		symbolValueRegister.mov("%edx", new LongValueOld(t));

		// trick
		// Now remove this trick for solving the problem of file
		// Virus.Win32.Belial.a at 0x00401195
		/*
		 * try { Instruction ins1 = assemblyMap.get(this.targetIndirect); //
		 * program.getS if (ins1.getName().startsWith("jmp") && contain(
		 * ((Win32StubLibrary) stubLibrary) .getAddressStubMap(),
		 * this.targetIndirect)) { this.evaluateValue =
		 * this.addrTraceList.getNextLast() .getValueOperand() + 5; return true;
		 * } } catch (Exception e) { System.out.println(e.toString()); }
		 */

		while (running) {
			if (stop) {
				System.out.println("Symbolic Execution stops at:" + targetTemp.toString() + " Indirect Jump:"
						+ targetIndirect.toString());
				return false;
			}
			/*
			 * if (this.specialTrace) { targetTemp =
			 * calculateNewTargetWithSpecalTrace(targetTemp); } else
			 */
			targetTemp = addrTraceList.pop();

			if (targetTemp == null)
				// System.out.println("End of Symbolic Execution");
				// formulas.printInfo();
				break;

			/*
			 * if (targetTemp.toString().equals("0x004013a0"))
			 * System.out.println("Debug");
			 * 
			 * if (targetTemp.toString().equals("0x004011ab"))
			 * System.out.println("Debug");
			 */

			Instruction ins = assemblyMap.get(targetTemp);
			// System.out.print(targetTemp.toString() + " " + ins.getName() +
			// " ");

			for (int i = 0; i < ins.getOperandCount(); i++)
				if (ins.getOperand(i).getClass().getSimpleName().equals("X86Register"))
					pcValue.add(ins.getOperand(i).toString(), 0);
				else if (ins.getOperand(i).getClass().getSimpleName().equals("X86RegisterPart"))
					pcValue.add(ins.getOperand(i).toString(), 0);
				else if (ins.getOperand(i).getClass().getSimpleName().equals("X86SegmentRegister"))
					pcValue.add(ins.getOperand(i).toString(), 0);
				else if (ins.getOperand(i).getClass().getSimpleName().equals("X86MemoryOperand")) {
					// var.add(((X86MemoryOperand)
					// ins.getOperand(i)).toString(), generateRandom(0,
					// Integer.MAX_VALUE));{
					/*
					 * X86MemoryOperand t = (X86MemoryOperand)
					 * ins.getOperand(i); X86Register r = (X86Register)
					 * t.getBase(); long disp = t.getDisplacement(); if (r !=
					 * null) var.add(((X86MemoryOperand) ins.getOperand(i))
					 * .toString(), disp + var.getVarValue(r.toString())); else
					 * var.add(((X86MemoryOperand) ins.getOperand(i))
					 * .toString(), disp);
					 */

					pcValue.add(((X86MemoryOperand) ins.getOperand(i)).toString(), Long.MIN_VALUE);
				}

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

			if (this.type instanceof IntegerWeight)
				this.weightConf.add(new WConf(new AbsoluteAddress(targetTemp.getValue()), ins, new IntegerWeight(1)));

			/*
			 * EnvWeight ew = new EnvWeight(); ew.setAddress(new
			 * AbsoluteAddress(targetTemp.getValueOperand()));
			 * ew.setInstruction(assemblyMap.get(targetTemp)); Environment e =
			 * new Environment(symbolValueRegister.clone(),
			 * symbolValueRegisterPart.clone(),
			 * symbolValueMemoryOperand.clone(), symbolValueSegment.clone(),
			 * symbolFlag.clone(), symbolStack.clone(), program, system);
			 * 
			 * sr.addWeight(ew);
			 */// ew.addWeight(new Environment())
		}

		// System.out.println(sr.toString());
		this.addrTraceList = addrL;

		// Check later - Important
		/*
		 * if (!checkSatPC()) return false; return evaluate() || executeZ3();
		 */
		return executeZ3();
	}

	@SuppressWarnings("null")
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
			String line = "";
			fis = new FileInputStream("z3Output.txt");
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			line = br.readLine();

			if (line != null || line.equals("sat")) {
				System.out.println("Check Sat");
				return true;
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		// Done with the file
		// System.out.println(result.substring(8));
		System.out.println("Check Unsat");
		br = null;
		fis = null;
		return false;
	}

	private Value getTargetExp(String type, String destName) {
		Value v = null;
		if (type.equals("X86Register"))
			v = this.symbolValueRegister.getRegVal(destName);

		else if (type.equals("X86RegisterPart"))
			v = this.symbolValueRegisterPart.getRegVal(destName);

		else if (type.equals("X86SegmentRegister"))
			v = this.symbolValueSegment.getRegVal(destName);

		else if (type.equals("X86MemoryOperand"))
			v = this.symbolValueMemoryOperand.getMemoryOperandVal(destName);

		return v;
	}

	private boolean checkArithmeticTargetExp(String type, String destName) {
		return (getTargetExp(type, destName) instanceof LongValueOld);
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
			long vTarget = Long.parseLong(getTargetExp(type, destName).toString());
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
							pcValue.add(ins.getOperand(i).toString(), 0);
						else if (ins.getOperand(i).getClass().getSimpleName().equals("X86SegmentRegister"))
							pcValue.add(ins.getOperand(i).toString(), 0);
						else if (ins.getOperand(i).getClass().getSimpleName().equals("X86RegisterPart"))
							pcValue.add(ins.getOperand(i).toString(), 0);
						else if (ins.getOperand(i).getClass().getSimpleName().equals("X86MemoryOperand"))
							pcValue.add(((X86MemoryOperand) ins.getOperand(i)).toString(), 0);

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

	public void printResult() {
		// TODO Auto-generated method stub
		System.out.println("Result of Symbolic Execution:");
		// printResult();
		this.formulaList.printInfo();
		this.symbolValueRegister.printInfo();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println();

	}

	// add, inc, and, sub, or
	private void X86ArithmeticInterprete(X86ArithmeticInstruction ins) {

		switch (ins.getOperation()) {
		case SDIV:
			// Statement supported: add, inc
			if (ins.getName().startsWith("unsignedDiv")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value d = null;
				Value s = null;

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					d = symbolValueRegister.getRegVal(dest.toString());
					if (d instanceof LongValueOld) {
						if (((LongValueOld) d).getValue() == 0) {
							evaluateValue = system.getSEHHandler().getStart().getSehHandler();
							running = false;
							return;
						}
					}
					if (src.getClass().getSimpleName().equals("X86Register")) {
						s = symbolValueRegister.getRegVal(src.toString());

						symbolValueRegister.div(dest.toString(), symbolValueRegister.getRegVal(src.toString()));

					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegister.add(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
						s = symbolValueRegisterPart.getRegVal(src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegister.add(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
						s = symbolValueSegment.getRegVal(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegister.add(dest.toString(), ((Immediate) src).getNumber().intValue());
						s = new LongValueOld(((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						/*
						 * symbolValueRegister .add(dest.toString(),
						 * symbolValueMemoryOperand
						 * .getMemoryOperandVal((X86MemoryOperand) src)); s =
						 * symbolValueMemoryOperand
						 * .getMemoryOperandVal((X86MemoryOperand) src);
						 */
						Value t = calculateValueMemoryOperand((X86MemoryOperand) src, ins);
						symbolValueRegister.add(dest.toString(), t);
					}
					// Bo sung them sau
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueRegisterPart.add(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegisterPart.add(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegisterPart.add(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegisterPart.add(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueRegisterPart.add(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueSegment.add(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueSegment.add(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueSegment.add(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueSegment.add(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueSegment.add(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				}
				if (d != null && s != null)
					symbolFlag.changeFlagWithADD(symbolValueRegister, symbolValueMemoryOperand, symbolStack, d, s);

				if (s instanceof LongValueOld && ((LongValueOld) s).getValue() == 0) {
					System.out.println("Error divide to 0");
					stop = true;
				}

				// newDest.addExprValue(SymbolicValue.ADD_EXPR, result);
			} else if (ins.getName().startsWith("inc")) {
				Operand dest = ins.getOperand1();
				if (dest.getClass().getSimpleName().equals("X86Register"))
					symbolValueRegister.add(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86RegisterPart"))
					symbolValueRegisterPart.add(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86SegmentRegister"))
					symbolValueSegment.add(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// System.out.println("Information of this X86MemoryOperand");
					// System.out.println(((X86MemoryOperand)dest).toString());
					// System.out.println(((X86MemoryOperand)dest).getDisplacement());
					// System.out.println(((X86MemoryOperand)dest).getScale());
					// System.out.println(((X86MemoryOperand)dest).);
					this.symbolValueMemoryOperand.add((X86MemoryOperand) dest, new HybridExp(new SymbolExp(
							((X86MemoryOperand) dest).toString()), "+", new LongValueOld(1)));
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
		case ADD:
			// Statement supported: add, inc
			if (ins.getName().startsWith("add")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value d = null;
				Value s = null;

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					d = symbolValueRegister.getRegVal(dest.toString());
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueRegister.add(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
						s = symbolValueRegister.getRegVal(src.toString());
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegister.add(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
						s = symbolValueRegisterPart.getRegVal(src.toString());
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegister.add(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
						s = symbolValueSegment.getRegVal(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegister.add(dest.toString(), ((Immediate) src).getNumber().intValue());
						s = new LongValueOld(((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						/*
						 * symbolValueRegister .add(dest.toString(),
						 * symbolValueMemoryOperand
						 * .getMemoryOperandVal((X86MemoryOperand) src)); s =
						 * symbolValueMemoryOperand
						 * .getMemoryOperandVal((X86MemoryOperand) src);
						 */
						Value t = calculateValueMemoryOperand((X86MemoryOperand) src, ins);
						symbolValueRegister.add(dest.toString(), t);
					}
					// Bo sung them sau
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueRegisterPart.add(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegisterPart.add(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegisterPart.add(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegisterPart.add(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueRegisterPart.add(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueSegment.add(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueSegment.add(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueSegment.add(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueSegment.add(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueSegment.add(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				}
				if (d != null && s != null)
					symbolFlag.changeFlagWithADD(symbolValueRegister, symbolValueMemoryOperand, symbolStack, d, s);

				// newDest.addExprValue(SymbolicValue.ADD_EXPR, result);
			} else if (ins.getName().startsWith("inc")) {
				Operand dest = ins.getOperand1();
				if (dest.getClass().getSimpleName().equals("X86Register"))
					symbolValueRegister.add(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86RegisterPart"))
					symbolValueRegisterPart.add(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86SegmentRegister"))
					symbolValueSegment.add(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// System.out.println("Information of this X86MemoryOperand");
					// System.out.println(((X86MemoryOperand)dest).toString());
					// System.out.println(((X86MemoryOperand)dest).getDisplacement());
					// System.out.println(((X86MemoryOperand)dest).getScale());
					// System.out.println(((X86MemoryOperand)dest).);
					this.symbolValueMemoryOperand.add((X86MemoryOperand) dest, new HybridExp(new SymbolExp(
							((X86MemoryOperand) dest).toString()), "+", new LongValueOld(1)));
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
						symbolValueRegister.add(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegister.add(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegister.add(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegister.add(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueRegister.add(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueRegisterPart.add(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegisterPart.add(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegisterPart.add(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegisterPart.add(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueRegisterPart.add(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueSegment.add(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueSegment.add(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueSegment.add(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueSegment.add(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueSegment.add(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				}

				// newDest.addExprValue(SymbolicValue.ADD_EXPR, result);
			} else if (ins.getName().startsWith("inc")) {
				Operand dest = ins.getOperand1();
				if (dest.getClass().getSimpleName().equals("X86Register"))
					symbolValueRegister.add(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86RegisterPart"))
					symbolValueRegisterPart.add(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86SegmentRegister"))
					symbolValueSegment.add(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// System.out.println("Information of this X86MemoryOperand");
					// System.out.println(((X86MemoryOperand)dest).toString());
					// System.out.println(((X86MemoryOperand)dest).getDisplacement());
					// System.out.println(((X86MemoryOperand)dest).getScale());
					// System.out.println(((X86MemoryOperand)dest).);
					this.symbolValueMemoryOperand.add((X86MemoryOperand) dest, new HybridExp(new SymbolExp(
							((X86MemoryOperand) dest).toString()), "+", new LongValueOld(1)));
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

		case AND:
			// Statement supported: and
			if (ins.getName().startsWith("and")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueRegister.and(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegister.and(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegister.and(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegister.and(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueRegister.and(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueRegisterPart.and(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegisterPart.and(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegisterPart.and(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegisterPart.and(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueRegisterPart.and(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueSegment.and(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueSegment.and(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueSegment.and(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueSegment.and(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueSegment.and(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
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
					Value tVal = calculateValueMemoryOperand((X86MemoryOperand) dest, ins);

					if (src.getClass().getSimpleName().equals("X86Register")) {
						Value val = symbolValueRegister.getRegVal(src.toString());
						// This is 32 bits so we have to divide it into 4 parts
						// and
						// assigns to 4 new memory operands
						if (val instanceof LongValueOld && t.getBase() == null && tVal instanceof LongValueOld) {
							long v = ((LongValueOld) val).getValue();
							v = BitVector.xor(v, ((LongValueOld) tVal).getValue());
							String x = Convert.longToHex(v, 32);
							int l = x.length();
							if (l < 8)
								return;
							symbolValueMemoryOperand.setSymbolMemoryOperandValue(t,
									new LongValueOld(Convert.hexToLong(x.substring(l - 2, l))));
							symbolValueMemoryOperand.setSymbolMemoryOperandValue(new X86MemoryOperand(t.getDataType(),
									t.getDisplacement() + 1),
									new LongValueOld(Convert.hexToLong(x.substring(l - 4, l - 2))));
							symbolValueMemoryOperand.setSymbolMemoryOperandValue(new X86MemoryOperand(t.getDataType(),
									t.getDisplacement() + 2),
									new LongValueOld(Convert.hexToLong(x.substring(l - 6, l - 4))));
							symbolValueMemoryOperand.setSymbolMemoryOperandValue(new X86MemoryOperand(t.getDataType(),
									t.getDisplacement() + 3),
									new LongValueOld(Convert.hexToLong(x.substring(l - 8, l - 6))));

						} else
							symbolValueMemoryOperand.setSymbolMemoryOperandValue(t, val);
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueMemoryOperand.mov((X86MemoryOperand) dest,
								symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueMemoryOperand.mov((X86MemoryOperand) dest,
								symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long x1 = ((Immediate) src).getNumber().intValue();
						if (tVal instanceof LongValueOld) {
							long v = ((LongValueOld) tVal).getValue();
							v = BitVector.xor(v, x1);
							symbolValueMemoryOperand.setSymbolMemoryOperandValue(t, v, ins);

						} else
							symbolValueMemoryOperand.xor((X86MemoryOperand) dest, new LongValueOld(x1));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueMemoryOperand.xor((X86MemoryOperand) dest,
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}

				} else if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (dest.toString().equals(src.toString())) {
						symbolValueRegister.mov(dest.toString(), new LongValueOld(0));
					} else if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueRegister.xor(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegister.xor(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegister.xor(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegister.xor(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueRegister.xor(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (dest.toString().equals(src.toString())) {
						symbolValueRegisterPart.mov(dest.toString(), new LongValueOld(0));
					} else if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueRegisterPart.xor(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegisterPart.xor(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegisterPart.xor(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegisterPart.xor(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueRegisterPart.xor(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (dest.toString().equals(src.toString())) {
						symbolValueSegment.mov(dest.toString(), new LongValueOld(0));
					} else if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueSegment.xor(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueSegment.xor(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueSegment.xor(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueSegment.xor(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueSegment.xor(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
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
						symbolValueRegister.sub(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegister.sub(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegister.sub(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegister.sub(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueRegister.sub(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueRegisterPart.sub(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegisterPart.sub(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegisterPart.sub(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegisterPart.sub(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueRegisterPart.sub(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueSegment.sub(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueSegment.sub(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueSegment.sub(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueSegment.sub(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueSegment.sub(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			} else if (ins.getName().startsWith("dec")) {
				// result = new SymbolicValue(1);
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
				Operand dest = ins.getOperand1();
				if (dest.getClass().getSimpleName().equals("X86Register"))
					symbolValueRegister.sub(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86RegisterPart"))
					symbolValueRegisterPart.sub(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86SegmentRegister"))
					symbolValueSegment.sub(dest.toString(), 1);
				else if (dest.getClass().getSimpleName().equals("X86MemoryOperand"))
					symbolValueMemoryOperand.sub((X86MemoryOperand) dest, new LongValueOld(1));
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
						symbolValueRegister.or(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegister.or(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegister.or(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegister.or(dest.toString(), ((Immediate) src).getNumber().intValue());
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueRegister.or(dest.toString(),
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueRegisterPart.or(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueRegisterPart.or(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueRegisterPart.or(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegisterPart.or(dest.toString(), ((Immediate) src).getNumber().intValue());
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						symbolValueSegment.or(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueSegment.or(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueSegment.or(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueSegment.or(dest.toString(), ((Immediate) src).getNumber().intValue());
					}
				}
				// symbolFlag.changeFlagWithOR(symbolValueRegister,
				// this.symbolValueMemoryOperand, symbolStack, dest, src);
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

				if (src == null || dest == null) {
					stop = true;
					return;
				}

				if (dest.getClass().getSimpleName().equals("X86Register")) {

					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						long x = (long) Math.pow(2, y);
						symbolValueRegister.divide(dest.toString(), x);
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						long x = (long) Math.pow(2, y);
						symbolValueRegisterPart.divide(dest.toString(), x);
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						long x = (long) Math.pow(2, y);
						symbolValueSegment.divide(dest.toString(), x);
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
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						// long x = (long) Math.pow(2, y);
						symbolValueRegister.rr(dest.toString(), y);
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						// long x = (long) Math.pow(2, y);
						symbolValueRegisterPart.rr(dest.toString(), y);
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						// long x = (long) Math.pow(2, y);
						symbolValueSegment.rr(dest.toString(), y);
					}
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
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						symbolValueRegister.rl(dest.toString(), y);
						// (long) Math.pow(2, ((Immediate)
						// src).getNumber().intValue()));
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						symbolValueRegisterPart.rl(dest.toString(), y);
						// (long) Math.rl(2, ((Immediate)
						// src).getNumber().intValue()));
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						int y = ((Immediate) src).getNumber().intValue();
						symbolValueSegment.rl(dest.toString(), y);
						// (long) Math.pow(2, ((Immediate)
						// src).getNumber().intValue()));
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
						symbolValueRegister.mul(dest.toString(), (long) Math.pow(2, 1));
					} else if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegister.mul(dest.toString(),
								(long) Math.pow(2, ((Immediate) src).getNumber().intValue()));
					}
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueRegisterPart.mul(dest.toString(),
								(long) Math.pow(2, ((Immediate) src).getNumber().intValue()));
					}
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueSegment.mul(dest.toString(),
								(long) Math.pow(2, ((Immediate) src).getNumber().intValue()));
					}
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			}
			break;
		case SMUL:
			if (ins.getName().startsWith("imul")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value eax = null;
				if (src.getClass().getSimpleName().equals("X86Register")) {
					eax = symbolValueRegister.getRegVal(src.toString());
					symbolValueRegister.setSymbolRegisterValue(src.toString(), new LongValueOld(0));
					// symbolValueRegister.set
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					eax = symbolValueRegisterPart.getRegVal(src.toString());
					symbolValueRegisterPart.setSymbolRegisterPartValue(src.toString(), new LongValueOld(0));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					eax = symbolValueSegment.getRegVal(src.toString());
					symbolValueSegment.setSymbolSegmentValue(src.toString(), new LongValueOld(0));
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					eax = symbolValueMemoryOperand.getMemoryOperandVal(src.toString());
					symbolValueMemoryOperand.setSymbolMemoryOperandValue((X86MemoryOperand) src, new LongValueOld(0));
				}

				// Exp eax = symbolValueRegister.getRegVal(src.toString());
				if (dest.getClass().getSimpleName().equals("X86Register")) {
					symbolValueRegister.mul(dest.toString(), eax);
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					symbolValueRegisterPart.mul(dest.toString(), eax);
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					symbolValueSegment.mul(dest.toString(), eax);
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					symbolValueMemoryOperand.mul((X86MemoryOperand) dest, eax);
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			}
			break;
		case UMUL:
			if (ins.getName().startsWith("unsignedMul")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value op = null;
				if (src.getClass().getSimpleName().equals("X86Register")) {
					op = symbolValueRegister.getRegVal(src.toString());
					// symbolValueRegister.setSymbolRegisterValue(dest.toString(),
					// new ValueLongExp(0));
					// symbolValueRegister.set
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					op = symbolValueRegisterPart.getRegVal(src.toString());
					// symbolValueRegisterPart.setSymbolRegisterPartValue(
					// src.toString(), new ValueLongExp(0));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					op = symbolValueSegment.getRegVal(src.toString());
					// symbolValueSegment.setSymbolSegmentValue(src.toString(),
					// new ValueLongExp(0));
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					op = symbolValueMemoryOperand.getMemoryOperandVal(src.toString());
					// symbolValueMemoryOperand.setSymbolMemoryOperandValue(
					// (X86MemoryOperand) src, new ValueLongExp(0));
				}

				// Exp eax = symbolValueRegister.getRegVal(src.toString());
				if (dest.getClass().getSimpleName().equals("X86Register")) {
					symbolValueRegister.mul(dest.toString(), op);
				} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
					symbolValueRegisterPart.mul(dest.toString(), op);
				} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					symbolValueSegment.mul(dest.toString(), op);
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					symbolValueMemoryOperand.mul((X86MemoryOperand) dest, op);
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			}
			break;
		default:
			if (ins.getName().startsWith("unsignedDiv")) {

			}
			System.out.println("Arithmetic statement not supported: " + ins.getName());
			// halt_status = FAILED;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// ins.toString(startAddress, symFinder));
			// return null;
		}

	}

	private void X86CallInterprete(X86CallInstruction inst) {
		Operand dest = inst.getOperand1();
		Value r = null;
		AbsoluteAddress returnAddress = new AbsoluteAddress(targetTemp.getValue() + inst.getSize());

		// if (this.targetTemp.getValueOperand() == 0)
		// System.out.println("Debug");
		// System.out.println("Instruction: " + inst.getName());

		// call structure: push call next eip to stack, jump to call address
		// first operand
		if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
			r = new LongValueOld(((AbsoluteAddress) dest).getValue());
		} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
			r = new LongValueOld(((X86PCRelativeAddress) dest).getEffectiveValue(targetTemp.getValue()));

		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			// returnAddress = (prevState.getRegValue(((X86Register)
			// dest).toString())).clone();
			r = symbolValueRegister.getRegVal(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			X86MemoryOperand d = (X86MemoryOperand) dest;
			r = this.calculateValueMemoryOperand(d, inst);
		}

		if (r != null && r instanceof LongValueOld) {
			if (this.targetIndirect.getValue() == this.targetTemp.getValue())
				this.evaluateValue = ((LongValueOld) r).getValue();

			if (stubLibrary != null
					&& contain(((Win32StubLibrary) stubLibrary).getAddressStubMap(), new AbsoluteAddress(
							((LongValueOld) r).getValue()))) {
				// String funcName
				// SystemCallStub.executeSymbolic(funcName);

				System.out.println("Address:" + targetTemp.toString());
				if (APIStub.executeSymbolic(funcName, this, inst)) {
					funcName = "";
					retAPIAddr = new AbsoluteAddress(targetTemp.getValue() + inst.getSize());
				} else
					System.out.println("Symbolic Execution of System Call failed:" + funcName);

				if (this.targetTemp.getValue() == targetIndirect.getValue())
					this.evaluateValue = returnAddress.getValue();

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

			symbolValueRegister.sub("%esp", new LongValueOld(4));
			symbolStack.push(new LongValueOld(returnAddress.getValue()));
		}

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

	private void X86ConditionalJumpInterprete(X86CondJmpInstruction inst) {
		String instName = inst.getName();
		// System.out.println("Conditional Jump:" + instName + " " +
		// dest.toString());
		/*
		 * if (neg.get(targetTemp).getValueOperand() ==
		 * this.addrTraceList.get(0) .getValueOperand())
		 * this.formulas.getTop().setOperator(
		 * CondJump.getReserCondJump((inst.getName()))); else
		 * this.formulas.getTop().setOperator(
		 * CondJump.getCondJump((inst.getName())));
		 */
		boolean reCond = false;

		pcValue.add("aFlag", 0);
		pcValue.add("cFlag", 0);
		pcValue.add("dFlag", 0);
		pcValue.add("iFlag", 0);
		pcValue.add("oFlag", 0);
		pcValue.add("pFlag", 0);
		pcValue.add("sFlag", 0);
		pcValue.add("tFlag", 0);
		pcValue.add("zFlag", 0);

		if (neg != null && !neg.isEmpty() && neg.containsKey(targetTemp)
				&& neg.get(targetTemp).getValue() == this.addrTraceList.get(0).getValue()) {
			// instName = reverseConditionJump(instName);
			reCond = true;
			// System.out.println("Reverse Conditional Jump:" + instName);
		}
		// Operand dest = inst.getOperand1();

		// structure: create new work associated with condition-false case
		// jump to condition-true case
		// add state condition into each trace
		// SymbolicCondition compareStatus = prevState.getCompareStatus();
		// we accept loop not to be constrained by compareStatus
		if (inst.getName().equals("loop")) {
			// Dang xu li, chua hoan hao
			// Se check sau voi 1 so truong hop cu the
			symbolValueRegister.sub("%ecx", new LongValueOld(1));
			Value ecx = symbolValueRegister.getRegVal("%ecx");

			if (reCond) {
				if (ecx instanceof LongValueOld) {
					long e = ((LongValueOld) ecx).getValue();

					if (e <= 0)
						// System.out.println("Loop Right")
						;
					else {
						// System.out.println("Debug Loop");
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
						// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
						// MAX_NUM_LOOP_EACH
						) {
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
				} else
					formulaList.add(new Formula(ecx, new BooleanExp(0), "=="));
			} else {
				if (ecx instanceof LongValueOld) {
					long e = ((LongValueOld) ecx).getValue();

					if (e > 0)
						// System.out.println("Loop Right")
						;
					else {
						// System.out.println("Debug Loop");
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
						// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
						// MAX_NUM_LOOP_EACH
						) {
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
					Value l1 = new HybridBooleanExp(ecx, new BooleanExp(0), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			}
		} else if (instName.equals("ja") || instName.equals("jnbe")) {
			// if
			// ((CF) = 0) or ((ZF) = 0)
			// Not finished
			Value l1 = new HybridExp(symbolFlag.getcFlag(), "==", new BooleanExp(0));
			Value l2 = new HybridExp(symbolFlag.getzFlag(), "==", new BooleanExp(0));

			if (reCond) {
				// Exp l3 = new OtherBooleanExp(l1, l2, "or");
				// formulaList.add(new Formula(l3, "not"));
				l1 = new HybridExp(symbolFlag.getcFlag(), "==", new BooleanExp(1));
				l2 = new HybridExp(symbolFlag.getzFlag(), "==", new BooleanExp(1));
				// Exp l3 = new OtherBooleanExp(l1, l2, "and");
				formulaList.add(new Formula(l1, l2, "or"));
			} else
				formulaList.add(new Formula(l1, l2, "or"));
		} else if (instName.equals("jae") || instName.equals("jnb")) {
			// if
			// ((CF) = 0)
			if (reCond) {
				if (symbolFlag.getcFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
						// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
						// MAX_NUM_LOOP_EACH
						) {
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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

						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getcFlag(), new BooleanExp(0), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getcFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
							/*
							 * this.addrTraceList.pushList(cfg.getCFGVertices()
							 * .getVertex(this.targetTemp).getListLoop(0));
							 */
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					formulaList.add(new Formula(symbolFlag.getcFlag(), new BooleanExp(0), "=="));
				}
				// formulaList.add(new Formula(symbolFlag.getzFlag(), new
				// ConstantBooleanExp(0), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanExp(symbolFlag.getcFlag(), new BooleanExp(0),
			 * "=="); formulaList.add(new Formula(l1, "not")); } else
			 * formulaList.add(new Formula(symbolFlag.getcFlag(), new
			 * BooleanExp(0), "=="));
			 */
			// var.add("cFlag", 0);
		} else if (instName.equals("jb") || instName.equals("jnae")) {
			// if
			// ((CF) = 1)
			if (reCond) {
				if (symbolFlag.getcFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getcFlag(), new BooleanExp(1), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getcFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (!z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						// else
						// this.stop = true;
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else
					formulaList.add(new Formula(symbolFlag.getcFlag(), new BooleanExp(1), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanExp(symbolFlag.getcFlag(), new BooleanExp(1),
			 * "=="); formulaList.add(new Formula(l1, "not")); } else
			 * formulaList.add(new Formula(symbolFlag.getcFlag(), new
			 * BooleanExp(1), "=="));
			 */
		} else if (instName.equals("jbe") || instName.equals("jna")) {
			// if
			// ((CF) = 1) or ((ZF) = 1)
			// Not finished
			HybridExp l1 = new HybridExp(symbolFlag.getcFlag(), "==", new BooleanExp(1));
			HybridExp l2 = new HybridExp(symbolFlag.getzFlag(), "==", new BooleanExp(1));

			if (reCond) {
				if (symbolFlag.getcFlag() instanceof BooleanExp && symbolFlag.getzFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getzFlag()).getValue();
					boolean c = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z || c) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop())
							this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
				} else {
					Value l3 = new HybridBooleanExp(l1, l2, "or");
					formulaList.add(new Formula(l3, "not"));
				}
			} else {
				if (symbolFlag.getcFlag() instanceof BooleanExp && symbolFlag.getzFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getzFlag()).getValue();
					boolean c = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!(z || c)) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop())
							this.addrTraceList.pushList(cfg.getCFGVertices().getVertex(this.targetTemp).getListLoop(0));
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
				} else {
					formulaList.add(new Formula(l1, l2, "or"));
				}
			}
			/*
			 * formulaList.add(new Formula(symbolFlag.getcFlag(), new
			 * ConstantBooleanExp(1), "=="));
			 */
		} else if (instName.equals("jc")) {
			// if
			// ((CF) = 1)
			if (reCond) {
				if (symbolFlag.getcFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getcFlag(), new BooleanExp(1), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getcFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (!z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						// else
						// this.stop = true;
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else
					formulaList.add(new Formula(symbolFlag.getcFlag(), new BooleanExp(1), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanExp(symbolFlag.getcFlag(), new BooleanExp(1),
			 * "=="); formulaList.add(new Formula(l1, "not")); } else
			 * formulaList.add(new Formula(symbolFlag.getcFlag(), new
			 * BooleanExp(1), "=="));
			 */
		} else if (instName.equals("jcxz")) {
			// if
			// ((CF) = 0)
			if (reCond) {
				if (symbolFlag.getcFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
						// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
						// MAX_NUM_LOOP_EACH
						) {
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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

						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getcFlag(), new BooleanExp(0), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getcFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
							/*
							 * this.addrTraceList.pushList(cfg.getCFGVertices()
							 * .getVertex(this.targetTemp).getListLoop(0));
							 */
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					formulaList.add(new Formula(symbolFlag.getcFlag(), new BooleanExp(0), "=="));
				}
				// formulaList.add(new Formula(symbolFlag.getzFlag(), new
				// ConstantBooleanExp(0), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanExp(symbolFlag.getcFlag(), new BooleanExp(0),
			 * "=="); formulaList.add(new Formula(l1, "not")); } else
			 * formulaList.add(new Formula(symbolFlag.getcFlag(), new
			 * BooleanExp(0), "=="));
			 */
		} else if (instName.equals("je") || instName.equals("jz")) {
			// if
			// ((ZF) = 1)

			// Truong hop dac biet, se xu li sau
			if (this.program.getFileName().equals("Virus.Win32.Aztec.01")
					&& (
					// (this.targetIndirect.toString().equals("0x00401135") &&
					// this.targetTemp.toString().equals("0x004010ce"))
					// || (this.targetIndirect.toString().equals("0x004012d0")
					// && this.targetTemp.toString().equals("0x0040114e"))
					// || (this.targetIndirect.toString().equals("0x004012bd")
					// && this.targetTemp.toString().equals("0x00401168"))
					// || (this.targetIndirect.toString().equals("0x004012d6")
					// && this.targetTemp.toString().equals("0x00401168"))

					// || (this.targetIndirect.toString().equals("0x004010b1")
					// && this.targetTemp.toString().equals("0x004010ce"))
					(this.targetIndirect.toString().equals("0x0040134b") && this.targetTemp.toString().equals(
							"0x00401198"))
							|| (this.targetIndirect.toString().equals("0x0040134b") && this.targetTemp.toString()
									.equals("0x004011a5"))
							|| (this.targetIndirect.toString().equals("0x004012bd") && this.targetTemp.toString()
									.equals("0x004011a5"))
							|| (this.targetIndirect.toString().equals("0x004012dc") && this.targetTemp.toString()
									.equals("0x004011a5"))
							|| (this.targetIndirect.toString().equals("0x004012d0") && this.targetTemp.toString()
									.equals("0x004011a5"))
							|| (this.targetIndirect.toString().equals("0x004012d6") && this.targetTemp.toString()
									.equals("0x004011a5"))
							|| (this.targetIndirect.toString().equals("0x004012d6") && this.targetTemp.toString()
									.equals("0x0040114e")) || (this.targetIndirect.toString().equals("0x0040111f") && this.targetTemp
							.toString().equals("0x0040114e"))))
				return;

			if (reCond) {
				if (symbolFlag.getzFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getzFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							// Bao dam rang duong di them vao khong nam san
							// trong duong di san co
							if (this.addrTraceList.get(0).getValue() == cfg.getCFGVertices().getVertex(this.targetTemp)
									.getListLoop(0).get(0).getValue()) {
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getzFlag(), new BooleanExp(1), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getzFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getzFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien

					if (!z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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

						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						// else
						// this.stop = true;
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
				} else
					formulaList.add(new Formula(symbolFlag.getzFlag(), new BooleanExp(1), "=="));
			}
		} else if (instName.equals("jg") || instName.equals("jnle")) {
			// if
			// ((SF) = (OF)) and ((ZF) = 0)
			Value l1 = new HybridBooleanExp(symbolFlag.getzFlag(), new BooleanExp(0), "==");
			Value l2 = new HybridBooleanExp(symbolFlag.getsFlag(), symbolFlag.getoFlag(), "==");

			if (reCond) {
				Value l3 = new HybridBooleanExp(l1, l2, "and");
				formulaList.add(new Formula(l3, "not"));
			} else
				formulaList.add(new Formula(l1, l2, "and"));

			// formulaList.add(new Formula(symbolFlag.getzFlag(), new
			// ConstantBooleanExp(0), "=="));
			// formulaList.add(new Formula(symbolFlag.getsFlag(),
			// symbolFlag.getoFlag(), "=="));
		} else if (instName.equals("jge") || instName.equals("jnl")) {
			// if
			// (SF) = (OF)

			if (reCond) {
				Value l1 = new HybridBooleanExp(symbolFlag.getsFlag(), symbolFlag.getoFlag(), "==");
				formulaList.add(new Formula(l1, "not"));
			} else
				formulaList.add(new Formula(symbolFlag.getsFlag(), symbolFlag.getoFlag(), "=="));
		} else if (instName.equals("jl") || instName.equals("jnge")) {
			// if
			// (SF) ≠ (OF)
			// formulaList.add(new Formula(symbolFlag.getsFlag(),
			// symbolFlag.getoFlag(), "!="));
			if (reCond) {
				Value l1 = new HybridBooleanExp(symbolFlag.getsFlag(), symbolFlag.getoFlag(), "!=");
				formulaList.add(new Formula(l1, "not"));
			} else
				formulaList.add(new Formula(symbolFlag.getsFlag(), symbolFlag.getoFlag(), "!="));
		} else if (instName.equals("jle") || instName.equals("jng")) {
			// if
			// ((SF) ≠ (OF)) or ((ZF) = 1)
			// Focus on ZF = 1
			// not finished
			// Change due to Demo1
			// OtherExp l1 = new OtherExp(symbolFlag.getzFlag(), new
			// ConstantBooleanExp(1), "=");
			Value l1 = symbolFlag.getzFlag();
			Value l2 = new HybridBooleanExp(symbolFlag.getsFlag(), symbolFlag.getoFlag(), "xor");
			// OtherExp l3 = new OtherExp(l2, new ConstantLongExp(0), "!=");

			if (reCond) {
				Value l3 = new HybridBooleanExp(l1, l2, "or");
				formulaList.add(new Formula(l3, "not"));
			} else
				formulaList.add(new Formula(l1, l2, "or"));

			// formulaList.add(new Formula(symbolFlag.getzFlag(), new
			// ConstantBooleanExp(1), "="));

			/*
			 * OtherExp l1 = new OtherExp(symbolFlag.getcFlag(), "!=", new
			 * ConstantBooleanExp(0)); OtherExp l2 = new
			 * OtherExp(symbolFlag.getzFlag(), "!=", new ConstantBooleanExp(0));
			 * formulaList.add(new Formula(l1, l2, "and"));
			 */
		} else if (instName.equals("jnc")) {
			// if
			// ((CF) = 0)
			if (reCond) {
				if (symbolFlag.getcFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
						// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
						// MAX_NUM_LOOP_EACH
						) {
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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

						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getcFlag(), new BooleanExp(0), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getcFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getcFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
							/*
							 * this.addrTraceList.pushList(cfg.getCFGVertices()
							 * .getVertex(this.targetTemp).getListLoop(0));
							 */
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					formulaList.add(new Formula(symbolFlag.getcFlag(), new BooleanExp(0), "=="));
				}
				// formulaList.add(new Formula(symbolFlag.getzFlag(), new
				// ConstantBooleanExp(0), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanExp(symbolFlag.getcFlag(), new BooleanExp(0),
			 * "=="); formulaList.add(new Formula(l1, "not")); } else
			 * formulaList.add(new Formula(symbolFlag.getcFlag(), new
			 * BooleanExp(0), "=="));
			 */
			// formulaList.add(new Formula(symbolFlag.getcFlag(), new
			// ConstantBooleanExp(0), "=="));
		} else if (instName.equals("jne") || instName.equals("jnz")) {
			// if
			// ((ZF) = 0)
			if (this.program.getFileName().equals("Virus.Win32.Aztec.01")
					&& (
					// (this.targetIndirect.toString().equals("0x00401135") &&
					// this.targetTemp.toString().equals("0x004010ce"))
					// || (this.targetIndirect.toString().equals("0x004012d0")
					// && this.targetTemp.toString().equals("0x0040114e"))
					// || (this.targetIndirect.toString().equals("0x004012bd")
					// && this.targetTemp.toString().equals("0x00401168"))
					// || (this.targetIndirect.toString().equals("0x004012d6")
					// && this.targetTemp.toString().equals("0x00401168"))
					// || (this.targetIndirect.toString().equals("0x004012d6")
					// && this.targetTemp.toString().equals("0x0040114e"))
					// || (this.targetIndirect.toString().equals("0x004010b1")
					// && this.targetTemp.toString().equals("0x004010ce"))
					// || (this.targetIndirect.toString().equals("0x004010b1")
					// && this.targetTemp.toString().equals("0x004010af"))
					(this.targetIndirect.toString().equals("0x004013b4") && this.targetTemp.toString().equals(
							"0x00401198"))
							|| (this.targetIndirect.toString().equals("0x004013c0") && this.targetTemp.toString()
									.equals("0x00401198"))
							|| (this.targetIndirect.toString().equals("0x004013c6") && this.targetTemp.toString()
									.equals("0x00401198"))
							|| (this.targetIndirect.toString().equals("0x004012a5") && this.targetTemp.toString()
									.equals("0x00401198"))
							|| (this.targetIndirect.toString().equals("0x004012b1") && this.targetTemp.toString()
									.equals("0x00401198")) || (this.targetIndirect.toString().equals("0x0040111f") && this.targetTemp
							.toString().equals("0x00401198"))))
				return;

			if (reCond) {
				if (symbolFlag.getzFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getzFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
						// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
						// MAX_NUM_LOOP_EACH
						) {
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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

						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getzFlag(), new BooleanExp(0), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getzFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getzFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
							/*
							 * this.addrTraceList.pushList(cfg.getCFGVertices()
							 * .getVertex(this.targetTemp).getListLoop(0));
							 */
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
				} else {
					formulaList.add(new Formula(symbolFlag.getzFlag(), new BooleanExp(0), "=="));
				}
				// formulaList.add(new Formula(symbolFlag.getzFlag(), new
				// ConstantBooleanExp(0), "=="));
			}
		} else if (instName.equals("jno")) {
			// if
			// ((OF) = 0)
			if (reCond) {
				if (symbolFlag.getoFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getoFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
						// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
						// MAX_NUM_LOOP_EACH
						) {
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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

						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getoFlag(), new BooleanExp(0), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getoFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getoFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
							/*
							 * this.addrTraceList.pushList(cfg.getCFGVertices()
							 * .getVertex(this.targetTemp).getListLoop(0));
							 */
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					formulaList.add(new Formula(symbolFlag.getoFlag(), new BooleanExp(0), "=="));
				}
				// formulaList.add(new Formula(symbolFlag.getzFlag(), new
				// ConstantBooleanExp(0), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanExp(symbolFlag.getoFlag(), new BooleanExp(0),
			 * "=="); formulaList.add(new Formula(l1, "not")); } else
			 * formulaList.add(new Formula(symbolFlag.getoFlag(), new
			 * BooleanExp(0), "=="));
			 */
			// formulaList.add(new Formula(symbolFlag.getoFlag(), new
			// ConstantBooleanExp(0), "=="));
		} else if (instName.equals("jns")) {
			// if
			// ((SF) = 0)
			if (reCond) {
				if (symbolFlag.getsFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getsFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
						// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
						// MAX_NUM_LOOP_EACH
						) {
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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

						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getsFlag(), new BooleanExp(0), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getsFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getsFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
							/*
							 * this.addrTraceList.pushList(cfg.getCFGVertices()
							 * .getVertex(this.targetTemp).getListLoop(0));
							 */
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					formulaList.add(new Formula(symbolFlag.getsFlag(), new BooleanExp(0), "=="));
				}
				// formulaList.add(new Formula(symbolFlag.getzFlag(), new
				// ConstantBooleanExp(0), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanExp(symbolFlag.getsFlag(), new BooleanExp(0),
			 * "=="); formulaList.add(new Formula(l1, "not")); } else
			 * formulaList.add(new Formula(symbolFlag.getsFlag(), new
			 * BooleanExp(0), "=="));
			 */
			// formulaList.add(new Formula(symbolFlag.getsFlag(), new
			// ConstantBooleanExp(0), "=="));
		} else if (instName.equals("jnp") || instName.equals("jpo")) {
			// if
			// ((PF) = 0)
			if (reCond) {
				if (symbolFlag.getpFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getpFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()
						// && loopTotal <= MAX_NUM_LOOP_TOTAL && loopEach <=
						// MAX_NUM_LOOP_EACH
						) {
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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

						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getpFlag(), new BooleanExp(0), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getpFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getpFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						if (cfg.getCFGVertices().getVertex(this.targetTemp).isLoop()) {
							/*
							 * this.addrTraceList.pushList(cfg.getCFGVertices()
							 * .getVertex(this.targetTemp).getListLoop(0));
							 */
							if (loopTotal > MAX_NUM_LOOP_TOTAL) {
								System.out.println("Outside Loop Total: " + loopTotal + " Address: "
										+ targetTemp.toString());
								this.stop = true;
								return;
							}

							if (loopEach > MAX_NUM_LOOP_EACH) {
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					formulaList.add(new Formula(symbolFlag.getpFlag(), new BooleanExp(0), "=="));
				}
				// formulaList.add(new Formula(symbolFlag.getzFlag(), new
				// ConstantBooleanExp(0), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanExp(symbolFlag.getpFlag(), new BooleanExp(0),
			 * "=="); formulaList.add(new Formula(l1, "not")); } else
			 * formulaList.add(new Formula(symbolFlag.getpFlag(), new
			 * BooleanExp(0), "=="));
			 */
			// formulaList.add(new Formula(symbolFlag.getpFlag(), new
			// ConstantBooleanExp(0), "=="));
		} else if (instName.equals("jo")) {
			// if
			// ((OF) = 1)
			if (reCond) {
				if (symbolFlag.getoFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getoFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getoFlag(), new BooleanExp(1), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getoFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getoFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (!z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						// else
						// this.stop = true;
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else
					formulaList.add(new Formula(symbolFlag.getoFlag(), new BooleanExp(1), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanExp(symbolFlag.getoFlag(), new BooleanExp(1),
			 * "=="); formulaList.add(new Formula(l1, "not")); } else
			 * formulaList.add(new Formula(symbolFlag.getoFlag(), new
			 * BooleanExp(1), "=="));
			 */
			// formulaList.add(new Formula(symbolFlag.getoFlag(), new
			// ConstantBooleanExp(1), "=="));
		} else if (instName.equals("jp") || instName.equals("jpe")) {
			// if
			// ((PF) = 1)
			if (reCond) {
				if (symbolFlag.getpFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getpFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getpFlag(), new BooleanExp(1), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getpFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getpFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (!z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						// else
						// this.stop = true;
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else
					formulaList.add(new Formula(symbolFlag.getpFlag(), new BooleanExp(1), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanExp(symbolFlag.getpFlag(), new BooleanExp(1),
			 * "=="); formulaList.add(new Formula(l1, "not")); } else
			 * formulaList.add(new Formula(symbolFlag.getpFlag(), new
			 * BooleanExp(1), "=="));
			 */
			// formulaList.add(new Formula(symbolFlag.getpFlag(), new
			// ConstantBooleanExp(0), "=="));
		} else if (inst.getName().equals("js")) {
			// if
			// ((SF) = 1)
			// var.add("sFlag", 0);
			if (reCond) {
				if (symbolFlag.getsFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getsFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = new HybridBooleanExp(symbolFlag.getsFlag(), new BooleanExp(1), "==");
					formulaList.add(new Formula(l1, "not"));
				}
			} else {
				if (symbolFlag.getsFlag() instanceof BooleanExp) {
					boolean z = ((BooleanExp) symbolFlag.getsFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (!z) {
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
								System.out.println("Outside Loop Each: " + loopEach + " Address: "
										+ targetTemp.toString());
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
						}
						// this.specialTrace = true;
						// targetSpecial = targetTemp;
						else
							this.stop = true;
						// System.out.println("Debug");
					} else
						// else
						// this.stop = true;
						System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else
					formulaList.add(new Formula(symbolFlag.getsFlag(), new BooleanExp(1), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanExp(symbolFlag.getsFlag(), new BooleanExp(1),
			 * "=="); formulaList.add(new Formula(l1, "not")); } else
			 * formulaList.add(new Formula(symbolFlag.getsFlag(), new
			 * BooleanExp(1), "=="));
			 */
			// formulaList.add(new Formula(symbolFlag.getsFlag(), new
			// ConstantBooleanExp(1), "=="));
		}
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

	private void X86JumpInterprete(X86JmpInstruction inst) {
		Operand dest = inst.getOperand1();
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
			Value t = symbolValueRegister.getRegVal(dest.toString());
			if (t instanceof LongValueOld) {
				jumpAddress = new AbsoluteAddress(((LongValueOld) t).getValue());
			}

		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			jumpAddress = new AbsoluteAddress(((X86MemoryOperand) dest).getDisplacement());
			System.out.println("MemoryOperand:" + jumpAddress.toString());
		}

		if (stubLibrary != null && contain(((Win32StubLibrary) stubLibrary).getAddressStubMap(), targetTemp)) {
			// String funcName
			// SystemCallStub.executeSymbolic(funcName);
			System.out.println("Address:" + targetTemp.toString());
			if (APIStub.executeSymbolic(funcName, this, inst)) {
				funcName = "";
			} else
				System.out.println("Symbolic Execution of System Call failed:" + funcName);
			this.evaluateValue = retAPIAddr.getValue();
		} else {
			if (jumpAddress != null && this.targetTemp.getValue() == this.targetIndirect.getValue())
				this.evaluateValue = jumpAddress.getValue();
			else
				this.evaluateValue = Long.MIN_VALUE;

		}

	}

	private long getValue(String dest, long val) {
		long r = val;
		if (r < 0)
			r += Math.pow(2, 32);
		if (dest.contains("al") || dest.contains("bl") || dest.contains("cl") || dest.contains("dl")
				|| dest.contains("ah") || dest.contains("bh") || dest.contains("ch") || dest.contains("dh"))
			r = (long) (r % Math.pow(2, 8));
		else if (dest.equals("%ax") || dest.equals("%bx") || dest.equals("%cx") || dest.equals("%dx"))
			r = (long) (r % Math.pow(2, 16));
		return r;
	}

	private void X86MoveInterprete(X86MoveInstruction inst) {
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();

		// System.out.println("Instruction: " + inst.getName());
		// SymbolicValue toMoveVal = null;

		// if (src.getClass().getSimpleName().equals("Immediate")) {
		// toMoveVal = new SymbolicValue(((Immediate)
		// src).getNumber().intValue());
		// } else if (src.getClass().getSimpleName().equals("X86MemoryOperand"))
		// {
		// toMoveVal = prevState.getMemoryValue((X86MemoryOperand) src);
		// if (toMoveVal == null) {
		// halt_status = MISSING_MEMORY;
		// memoryAddress = (X86MemoryOperand) src;
		// SymbolicExecution.programTrace.setVisited(startAddress, null,
		// inst.toString(startAddress, symFinder));
		// return null;
		// }
		// toMoveVal = toMoveVal.clone();
		// } else if (src.getClass().getSimpleName().equals("X86Register")) {
		// toMoveVal = prevState.getRegValue(((X86Register)
		// src).toString()).clone();
		// }

		if (inst.getName().startsWith("mov")) {
			// normal move
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// prevState.getMemoryValues().put((MemoryOperand) dest,
				// toMoveVal);
				X86MemoryOperand y = (X86MemoryOperand) dest;
				// Xu li truong hop mov fs:0, esp
				// Khi do se tac dong den SEH
				boolean b = false;
				if (y.getDisplacement() == 0 && y.getBase() == null)
					b = true;
				else {
					if (y.getBase() != null) {
						Value v = symbolValueRegister.getRegVal(y.getBase().toString());
						if (v instanceof LongValueOld)
							b = ((LongValueOld) v).getValue() == 0;
					}
				}

				if (y.getSegmentRegister() != null && y.getSegmentRegister().toString() == "%fs" && b) {
					System.out.println("SEH Exploit:" + inst.toString());
					if (src.getClass().getSimpleName().equals("X86Register")) {
						if (((X86Register) src).toString().equals("%esp")) {
							system.getSEHHandler().getStart()
									.setNextSEHRecord(((LongValueOld) symbolStack.get(0)).getValue());
							system.getSEHHandler().getStart()
									.setSEHHandler(((LongValueOld) symbolStack.get(4)).getValue());
						}
					}
				} else {
					X86MemoryOperand t = calculateMemoryOperand((X86MemoryOperand) dest);

					if (src.getClass().getSimpleName().equals("X86Register")) {
						Value val = symbolValueRegister.getRegVal(src.toString());
						// This is 32 bits so we have to divide it into 4 parts
						// and
						// assigns to 4 new memory operands
						if (val instanceof LongValueOld && t.getBase() == null) {
							long v = ((LongValueOld) val).getValue();
							String x = Convert.longToHex(v, 32);
							int l = x.length();
							if (l < 8)
								return;
							symbolValueMemoryOperand.setSymbolMemoryOperandValue(t,
									new LongValueOld(Convert.hexToLong(x.substring(l - 2, l))));
							symbolValueMemoryOperand.setSymbolMemoryOperandValue(new X86MemoryOperand(t.getDataType(),
									t.getDisplacement() + 1),
									new LongValueOld(Convert.hexToLong(x.substring(l - 4, l - 2))));
							symbolValueMemoryOperand.setSymbolMemoryOperandValue(new X86MemoryOperand(t.getDataType(),
									t.getDisplacement() + 2),
									new LongValueOld(Convert.hexToLong(x.substring(l - 6, l - 4))));
							symbolValueMemoryOperand.setSymbolMemoryOperandValue(new X86MemoryOperand(t.getDataType(),
									t.getDisplacement() + 3),
									new LongValueOld(Convert.hexToLong(x.substring(l - 8, l - 6))));

						} else
							symbolValueMemoryOperand.setSymbolMemoryOperandValue(t, val);
					} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
						symbolValueMemoryOperand.mov((X86MemoryOperand) dest,
								symbolValueRegisterPart.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						symbolValueMemoryOperand.mov((X86MemoryOperand) dest,
								symbolValueSegment.getRegVal(src.toString()));
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						symbolValueMemoryOperand.mov((X86MemoryOperand) dest, new LongValueOld(((Immediate) src)
								.getNumber().intValue()));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						symbolValueMemoryOperand.mov((X86MemoryOperand) dest,
								symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
					}

				}
			} else if (dest.getClass().getSimpleName().equals("X86Register")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					symbolValueRegister.mov(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					symbolValueRegister.mov(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					symbolValueRegister.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					symbolValueRegister.mov(dest.toString(), ((Immediate) src).getNumber().intValue());
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					X86MemoryOperand t = (X86MemoryOperand) src;
					long desp = t.getDisplacement();
					// Xu ly cho van de MemoryOperand la [esp + 24]
					if (t.getBase() != null && t.getBase().toString() == "%esp") {
						symbolValueRegister.mov(dest.toString(), symbolStack.get(desp));
					} else if (t.getBase() != null) {
						// Xy li cho van de MemoryOperand la [eax + 0x401201]
						Value v = symbolValueRegister.getRegVal(t.getBase().toString());

						if (v instanceof LongValueOld) {
							symbolValueRegister.mov(
									dest.toString(),
									calculateValueMemoryOperand(new X86MemoryOperand(t.getDataType(), desp
											+ ((LongValueOld) v).getValue()), inst));
						}

					} else
						// Xu li cho van de MemoryOperand la 0x401020
						symbolValueRegister.mov(dest.toString(),
								calculateValueMemoryOperand(new X86MemoryOperand(t.getDataType(), desp), inst));
				}
			} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					symbolValueRegisterPart.mov(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					symbolValueRegisterPart.mov(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					symbolValueRegisterPart.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					int v = ((Immediate) src).getNumber().intValue();
					v = (int) getValue(dest.toString(), v);
					symbolValueRegisterPart.mov(dest.toString(), v);
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					symbolValueRegisterPart.mov(dest.toString(),
							symbolValueMemoryOperand.getMemoryOperandValByte((X86MemoryOperand) src));
				}

			} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					symbolValueSegment.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					symbolValueSegment.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					symbolValueSegment.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					symbolValueSegment.mov(dest.toString(), ((Immediate) src).getNumber().intValue());
				}
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
			} else if (dest.getClass().getSimpleName().equals("X86Register")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					Value t = symbolValueRegister.getRegVal(dest.toString());
					symbolValueRegister.mov(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
					symbolValueRegister.mov(src.toString(), t);
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					// symbolValueRegister.mov(dest.toString(),
					// symbolValueRegisterPart.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					Value t = symbolValueRegister.getRegVal(dest.toString());
					symbolValueRegister.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					symbolValueSegment.mov(src.toString(), t);
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					// symbolValueRegister.mov(dest.toString(), ((Immediate)
					// src).getNumber().intValue());
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					Value t = symbolValueRegister.getRegVal(dest.toString());
					symbolValueRegister.mov(dest.toString(),
							symbolValueMemoryOperand.getMemoryOperandVal(src.toString()));
					symbolValueMemoryOperand.mov((X86MemoryOperand) src, t);
				}
			} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					// symbolValueRegisterPart.mov(dest.toString(),
					// symbolValueRegister.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					Value t = symbolValueRegisterPart.getRegVal(dest.toString());
					symbolValueRegisterPart.mov(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
					symbolValueRegisterPart.mov(src.toString(), t);
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					Value t = symbolValueRegisterPart.getRegVal(dest.toString());
					symbolValueRegisterPart.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
					symbolValueSegment.mov(src.toString(), t);
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					symbolValueRegister.mov(dest.toString(), ((Immediate) src).getNumber().intValue());
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					Value t = symbolValueRegisterPart.getRegVal(dest.toString());
					symbolValueRegisterPart.mov(dest.toString(),
							symbolValueMemoryOperand.getMemoryOperandVal(src.toString()));
					symbolValueMemoryOperand.mov((X86MemoryOperand) src, t);
				}
			} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					symbolValueSegment.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					symbolValueSegment.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					symbolValueSegment.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					symbolValueSegment.mov(dest.toString(), ((Immediate) src).getNumber().intValue());
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

	private void X86ReturnInterprete(X86RetInstruction inst) {
		// if (this.targetTemp.getValueOperand() == 4199186) {
		// System.out.println("Debug");
		// }

		if (inst.getName().startsWith("ret")) {
			if (this.targetIndirect.getValue() == this.targetTemp.getValue()) {
				Value temp = symbolStack.pop();

				if (temp instanceof LongValueOld)
					this.evaluateValue = ((LongValueOld) temp).getValue();

			} else {
				symbolStack.pop();
				symbolValueRegister.add("%esp", new LongValueOld(4));
			}
		}
	}

	private void X86InstructionInterprete(X86Instruction inst) {
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();
		Value d = null, s = null;
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
					Value ecx = this.symbolValueRegister.getRegVal("%ecx");

					if (ecx instanceof LongValueOld) {
						int t = (int) ((LongValueOld) ecx).getValue();
						for (int i = 0; i < t; i++) {
							Value ediValue = this.calculateValueMemoryOperand(new X86MemoryOperand(edi.getDataType(),
									edi.getDisplacement() + i), inst);
							Value esiValue = this.calculateValueMemoryOperand(new X86MemoryOperand(esi.getDataType(),
									esi.getDisplacement() + i), inst);

							if (!ediValue.equal(esiValue)) {
								this.symbolFlag.setzFlag(new BooleanExp(false));
								return;
							}
						}
						this.symbolFlag.setzFlag(new BooleanExp(true));
						this.symbolValueRegister.add("%esi", ecx);
						this.symbolValueRegister.add("%edi", ecx);
					}

				}
			} else if (inst.hasPrefixREPNZ()) {
				System.out.println("Debug Instruction REPNZ" + inst.getName());
			} else {
				System.out.println("Debug Instruction " + inst.getName());
			}

		} else if (inst.getName().startsWith("std")) {
			symbolFlag.setdFlag(new BooleanExp(1));
		} else if (inst.getName().startsWith("cld")) {
			// Clear direction flag
			symbolFlag.setdFlag(new BooleanExp(0));
		} else if (inst.getName().startsWith("scas")) {
			/*
			 * @_1: scasb jnz @_1 sub edi,esi ; EDI = API Name size
			 */
			// Scas is followed by jump condition. This is a loop
			// So we run this loop by symbolic execution with while loop
			// System.out.println("Debug Instruction scas");
			Value x = symbolValueRegisterPart.getRegVal(dest.toString());
			Value y = symbolValueRegister.getRegVal("%edi");

			if (x instanceof LongValueOld && y instanceof LongValueOld) {
				long x1 = ((LongValueOld) x).getValue();
				long y1 = ((LongValueOld) y).getValue();
				// while (true) {
				long z = program.getByteValueMemory(new AbsoluteAddress(y1));
				// Dieu kien bang xay ra
				if (x1 == z)
					// break;
					symbolFlag.setzFlag(new BooleanExp(true));
				else
					symbolFlag.setzFlag(new BooleanExp(false));

				// }

				symbolValueRegister.mov("%edi", new LongValueOld(y1 + 1));

			}

		} else if (inst.getName().startsWith("stos")) {
			// Load String
			if (inst.hasPrefixREPZ()) {
				// System.out.println("Debug Instruction REPZ STOS:" +
				// inst.toString());

				Value x = null;
				Value ecx = symbolValueRegister.getRegVal("%ecx");
				if (src.getClass().getSimpleName().equals("X86Register")) {
					x = symbolValueRegister.getRegVal("%eax");
					// FlagRelationship.totalChangePart(symbolValueRegisterPart,
					// symbolValueRegister, "%eax");
					if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
						X86MemoryOperand t = this.calculateMemoryOperand((X86MemoryOperand) dest);

						long disp = t.getDisplacement();
						if (t.getBase() == null) {
							/*
							 * Exp base = symbolValueRegister.getRegVal(t
							 * .getBase().toString());
							 */

							if (ecx instanceof LongValueOld) {
								// long b = ((ValueLongExp)
								// base).getValueOperand();
								long cx = ((LongValueOld) ecx).getValue();
								// b1 = calculateValueMemoryOperand(new
								// X86MemoryOperand(inst.getDataType(), b +
								// disp),
								// inst);
								for (long i = 0; i < cx;) {
									symbolValueMemoryOperand.movDoubleWord(new X86MemoryOperand(inst.getDataType(), i
											+ disp), x);
									i += 4;
								}

								symbolValueRegister.add("%edi", new LongValueOld(cx));
								symbolValueRegister.mov("%ecx", new LongValueOld(0));
							}
						}
					}

					// symbolValueRegister.add("%edi", new ValueLongExp(4));
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {

					if (src.toString().equals("%al")) {
						x = symbolValueRegisterPart.getRegVal("%al");
						// FlagRelationship.partChangeTotal(symbolValueRegisterPart,
						// symbolValueRegister, "%ax");
						if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
							X86MemoryOperand t = this.calculateMemoryOperand((X86MemoryOperand) dest);

							long disp = t.getDisplacement();
							if (t.getBase() == null) {
								// Exp base = symbolValueRegister.getRegVal(t
								// .getBase().toString());

								if (ecx instanceof LongValueOld) {
									// long b = ((ValueLongExp)
									// base).getValueOperand();
									long cx = ((LongValueOld) ecx).getValue();
									// b1 = calculateValueMemoryOperand(new
									// X86MemoryOperand(inst.getDataType(), b +
									// disp),
									// inst);

									for (long i = 0; i < cx; i++) {
										symbolValueMemoryOperand.movByte(new X86MemoryOperand(inst.getDataType(), disp
												+ i), x);
									}
									symbolValueRegister.add("%edi", new LongValueOld(cx));
									symbolValueRegister.mov("%ecx", new LongValueOld(0));
								}
							}
						}
					}

				}

			} else if (inst.hasPrefixREPNZ()) {
				System.out.println("Debug Instruction REPNZ STOS:" + inst.toString());
			} else {
				// System.out.println("Debug Instruction stos:" +
				// inst.toString());
				Value x = null;
				if (src.getClass().getSimpleName().equals("X86Register")) {
					x = symbolValueRegister.getRegVal(src.toString());
					// FlagRelationship.totalChangePart(symbolValueRegisterPart,
					// symbolValueRegister, "%eax");
					if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
						X86MemoryOperand t = this.calculateMemoryOperand((X86MemoryOperand) dest);
						symbolValueMemoryOperand.movDoubleWord(t, x);
						symbolValueRegister.add("%edi", new LongValueOld(4));
						// X86MemoryOperand t1 = this.calculateMemoryOperand(t);
						// long disp = t.getDisplacement();
						/*
						 * if (t.getBase() != null) { Exp base =
						 * symbolValueRegister.getRegVal(t
						 * .getBase().toString());
						 * 
						 * if (base instanceof ValueLongExp) { long b =
						 * ((ValueLongExp) base).getValueOperand(); // b1 =
						 * calculateValueMemoryOperand(new //
						 * X86MemoryOperand(inst.getDataType(), b + // disp), //
						 * inst);
						 * 
						 * symbolValueMemoryOperand.movDoubleWord( new
						 * X86MemoryOperand( inst.getDataType(), b + disp), x);
						 * } }
						 */
					}

					// symbolValueRegister.add("%edi", new ValueLongExp(4));
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					x = symbolValueRegisterPart.getRegVal(src.toString());
					// FlagRelationship.partChangeTotal(symbolValueRegisterPart,
					// symbolValueRegister, "%ax");
					if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
						X86MemoryOperand t = this.calculateMemoryOperand((X86MemoryOperand) dest);
						if (src.toString().endsWith("x")) {
							symbolValueMemoryOperand.movWord(t, x);
							symbolValueRegister.add("%edi", new LongValueOld(2));
						} else if (src.toString().endsWith("l") || src.toString().endsWith("h")) {
							symbolValueMemoryOperand.movByte(t, x);
							symbolValueRegister.add("%edi", new LongValueOld(1));
						}

						/*
						 * long disp = t.getDisplacement(); if (t.getBase() !=
						 * null) { Exp base = symbolValueRegister.getRegVal(t
						 * .getBase().toString());
						 * 
						 * if (base instanceof ValueLongExp) { long b =
						 * ((ValueLongExp) base).getValueOperand(); // b1 =
						 * calculateValueMemoryOperand(new //
						 * X86MemoryOperand(inst.getDataType(), b + // disp), //
						 * inst);
						 * 
						 * symbolValueMemoryOperand.movWord( new
						 * X86MemoryOperand( inst.getDataType(), b + disp), x);
						 * } }
						 */
					}

					// symbolValueRegister.add("%edi", new ValueLongExp(2));
				}

			}

		} else if (inst.getName().startsWith("lods")) {
			// Load String
			// System.out.println("Debug Instruction lods");
			Value b1 = new LongValueOld(0);

			if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = (X86MemoryOperand) src;

				long disp = t.getDisplacement();
				if (t.getBase() != null) {
					Value base = symbolValueRegister.getRegVal(t.getBase().toString());

					if (inst.getName().toString().endsWith("b")) {
						symbolValueRegister.add(t.getBase().toString(), new LongValueOld(4));
					} else if (inst.getName().toString().endsWith("s")) {
						symbolValueRegister.add(t.getBase().toString(), new LongValueOld(4));
					} else if (inst.getName().toString().endsWith("w")) {
						symbolValueRegister.add(t.getBase().toString(), new LongValueOld(4));
					} else if (inst.getName().toString().endsWith("l")) {
						symbolValueRegister.add(t.getBase().toString(), new LongValueOld(4));
					}
					// if (this.targetTemp.getValueOperand() == 4199303)
					// System.out.println("Debug");

					if (base instanceof LongValueOld) {
						long b = ((LongValueOld) base).getValue();
						b1 = calculateValueMemoryOperand(new X86MemoryOperand(inst.getDataType(), b + disp), inst);
					}
				}
			}

			if (dest.getClass().getSimpleName().equals("X86Register")) {
				symbolValueRegister.mov("%eax", b1);
				// RegisterRelationship.totalChangePart(symbolValueRegisterPart,
				// symbolValueRegister, "%eax");
			} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
				symbolValueRegisterPart.mov("%ax", b1);
				// RegisterRelationship.partChangeTotal(symbolValueRegisterPart,
				// symbolValueRegister, "%ax");
			}

		} else if (inst.getName().startsWith("movsb")) {
			Value val = symbolValueRegister.getRegVal("%ecx");
			symbolValueRegister.add("%esi", val);
			symbolValueRegister.add("%edi", val);
			symbolValueRegister.mov("%ecx", new LongValueOld(0));
		} else if (inst.getName().startsWith("cld")) {
			symbolFlag.setdFlag(new BooleanExp(0));
		} else if (inst.getName().startsWith("int")) {
			if (dest.getClass().getSimpleName().equals("Immediate")) {
				int x = ((Immediate) dest).getNumber().intValue();

				// Process interrupt 80h
				if (x == 80) {

				} else
				// Process interrupt 21h
				if (x == 33) {
					// Check if AH = 2A
					// Return: CX = year (1980-2099) DH = month DL = day AL =
					// day of week (00h=Sunday)
					if (symbolValueRegisterPart.getRegVal("%ah").equal(new LongValueOld(42))) {
						symbolValueRegisterPart.setSymbolRegisterPartValue("%cx", new LongValueOld(Calendar.getInstance()
								.get(Calendar.YEAR)));
						// int z = Calendar.getInstance().get(Calendar.MONTH);
						symbolValueRegisterPart.setSymbolRegisterPartValue("%dh", new LongValueOld(Calendar.getInstance()
								.get(Calendar.MONTH) + 1));
						symbolValueRegisterPart.setSymbolRegisterPartValue("%dl", new LongValueOld(Calendar.getInstance()
								.get(Calendar.DATE)));
						symbolValueRegisterPart.setSymbolRegisterPartValue("%al", new LongValueOld(Calendar.getInstance()
								.get(Calendar.DAY_OF_WEEK)));
					}
				}

			}
		} else if (inst.getName().startsWith("leave")) {

		} else if (inst.getName().startsWith("lea")) {
			// just like MOV but with minor difference - Come back later
			// http://stackoverflow.com/questions/1699748/what-is-the-difference-between-mov-and-lea
			if (dest.getClass().getSimpleName().equals("X86Register")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					symbolValueRegister.mov(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					symbolValueRegister.mov(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					symbolValueRegister.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					symbolValueRegister.mov(dest.toString(), ((Immediate) src).getNumber().intValue());
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					X86MemoryOperand t = (X86MemoryOperand) src;
					// X86MemoryOperand t =
					// calculateMemoryOperand((X86MemoryOperand) src);
					// symbolValueRegister.mov(dest.toString(), regVal)

					if (t.getBase() != null) {
						symbolValueRegister.mov(dest.toString(), symbolValueRegister.getRegVal(t.getBase().toString()));
						if (t.getDisplacement() != 0)
							symbolValueRegister.add(dest.toString(), new LongValueOld(t.getDisplacement()));
					} else if (t.getDisplacement() != 0)
						symbolValueRegister.mov(dest.toString(), new LongValueOld(t.getDisplacement()));
				}
			} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					symbolValueRegisterPart.mov(dest.toString(), symbolValueRegister.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					symbolValueRegisterPart.mov(dest.toString(), symbolValueRegisterPart.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					symbolValueRegisterPart.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					symbolValueRegisterPart.mov(dest.toString(), ((Immediate) src).getNumber().intValue());
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					X86MemoryOperand t = (X86MemoryOperand) src;
					if (t.getBase() != null)
						symbolValueRegisterPart.mov(dest.toString(),
								symbolValueRegister.getRegVal(t.getBase().toString()));
					if (t.getDisplacement() != 0)
						symbolValueRegisterPart.add(dest.toString(), new LongValueOld(t.getDisplacement()));
				}
			} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					symbolValueSegment.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
					symbolValueSegment.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					symbolValueSegment.mov(dest.toString(), symbolValueSegment.getRegVal(src.toString()));
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					symbolValueSegment.mov(dest.toString(), ((Immediate) src).getNumber().intValue());
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					X86MemoryOperand t = (X86MemoryOperand) src;
					if (t.getBase() != null) {
						symbolValueSegment.mov(dest.toString(), symbolValueRegister.getRegVal(t.getBase().toString()));
						if (t.getDisplacement() != 0)
							symbolValueSegment.add(dest.toString(), new LongValueOld(t.getDisplacement()));
					} else if (t.getDisplacement() != 0)
						symbolValueSegment.mov(dest.toString(), new LongValueOld(t.getDisplacement()));
				}
			}

		} else if (inst.getName().startsWith("cmp") || inst.getName().startsWith("test")) {
			// SymbolicValue lhs = null, rhs = null;
			// if (this.targetTemp.getValueOperand() == 4199181)
			// System.out.println("Debug CMP");
			if (dest.getClass().getSimpleName().equals("X86Register"))
				d = symbolValueRegister.getRegVal(dest.toString());
			else if (dest.getClass().getSimpleName().equals("X86RegisterPart"))
				d = symbolValueRegisterPart.getRegVal(dest.toString());
			else if (dest.getClass().getSimpleName().equals("X86SegmentRegister"))
				d = symbolValueSegment.getRegVal(dest.toString());
			else if (dest.getClass().getSimpleName().equals("Immediate"))
				d = new LongValueOld(((Immediate) dest).getNumber().intValue());
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				d = calculateValueMemoryOperand((X86MemoryOperand) dest, inst);
			}

			if (src.getClass().getSimpleName().equals("X86Register"))
				s = symbolValueRegister.getRegVal(src.toString());
			else if (src.getClass().getSimpleName().equals("X86RegisterPart"))
				s = symbolValueRegisterPart.getRegVal(src.toString());
			else if (src.getClass().getSimpleName().equals("X86SegmentRegister"))
				s = symbolValueSegment.getRegVal(src.toString());
			else if (src.getClass().getSimpleName().equals("Immediate")) {
				if (inst.getName().endsWith("b")) {
					long t = ((Immediate) src).getNumber().intValue() & 0xff;
					s = new LongValueOld(t);
				} else
					s = new LongValueOld(((Immediate) src).getNumber().intValue());
			} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// s = symbolValueMemoryOperand
				// .getMemoryOperandVal((X86MemoryOperand) src);
				X86MemoryOperand t = (X86MemoryOperand) src;
				long desp = t.getDisplacement();
				Value base = null;
				if (t.getBase() != null) {
					if (t.getBase().toString().equals("%esp"))
						base = symbolStack.get(0);
					else
						base = symbolValueRegister.getRegVal(t.getBase().toString());

					if (base instanceof LongValueOld)
						s = symbolValueMemoryOperand.getMemoryOperandValByte(new X86MemoryOperand(t.getDataType(), desp
								+ ((LongValueOld) base).getValue()));
					else
						s = symbolValueMemoryOperand.getMemoryOperandValByte(t);
				} else
					s = symbolValueMemoryOperand.getMemoryOperandValByte(new X86MemoryOperand(t.getDataType(), desp));
			}

			// this.formulas.add(new Formula(d.clone(), s.clone()));
			if (inst.getName().startsWith("cmp"))
				symbolFlag.changeFlagWithCMP(symbolValueRegister, symbolValueMemoryOperand, symbolStack, d, s);
			else
				symbolFlag.changeFlagWithTEST(symbolValueRegister, symbolValueMemoryOperand, symbolStack, d, s);
			// set compare status

		} else if (inst.getName().startsWith("pop")) {
			if (dest == null)
				return;

			if (dest.getClass().getSimpleName().equals("X86Register")) {
				if (!symbolValueRegister.setSymbolRegisterValue(dest.toString(), symbolStack.pop()))
					System.out.println("Error");
			} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
				if (!symbolValueRegisterPart.setSymbolRegisterPartValue(dest.toString(), symbolStack.pop()))
					System.out.println("Error");
				symbolValueRegister.add("%esp", new LongValueOld(2));
			} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				if (!symbolValueSegment.setSymbolSegmentValue(dest.toString(), symbolStack.pop()))
					System.out.println("Error");
			} else if (dest.getClass().getSimpleName().equals("Immediate"))
				// d = new ConstantExp(((Immediate)
				// dest).getNumber().intValue());
				;
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				if (!symbolValueMemoryOperand.setSymbolMemoryOperandValue((X86MemoryOperand) dest, symbolStack.pop()))
					System.out.println("Error");
			}

		} else if (inst.getName().startsWith("pusha")) {
			symbolStack.push(symbolValueRegister.getRegVal("%eax"));
			symbolStack.push(symbolValueRegister.getRegVal("%ecx"));
			symbolStack.push(symbolValueRegister.getRegVal("%edx"));
			symbolStack.push(symbolValueRegister.getRegVal("%ebx"));
			symbolStack.push(symbolValueRegister.getRegVal("%esp"));
			symbolStack.push(symbolValueRegister.getRegVal("%ebp"));
			symbolStack.push(symbolValueRegister.getRegVal("%esi"));
			symbolStack.push(symbolValueRegister.getRegVal("%edi"));

		} else if (inst.getName().startsWith("pushf")) {
			symbolStack.push(new LongValueOld(0));
		} else if (inst.getName().startsWith("push")) {
			if (dest == null)
				return;

			if (dest.getClass().getSimpleName().equals("X86Register"))
				d = symbolValueRegister.getRegVal(dest.toString());
			else if (dest.getClass().getSimpleName().equals("X86RegisterPart"))
				d = symbolValueRegisterPart.getRegVal(dest.toString());
			else if (dest.getClass().getSimpleName().equals("Immediate")) {
				// Immediate t = (Immediate) dest;
				// long x = t.getNumber().longValue();
				d = new LongValueOld(((Immediate) dest).getNumber().longValue());
			} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// d = symbolValueMemoryOperand
				// .getMemoryOperandVal((X86MemoryOperand) dest);
				X86MemoryOperand t = (X86MemoryOperand) dest;
				if (t.getSegmentRegister() != null && t.getSegmentRegister().toString() == "%fs"
						&& t.getDisplacement() == 0) {
					d = new LongValueOld(system.getSEHHandler().getStart().getNextSEHRecord());
				} else if (t.getBase() != null) {
					d = symbolValueRegister.getRegVal(t.getBase().toString());
					if (t.getDisplacement() != 0)
						d = d.addFunc(new LongValueOld(t.getDisplacement()));
				} else if (t.getDisplacement() != 0)
					d = new LongValueOld(t.getDisplacement());
				/*
				 * if (inst.getName().endsWith("l")) d =
				 * symbolValueMemoryOperand .getMemoryOperandValDoubleWord(new
				 * X86MemoryOperand( t.getDataType(), ((ValueLongExp) d)
				 * .getValueOperand())); else if (inst.getName().endsWith("w"))
				 * d = symbolValueMemoryOperand .getMemoryOperandValWord(new
				 * X86MemoryOperand(t .getDataType(), ((ValueLongExp) d)
				 * .getValueOperand())); else if (inst.getName().endsWith("b"))
				 * d = symbolValueMemoryOperand .getMemoryOperandValByte(new
				 * X86MemoryOperand(t .getDataType(), ((ValueLongExp) d)
				 * .getValueOperand()));
				 */
				d = this.calculateValueMemoryOperand(t, inst);

			} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister"))
				d = symbolValueSegment.getRegVal(dest.toString());

			symbolStack.push(d);
		} else if (inst.getName().startsWith("nop")) {
			// do nothing
		} else {

		}
	}

	private Value calculateValueMemoryOperand(X86MemoryOperand t, Instruction inst) {
		// TODO Auto-generated method stub
		Value d = null;
		// X86MemoryOperand t = (X86MemoryOperand) dest;
		long desp = t.getDisplacement();
		Value base = null;
		if (t.getBase() != null) {
			if (t.getBase().toString().equals("%esp"))
				base = symbolStack.get(desp);
			else
				base = symbolValueRegister.getRegVal(t.getBase().toString());

			if (base instanceof LongValueOld) {
				long ad = desp + ((LongValueOld) base).getValue();
				// Truy cap dia chi trong kernel32 Address
				if (system.checkAddrInKernel32(ad)) {
					if (inst.getName().toString().endsWith("b")) {
						d = new LongValueOld(system.getKernel().readByte((int) ad));
					} else if (inst.getName().toString().endsWith("s")) {
						d = new LongValueOld(system.getKernel().readWord((int) ad));
					} else if (inst.getName().toString().endsWith("w")) {
						d = new LongValueOld(system.getKernel().readWord((int) ad));
					} else if (inst.getName().toString().endsWith("l")) {
						d = new LongValueOld(system.getKernel().readDoubleWord((int) ad));
					}
				} else if (system.checkAddrInFile(ad)) {
					if (inst.getName().toString().endsWith("b")) {
						d = new LongValueOld(system.getFileHandle().readByte((int) ad));
					} else if (inst.getName().toString().endsWith("s")) {
						d = new LongValueOld(system.getFileHandle().readWord((int) ad));
					} else if (inst.getName().toString().endsWith("w")) {
						d = new LongValueOld(system.getFileHandle().readWord((int) ad));
					} else if (inst.getName().toString().endsWith("l")) {
						d = new LongValueOld(system.getFileHandle().readDoubleWord((int) ad));
					}
				} else {
					if (inst.getName().toString().endsWith("b")) {
						if (inst.getName().toString().startsWith("cmp")
								&& !symbolValueMemoryOperand.contain(new X86MemoryOperand(t.getDataType(), desp))) {
							d = new LongValueOld(program.getByteValueMemory(new AbsoluteAddress(ad)));
						} else
							d = symbolValueMemoryOperand.getMemoryOperandValByte(new X86MemoryOperand(t.getDataType(),
									ad));
					} else if (inst.getName().toString().endsWith("s")) {
						d = symbolValueMemoryOperand.getMemoryOperandValWord(new X86MemoryOperand(t.getDataType(), ad));
					} else if (inst.getName().toString().endsWith("w")) {
						d = symbolValueMemoryOperand.getMemoryOperandValWord(new X86MemoryOperand(t.getDataType(), ad));
					} else if (inst.getName().toString().endsWith("l")) {
						d = symbolValueMemoryOperand.getMemoryOperandValDoubleWord(new X86MemoryOperand(
								t.getDataType(), ad));
					}

					/*
					 * d = symbolValueMemoryOperand .getMemoryOperandVal(new
					 * X86MemoryOperand(t .getDataType(), ad));
					 */
				}
			} else
				d = symbolValueMemoryOperand.getMemoryOperandValByte(t);
		} else {
			/*
			 * d = symbolValueMemoryOperand .getMemoryOperandVal(new
			 * X86MemoryOperand(t.getDataType(), desp));
			 */
			if (system.checkAddrInKernel32(desp)) {
				if (inst.getName().toString().endsWith("b")) {
					d = new LongValueOld(system.getKernel().readByte((int) desp));
				} else if (inst.getName().toString().endsWith("s")) {
					d = new LongValueOld(system.getKernel().readWord((int) desp));
				} else if (inst.getName().toString().endsWith("w")) {
					d = new LongValueOld(system.getKernel().readWord((int) desp));
				} else if (inst.getName().toString().endsWith("l")) {
					d = new LongValueOld(system.getKernel().readDoubleWord((int) desp));
				}
			} else if (system.checkAddrInFile(desp)) {
				if (inst.getName().toString().endsWith("b")) {
					d = new LongValueOld(system.getFileHandle().readByte((int) desp));
				} else if (inst.getName().toString().endsWith("s")) {
					d = new LongValueOld(system.getFileHandle().readWord((int) desp));
				} else if (inst.getName().toString().endsWith("w")) {
					d = new LongValueOld(system.getFileHandle().readWord((int) desp));
				} else if (inst.getName().toString().endsWith("l")) {
					d = new LongValueOld(system.getFileHandle().readDoubleWord((int) desp));
				}
			} else {
				if (inst.getName().toString().endsWith("b")) {
					if (inst.getName().toString().startsWith("cmps")
							&& !symbolValueMemoryOperand.contain(new X86MemoryOperand(t.getDataType(), desp))) {
						d = new LongValueOld(program.getByteValueMemory(new AbsoluteAddress(desp)));
					} else
						d = symbolValueMemoryOperand
								.getMemoryOperandValByte(new X86MemoryOperand(t.getDataType(), desp));

				} else if (inst.getName().toString().endsWith("s")) {
					d = symbolValueMemoryOperand.getMemoryOperandValWord(new X86MemoryOperand(t.getDataType(), desp));
				} else if (inst.getName().toString().endsWith("w")) {
					d = symbolValueMemoryOperand.getMemoryOperandValWord(new X86MemoryOperand(t.getDataType(), desp));
				} else if (inst.getName().toString().endsWith("l")) {
					d = symbolValueMemoryOperand.getMemoryOperandValDoubleWord(new X86MemoryOperand(t.getDataType(),
							desp));
				}
			}
		}

		return d;
	}

	private X86MemoryOperand calculateMemoryOperand(X86MemoryOperand t) {
		// TODO Auto-generated method stub
		Value base = null;
		long disp = 0;

		if (t.getBase() != null) {
			base = symbolValueRegister.getRegVal(t.getBase().toString());
		}
		if (t.getDisplacement() != 0) {
			disp = t.getDisplacement();
		}

		if (base != null) {
			if (base instanceof LongValueOld) {
				return new X86MemoryOperand(t.getDataType(), disp + ((LongValueOld) base).getValue());
			}
		}
		return t;
	}

	public AbsoluteAddress getTarget() {
		return targetTemp;
	}

	public void setTarget(AbsoluteAddress target) {
		this.targetTemp = target;
	}

	public AddressList getAddrTraceList() {
		return addrTraceList;
	}

	public void setAddrTraceList(AddressList addrTraceList) {
		this.addrTraceList = addrTraceList;
	}

	public Map<AbsoluteAddress, Instruction> getAssemblyMap() {
		return assemblyMap;
	}

	public void setAssemblyMap(Map<AbsoluteAddress, Instruction> assemblyMap) {
		this.assemblyMap = assemblyMap;
	}

	public Condition generateCondProver() {
		Condition cond = new Condition();
		return cond;
	}

	public FormulaSet getFormulas() {
		return formulaList;
	}

	public void setFormulas(FormulaSet formulas) {
		this.formulaList = formulas;
	}

	public void writeZ3Input(String fileName) {
		PrintWriter writer;
		System.out.println("Z3 Input File: ");
		try {
			writer = new PrintWriter(fileName, "UTF-8");
			writer.println("(benchmark program");
			writer.println(" :status sat");
			writer.println(" :logic QF_LIA");
			writer.println(":extrafuns (" + pcValue.getStringZ3() + ")");
			System.out.println(":extrafuns (" + pcValue.getStringZ3() + ")");
			// writer.println(":formula" + formulaList.toStringPrefix());
			if (formulaList.isEmpty()) {
				writer.println(":formula (true)");
				System.out.println(":formula (true)");
			} else {
				writer.println(":formula" + formulaList.toStringPrefix());
				System.out.println(":formula" + formulaList.toStringPrefix());
			}
			writer.println(")");
			System.out.println(")");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean readZ3Output(String fileName) {
		// String result = "";
		InputStream fis;
		BufferedReader br;
		String line;
		try {
			fis = new FileInputStream(fileName);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			line = br.readLine();

			if (line != null && line.equals("sat")) {
				System.out.println("Sat Result:");
				while ((line = br.readLine()) != null) {
					String name = getNameZ3Result(line);
					long val = getValueZ3Result(line);
					name = reverseName(name);
					pcValue.add(name, val);
					System.out.println(name + " --> " + val + " = " + getValueZ3(line));
				}
				br.close();
				/*
				 * if (sr != null) { //EnvWeight ew = new EnvWeight();
				 * //ew.setAddress(targetIndirect);
				 * //ew.setInstruction(assemblyMap.get(targetIndirect));
				 * 
				 * Environment e = new Environment(symbolValueRegister.clone(),
				 * symbolValueRegisterPart.clone(),
				 * symbolValueMemoryOperand.clone(), symbolValueSegment.clone(),
				 * symbolFlag.clone(), symbolStack.clone(), program, system);
				 * 
				 * Environment e = new Environment(symbolValueRegister,
				 * symbolValueRegisterPart, symbolValueMemoryOperand,
				 * symbolValueSegment, symbolFlag, symbolStack, program,
				 * system);
				 * 
				 * //sr.getEnvWeight().addWeight(e); }
				 */
				return true;
			} else {
				System.out.println("Unsat");
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

	private String reverseName(String name) {
		// TODO Auto-generated method stub
		String var = name;
		// %eax
		if (name.startsWith("op_addr_base_") && !name.contains("disp"))
			var = "(%" + var + ")";
		// 0x441023
		else if (var.startsWith("op_addr_disp_"))
			var = var.substring(13);
		else if (var.startsWith("op_addr_base_disp")) {
			String temp[] = var.split("_");
			var = temp[4] + "(%" + temp[5] + ")";
		} else if (var.startsWith("op_addr_base2_disp")) {
			String temp[] = var.split("_");
			var = temp[4] + "(%" + temp[5] + "," + temp[6] + ")";
		} else if (var.startsWith("op_addr_base_index")) {
			String temp[] = var.split("_");
			var = "(%" + temp[4] + ",%" + temp[5] + ")";
		} else if (var.startsWith("op_addr_base_index_disp_")) {
			String temp[] = var.split("_");
			var = "%" + temp[5] + ":(%" + temp[6] + ")";
		} else if (var.startsWith("op_addr_base_index_base_")) {
			String temp[] = var.split("_");
			var = temp[5] + "(%" + temp[6] + "," + temp[7] + ")";
		} else
			var = "%" + var;

		return var;
	}

	private long getValueZ3(String line) {
		// TODO Auto-generated method stub
		long val = 0;
		String result[] = line.split(" ");

		if (result[0].contains("Flag")) {
			if (result[2].contains("false"))
				val = 0;
			else if (result[2].contains("true"))
				val = 1;
			return val;
		}

		if (result[2].startsWith("(")) {
			val = Long.parseLong(result[3].substring(0, result[3].length() - 1));
			// if (val > 100000) val = 0;
			if (result[2].substring(1).equals("-"))
				val = -val;
		} else {
			val = Long.parseLong(result[2].substring(2, result[2].length() - 4));
			if (val >= Math.pow(2, 31))
				val = (long) (val - Math.pow(2, 32));
		}
		return val;
	}

	private long getValueZ3Result(String line) {
		// TODO Auto-generated method stub
		long val = 0;
		String result[] = line.split(" ");
		if (result[0].contains("Flag")) {
			if (result[2].contains("false"))
				val = 0;
			else if (result[2].contains("true"))
				val = 1;
			return val;
		}

		if (result[2].startsWith("(")) {
			val = Long.parseLong(result[3].substring(0, result[3].length() - 1));
			// if (val > 100000) val = 0;
			if (result[2].substring(1).equals("-"))
				val = -val;
		} else {
			val = Long.parseLong(result[2].substring(2, result[2].length() - 4));
			// if (val >= Math.pow(2, 31))
			// val = (long) (val - Math.pow(2, 32));
		}
		return val;
	}

	private String getNameZ3Result(String line) {
		// TODO Auto-generated method stub
		String result[] = line.split(" ");
		return result[0];
	}

	public void solver() {

	}

	public long getEvaluateValue() {
		return evaluateValue;
	}

	public void setEvaluateValue(long evaluateValue) {
		this.evaluateValue = evaluateValue;
	}

	public void setWeightConf(List<WConf> conf, IntegerWeight w) {
		// TODO Auto-generated method stub
		this.weightConf = conf;
		this.type = w;
	}

	/**
	 * @return the weightConf
	 */
	public List<WConf> getWeightConf() {
		return weightConf;
	}

	/**
	 * @param weightConf
	 *            the weightConf to set
	 */
	public void setWeightConf(List<WConf> weightConf) {
		this.weightConf = weightConf;
	}

	/**
	 * @return the type
	 */
	public Weight getTypeWeight() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setTypeWeight(Weight type) {
		this.type = type;
	}
}
