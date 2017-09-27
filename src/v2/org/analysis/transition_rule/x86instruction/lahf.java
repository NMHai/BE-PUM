package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;

public class lahf extends X86InstructionStub {

	@Override
	public BPState execute() {
		long bit0, bit1, bit2, bit3, bit4, bit5, bit6, bit7;

		if (env.getFlag().getCFlag().equal(new BooleanValue(1))) {
			bit0 = 1;
		} else {
			bit0 = 0;
		}

		bit1 = 2;

		if (env.getFlag().getPFlag().equal(new BooleanValue(1))) {
			bit2 = 4;
		} else {
			bit2 = 0;
		}

		bit3 = 0;

		if (env.getFlag().getAFlag().equal(new BooleanValue(1))) {
			bit4 = 16;
		} else {
			bit4 = 0;
		}

		bit5 = 0;

		if (env.getFlag().getZFlag().equal(new BooleanValue(1))) {
			bit6 = 64;
		} else {
			bit6 = 0;
		}

		if (env.getFlag().getSFlag().equal(new BooleanValue(1))) {
			bit7 = 128;
		} else {
			bit7 = 0;
		}

		long result = 0;
		result = bit0 + bit1 + bit2 + bit3 + bit4 + bit5 + bit6 + bit7;

		// env.getRegister().setRegisterValue("al", new LongValue(result));
		// PHONG: fix here
		env.getRegister().setRegisterValue("ah", new LongValue(result));
		return null;
	}

}
