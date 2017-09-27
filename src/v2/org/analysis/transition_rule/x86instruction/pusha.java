package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.Value;

public class pusha extends X86InstructionStub {

	@Override
	public BPState execute() {
		// PHONG
		// Save ESP here
		Value esp_addr = env.getRegister().getRegisterValue("esp");

		env.getStack().push(env.getRegister().getRegisterValue("%eax"));
		env.getStack().push(env.getRegister().getRegisterValue("%ecx"));
		env.getStack().push(env.getRegister().getRegisterValue("%edx"));
		env.getStack().push(env.getRegister().getRegisterValue("%ebx"));

		// env.getStack().push(env.getRegister().getRegisterValue("%esp"));
		// PUSH original ESP
		env.getStack().push(esp_addr);

		env.getStack().push(env.getRegister().getRegisterValue("%ebp"));
		env.getStack().push(env.getRegister().getRegisterValue("%esi"));
		env.getStack().push(env.getRegister().getRegisterValue("%edi"));
		return null;
	}

}
