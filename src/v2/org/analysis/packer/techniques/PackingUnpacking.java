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

public class PackingUnpacking extends PackerTechnique {

	/**
	 * Using for record packing/unpacking
	 * Version: v1
	 */
	private final int DEFAULT_NUMBER = 50;
	private Map<Long, Integer> locationNum;
	private Map<Long, Long> locationAddr;

	public PackingUnpacking() {
		id = PackerConstants.PACKING_UNPACKING;
		name = "PackingUnpacking-V1";
		locationNum = new HashMap<>();
		locationAddr = new HashMap<>();
	}

	// @Override
	// public boolean check (BPState curState, Program prog) {
	// if (curState == null || curState.getInstruction() == null) {
	// return false;
	// }
	//
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
	// tempLocation.put(location, num + 1);
	// return false;
	// }
	//
	// Operation dest = curState.getInstruction().getOperand(0);
	// if (dest != null && dest instanceof X86MemoryOperand) {
	// Value addrVal =
	// curState.getEnvironement().getMemory().calculateAddress((X86MemoryOperand)dest);
	// if (addrVal != null && addrVal instanceof LongValue) {
	// if (inst instanceof X86Instruction) {
	// if (((X86Instruction) inst).hasPrefixREPNZ() || ((X86Instruction)
	// inst).hasPrefixREPZ()) {
	// locList.add(location);
	// return true;
	// }
	// }
	// tempLocation.put(location, 1);
	// }
	// }
	// if (dest != null && dest instanceof X86MemoryOperand) {
	// Value addrVal =
	// curState.getEnvironement().getMemory().calculateAddress((X86MemoryOperand)dest);
	// if (addrVal != null && addrVal instanceof LongValue) {
	// locList.add(location);
	// return true;
	//
	// }
	// }
	// }
	// return false;
	// }

	@Override
	public boolean check(BPState curState, Program prog) {
		if (curState == null || curState.getInstruction() == null) {
			return false;
		}

		long location = curState.getLocation().getValue();
		int opCount = curState.getInstruction().getOperandCount();
		Instruction inst = curState.getInstruction();
		if (!contain(location) && opCount >= 2 && inst != null && PackerConstants.isSpecialInstruction(inst.getName())) {
			Operand dest = curState.getInstruction().getOperand(0);
			// Operation src = curState.getInstruction().getOperand(1);
			if (dest != null && dest instanceof X86MemoryOperand) {
				Value destAddr = curState.getEnvironement().getMemory().calculateAddress((X86MemoryOperand) dest);
				if (destAddr != null && destAddr instanceof LongValue) {
					long address = ((LongValue)destAddr).getValue();
					if (locationNum.containsKey(location)) {
						int num = locationNum.get(location);
						long addr = locationAddr.get(location);
						if (address != addr) {
							if (num > DEFAULT_NUMBER) {
								resultList.add(location);
								return true;
							}
							locationNum.put(location, num + 1);
							locationAddr.put(location, address);
						}
						return false;
					}

					if (inst instanceof X86Instruction) {
						if (((X86Instruction) inst).hasPrefixREPNZ() || ((X86Instruction) inst).hasPrefixREPZ()) {
							resultList.add(location);
							return true;
						}
					}
					locationNum.put(location, 1);
					locationAddr.put(location, address);
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
