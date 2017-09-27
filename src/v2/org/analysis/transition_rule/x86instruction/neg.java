package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class neg extends X86InstructionStub {

	@Override
	public BPState execute() {
		Value temp = rule.getValueOperand(inst.getOperand1(), env, inst);
		temp = temp.notFunction();
		temp = temp.addFunction(new LongValue(1));
		rule.setValueOperand(inst.getOperand1(), temp, env, inst);

		env.getFlag().changeFlagWithNEG(temp, env, opSize);
		return null;
	}

}
