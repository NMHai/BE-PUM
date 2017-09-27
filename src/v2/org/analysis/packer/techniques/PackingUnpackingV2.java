package v2.org.analysis.packer.techniques;

import java.util.HashMap;
import java.util.Map;

import org.jakstab.Program;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class PackingUnpackingV2 extends PackerTechnique {
	class TwoVal {
		private long key, value;

		public long getKey() {
			return key;
		}

		public void setKey(long key) {
			this.key = key;
		}

		public long getValue() {
			return value;
		}

		public void setValue(long value) {
			this.value = value;
		} 
		
		public TwoVal(long key, long value) {
			this.key = key;
			this.value = value;
		}
		
	}
	/**
	 * Using for record packing/unpacking
	 * Version: v2
	 */
	private final int DEFAULT_NUMBER = 5;
	private Map<Long, Integer> locationNum;
//	private List<TwoVal> candidateList;

	public PackingUnpackingV2() {
		id = PackerConstants.PACKING_UNPACKING;
		name = "PackingUnpacking-V2";
		locationNum = new HashMap<>();
//		candidateList = new ArrayList<>();
	}


	@Override
	public boolean check(BPState curState, Program prog) {
		if (curState == null || curState.getInstruction() == null) {
			return false;
		}
		Instruction inst = curState.getInstruction();
		long location = curState.getLocation().getValue();

		if (contain(location)) {
			return false;
		}
		
//		for (TwoVal pair : candidateList) {
//			if (pair.getValue() >= location && pair.getValue() < location + inst.getSize()) {
//				resultList.add(pair.getKey());
//				System.out.println("Pack: Result 0x" + Long.toHexString(pair.getKey()));
//				return true;
//			}
//		}
		
		int opCount = curState.getInstruction().getOperandCount();
		if (opCount >= 2 && PackerConstants.isSpecialInstruction(curState.getInstruction().getName())) {
			Operand dest = curState.getInstruction().getOperand(0);
						
			if (dest != null && dest instanceof X86MemoryOperand) {
//				System.out.println("Pack: Check 0x" + Long.toHexString(location));
				if (locationNum.containsKey(location)) {
					int num = locationNum.get(location);
					if (num > DEFAULT_NUMBER) {
						resultList.add(location);
						return true;
					}
					locationNum.put(location, num + 1);					
					return false;
				}
				
				Value addrVal = curState.getEnvironement().getMemory().calculateAddress((X86MemoryOperand)dest);
				if (inst instanceof X86Instruction) {
					if (((X86Instruction) inst).hasPrefixREPNZ() || ((X86Instruction) inst).hasPrefixREPZ()) {
//						Environment env = curState.getEnvironement();
//						Value ecx = env.getRegister().getRegisterValue("ecx");
//						Value cx = env.getRegister().getRegisterValue("cx");
//						long t = ((LongValue) ecx).getValue();
//						int dist = X86TransitionRule.getBitCount(inst) / 8;
//						
//						if (ecx != null && ecx instanceof LongValue) {							 
//							for (int i = 0; i < t; i++) {
//								addCandidate(location, ((LongValue) addrVal).getValue() + t * dist);
//							}
//						} else if (cx != null && cx instanceof LongValue) {
//							t = ((LongValue) cx).getValue();
//							for (int i = 0; i < t; i++) {
//								addCandidate(location, ((LongValue) addrVal).getValue() + t * dist);
//							}
//						}						
//						
//						return false;
						resultList.add(location);
						return true;
					}
				}
				
				if (addrVal != null && addrVal instanceof LongValue) {
//					System.out.println("Pack: Insert 0x" + Long.toHexString(location) + " 0x" + Long.toHexString(((LongValue) addrVal).getValue()));
//					candidateList.add(new TwoVal(location, ((LongValue) addrVal).getValue()));	
					locationNum.put(location, 1);					
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		return false;
	}

//	public boolean containCandidate(long location) {
//		// TODO Auto-generated method stub
//		for (TwoVal v: candidateList) {
//			if (v.getKey() == location) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
//
//	public void addCandidate(long location, long value) {
//		// TODO Auto-generated method stub
//		candidateList.add(new TwoVal(location, value));
//	}
}
