package v2.org.analysis.packer.techniques;

import org.jakstab.Program;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;

public class AntiDebugging extends PackerTechnique {

	/** 
	 * Using for record anti-debugging
	 */	
		
	public AntiDebugging () {
//		num = 0;
		name = "AntiDebugging-Done";
		id = PackerConstants.ANTI_DEBUGGING;
	}
	
	@Override
	public boolean check (BPState curState, Program prog) {
//		if (curState == null || curState.getInstruction() == null) {
//			return false;
//		}
//		long location = curState.getLocation().getValue();
//		String insName = curState.getInstruction().getName();
//		if (insName.contains("call")) {
//			Operand dest = curState.getInstruction().getOperand(0);
//			Value aVal = PackerHelper.GetOperandValue(curState, dest);
//			if (aVal instanceof LongValue)
//			{
//				String apiName = PackerHelper.GetAPIName(curState, aVal);
//				if (apiName != null) {
//					for (String antiAPI: ANTI_APIs) {
//						if (apiName.contains(antiAPI)) {
//							if (!contain(location)) {
//								insertLocation(location);
//								num++;
//								return true;
//							}							
//						}
//					}
//				}
//			}	
//		}
		return false;
	}	
	
	@Override
	public boolean checkAPIName (String apiName, long location) {
		String name = apiName.toLowerCase();
		if (contain(location)) {
			return false;
		}
		for (String antiAPI: PackerConstants.ANTIDEBUGGING_APIs) {
			if (name.contains(antiAPI.toLowerCase())) {
				insertLocation(location);
				return true;
			}
		}
		return false;
	}
}
