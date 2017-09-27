package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.BitVector;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class cwde extends X86InstructionStub {

	@Override
	public BPState execute() {
		if (opSize == 16) {
			Value al = env.getRegister().getRegisterValue("al");
			// Value ah = env.getRegister().getRegisterValue("ah");
			if (al instanceof LongValue) {
				long tempal = ((LongValue) al).getValue();
				// tempax = 21645;
				// int t = (int) (tempal / Math.pow(2, 7));
				long ax = BitVector.signExtend(tempal, opSize);
				// long ax = (long) (tempal + t * 4294901760l);
				// Change flag aad. flag SF, ZF, PF
				env.getRegister().setRegisterValue("ax", new LongValue(ax));
			}
		} else if (opSize == 32) {
			Value ax = env.getRegister().getRegisterValue("ax");
			// Value ah = env.getRegister().getRegisterValue("ah");
			if (ax instanceof LongValue) {
				long tempax = ((LongValue) ax).getValue();
				// tempax = 21645;
				long eax = BitVector.signExtend(tempax, opSize);
				// Change flag aad. flag SF, ZF, PF
				env.getRegister().setRegisterValue("eax", new LongValue(eax));
			}
		}

		return null;
	}

}
