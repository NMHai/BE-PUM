package org.analysis.complement;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

public class PMLocation implements Comparable<PMLocation> {
	private final AbsoluteAddress address;
	private int index = 0;
	private Instruction instruction = null;

	public PMLocation(AbsoluteAddress addr) {
		this.address = addr;
	}

	public PMLocation(AbsoluteAddress address, int index) {
		super();
		this.address = address;
		this.index = index;
	}

	public PMLocation(AbsoluteAddress address, int index, Instruction instr) {
		this(address, index);
		this.instruction = instr;
		// this.address = address;
		// this.index = index;
	}

	public PMLocation(AbsoluteAddress address, Instruction instr) {
		// TODO Auto-generated constructor stub
		this(address);
		this.instruction = instr;
	}

	/**
	 * @return the instruction
	 */
	public Instruction getInstruction() {
		return instruction;
	}

	/**
	 * @param instruction
	 *            the instruction to set
	 */
	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	public AbsoluteAddress getAddress() {
		return address;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		if (instruction != null)
			return address.toString() + '_' + index + "_" + instruction.getName();

		return address.toString() + '_' + index;
	}

	@Override
	public int compareTo(PMLocation other) {
		if (other.address.equals(address) && other.index == index
				&& other.getInstruction().compareInstruction(this.instruction))
			return 0;
		else if (other.address.getValue() < address.getValue()
				|| (other.address.getValue() == address.getValue() && other.index < index))
			return 1;
		else
			return -1;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + index;
		return result;
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PMLocation other = (PMLocation) obj;
		if (address == null) {
			if (other.getAddress() != null)
				return false;
		} else if (!address.equals(other.getAddress()))
			return false;
		if (index != other.getIndex())
			return false;
		if (instruction == null) {
			if (other.getInstruction() != null)
				return false;
		} else if (!instruction.compareInstruction(other.getInstruction()))
			return false;

		return true;
	}

}
