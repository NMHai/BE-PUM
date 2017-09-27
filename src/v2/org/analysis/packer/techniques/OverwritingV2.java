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
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class OverwritingV2 extends PackerTechnique {

	/**
	 * Using for record overwriting
	 * Version: v2
	 */

//	private static ArrayList<Long> savedSMCState;
	private Map<Long, Long> candidateList;
	private List<Long> excludeList; 
		
	public OverwritingV2() {
		name = "Overwriting-V2";
		id = PackerConstants.OVERWRITING;
		candidateList = new HashMap<>();
		excludeList = new ArrayList<>();
	}

	@Override
	public boolean check(BPState curState, Program prog) {
		Instruction inst = curState.getInstruction();
				
		if (curState == null || curState.getInstruction() == null) {
			return false;
		}		
		
		long location = curState.getLocation().getValue();
		
		if (excludeList.contains(location)) {
			return false;
		}
		
		if (candidateList.containsKey(location)) {
//			System.out.println("Overrite: Remove 0x" + Long.toHexString(location));
			excludeList.add(location);
			candidateList.remove(location);
			return false; 
		}		
		
		for (Map.Entry<Long, Long> pair : candidateList.entrySet()) {
			if (pair.getValue() >= location && pair.getValue() < location + inst.getSize()) {
				resultList.add(pair.getKey());
//				System.out.println("Overrite: Result 0x" + Long.toHexString(pair.getKey()));
				return true;
			}
		}	
		
		int opCount = curState.getInstruction().getOperandCount();
		if (opCount >= 2 && PackerConstants.isSpecialInstruction(curState.getInstruction().getName())) {			
			Operand dest = curState.getInstruction().getOperand(0);
			
			if (dest != null && dest instanceof X86MemoryOperand) {
//				System.out.println("Overrite: Check 0x" + Long.toHexString(location));
				Value addrVal = curState.getEnvironement().getMemory().calculateAddress((X86MemoryOperand)dest);
				if (inst instanceof X86Instruction) {
					if (((X86Instruction) inst).hasPrefixREPNZ() || ((X86Instruction) inst).hasPrefixREPZ()) {				
						return false;
					}
				}
				
				if (addrVal != null && addrVal instanceof LongValue) {
//					System.out.println("Overrite: Insert 0x" + Long.toHexString(location) + " 0x" + Long.toHexString(((LongValue) addrVal).getValue()));
					candidateList.put(location, ((LongValue) addrVal).getValue());					
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
}
