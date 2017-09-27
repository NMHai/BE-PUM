package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.BitVector;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class aad extends X86InstructionStub {

	@Override
	public BPState execute() {
		// Khanh: Check later
		Value al = env.getRegister().getRegisterValue("al");
		Value ah = env.getRegister().getRegisterValue("ah");
		if (al instanceof LongValue && ah instanceof LongValue) {
			long tempal = ((LongValue) al).getValue();
			long tempah = ((LongValue) ah).getValue();
			long temp_al = tempal + (tempah * 10);
			long temp_ah = 0;
			env.getRegister().setRegisterValue("al", new LongValue(temp_al));
			env.getRegister().setRegisterValue("ah", new LongValue(temp_ah));
			// Change flag aad. flag SF, ZF, PF

			long t = Convert.convertSignedValue(temp_al, opSize);
			env.getFlag().setPFlag(new BooleanValue(BitVector.getParityBit(t)));
			env.getFlag().setSFlag(new BooleanValue(t < 0));
			env.getFlag().setZFlag(new BooleanValue(t == 0));
		}
		return null;
	}

}
