package v2.org.analysis.packer.techniques;

import org.jakstab.Program;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;

public class IndirectJump extends PackerTechnique {

	/** 
	 * Using for record indirect jump
	 */
	
//	private static int numOfIndirectCall;
//	private static int numOfIndirectJump;
//	private static ArrayList<Long> savedIndirectState;
	
	public IndirectJump ()
	{
//		numOfIndirectCall 		= 0;
//		numOfIndirectJump		= 0;
//		num = 0;
		id = PackerConstants.INDIRECT_JUMP;
		name = "IndirectJump-Done";
//		savedIndirectState		= new ArrayList<Long>();
	}
	
	@Override
	public boolean check (BPState curState, Program prog)
	{
//		if (curState == null || curState.getInstruction() == null) {
//			return false;
//		}
//		
//		Instruction ins = curState.getInstruction();
//		String insName = ins.getName();
//		long location = curState.getLocation().getValue();
//		if (PackerHelper.IsExisted(savedIndirectState, new Long(curState.getLocation().getValue())))
//		{
//			return false;
//		}
//		if (insName.contains("call"))
//		{
//			Operand dest = curState.getInstruction().getOperand(0);
//			if (dest instanceof X86Register || dest instanceof X86MemoryOperand)
//			{
//				numOfIndirectCall++;
//				if (!contain(location)) {
//					num++;
//					locList.add(location);
//					return true;
//				}				
//			}
//		}
//		else if (ins instanceof X86JmpInstruction || ins instanceof X86CondJmpInstruction)
//		{
//			Operand dest = curState.getInstruction().getOperand(0);
//			if (dest instanceof X86Register || dest instanceof X86MemoryOperand)
//			{
//				numOfIndirectJump++;
////				num ++;
//				
//				if (!contain(location)) {
//					num++;
//					locList.add(location);
//					return true;
//				}				
//			}
//		}
//		savedIndirectState.add(new Long(curState.getLocation().getValue()));
		return false;
	}

	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		return false;
	}	
}
