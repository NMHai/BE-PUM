package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;

public class nop extends X86InstructionStub {

	@Override
	public BPState execute() {
		// DO NOTHING
		return null;
	}

}
