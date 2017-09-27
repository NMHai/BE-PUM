package v2.org.analysis.transition_rule.x86move;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86MoveStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.Value;

public class cmovle extends X86MoveStub {
	@Override
	public BPState execute() {
		// boolean ZF, SF, OF;

		Value zFlag = env.getFlag().getZFlag();
		zFlag = zFlag.evaluate(zFlag.getValueMap());
		// ZF = ((BooleanValue) zFlag).getValue();

		Value sFlag = env.getFlag().getSFlag();
		sFlag = sFlag.evaluate(sFlag.getValueMap());
		// SF = ((BooleanValue) sFlag).getValue();

		Value oFlag = env.getFlag().getOFlag();
		oFlag = oFlag.evaluate(oFlag.getValueMap());
		// OF = ((BooleanValue) oFlag).getValue();

		if (zFlag.equal(new BooleanValue(true)) || !(sFlag.equal(oFlag))) {
			isSet_CMOVcc = true;
		}
		return this.cmov();
	}

}
