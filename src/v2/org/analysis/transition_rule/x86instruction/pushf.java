package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;

public class pushf extends X86InstructionStub {

	@Override
	public BPState execute() {
		if (opSize == 32) {
			env.getStack().push(env.getFlag().getEFLAGS());
		} else if (opSize == 16) {
			env.getStack().push16(env.getFlag().getEFLAGS());
		}
		return null;
	}

}
