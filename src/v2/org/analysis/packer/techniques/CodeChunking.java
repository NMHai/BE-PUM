package v2.org.analysis.packer.techniques;

import java.util.HashSet;
import java.util.Set;

import org.jakstab.Program;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;

public class CodeChunking extends PackerTechnique {

	/** 
	 * Using for record code chunk-
	 */
	
//	private static ArrayList<PackerSavedState> chunkCodeStates;
//	private static ArrayList<Long> savedChunkCodeStates;	
	private static final long CHUNKING_THRESHOLD = 4;
//	private Map<Long, Long> blockList; 
	private long startAddr; 
	private int numInst = 0; 
	private Set<String> detailedList;

	
	public CodeChunking () { 
		id = PackerConstants.CODE_CHUNKING;
//		chunkCodeStates 				= new ArrayList<PackerSavedState>();
//		savedChunkCodeStates			= new ArrayList<Long>();
		name = "CodeChunking-Done";
//		blockList = new HashMap<Long, Long>();
		startAddr = -1;		
		detailedList = new HashSet<> ();
	}
	
	@Override
	public String toString() {
		String ret = "Name:" + name + ", ID:" + id + ", Location:";
		for (String location: detailedList) {
			ret += location + ", ";
		}
		
		return ret;
	}
	
	@Override
	public boolean check (BPState curState, Program prog) {
		if (curState == null || curState.getInstruction() == null) {
			return false;
		}
		
		Instruction inst = curState.getInstruction();
				
		if (inst instanceof X86CondJmpInstruction || inst.getName().contains("call") || inst.getName().contains("ret")) {
			numInst += CHUNKING_THRESHOLD;
			startAddr = -1;
			return false;
		}
		
		long curAddr = curState.getLocation().getValue();
		if (startAddr == -1) {
			startAddr = curAddr;
		}
		
		if (!inst.getName().contains("jmp")) {
			numInst ++;
			return false;
		}
		
		if (!contain(curAddr)) {			
			if (numInst > 0 && numInst < CHUNKING_THRESHOLD) {
				insertLocation(curAddr);	
				numInst = 0;
				detailedList.add("0x" + Long.toHexString(startAddr) + "_" + "0x" + Long.toHexString(curAddr));
				startAddr = -1;
				return true;
			}					
		}	
		numInst = 0;
		startAddr = -1;
		return false;	
	}

	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		return false;
	}

//	public boolean checkJmpAddr(long curAddr) {
//		// TODO Auto-generated method stub
////		if (startAddr > 0) {
////			if (curAddr - startAddr > 0 && curAddr - startAddr < CHUNKING_THRESHOLD) {
////				detailedList.add("0x"+ Long.toHexString(startAddr) + "-0x" + Long.toHexString(curAddr));
////				return true;
////			}
////		}
////		return false;
//		return numInst < CHUNKING_THRESHOLD;
//	}
//
//	public void insertJmpAddr(long nextAddr) {
//		// TODO Auto-generated method stub					
//		startAddr = nextAddr;
//		numInst = -1;
//	}	
}
