package v2.org.analysis.packer.techniques;

import java.util.HashSet;
import java.util.Set;

import org.jakstab.Program;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86ArithmeticInstruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86MoveInstruction;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.path.BPState;

public class ObfuscatedConstant extends PackerTechnique {

	/** 
	 * Using for record obfuscated constants
	 * The format X86MovInstruction Argument1, Intermediate
	 *            X86ArithmeticInstruction Argument1, Intermediate
	 */
	
//	private static ArrayList<Long> savedObfuscatedConstState;
	private String arg = ""; 
	private boolean firstCond = false;
	private long startAddr; 
	private Set<String> detailedList;
	
	public ObfuscatedConstant () {
		id = PackerConstants.OBFUSCATED_CONST;
		name = "ObfuscatedConst-Done";		
		detailedList = new HashSet<> ();
		startAddr = -1;
	}
	
	@Override
	public boolean check (BPState curState, Program prog) {
		if (curState == null || curState.getInstruction() == null) {
			return false;
		}
		
		long location = curState.getLocation().getValue();
		Instruction ins = curState.getInstruction();
		int opCount = ins.getOperandCount();
		if (ins instanceof X86MoveInstruction) {
			if (opCount > 1) {
				Operand op1 = curState.getInstruction().getOperand(0);
				Operand op2 = curState.getInstruction().getOperand(1);
				if (op2 != null && (op2 instanceof Immediate || 
						op2 instanceof X86MemoryOperand)) {					
					arg = op1.toString();
					startAddr = location;
					firstCond = true;
				} else if (op2 != null && op2.toString().equals(arg)) {
					arg = op1.toString();
					firstCond = true;
				}
			}
			return false;
		}
		long dist = location - startAddr;
		if (dist < 0 || dist > 20) {
			return false;
		}
		
		if (firstCond && ins instanceof X86ArithmeticInstruction
				&& !contain(location)) {
			if (opCount > 1) {
				Operand op1 = curState.getInstruction().getOperand(0);
				Operand op2 = curState.getInstruction().getOperand(1);
				
				if (arg.equals(op1.toString()) && op2 != null && op2 instanceof Immediate) {					
					resultList.add(location);
					firstCond = false;
					arg = "";
					detailedList.add("0x" + Long.toHexString(startAddr) + "_" + "0x" + Long.toHexString(location));
					startAddr = -1;
					return true;
				}
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

	@Override
	public boolean checkAPIName(String apiName, long location) {
		// TODO Auto-generated method stub
		return false;
	}	
}
