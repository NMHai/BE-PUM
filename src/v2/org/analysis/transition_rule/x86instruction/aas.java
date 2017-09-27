package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class aas extends X86InstructionStub {

	@Override
	public BPState execute() {
		Value AL = env.getRegister().getRegisterValue("al");
		Value AF = env.getFlag().getAFlag();
		boolean isCF = false;
		if ((AL instanceof LongValue) && ((LongValue) AL).getValue() > 9
				|| (AF instanceof BooleanValue && ((BooleanValue) AF).getValue())) {
			isCF = true;
		}

		if (isCF) {
			Long t = ((LongValue) AL).getValue() - 6;
			env.getRegister().setRegisterValue("al", new LongValue(t & 0xF));
			env.getRegister().sub("ah", new LongValue(1));
		}
		env.getFlag().setCFlag(new BooleanValue(isCF));
		env.getFlag().setAFlag(new BooleanValue(isCF));
		return null;
	}

}
