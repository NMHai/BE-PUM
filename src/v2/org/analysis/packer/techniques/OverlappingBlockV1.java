package v2.org.analysis.packer.techniques;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jakstab.Program;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;

public class OverlappingBlockV1 extends PackerTechnique {

	/**
	 * Using for record overlapping function
	 */

	// private static boolean firstCheck;
	// private static long tracingBlockLoc;
	// private static ArrayList<PackerSavedBlock> blocks;
	// private static ArrayList<Long> savedListBlock;
	// private static boolean isOverlap;
	private Map<Long, Long> blockList;
	private long startAddr, endAddr;
	private Set<String> detailedList;

	public OverlappingBlockV1() {
		// num = 0;
		name = "OverlappingBlock-Done";
		id = PackerConstants.OVERLAPPING_BLOCK;
		blockList = new HashMap<>();
		startAddr = -1;
		endAddr = -1;
		detailedList = new HashSet<>();
		// firstCheck = true;
		// tracingBlockLoc = 0x0;
		// blocks = new ArrayList<PackerSavedBlock>();
		// savedListBlock = new ArrayList<Long>();
		// isOverlap = false;
	}

	@Override
	public boolean check(BPState curState, Program prog) {
		Instruction inst = curState.getInstruction();		
				
		if (inst == null) {
			return false;
		}
		
		long curAddr = curState.getLocation().getValue();
		if (inst instanceof X86CondJmpInstruction || inst.getName().contains("call") || inst.getName().contains("ret")) {
			startAddr = -1;
			return false;
		}

		if (startAddr == -1) {
			startAddr = curAddr;
		}

		if (!inst.getName().contains("jmp")) {
			return false;
		} else {
			endAddr = curAddr;
			if (!contain(curAddr) && checkAddrInside(startAddr, endAddr)) {
				// techOrder += PackerConstants.CODE_CHUNKING + "_";
				// setPosOrder(getPosOrder() + "0x" +
				// Long.toHexString(curAddr) + "_");
				// posOrder += "0x" + Long.toHexString(curAddr) + "_";
				insertLocation(curAddr);
				// detailedList.add("0x" + Long.toHexString(startAddr) + "_"
				// + "0x" + Long.toHexString(curAddr));
				blockList.put(startAddr, endAddr);
				startAddr = -1;
				return true;
			}
			blockList.put(startAddr, endAddr);
			startAddr = -1;
			return false;
		}
	}

	private boolean checkAddrInside(long start, long end) {
		// TODO Auto-generated method stub
		for (Map.Entry<Long, Long> pair : blockList.entrySet()) {
			if ((pair.getKey() < start && pair.getValue() > start && pair.getValue() < end)
					|| (pair.getKey() > start && pair.getKey() < end && pair.getValue() > end)) {
				// && !Program.getProgram().getBPCFG().containVertex(value)) {
				detailedList.add("0x" + Long.toHexString(pair.getKey()) + "-0x" + Long.toHexString(pair.getValue())
						+ " in " + "0x" + Long.toHexString(start) + "-0x" + Long.toHexString(end));
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkJmpAddr(long value) {
		// TODO Auto-generated method stub
		return checkAddrInside(value);
	}

	private boolean checkAddrInside(long value) {
		for (Map.Entry<Long, Long> pair : blockList.entrySet()) {
			if (pair.getKey() < value && pair.getValue() > value) {
				// && !Program.getProgram().getBPCFG().containVertex(value)) {
				detailedList.add("0x" + Long.toHexString(pair.getKey()) + "-0x" + Long.toHexString(pair.getValue())
						+ " ; " + "0x" + Long.toHexString(value));
				return true;
			}
		}

		return false;
	}

//	@Override
//	public String toString() {
//		String ret = "Name:" + name + ", ID:" + id + ", Location:";
//		for (String location : detailedList) {
//			ret += location + ", ";
//		}
//
//		return ret;
//	}

	public void insertJmpAddr(long curAddr, long nextAddr) {
		// TODO Auto-generated method stub
		if (startAddr > 0) {
			blockList.put(startAddr, curAddr);
		}
		startAddr = nextAddr;
		blockList.put(nextAddr, (long) -1);
	}
}
