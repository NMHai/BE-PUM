package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;

public class cld extends X86InstructionStub {

	@Override
	public BPState execute() {
		// Clear direction flag
		env.getFlag().setDFlag(new BooleanValue(0));
		return null;
	}

}
