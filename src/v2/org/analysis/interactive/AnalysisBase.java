/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.interactive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import org.jakstab.loader.ExecutableImage;

import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Flag;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.MemoryV2;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.X86TransitionRule;
import v2.org.analysis.value.Formula;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SolverResult;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

/**
 *
 * @author zunc
 */
public class AnalysisBase implements IInteractive {

	protected Program program;
	protected X86TransitionRule rule;
	protected BPPath path;
	protected BPState state;
	protected Environment env;
	protected MemoryV2 memory;
	protected Register register;
	protected Stack stack;

	//--- Symbolic generate
	protected boolean autoSymbolicGenerate = false;
	protected int nSymbolicByte = 32;
	private boolean isLogInstr = false, isLogAPI = false;

	//--- Stack tracking
	protected java.util.Stack<Long> _callStack = new java.util.Stack<>();
	protected Instruction _lastInstr = null;

	public enum SYMBOLIC_REASON {
		NOP,
		EXTERNAL_API,
		FLIRT_API, // focus detect inline api
		ARG
	};

	private static List<String> _symbolicAPI; // input api
	private static List<String> _outputAPI; // input api

	static {
		_symbolicAPI = new ArrayList<>();
		_symbolicAPI.addAll(Arrays.asList(
				"scanf",
				"_scanf",
				"ReadFile"
		));

		_outputAPI = new ArrayList<>();
		_outputAPI.addAll(Arrays.asList(
				"printf",
				"sprintf",
				"puts",
				"_puts",
				"WriteFile"
		));
	}

	public AnalysisBase() {
		this.autoSymbolicGenerate = AnalysisModel.getInstance().getIsAutoGenerateSymbolic();
		this.isLogInstr = AnalysisModel.getInstance().getIsLogInstr();
		this.isLogAPI = AnalysisModel.getInstance().getIsLogAPI();
	}

	public AnalysisBase(boolean autoSymbolicGenerate, boolean isLogInstr) {
		this();
	}

	public AnalysisBase(boolean autoSymbolicGenerate, boolean isLogInstr, boolean isLogAPI) {
		this();
	}

	@Override
	public void _init(Program program, X86TransitionRule rule, BPPath path, BPState state) {
		this.program = program;
		this.rule = rule;
		this.path = path;
		this.state = state;
		this.env = state.getEnvironement();
		this.memory = env.getMemory();
		this.register = env.getRegister();
		this.stack = env.getStack();
	}

	//
	protected boolean onSymbolicGenerate(SYMBOLIC_REASON reason, long address,
			Instruction ins, String info) {
		return true;
	}

	protected ACTION_OP _onInstruction(BPPath path, long address, Instruction ins) {
		String strInst = getInst(address, ins);
		String strInstLine = String.format("0x00%s\t%s", Long.toHexString(address), strInst);
		System.out.println(strInstLine);
		
		return ACTION_OP.NOP;
	}

	protected void onPrintOutput(BPPath path, long addr, String output) {
		Formulas pathCond = path.getPathCondition();
		SolverResult solver = pathCond.getResult();
		String strInput = "";
		if (solver != null) {
			strInput = _getSymbolicString(solver, nSymbolicByte);
		}
		String stdOutput = output.replace("\n", "").replace("\r", "");
		String log = String.format("[OUTPUT] Output=\"%s\" , Input=\"%s\"",
				stdOutput, strInput);
		System.out.println(log);
	}

	static final int SIZE = 4;

	private long getStackIndex(Stack stack, int index) {
		return ((LongValue) stack.getIndex(SIZE * index)).getValue();
	}

	private void procOutput(String api, BPPath path, long addr) {
		if (api.isEmpty() || !_outputAPI.contains(api)) {
			return;
		}

		BPState curState = path.getCurrentState();
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		String strOutput = "";
		switch (api) {
			case "WriteFile": {
				//.text:0040107B	push    0	; lpOverlapped
				//.text:0040107D	lea     eax, [ebp+NumberOfBytesWritten]
				//.text:00401080	push    eax	; lpNumberOfBytesWritten
				//.text:00401081	push    12h	; nNumberOfBytesToWrite
				//.text:00401083	push    offset aYouAreFailure ; "You are failure\r\n"
				//.text:00401088	push    [ebp+hFile]	; hFile
				//.text:0040108B	call    WriteFile
				long offset = getStackIndex(stack, 1);
				strOutput = memory.getText(1, offset);
			}
		}
		onPrintOutput(path, addr, strOutput);
	}

	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
		if (!isLogInstr) {
			return ACTION_OP.NOP;
		}
		
		BPCFG cfg = Program.getProgram().getBPCFG();
		BPVertex src = cfg.getVertex(path.getCurrentState().getLocation(), ins);
		String name = ins.getName();
		if (name.contains("call")) {
			_callStack.push(address);
		} else if (name.contains("ret")) {
			if (!_callStack.empty()) {
				_callStack.pop();
			}
		}

		_onInstruction(path, address, ins);

		//--- local stuff
		BPState curState = path.getCurrentState();
		Environment env = curState.getEnvironement();
		Flag flag = env.getFlag();
		MemoryV2 memory = env.getMemory();
		Stack stack = env.getStack();
		Register register = env.getRegister();
		
		/////////////
		String apiName = "";
		String libName = "";
		SYMBOLIC_REASON reason = SYMBOLIC_REASON.NOP;
		//--- detect external api
		// <!> dirty code: parse string
		String strLine = getInst(address, ins);
		if (strLine.contains("@")) {
			String strInst = strLine.split("\t")[1];
			String[] arApiInfo = strInst.split("@", 2);
			apiName = arApiInfo[0];
			libName = arApiInfo[1];
			reason = SYMBOLIC_REASON.EXTERNAL_API;
		} else {
//			APIInfo api = ins.getAPI();
//			if (api != null && !api.getName().isEmpty()) {
//				apiName = api.getName();
//				libName = api.getLibName();
//				reason = SYMBOLIC_REASON.FLIRT_API;
//			}
		}

		////// process output
		procOutput(apiName, path, address);
		if (!autoSymbolicGenerate || apiName.isEmpty() || !_symbolicAPI.contains(apiName)) {
			_lastInstr = ins;
			return ACTION_OP.NOP;
		}

		////// process input
		///--- inline API
		//0x00401059	call	0x004011a2				<- parent stack
		// ---> [Flirt] API: _scanf@wa32rtn.sig.dump
		//0x004011a2	leal	0x8(%esp), %eax
		//0x004011a6	pushl	%eax
		//-> ??
		///--- external API
		//0x0040102f	call	WriteFile@kernel32.dll
		//-> easy by pass to next instruction
		long stackTop = ((LongValue) stack.pop()).getValue();
		if (reason == SYMBOLIC_REASON.EXTERNAL_API) {
			long szBuffer = 0;
			if (apiName.equals("ReadFile")) {
				Value st1 = stack.pop();
				Value st2 = stack.pop(); // lpBuffer
				szBuffer = ((LongValue) st1).getValue();
			} else if (apiName.equals("scanf")) {
				szBuffer = ((LongValue) register.getRegisterValue("eax")).getValue();
			}

			// check reference stack
			long parent = _callStack.peek();
			boolean isRefStack = _lastInstr.getName().equals("call");
			if (isRefStack) {
				// jump to next instruction of parent stack
				rule.generateNextInstructionForce(ins, stackTop, path);
				//stack.pop(); // clean stack
				System.out.println("[+] Goto parent stack");
			} else {
				System.out.println("[+] Goto next instruction");
				rule.generateNextInstructionForce(ins, path);
			}

			if (szBuffer > 0) {
				System.err.println(String.format("[0x%x] SymbolicVar create: 0x%x",
						address, szBuffer));
				for (int i = 0; i < nSymbolicByte; i++) {
					String varName = String.format("k%d", i);
					SymbolValue symVar = new SymbolValue(varName);
					symVar.setType(SymbolValue.Type.CHAR);
					memory.setByteMemoryValue(szBuffer + i, symVar);
				}
			}
		}

		_lastInstr = ins;
		return ACTION_OP.PASS;
	}

	@Override
	public void onMemoryRead(MEM_OP op, long address, Object readData) {
		//
	}

	@Override
	public void onMemoryWrite(MEM_OP op, long address, Object writeData) {
		//
	}

	// API Symbolic Map
	private ConcurrentHashMap<String, Integer> _mapSymbolicAPI = new ConcurrentHashMap<>();

	protected synchronized SymbolValue _genSymbolicAPI(String strAPI) {
		if (!_mapSymbolicAPI.contains(strAPI)) {
			_mapSymbolicAPI.put(strAPI, 0);
		}
		int idxSymVar = _mapSymbolicAPI.get(strAPI) + 1;
		_mapSymbolicAPI.put(strAPI, idxSymVar);
		return new SymbolValue(strAPI + "_" + idxSymVar);
	}

	@Override
	public Value onAPI(BPState state, long address, Instruction inst, String apiName, List<Long> params) {
		if (!this.autoSymbolicGenerate || !isLogAPI) {
			return null;
		}
//		if (apiName.startsWith("HttpQueryInfo")) {
//			long buffer = params.get(2);
////			state.getEnvironement().getMemory().setDoubleWordMemoryValue(buffer, _genSymbolicAPI(apiName + "_Bf"));
//			state.getEnvironement().getMemory().setDoubleWordMemoryValue(buffer, new LongValue(200));
//			return  new LongValue(1); // _genSymbolicAPI(apiName + "_Ret");
//		} else if (apiName.startsWith("HttpSendRequest")) {
//			return new LongValue(1); //_genSymbolicAPI(apiName + "_Ret");
//		} else if (apiName.startsWith("InternetReadFile")) {
//			return new LongValue(1); // _genSymbolicAPI(apiName + "_Ret");
//		} else if (apiName.startsWith("RaiseException")) {
//			return new LongValue(1);
//		}
		return null;
	}

	public String onReadMem(long startMem, int num) {
		// TODO Auto-generated method stub
		String result = "";
		for (int i = 0; i < num; i++) {
			result += "0x" + Long.toHexString(startMem) + "=" + memory.getByteMemoryValue(startMem) + ", ";
			startMem += 1;
		}
		result += "\n";
		return result;
	}

	public String onReadByte(long startMem) {
		// TODO Auto-generated method stub
		return memory.getByteMemoryValue(startMem).toString();
	}

	public String onReadWord(long startMem) {
		// TODO Auto-generated method stub
		return memory.getWordMemoryValue(startMem).toString();
	}

	public String onReadDoubleWord(long startMem) {
		// TODO Auto-generated method stub
		return memory.getDoubleWordMemoryValue(startMem).toString();
	}

	protected String getInst(Instruction inst) {
		String ret = inst.getName() + " ";
		for (int i = 0; i < inst.getOperandCount(); i++) {
			if (i != 0) {
				ret += ", ";
			}
			ret += inst.getOperand(i);
		}
		return ret;
	}

	protected String getInst(long addr, Instruction inst) {
		ExecutableImage ext = Program.getProgram().getModule(new AbsoluteAddress(addr));
		String ret;
		if (ext != null) {
			ret = inst.toString(addr, ext.getSymbolFinder());
		} else {
			ret = inst.toString(addr, null);
		}
		return ret;
	}

	@Override
	public void onSymbolicBranch(X86CondJmpInstruction inst, Formulas condition, SolverResult solver) {

	}

	//=== util
	protected void printFomula(Formulas condition) {
		System.err.println(" --- Condition");
		for (Formula cond : condition.getListFormula()) {
			cond.evaluate();
			System.err.println(" - " + cond.toString());
		}
	}

	protected String _getSymbolicString(SolverResult solver, int n) {
		String sbResult = "";
		Map<String, Long> var = solver.getResult();
		for (int i = 0; i < n; i++) {
			String varName = String.format("k%d", i);
			if (!var.containsKey(varName)) {
				break;
			}
			sbResult += (char) var.get(varName).byteValue();
		}
		return sbResult;
	}
}
