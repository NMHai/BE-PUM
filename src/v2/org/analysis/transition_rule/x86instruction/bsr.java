package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.Program;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.AnalysisBit;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;

public class bsr extends X86InstructionStub {

	@Override
	public BPState execute() {
		long temp_s = 0;
		long result = 0;
		int get_bit = rule.getBitCount(inst);

		d = rule.getValueOperand(dest, env, inst);
		s = rule.getValueOperand(src, env, inst);

		if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// X86MemoryOperand t =
			// env.getMemory(bv hghgh g ).evaluateAddress((X86MemoryOperand)
			// src, env);
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
		}

		// if (s == null || !(s instanceof LongValue))
		// return curState;

		// temp_d = ((LongValue) d).getValue();
		if (s != null && s instanceof LongValue) {
			temp_s = ((LongValue) s).getValue();
			if (temp_s == 0) {
				env.getFlag().setZFlag(new BooleanValue(true));
				// rule.setValueOperand(dest, new
				// SymbolValue(dest.toString()),
				// env, inst);
			} else {
				env.getFlag().setZFlag(new BooleanValue(false));
				result = new AnalysisBit().BSR(temp_s, get_bit);
				// Phong: change flag here
				// -------------------------------------------------------------------------------------
				for (int i = get_bit - 1; i > result; i--) {
					LongValue temp_value = new LongValue(i);
					env.getFlag().changeFlagWithDEC(temp_value, env, get_bit);
				}
				env.getRegister().setRegisterValue(dest.toString(), new LongValue(result));
				// -------------------------------------------------------------------------------------
			}
		} else {
			Program.getProgram().setLog("BSR with Symbol Value at " + curState.getLocation());
			rule.generateNextInstruction(inst, path, pathList, true);
			return curState;
		}
		return null;
	}

}
