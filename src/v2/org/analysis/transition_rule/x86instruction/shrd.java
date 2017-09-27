package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.Immediate;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.transition_rule.AnalysisBit;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class shrd extends X86InstructionStub {

	@Override
	public BPState execute() {
		Operand count = inst.getOperand3();
		Value c = null;

		long temp_d = 0;
		long temp_s = 0;
		long temp_c = 0;
		long result = 0;
		int get_bit = rule.getBitCount(inst);

		if (dest.getClass().getSimpleName().equals("X86Register")
				|| dest.getClass().getSimpleName().equals("X86RegisterPart")
				|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// X86MemoryOperand t =
			// env.getMemory().evaluateAddress((X86MemoryOperand) dest,
			// env);
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			d = env.getMemory().getMemoryValue((X86MemoryOperand) src, inst);
		}

		if (src.getClass().getSimpleName().equals("X86Register")
				|| src.getClass().getSimpleName().equals("X86RegisterPart")
				|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
			s = env.getRegister().getRegisterValue(src.toString());
		}

		if (count.getClass().getSimpleName().equals("X86Register")
				|| count.getClass().getSimpleName().equals("X86RegisterPart")
				|| count.getClass().getSimpleName().equals("X86SegmentRegister")) {
			c = env.getRegister().getRegisterValue(count.toString());
		} else if (count.getClass().getSimpleName().equals("Immediate")) {
			c = new LongValue(Convert.convetUnsignedValue(((Immediate) count).getNumber().intValue(),
					8));
		}

		if (d != null && d instanceof LongValue && s != null && s instanceof LongValue && c != null
				&& c instanceof LongValue) {
			temp_d = ((LongValue) d).getValue();
			temp_s = ((LongValue) s).getValue();
			temp_c = ((LongValue) c).getValue();

			if (temp_c > 0) {
				result = new AnalysisBit().SHRD(temp_d, temp_s, temp_c, get_bit);
				// PHONG: change here. ChangeFlag here
				// ------------------------------------------------------------------------------------
				if (temp_c < get_bit) {
					long array_dest[] = new long[get_bit];
					long temp = temp_d;
					for (int i = 0; i < get_bit; i++) {
						array_dest[i] = 0;
					}
					int i = 0;
					while (temp != 0) {
						array_dest[i] = temp % 2;
						temp = temp / 2;
						i++;
					}
					// change another flag
					LongValue destflag = new LongValue(result);
					LongValue srcflag = new LongValue(0);
					env.getFlag().changeFlagWithADD(destflag, srcflag, env, get_bit);
					// change OFlag here
					// change CFlag here
					BooleanValue cflag = new BooleanValue(array_dest[get_bit - 1] != 0);
					env.getFlag().setCFlag(cflag);
				}
				// ---------------------------------------------------------------------------------------
				if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(result), inst);
				} else {
					env.getRegister().setRegisterValue(dest.toString(), new LongValue(result));
				}
			}
		}

		return null;
	}

}
