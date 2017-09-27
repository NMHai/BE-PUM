package org.analysis.wpds;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

public class State {
	private Instruction inst;
	private AbsoluteAddress addr;

	public State(Instruction inst, AbsoluteAddress addr) {
		super();
		this.inst = inst;
		this.addr = addr;
	}

	/**
	 * @return the inst
	 */
	public Instruction getInst() {
		return inst;
	}

	/**
	 * @param inst
	 *            the inst to set
	 */
	public void setInst(Instruction inst) {
		this.inst = inst;
	}

	/**
	 * @return the addr
	 */
	public AbsoluteAddress getAddr() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddr(AbsoluteAddress addr) {
		this.addr = addr;
	}

	public String printInfo() {
		// TODO Auto-generated method stub
		return "Address:" + addr.toString() + " Instruction:" + inst.getName();
	}

}
