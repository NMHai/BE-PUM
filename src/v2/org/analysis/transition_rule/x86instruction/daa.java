package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class daa extends X86InstructionStub {

	@Override
	public BPState execute() {
		Value AL = env.getRegister().getRegisterValue("al");
		Value AF = env.getFlag().getAFlag();
		Value CF = env.getFlag().getCFlag();
		boolean isCF = false;
		if ((AL instanceof LongValue && ((LongValue) AL).getValue() > 9)
				|| (AF instanceof BooleanValue && ((BooleanValue) AF).getValue())) {
			isCF = true;
		}

		if (isCF) {
			env.getRegister().add("al", new LongValue(6));
			env.getFlag().setAFlag(new BooleanValue(true));
			// change Flag cF
			// Tu: change flag with DAA ?
			// env.getFlag().changCFlagWithDAA(env.getRegister().getRegisterValue("al"),
			// new LongValue(6), env);
		}

		if ((AL instanceof LongValue && ((LongValue) AL).getValue() > 0x9F)
				|| (CF instanceof BooleanValue && ((BooleanValue) CF).getValue())) {
			env.getRegister().add("al", new LongValue(0x60));
			env.getFlag().setCFlag(new BooleanValue(true));
		}
		return null;
	}

}
