/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.interactive;

import java.util.List;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import org.jakstab.loader.ExecutableImage;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.MemoryV2;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.X86TransitionRule;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.SolverResult;
import v2.org.analysis.value.Value;

/**
 *
 * @author zunc
 */
public class AnalysisFactory implements IInteractive {

	protected Program program;
	protected X86TransitionRule rule;
	protected BPPath path;
	protected BPState state;
	protected Environment env;
	protected MemoryV2 memory;
	protected Register register;
	protected Stack stack;

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

	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
		return ACTION_OP.NOP;
	}

	@Override
	public void onMemoryRead(MEM_OP op, long address, Object readData) {
		//
	}

	@Override
	public void onMemoryWrite(MEM_OP op, long address, Object writeData) {
		//
	}

	@Override
	public Value onAPI(BPState state, long address, Instruction inst, String apiName, List<Long> params) {
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
//		ExecutableImage ext = Program.getProgram().getModule(new AbsoluteAddress(addr));
//		String ret;
//			if (ext != null)
//				ret = inst.toString(addr, ext.getSymbolFinder());
//			else {
//				ret = inst.toString(addr, null);
//			}
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
//		System.out.println("DEBUG");
	}
}
