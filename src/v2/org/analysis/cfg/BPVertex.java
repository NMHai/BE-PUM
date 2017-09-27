package v2.org.analysis.cfg;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

public class BPVertex {
	public static final int APINode = 3;
	public static final int EnterNode = 0;
	public static final int ExitNode = 1;
	public static final int NormalNode = 2;
	public static final int UnknownNode = 4;
	private AbsoluteAddress addr = null; // The absolute address of this node
	private Instruction ins = null; // Instruction contained in this edge
	private int type = 2;
	/*
	 * Type = 2 : Normal node Type = 0 : Enter node Type = 1 : Exit node Type =
	 * 3 : API node Type = 4 : Unprocessed Node
	 */
	private String property = "";

	public BPVertex(AbsoluteAddress addr, Instruction ins) {
		this.addr = addr;
		this.ins = ins;
	}

	public BPVertex() {
		// TODO Auto-generated constructor stub
	}

	public AbsoluteAddress getAddress() {
		return addr;
	}

	public void setAddress(AbsoluteAddress addr) {
		this.addr = addr;
	}

	public Instruction getInstruction() {
		return ins;
	}

	public void setInstruction(Instruction ins) {
		this.ins = ins;
	}

	@Override
	public String toString() {
		if (addr == null)
			return property;
		if (ins == null)
			return addr.toString() + " null";
		return addr.toString() + " " + ins.getName();
	}

	public boolean equal(BPVertex v) {
		// TODO Auto-generated method stub
		if (addr != null && v != null && v.getAddress() != null && ins != null && v.getInstruction() != null
				&& addr.getValue() == v.getAddress().getValue() && ins.getName().equals(v.getInstruction().getName())
				&& ins.getOperandCount() == v.getInstruction().getOperandCount()) {
			for (int i = 0; i < ins.getOperandCount(); i++)
				if (!ins.getOperand(i).toString().equals(v.getInstruction().getOperand(i).toString()))
					return false;

			return true;
		}

		if (v != null && addr == null && v.getAddress() == null && v.getType() == type
				&& v.getProperty().equals(property))
			return true;

		if (addr != null && v != null && v.getAddress() != null && addr.getValue() == v.getAddress().getValue()
				&& ins == null && v.getInstruction() == null && v.getType() == type && v.getProperty().equals(property))
			return true;

		return false;
		// && ins.equals(v.getInstruction());
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property
	 *            the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}
}
