package org.analysis.symbolic_execution;

import org.analysis.formula.LongValueOld;
import org.analysis.formula.SymbolExp;
import org.analysis.formula.Value;
import org.jakstab.asm.x86.X86MemoryOperand;

import java.util.ArrayList;
import java.util.List;

public class SymbolMemoryOperandList {
	private List<Value> val;
	private List<X86MemoryOperand> addr;

	public SymbolMemoryOperandList() {
		val = new ArrayList<Value>();
		addr = new ArrayList<X86MemoryOperand>();
	}

	public void addNew(X86MemoryOperand s) {
		addr.add(s);
		// if (s.getBase() == null)
		// val.add(new ValueLongExp(s.getDisplacement()));
		// else
		// val.add(new AdditionBitVecExp(new SymbolExp(s.getBase().toString()),
		// "+", new ValueLongExp(s.getDisplacement())));
		val.add(new LongValueOld(0));
	}

	public void addNew(X86MemoryOperand s, Value l) {
		addr.add(s);
		val.add(l);
	}

	public boolean contain(X86MemoryOperand s) {
		for (X86MemoryOperand i : addr)
			if (i.toString().equals(s.toString()))
				return true;
		return false;
	}

	public Value getVal(X86MemoryOperand s) {
		int index = 0;
		for (X86MemoryOperand i : addr) {
			if (i.toString().equals(s.toString()))
				return val.get(index);
			index++;
		}
		return null;
	}

	public void add(X86MemoryOperand dest, Value l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString())) {
				val.set(i, val.get(i).addFunc(l));
				return;
			}
			i++;
		}
	}

	private String getNameOp(int i) {
		return "op_addr_" + this.addr.get(i).toString();
	}

	public void printInfo() {
		// TODO Auto-generated method stub
		System.out.println("Information of Memory Operand");
		for (int i = 0; i < this.addr.size(); i++) {
			System.out.println("Address = " + this.addr.get(i).toString());
			System.out.println("Name: " + this.getNameOp(i));
			System.out.print("Value:");
			this.val.get(i).toString();
			System.out.println();
			// System.out.println("~~~~~~~~~~~~");
		}

	}

	public Value getVal(String dest) {
		// TODO Auto-generated method stub
		int index = 0;
		for (X86MemoryOperand i : addr) {
			if (i.toString().equals(dest))
				return val.get(index);
			index++;
		}
		// addr.add(arg0)
		return new SymbolExp(dest);
	}

	public boolean setRegVal(X86MemoryOperand dest, Value pop) {
		// TODO Auto-generated method stub
		int index = 0;
		for (X86MemoryOperand i : addr) {
			if (i.toString().equals(dest.toString())) {
				val.set(index, val.get(index).movFunc(pop));
				return true;
			}
			index++;
		}

		addr.add(dest);
		val.add(pop);
		return true;
	}

	public void mul(X86MemoryOperand dest, Value l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString())) {
				val.set(i, val.get(i).mulFunc(l));
				return;
			}
			i++;
		}
	}

	public void sub(X86MemoryOperand dest, Value l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString())) {
				val.set(i, val.get(i).subFunc(l));
				return;
			}
			i++;
		}
	}

	public int length() {
		// TODO Auto-generated method stub
		return val.size();
	}

}
