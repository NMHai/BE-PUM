package v2.org.analysis.packer.techniques;

import org.jakstab.Program;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;

public class SEH extends PackerTechnique {

	/** 
	 * Using for record SEH
	 */
	
//	private static boolean setupSEH;
	
	public SEH () {
//		num				= 0;
		id = PackerConstants.SEH;
		name = "SEH-Done";
//		setupSEH 			= false;
	}
	
	@Override
	public boolean check (BPState curState, Program prog) {
		return false;
	}

	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		return false;
	}
}
