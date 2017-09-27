package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class cltd extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO: Yen Nguyen - CWD/CDQ - Convert Word to Doubleword/Convert
		// Doubleword to Quadword// PHONG: fix here
		Value EAX = env.getRegister().getRegisterValue("eax");
		EAX = EAX.evaluate(EAX.getValueMap());

		// The CWD instruction copies the sign (bit 15) of the value in the
		// AX register into every bit position in the DX register
		if (EAX instanceof LongValue) {
			long EAXValue = ((LongValue) EAX).getValue();
			boolean signBit = (((EAXValue & (1 << 31)) >> 31) == 1) ? true : false;

			if (signBit) {
				env.getRegister().setRegisterValue("edx", new LongValue(0xFFFFFFFF));
			} else {
				env.getRegister().setRegisterValue("edx", new LongValue(0x00000000));
			}
		}
		return null;
	}

}
