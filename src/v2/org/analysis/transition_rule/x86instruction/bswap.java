package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86Register;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.AnalysisBit;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;

public class bswap extends X86InstructionStub {

	@Override
	public BPState execute() {
		// swap bit thanh ghi 32 [0...7] [8...15] [16...23] [24...31]
		// ket qua [24...31] [8....15] [16...23] [0....7]

		long temp_d = 0;
		long temp = 0;

		if (dest instanceof X86Register) {
			d = env.getRegister().getRegisterValue(dest.toString());

			if (d != null && d instanceof LongValue) {
				temp_d = ((LongValue) d).getValue();
				temp = new AnalysisBit().SwapBit32(temp_d);
				env.getRegister().setRegisterValue(dest.toString(), new LongValue(temp));
			} else {
				env.getRegister().setRegisterValue(dest.toString(), new SymbolValue(dest.toString()));
			}
		}

		return null;
	}

}
