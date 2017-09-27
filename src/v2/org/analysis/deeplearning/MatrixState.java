package v2.org.analysis.deeplearning;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

public class MatrixState {
	private AbsoluteAddress addr = null; // The absolute address of this node
	private Instruction ins = null; // Instruction contained in this edge
	private String hash = "";
	
	public AbsoluteAddress getAddr() {
		return addr;
	}
	public void setAddr(AbsoluteAddress addr) {
		this.addr = addr;
	}
	public Instruction getIns() {
		return ins;
	}
	public void setIns(Instruction ins) {
		this.ins = ins;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public MatrixState(AbsoluteAddress addr, Instruction ins, String hash) {
		super();
		this.addr = addr;
		this.ins = ins;
		this.hash = hash;
	}	
	
	public boolean equal(MatrixState v) {
		if (!hash.equals(v.getHash())) {
			return false;
		}
		
		if (addr != null && v != null && v.getAddr() != null && ins != null && v.getIns() != null
				&& addr.getValue() == v.getAddr().getValue() && ins.getName().equals(v.getIns().getName())
				&& ins.getOperandCount() == v.getIns().getOperandCount()) {
			for (int i = 0; i < ins.getOperandCount(); i++) {
				if (!ins.getOperand(i).toString().equals(v.getIns().getOperand(i).toString())) {
					return false;
				}
			}

			return true;
		}

		if (addr != null && v != null && v.getAddr() != null && addr.getValue() == v.getAddr().getValue()
				&& ins == null && v.getIns() == null) {
			return true;
		}

		return false;

	}
}
