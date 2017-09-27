package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class xlat extends X86InstructionStub {

	@Override
	public BPState execute() {
		Value ebx = env.getRegister().getRegisterValue("ebx");
		if (ebx instanceof LongValue) {
			long e = ((LongValue) ebx).getValue();
			// Value m = env.getMemory().getWordMemoryValue(e);
			// env.getRegister().setRegisterValue("al", m);
			// PHONG: fix here
			Value m = env.getMemory().getByteMemoryValue(
					e + ((LongValue) env.getRegister().getRegisterValue("al")).getValue());
			env.getRegister().setRegisterValue("al", m);
		} else {
			Value al = env.getRegister().getRegisterValue("al");
			if (al instanceof LongValue) {
				long e = ((LongValue) al).getValue();
				Value m = env.getMemory().getWordMemoryValue(e);
				env.getRegister().setRegisterValue("al", m);
			}
		}
		// long print = ((LongValue) temp).getValueOperand();
		// System.out.println(print);
		return null;
	}

}
