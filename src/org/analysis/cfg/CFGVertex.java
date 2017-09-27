package org.analysis.cfg;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86PCRelativeAddress;
import v2.org.analysis.cfg.AddressList;

import java.util.ArrayList;
import java.util.List;

public class CFGVertex {
	private AbsoluteAddress addr; // The absolute address of this node
	private Instruction ins; // Instruction contained in this edge
	private boolean isLoop = false;
	private int type[];
	private int size = 0;
	private short sizeT = 8;
	private List<AddressList> traceLoop, traceList, listLoop;
	/*
	 * Start node with type=0, Exit node with type=1, Condition node with
	 * type=2, Join node with type=3, Loop node with type=4, Jump node with
	 * type=5, Other Instruction node with type=6. Indirect Branch node with
	 * type=7 type=-1 means that this node has not been processed yet
	 */

	private List<CFGEdge> in;
	private List<CFGEdge> out;

	public CFGVertex(AbsoluteAddress addr, Instruction ins) {
		this.addr = addr;
		this.ins = ins;
		type = new int[sizeT];
		traceLoop = new ArrayList<AddressList>();
		this.traceList = new ArrayList<AddressList>();
		this.listLoop = new ArrayList<AddressList>();
		in = new ArrayList<CFGEdge>();
		out = new ArrayList<CFGEdge>();
	}

	public CFGVertex(AbsoluteAddress addr, Instruction ins, int t) {
		this.addr = addr;
		this.ins = ins;
		type = new int[sizeT];
		type[size++] = t;
		traceLoop = new ArrayList<AddressList>();
		this.listLoop = new ArrayList<AddressList>();
		this.traceList = new ArrayList<AddressList>();
		in = new ArrayList<CFGEdge>();
		out = new ArrayList<CFGEdge>();
	}

	public boolean addEdgeIn(CFGEdge e) {
		if (in.contains(e))
			return false;
		in.add(e);
		return true;
	}

	public boolean addEdgeOut(CFGEdge e) {
		if (out.contains(e))
			return false;
		out.add(e);
		return true;
	}

	public CFGEdge getEdgeIn(int index) {
		if (index >= in.size())
			return null;
		return in.get(index);
	}

	public CFGEdge getEdgeOut(int index) {
		if (index >= out.size())
			return null;
		return out.get(index);
	}

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

	public int[] getType() {
		return type;
	}

	public void setType(int t) {
		if (t == 4)
			this.setLoop(true);
		if (size < sizeT && !(containType(t)))
			this.type[size++] = t;
	}

	public boolean containType(int t) {
		for (int i = 0; i < size; i++)
			if (type[i] == t)
				return true;
		return false;
	}

	public boolean equal(Object o) {
		if (!(o instanceof CFGVertex))
			return false;
		CFGVertex v = (CFGVertex) o;

		return v.getAddr().getValue() == this.addr.getValue();
	}

	public void setTraceLoop(AddressList addrList) {
		this.traceLoop.add(addrList);
	}

	public AddressList getTraceLoop(int index) {
		return this.traceLoop.get(index);
	}

	public void printInfo() {
		System.out.println("Vertex:");
		System.out.println("Address:" + this.addr.toString() + " Long value:" + this.addr.getValue());
		if (ins != null) {
			System.out.println("Instruction:" + ins.getName());
			for (int j = 0; j < ins.getOperandCount(); j++) {
				String op = "";
				if (ins.getOperand(j).getClass().getSimpleName().equals("X86PCRelativeAddress")) {
					// long start = this.addr.getValueOperand();
					op = "" + ((X86PCRelativeAddress) ins.getOperand(j)).getEffectiveValue(this.addr.getValue());
				} else
					op = ins.getOperand(j).toString();
				System.out.println("Operand number " + j + " is " + op);
			}
		} else
			System.out.println("This instruction is not yet processed");
		System.out.print("Type: ");
		for (int i = 0; i < this.size; i++) {
			System.out.print(type[i] + " ");
			if (type[i] == 7)
				this.printTraceList();
			if (type[i] == 4)
				this.printTraceLoop();
		}
		System.out.println();
		System.out.println("-------------------------------------------------------------------");
	}

	public void printTraceList() {
		System.out.println("Print the list of Addresses lead to this edge:");
		for (AddressList aL : this.traceList) {
			aL.printInfo();
			System.out.println("++++++++++++++++++++++++++++++++++++");
		}
	}

	public void printTraceLoop() {
		System.out.println("Print the list of Addresses lead to this Loop");
		for (AddressList aL : this.traceLoop) {
			aL.printInfo();
			System.out.println("++++++++++++++++++++++++++++++++++++");
		}
	}

	public AddressList getTrace(int index) {
		if (traceList.size() > index)
			return traceList.get(index);
		else
			return null;
	}

	public List<AddressList> getTraceList() {
		return this.traceList;
	}

	public void setTraceList(AddressList trace) {
		this.traceList.add(trace);
	}

	public int getSize() {
		return size;
	}

	public List<CFGEdge> getOut() {
		return out;
	}

	public List<CFGEdge> getIn() {
		// TODO Auto-generated method stub
		return in;
	}

	public boolean isLoop() {
		return isLoop;
	}

	public void setLoop(boolean isLoop) {
		this.isLoop = isLoop;
	}

	public List<AddressList> getListLoop() {
		return listLoop;
	}

	public AddressList getListLoop(int i) {
		return listLoop.get(i);
	}

	public void setListLoop(List<AddressList> listLoop) {
		this.listLoop = listLoop;
	}

	public void setListLoop(AddressList clone) {
		// TODO Auto-generated method stub
		if (clone != null)
			this.listLoop.add(clone);
	}
}
