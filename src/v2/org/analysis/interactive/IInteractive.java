/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.interactive;

import java.util.List;
import org.jakstab.Program;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;
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
public interface IInteractive {

	public enum ACTION_OP {

		NOP,
		PASS
	}

	public enum MEM_OP {

		BYTE, // Value
		WORD, // Value
		DWORD, // Value
		QWORD, // Value
		BYTE_ARRAY, // byte[]
		STRING // String
	}

	void _init(Program program, X86TransitionRule rule, BPPath path, BPState state);

	ACTION_OP onInstruction(BPPath path, long address, Instruction ins);

	// on read/write memory
	// value type depend MEM_OP
	void onMemoryRead(MEM_OP op, long address, Object readData);

	void onMemoryWrite(MEM_OP op, long address, Object writeData);

	Value onAPI(BPState state, long address, Instruction inst, String apiName, List<Long> params);

	void onSymbolicBranch(X86CondJmpInstruction inst, Formulas condition, SolverResult solver);
	
}
