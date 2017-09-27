package org.analysis.complement;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

public class PMState {
	private AbsoluteAddress addr;
	private Instruction instr;

	public PMState(AbsoluteAddress addr, Instruction instr) {
		super();
		this.addr = addr;
		this.instr = instr;
	}

	/**
	 * @return the addr
	 */
	public AbsoluteAddress getAddress() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddress(AbsoluteAddress addr) {
		this.addr = addr;
	}

	/**
	 * @return the instr
	 */
	public Instruction getInstruction() {
		return instr;
	}

	/**
	 * @param instr
	 *            the instr to set
	 */
	public void setInstruction(Instruction instr) {
		this.instr = instr;
	}

	public boolean compare(PMState state) {
		return addr.getValue() == state.getAddress().getValue()
				&& this.instr.compareInstruction(state.getInstruction());
	}
}
