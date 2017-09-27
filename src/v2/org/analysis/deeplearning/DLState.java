package v2.org.analysis.deeplearning;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Flag;
import v2.org.analysis.environment.Register;

public class DLState {
	private AbsoluteAddress location; 
	private Instruction inst;
	private Register reg;
	private Flag flag;
	
	public DLState(AbsoluteAddress location, Instruction instruction, Register reg, Flag flag) {
		// TODO Auto-generated constructor stub
		this.location = location;
		this.inst = instruction;
		this.reg = reg;
		this.flag = flag;
	}

	public AbsoluteAddress getLocation() {
		return location;
	}

	public void setLocation(AbsoluteAddress location) {
		this.location = location;
	}

	public Instruction getInst() {
		return inst;
	}

	public void setInst(Instruction inst) {
		this.inst = inst;
	}

	public Register getReg() {
		return reg;
	}

	public void setReg(Register reg) {
		this.reg = reg;
	}

	public Flag getFlag() {
		return flag;
	}

	public void setFlag(Flag flag) {
		this.flag = flag;
	}
	
	
	
}
