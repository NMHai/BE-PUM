package v2.org.analysis.packer.techniques;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jakstab.Program;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;

public class OverlappingFunctionV1 extends PackerTechnique {

	/** 
	 * Using for record overlapping function
	 */
	
//	private static ArrayList<PackerSavedBlock> funcs;
//	private static long tracingFuncLoc;
//	private static ArrayList<Long> savedListFunc;
	private Map<Long, Long> funcList; 
	private List<Long> callAddr; 
	private Set<String> detailedList;
	
//	private static boolean isOverlap;
	
	public OverlappingFunctionV1 () {
		name = "OverlappingFunction-Done";
		id = PackerConstants.OVERLAPPING_FUNC;
		funcList = new HashMap<Long, Long>();
		callAddr = new ArrayList<Long> ();
		detailedList = new HashSet<String> ();
//		tracingFuncLoc					= 0x0;
//		funcs							= new ArrayList<PackerSavedBlock>();		
//		savedListFunc					= new ArrayList<Long>();		
//		isOverlap						= false;
	}
	
//	@Override
//	public boolean check (BPState curState, Program prog)
//	{
//		if (curState == null || curState.getInstruction() == null) {
//			return false;
//		}
//		
//		boolean ret = false;
//		if (curState.getInstruction().getName().contains("call"))
//		{
//			Operand op = curState.getInstruction().getOperand(0);
//			Value aVal = PackerHelper.GetOperandValue(curState, op);
//			if (aVal instanceof LongValue)
//			{
//				AbsoluteAddress aAddr = new AbsoluteAddress(((LongValue) aVal).getValue());
//				tracingFuncLoc = aAddr.getValue();
//			}
//		}
//		
//		Instruction ins = curState.getInstruction();
//		String insName = ins.getName();
//		if (insName.contains("ret"))
//		{
//			PackerSavedBlock savedFunc = new PackerSavedBlock(tracingFuncLoc
//					 										, curState.getLocation().getValue());
//			funcs.add(savedFunc);
//			int size = funcs.size();
//			if (size > 1)
//			{
//				for (PackerSavedBlock func: funcs)
//				{
//					if (PackerHelper.CheckOverlapping(func, savedFunc)
//							&& !PackerHelper.IsExisted(savedListFunc, new Long(func.getBeginBlock()))
//							&& !PackerHelper.IsExisted(savedListFunc, new Long(savedFunc.getBeginBlock())))
//					{
////						num++;
//						long location = curState.getLocation().getValue();
//						if (!contain(location)) {
////							num++;
//							locList.add(location);
//						}
//			
//						ret = true;
//						isOverlap = true;
//						savedListFunc.add(new Long(func.getBeginBlock()));
//						savedListFunc.add(new Long(savedFunc.getBeginBlock()));
//						break;
//					}
//				}
//			}
//			
//			if (isOverlap)
//			{
//				funcs = PackerHelper.ClearStates(funcs);
//				isOverlap = false;
//			}
//		}
//		return ret;
//	}

	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean check(BPState curState, Program prog) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkCallAddr(long value) {
		// TODO Auto-generated method stub		
		return checkAddrInside(value);
	}
	
	private boolean checkAddrInside(long value) {
		for (Map.Entry<Long, Long> pair : funcList.entrySet()) {
			 if (pair.getKey() < value && pair.getValue()> value){  
//					 && !Program.getProgram().getBPCFG().containVertex(value)) {
				detailedList.add("0x" + Long.toHexString(value) + " in 0x"+ Long.toHexString(pair.getKey()) 
						+ "-0x" + Long.toHexString(pair.getValue())); 
				return true;
			}
		} 
	    
	    return false;
	}
	
//	@Override
//	public String toString() {
//		String ret = "Name:" + name + ", ID:" + id + ", Location:";
//		for (String location: detailedList) {
//			ret += location + ", ";
//		}
//		
//		return ret;
//	}

	public void insertCallAddr(long value) {
		// TODO Auto-generated method stub
		if (!callAddr.contains(value)) {
//			funcList.put(value, (long) -1);
			callAddr.add(value);
		}
	}

	public boolean checkRetAddr(long end) {
		// TODO Auto-generated method stub
		int t = callAddr.size(); 
		if (t > 0) {
			long start = callAddr.remove(t-1);			
			for (Map.Entry<Long, Long> pair : funcList.entrySet()) {
				if ((pair.getKey() < start && pair.getValue() > start && pair.getValue() < end)
						|| (pair.getKey() > start && pair.getKey() < end && pair.getValue() > end)) {
					// && !Program.getProgram().getBPCFG().containVertex(value)) {
					detailedList.add("0x" + Long.toHexString(pair.getKey()) + "-0x" + Long.toHexString(pair.getValue())
							+ " in " + "0x" + Long.toHexString(start) + "-0x" + Long.toHexString(end));
					funcList.put(start, end);
					return true;
				}
			}
			funcList.put(start, end);
		}				
		return false;
	}

//	public void insertRetAddr(long value) {
//		// TODO Auto-generated method stub
//		int t = callAddr.size(); 
//		if (t > 0) {
//			long v = callAddr.remove(t-1);			
//			funcList.put(v, value);
//		}
//	}	
}
