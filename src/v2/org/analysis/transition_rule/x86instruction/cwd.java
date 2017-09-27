package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.AnalysisBit;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class cwd extends X86InstructionStub {

	@Override
	public BPState execute() {
		Value AX = env.getRegister().getRegisterValue("ax");
		long temp_AX = ((LongValue) AX).getValue();
		long temp = 0;

		temp = new AnalysisBit().CWD_CDQ(temp_AX);
		env.getRegister().setRegisterValue("dx", new LongValue(temp));

		return null;
	}

}
