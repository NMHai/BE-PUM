package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;

public class cmc extends X86InstructionStub {

	@Override
	public BPState execute() {
		// temp = true
		if (env.getFlag().getCFlag().equal(new BooleanValue(1))) {
			env.getFlag().setCFlag(new BooleanValue(0));
		}

		// temp = false
		else if (env.getFlag().getCFlag().equal(new BooleanValue(0))) {
			env.getFlag().setCFlag(new BooleanValue(1));
		}

		return null;
	}

}
