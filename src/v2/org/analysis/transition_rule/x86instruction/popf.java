package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.system.SEHHandle;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.Value;

public class popf extends X86InstructionStub {
	@Override
	public BPState execute() {
		Value v = null;
		if (opSize == 32) {
			v = env.getStack().pop();
		} else if (opSize == 16) {
			v = env.getStack().pop16();
		}

		// Value v = env.getStack().pop();
		env.getFlag().setAllFlagValue(v);

		// PHONG - 20150916 - SINGLE_STEP_EXCEPTION
		Value tFlag = env.getFlag().getTFlag();
		if (tFlag instanceof BooleanValue) {
			if (((BooleanValue) tFlag).getValue() == true) {
				// If tF == 1, execute exception here
				SEHHandle sehHandle = curState.getEnvironement().getSystem().getSEHHandler();
				sehHandle.setSEHType(SEHHandle.SINGLE_STEP);
				return rule.processSEH(curState);
			}
		}
		// /////////////////////////////////////////

		//System.out.println("Restore value of Flags: " + v.toString());
		return null;
	}

}
