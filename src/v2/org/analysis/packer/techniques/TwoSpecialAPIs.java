package v2.org.analysis.packer.techniques;

import org.jakstab.Program;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;

public class TwoSpecialAPIs extends PackerTechnique {

	/** 
	 * Using for record two APIs using
	 */
	private boolean firstCall = false; 
//	private static boolean useLoadLibrary;
//	private static boolean useGetProcAddress;
	
	public TwoSpecialAPIs () {
//		num				= 0;
		name = "TwoSpecialAPIs-Done";
		id = PackerConstants.TWO_APIS;
//		useLoadLibrary				= false;
//		useGetProcAddress			= false;
	}
	
	@Override
	public boolean check (BPState curState, Program prog) {
//		if (curState == null || curState.getInstruction() == null) {
//			return false;
//		}
//		
//		String insName = curState.getInstruction().getName();
//		if (insName.contains("call")) {
//			Operand dest = curState.getInstruction().getOperand(0);
//			Value aVal = PackerHelper.GetOperandValue(curState, dest);
//			if (aVal instanceof LongValue)
//			{
//				String apiName = PackerHelper.GetAPIName(curState, aVal);
//				if (apiName != null)
//				{
//					if (apiName.contains("LoadLibrary"))
//					{
//						useLoadLibrary = true;
//					}
//					else if (apiName.contains("GetProcAddress"))
//					{
//						useGetProcAddress = true;
//					}
//				}
//			}	
//		}
//		if (useLoadLibrary && useGetProcAddress)
//		{
////			num++;
//			long location = curState.getLocation().getValue();
//			if (!contain(location)) {
//				num++;
//				locList.add(location);
//			}
//
//			useLoadLibrary = false;
//			useGetProcAddress = false;
//			return true;
//		}
		return false;
	}

	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		String name = apiName.toLowerCase();
		if (contain(location)) {
			return false;
		}
		
		for (String twoSpecialName: PackerConstants.TWOSPECIAL_APIs_Main) {
			if (name.contains(twoSpecialName.toLowerCase())) {
				insertLocation(location);
//				num++;
				return true;								
			}
		}
		return false;
	}

	public boolean isFirstCall() {
		return firstCall;
	}

	public void setFirstCall(boolean firstCall) {
		this.firstCall = firstCall;
	}
}
