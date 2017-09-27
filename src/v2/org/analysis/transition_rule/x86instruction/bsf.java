package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.Program;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.AnalysisBit;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;

public class bsf extends X86InstructionStub {

	public bsf() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BPState execute() {
		long temp_d = 0;
		long temp_s = 0;
		long result = 0;
		int get_bit = rule.getBitCount(inst);

		if (dest.getClass().getSimpleName().equals("X86Register")
				|| dest.getClass().getSimpleName().equals("X86RegisterPart")
				|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		}

		if (src.getClass().getSimpleName().equals("X86Register")
				|| src.getClass().getSimpleName().equals("X86RegisterPart")
				|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
			s = env.getRegister().getRegisterValue(src.toString());
		} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// X86MemoryOperand t =
			// env.getMemory().evaluateAddress((X86MemoryOperand) src, env);
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			s = env.getMemory().getMemoryValue((X86MemoryOperand) src, inst);
		}

		if (d instanceof LongValue && s instanceof LongValue) {
			temp_d = ((LongValue) d).getValue();
			temp_s = ((LongValue) s).getValue();
			if ((temp_d == 0) && (temp_s == 0)) {
				env.getFlag().setZFlag(new BooleanValue(true));
			} else {
				env.getFlag().setZFlag(new BooleanValue(false));
				result = new AnalysisBit().BSF(temp_s, get_bit);
				env.getRegister().setRegisterValue(dest.toString(), new LongValue(result));
				// PHONG: change flag here
				// As documentation: flag will change when temp + 1. Dest
				// will
				// be number of loops
				// --------------------------------------------------------------------------------------
				for (int i = 0; i < result; i++) {
					LongValue temp_value = new LongValue(i);
					env.getFlag().changeFlagWithINC(temp_value, env, get_bit);
				}
				// --------------------------------------------------------------------------------------
			}
		} else {
			Program.getProgram().setLog("BSF with Symbol Value at " + curState.getLocation());
			rule.generateNextInstruction(inst, path, pathList, true);
			return curState;
		}
		/*
		 * PHONG: change here env.getFlag().setCFlag(new BooleanValue(false));
		 * env.getFlag().setOFlag(new BooleanValue(false));
		 * env.getFlag().setSFlag(new BooleanValue(false));
		 * env.getFlag().setAFlag(new BooleanValue(false));
		 * env.getFlag().setPFlag(new BooleanValue(false));
		 */

		return null;
	}

}
