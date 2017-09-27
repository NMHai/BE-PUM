package v2.org.analysis.transition_rule.x86move;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.BitVector;
import v2.org.analysis.transition_rule.stub.X86MoveStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class movsx extends X86MoveStub {

	@Override
	public BPState execute() {
		Value source = rule.getValueOperand(src, env, inst);
		if (source != null && source instanceof LongValue) {
			long t = ((LongValue) source).getValue();
			int s = rule.geSizeOprand(src);
			int sign = BitVector.getMSB(t, s);
			rule.setValueOperand(dest, new LongValue(BitVector.extend(t, sign, s, rule.geSizeOprand(dest))), env, inst);
		} else {
			rule.setValueOperand(dest, source, env, inst);
		}
		return null;
	}

}
