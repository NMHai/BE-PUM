package v2.org.analysis.transition_rule.x86move;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86MoveStub;
import v2.org.analysis.value.Value;

public class cmovnl extends X86MoveStub {

	@Override
	public BPState execute() {
		//boolean SF, OF;

		Value sFlag = env.getFlag().getSFlag();
		sFlag = sFlag.evaluate(sFlag.getValueMap());
		//SF = ((BooleanValue) sFlag).getValue();

		Value oFlag = env.getFlag().getOFlag();
		oFlag = oFlag.evaluate(oFlag.getValueMap());
		//OF = ((BooleanValue) oFlag).getValue();

		if (sFlag.equal(oFlag)) {
			isSet_CMOVcc = true;
		}
		return this.cmov();
	}

}
