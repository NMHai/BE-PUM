package v2.org.analysis.packer.techniques;

import org.jakstab.Program;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;

public class TimingCheck extends PackerTechnique {

	/** 
	 * Using for record timing check-
	 */
	
	public TimingCheck ()
	{
//		num				= 0;
		id = PackerConstants.TIMING_CHECK;
		name = "TimingCheck-Done";
	}
	
	@Override
	public boolean check (BPState curState, Program prog) {
		if (curState == null || curState.getInstruction() == null) {
			return false;
		}
		
		long location = curState.getLocation().getValue();
		if (contain(location)) {
			return false;
		}
		
		String insName = curState.getInstruction().getName();
		if (insName.contains("RDTSC")) {
			resultList.add(location);
			return true;
		}
		return false;
	}

	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		String name = apiName.toLowerCase();
		if (contain(location)) {
			return false;
		}
		
		for (String antiAPI: PackerConstants.TIMINGCHECK_APIs) {
			if (name.contains(antiAPI.toLowerCase())) {
				insertLocation(location);
//				num++;
				return true;							
			}
		}
		return false;
	}	
}
