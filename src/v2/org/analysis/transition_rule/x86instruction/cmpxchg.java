package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.Value;

public class cmpxchg extends X86InstructionStub {
	@Override
	public BPState execute() {
		// ystem.out.println();
		Value acc = null;
		if (opSize == 8) {
			acc = env.getRegister().getRegisterValue("al");
		} else if (opSize == 16) {
			acc = env.getRegister().getRegisterValue("ax");
		} else if (opSize == 32) {
			acc = env.getRegister().getRegisterValue("eax");
		}

		Value destVal = rule.getValueOperand(dest, env, inst);
		Value srcVal = rule.getValueOperand(src, env, inst);

		if (destVal != null && srcVal != null && acc != null && acc.equal(destVal)) {
			env.getFlag().setZFlag(new BooleanValue(true));
			rule.setValueOperand(dest, srcVal, env, inst);
		} else {
			env.getFlag().setZFlag(new BooleanValue(false));

			if (opSize == 8) {
				env.getRegister().setRegisterValue("al", destVal);
			} else if (opSize == 16) {
				env.getRegister().setRegisterValue("ax", destVal);
			} else if (opSize == 32) {
				env.getRegister().setRegisterValue("eax", destVal);
			}
		}

		env.getFlag().changeFlagWithCMP(destVal, acc, env, opSize);
		
		return null;
	}

}
