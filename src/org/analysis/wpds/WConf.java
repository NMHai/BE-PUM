/**
 * 
 */
package org.analysis.wpds;

import org.analysis.wpds.weight.Weight;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

/**
 * This class defines the configuration which contains Instruction, Address and
 * Weight
 * 
 * @author NMHai
 * 
 */
public class WConf {
	private Instruction inst;
	private AbsoluteAddress addr;
	private Weight weight;

	public WConf(AbsoluteAddress addr, Instruction inst, Weight w) {
		this.addr = addr;
		this.inst = inst;
		this.weight = w;
	}

	public WConf() {
		super();
	}

	/**
	 * @return the inst
	 */
	public Instruction getInstruction() {
		return inst;
	}

	/**
	 * @param inst
	 *            the inst to set
	 */
	public void setInstruction(Instruction inst) {
		this.inst = inst;
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
	 * @return the weight
	 */
	public Weight getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Weight weight) {
		this.weight = weight;
	}

	public Weight getWeight(AbsoluteAddress addr) {
		return null;
	}
}
