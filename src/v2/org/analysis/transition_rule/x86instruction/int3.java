package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.Program;

import v2.org.analysis.path.BPState;
import v2.org.analysis.system.SEHHandle;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;

public class int3 extends X86InstructionStub {

	@Override
	public BPState execute() {
		
		if (env.getSystem().getSEHHandler().isSet()) {
			SEHHandle sehHandle = curState.getEnvironement().getSystem().getSEHHandler();
			sehHandle.setSEHType(SEHHandle.INTERUPT);
			return rule.processSEH(curState);
		} else {
			Program.getProgram().getLog()
					.debugString("Not processed" + inst.getName() + " at " + curState.getLocation());
		}

		return null;
	}

}
