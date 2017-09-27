package v2.org.analysis.packer.techniques;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jakstab.Program;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.packer.PackerManager;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class Checksumming extends PackerTechnique {

	/**
	 * Using for record checksum
	 */
	private final int DEFAULT_NUMBER = 80;
//	private final int MAX_NUM_INST = 7;
//	private long curLoc = -1;
	private Map<Long, Integer> locationNum;
	private Map<Long, Long> locationAddr;
	private List<Long> noneList;
	private List<Long> traceList;
	private boolean isPossible = false;

	public Checksumming() {
		id = PackerConstants.CHECKSUMMING;
		name = "Checksumming-Done";
		locationNum = new HashMap<>();
		locationAddr = new HashMap<>();
		noneList = new ArrayList<> ();
		traceList = new ArrayList<> ();
	}

	// @Override
	// public boolean check (BPState curState, Program prog) {
	// if (curState == null || curState.getInstruction() == null) {
	// return false;
	// }
	//
	// Operation src = curState.getInstruction().getOperand(1);
	// Operation dest = curState.getInstruction().getOperand(0);
	// if (src == null || dest == null) {
	// return false;
	// }
	//
	// String destStringValue = getValue(curState, dest);
	// long location = curState.getLocation().getValue();
	// int opCount = curState.getInstruction().getOperandCount();
	// Instruction inst = curState.getInstruction();
	// if (!contain(location) && opCount >= 2 &&
	// inst != null && PackerConstants.isSpecialInstruction(inst.getName())) {
	// if (tempLocation.containsKey(location)) {
	// int num = tempLocation.get(location);
	// if (tempLocation.get(location) > DEFAULT_NUMBER) {
	// locList.add(location);
	// return true;
	// }
	//
	// if (destValue.get(location).equals(destStringValue)) {
	// tempLocation.put(location, num + 1);
	// } else {
	// tempLocation.remove(location);
	// }
	// return false;
	// }
	//
	//
	// if (src != null && src instanceof X86MemoryOperand) {
	// Value addrVal =
	// curState.getEnvironement().getMemory().calculateAddress((X86MemoryOperand)src);
	// if (addrVal != null && addrVal instanceof LongValue) {
	// // AbsoluteAddress aAddr = new AbsoluteAddress(((LongValue)
	// addrVal).getValue());
	// // if (PackerHelper.IsI nCodeSection(prog, aAddr)) {
	// if (inst instanceof X86Instruction) {
	// if (((X86Instruction) inst).hasPrefixREPNZ() || ((X86Instruction)
	// inst).hasPrefixREPZ()) {
	// locList.add(location);
	// return true;
	// }
	// }
	// destValue.put(location, destStringValue);
	// tempLocation.put(location, 1);
	// }
	// }
	// if (src != null && src instanceof X86MemoryOperand) {
	// Value addrVal =
	// curState.getEnvironement().getMemory().calculateAddress((X86MemoryOperand)src);
	// if (addrVal != null && addrVal instanceof LongValue) {
	// locList.add(location);
	// return true;
	// }
	// }
	// }
	// return false;
	// }

//	@Override
//	public boolean check(BPState curState, Program prog) {
//		if (curState == null || curState.getInstruction() == null) {
////				|| curState.getInstruction().getName().startsWith("mov")) {
//			return false;
//		}
//
//		Operation src = curState.getInstruction().getOperand(1);
//		Operation dest = curState.getInstruction().getOperand(0);
//		if (src == null || dest == null || !(src instanceof X86MemoryOperand) || dest instanceof X86MemoryOperand) {
//			return false;
//		}
//
//		long location = curState.getLocation().getValue();
//		Instruction inst = curState.getInstruction();
//		if (!contain(location) && PackerConstants.isSpecialInstruction(inst.getName())) {
//			if (tempLocation.containsKey(location)) {
//				int num = tempLocation.get(location);
//				if (num > DEFAULT_NUMBER) {
//					locList.add(location);
//					return true;
//				}
//				tempLocation.put(location, num + 1);
//				return false;
//			}
//
//			Value addrVal = curState.getEnvironement().getMemory().calculateAddress((X86MemoryOperand) src);
//			if (addrVal != null && addrVal instanceof LongValue) {
//				// AbsoluteAddress aAddr = new AbsoluteAddress(((LongValue)
//				// addrVal).getValue());
//				// if (PackerHelper.IsI nCodeSection(prog, aAddr)) {
//				if (inst instanceof X86Instruction) {
//					if (((X86Instruction) inst).hasPrefixREPNZ() || ((X86Instruction) inst).hasPrefixREPZ()) {
//						locList.add(location);
//						return true;
//					}
//				}				
//				tempLocation.put(location, 1);
//			}
//		}
//		return false;
//	}
	
	@Override
	public boolean check(BPState curState, Program prog) {
		if (curState == null || curState.getInstruction() == null) {
			return false;
		}

		long location = curState.getLocation().getValue();
//		if (location == 4198545) {
//			System.out.println("Debug");
//		}
		
		if (isPossible && !traceList.contains(location)) {
			traceList.add(location);
		}
		
		if (noneList.contains(location)) {
			return false;
		}
		
		int opCount = curState.getInstruction().getOperandCount();
		Instruction inst = curState.getInstruction();
		if (!contain(location) && opCount >= 2 && inst != null && PackerConstants.isSpecialInstruction(inst.getName())) {
			Operand src = curState.getInstruction().getOperand(1);
			if (src != null && src instanceof X86MemoryOperand) {
				Value destAddr = curState.getEnvironement().getMemory().calculateAddress((X86MemoryOperand) src);
				if (destAddr != null && destAddr instanceof LongValue) {
					long address = ((LongValue)destAddr).getValue();
					if (locationNum.containsKey(location)) {
						int num = locationNum.get(location);
						long addr = locationAddr.get(location);
						if (checkCond(addr, address, num)) {
							if (num > DEFAULT_NUMBER) {
								if (checkCondition()) {
									resultList.add(location);	
									System.out.println("CS at 0x" + Long.toHexString(location) + " " + traceList.toString());
									isPossible = false;
									traceList.clear();
									return true;
								} else {
									noneList.add(location);
									isPossible = false;
									traceList.clear();
									return false;
								}
							}
							locationNum.put(location, num + 1);
//							locationAddr.put(location, address);
						} else {
							noneList.add(location);
						}
						return false;
					}

					if (inst instanceof X86Instruction) {
						if (((X86Instruction) inst).hasPrefixREPNZ() || ((X86Instruction) inst).hasPrefixREPZ()) {
							noneList.add(location);
							isPossible = false;
							traceList.clear();
							return false;
						}
					}
					locationNum.put(location, 1);
					locationAddr.put(location, address);
					traceList.clear();
					isPossible = true;
					traceList.add(location);					
				}
			}
		}
		return false;
	}


	private boolean checkCondition() {
		// TODO Auto-generated method stub
		if (traceList.size() == 0 || traceList.size() > 6) {
			return false;
		}
		
		PackerTechnique p = PackerManager.getInstance().getTechniques().getTechnique(PackerConstants.PACKING_UNPACKING);
		for (long loc: traceList) {
			if (p.contain(loc)) {
				return false;
			}
		}
		
		return true;
	}

	private boolean checkCond(long addr, long address, int num) {
		// TODO Auto-generated method stub
		long temp = Math.abs(address - addr); 
		return temp == num || temp == num * 2 || temp == num * 4;
	}

	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		return false;
	}
}
