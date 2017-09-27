package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class sahf extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO: Yen Nguyen - SAHF - Store AH into Flags
		Value AH = env.getRegister().getRegisterValue("ah");
		AH = AH.evaluate(AH.getValueMap());
		// EFLAGS(SF:ZF:0:AF:0:PF:1:CF) = AH;
		if (AH instanceof LongValue) {
			long AHValue = ((LongValue) AH).getValue();
			boolean[] AHBits = new boolean[8];
			for (int i = 0; i < 8; i++) {
				AHBits[i] = (((AHValue & (1 << i)) >> i) == 1) ? true : false;
			}
			// Loads the SF, ZF, AF, PF, and CF flags of the EFLAGS register
			// with values
			// from the corresponding bits in the AH register (bits 7, 6, 4,
			// 2, and 0, respectively)
			env.getFlag().setSFlag(new BooleanValue(AHBits[7]));
			env.getFlag().setZFlag(new BooleanValue(AHBits[6]));
			env.getFlag().setAFlag(new BooleanValue(AHBits[4]));
			env.getFlag().setPFlag(new BooleanValue(AHBits[2]));
			env.getFlag().setCFlag(new BooleanValue(AHBits[0]));
		}
		return null;
	}

}
