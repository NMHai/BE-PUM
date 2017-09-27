package v2.org.analysis.packer.techniques;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jakstab.Program;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;

public class OverlappingFunctionV2 extends PackerTechnique {

	/** 
	 * Using for record overlapping function
	 */
	private Map<Long, Integer> instList;
	private boolean isInsideFunc = false;
	private Set<String> detailedList;

	public OverlappingFunctionV2() {
		// num = 0;
		name = "OverlappingFunction-Done";
		id = PackerConstants.OVERLAPPING_FUNC;		
//		instList = new HashMap<>();
		detailedList = new HashSet<>();
	}

	@Override
	public boolean check(BPState curState, Program prog) {			
		if (curState == null || curState.getInstruction() == null) {
			return false;
		}
		
		Instruction inst = curState.getInstruction();
		if (instList == null) {
			instList = Program.getProgram().extractStaticInst();
		}
		
		long curAddr = curState.getLocation().getValue();
		int size = inst.getSize();
		instList.put(curAddr, size);
		
		if (inst.getName().contains("call") || inst.getName().contains("ret")) {
			isInsideFunc = true;			
			return false;
		} else if (inst instanceof X86CondJmpInstruction || inst.getName().contains("jmp")) {
			isInsideFunc = false;
			return false;
		} else {
			if (isInsideFunc && !contain(curAddr) && checkOverlappingInstruction(curAddr, size)) {
				insertLocation(curAddr);	
				return true;
			}
			return false;
		}
	}

	private boolean checkOverlappingInstruction(long addr, int size) {
		// TODO Auto-generated method stub
		for (Map.Entry<Long, Integer> pair : instList.entrySet()) {
			if ((pair.getKey() < addr && (addr - pair.getKey()) < pair.getValue())
					|| (pair.getKey() > addr && (pair.getKey() - addr) < size)) {
				// && !Program.getProgram().getBPCFG().containVertex(value)) {
				detailedList.add("0x" + Long.toHexString(pair.getKey()) + "-" + pair.getValue()
						+ " in " + "0x" + Long.toHexString(addr) + "-" + size);
				return true;
			}
		}

		return false;
	}
	
	@Override
	public String toString() {
		String ret = "Name:" + name + ", Frequency:" + getFrequency() 
		+ ", Location:";
		for (String location : detailedList) {
			ret += location + ", ";
		}

		return ret;
	}


	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		return false;
	}
}
