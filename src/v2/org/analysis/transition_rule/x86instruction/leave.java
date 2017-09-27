package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;

public class leave extends X86InstructionStub {

	@Override
	public BPState execute() {
		env.getRegister().setRegisterValue("esp", env.getRegister().getRegisterValue("ebp"));
		env.getRegister().setRegisterValue("ebp", env.getStack().pop());

		return null;
	}

}
