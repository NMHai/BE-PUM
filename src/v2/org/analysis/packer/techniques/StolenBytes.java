package v2.org.analysis.packer.techniques;

import org.jakstab.Program;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;

public class StolenBytes extends PackerTechnique {

	/** 
	 * Using for record stolen bytes-
	 */
	
	public StolenBytes () {
//		num				= 0;
		name = "StolenBytes-Done";
		id = PackerConstants.STOLEN_BYTES;
	}
	
	@Override
	public boolean check (BPState curState, Program prog) {
		return false;
	}

	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		String name = apiName.toLowerCase();
		if (contain(location)) {
			return false;
		}
		
		for (String antiAPI: PackerConstants.STOLENBYTE_APIs) {
			if (name.contains(antiAPI.toLowerCase())) {
				insertLocation(location);
				return true;							
			}
		}
		return false;
	}	
}
