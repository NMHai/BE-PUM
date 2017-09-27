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

public class Overwriting extends PackerTechnique {

	/**
	 * Using for record overwriting
	 * Version: V1
	 */

//	private static ArrayList<Long> savedSMCState;
	private Map<Long, Long> candidateList; 
	public Overwriting() {
		// num = 0;
		name = "Overwriting-V1";
		id = PackerConstants.OVERWRITING;
		candidateList = new HashMap<>();
//		savedSMCState = new ArrayList<Long>();
	}

	@Override
	public boolean check(BPState curState, Program prog) {
		if (curState == null || curState.getInstruction() == null) {
			return false;
		}
		Instruction inst = curState.getInstruction();
		long location = curState.getLocation().getValue();
		
		if (!resultList.contains(location)) {
			for (Map.Entry<Long, Long> pair : candidateList.entrySet()) {
				if (pair.getValue() >= location && pair.getValue() < location + inst.getSize()) {
					resultList.add(pair.getKey());
					return true;
				}
			}
		}
		
		int opCount = curState.getInstruction().getOperandCount();
		if (opCount >= 2 && !contain(location) && 
				PackerConstants.isSpecialInstruction(curState.getInstruction().getName())) {
			Operand dest = curState.getInstruction().getOperand(0);
			
			if (inst instanceof X86Instruction) {
				if (((X86Instruction) inst).hasPrefixREPNZ() || ((X86Instruction) inst).hasPrefixREPZ()) {
					return false;
				}
			}
			
			if (dest != null && dest instanceof X86MemoryOperand) {
				Value addrVal = curState.getEnvironement().getMemory().calculateAddress((X86MemoryOperand)dest);
				if (addrVal != null && addrVal instanceof LongValue) {
					candidateList.put(location, ((LongValue) addrVal).getValue());
//					AbsoluteAddress aAddr = new AbsoluteAddress(((LongValue) addrVal).getValue());
					// if (PackerHelper.IsI nCodeSection(prog, aAddr)) {
//					if (prog.getMainModule().isCodeArea(aAddr)) {
//						locList.add(location);
//						return true;
//					}
					
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
