/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.transition_rule
 * File name: X86InstructionStub.java
 * Created date: Sep 23, 2015
 * Description:
 */
package v2.org.analysis.transition_rule.stub;

import org.jakstab.asm.AbsoluteAddress;

import v2.org.analysis.path.BPState;
import v2.org.analysis.system.SEHHandle;

/**
 * @author Yen Nguyen
 *
 */
public abstract class X86InstructionStub extends AssemblyInstructionStub {

	public X86InstructionStub() {
		this.groupName = "X86Instruction";
	}

	@Override
	protected BPState preExecute() {
		// PHONG - 20150921 - Check SEH before going ///////////////
		AbsoluteAddress curAddr = curState.getLocation();
		if (rule.getSEHHandle().causeException(curAddr)) {
			SEHHandle sehHandle = curState.getEnvironement().getSystem().getSEHHandler();
			sehHandle.setSEHType(SEHHandle.SINGLE_STEP);
			return rule.processSEH(curState);
		}
		
		return null;
	}
	
	@Override
	protected void postExecute() {
		// DO NOTHING
	}
}
