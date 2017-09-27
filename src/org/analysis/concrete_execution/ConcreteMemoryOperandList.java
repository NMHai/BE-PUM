package org.analysis.concrete_execution;

import org.analysis.complement.OldBitVector;
import org.analysis.concolic_testing.TestCaseValue;
import org.jakstab.asm.x86.X86MemoryOperand;

import java.util.ArrayList;
import java.util.List;

public class ConcreteMemoryOperandList {
	private List<Long> val;
	private List<X86MemoryOperand> addr;
	TestCaseValue var;

	public ConcreteMemoryOperandList() {
		val = new ArrayList<Long>();
		addr = new ArrayList<X86MemoryOperand>();
	}

	public ConcreteMemoryOperandList(TestCaseValue var2) {
		// TODO Auto-generated constructor stub
		var = var2;
		val = new ArrayList<Long>();
		addr = new ArrayList<X86MemoryOperand>();
	}

	private long getInitValue(String name) {
		long t = var.getVarValue(name);
		if (t != Long.MIN_VALUE)
			return t;
		else
			return 0;
	}

	public void addNew(X86MemoryOperand s) {
		addr.add(s);
		val.add(getInitValue(((X86MemoryOperand) s).toString()));
	}

	public void addNew(X86MemoryOperand s, long l) {
		addr.add(s);
		// val.add(OldBitVector.add(getInitValue(((X86MemoryOperand)
		// s).toString()),
		// l));
		val.add(OldBitVector.add(0, l));
	}

	public boolean contain(X86MemoryOperand s) {
		for (X86MemoryOperand i : addr)
			if (i.toString().equals(s.toString()))
				return true;
		return false;
	}

	public long getVal(X86MemoryOperand s) {
		int index = 0;
		for (X86MemoryOperand i : addr) {
			if (i.toString().equals(s.toString()))
				return val.get(index);
			index++;
		}
		return Long.MIN_VALUE;
	}

	public void add(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString()))
				val.set(i, OldBitVector.add(val.get(i), l));
			i++;
		}
	}

	public void sub(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString()))
				val.set(i, OldBitVector.sub(val.get(i), l));
			i++;
		}
	}

	public void mul(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString()))
				val.set(i, OldBitVector.unsignedMul(val.get(i), l));
			i++;
		}
	}

	public void div(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString()))
				val.set(i, OldBitVector.unsignedDiv(val.get(i), l));
			i++;
		}
	}

	public void and(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString()))
				val.set(i, OldBitVector.and(val.get(i), l));
			i++;
		}
	}

	public void or(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString()))
				val.set(i, OldBitVector.or(val.get(i), l));
			i++;
		}
	}

	public void xor(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString()))
				val.set(i, OldBitVector.xor(val.get(i), l));
			i++;
		}
	}

	private String getNameOp(int i) {
		return "" + this.addr.get(i);
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

	public long getVal(String dest) {
		// TODO Auto-generated method stub
		int index = 0;
		for (X86MemoryOperand i : addr) {
			if (i.toString().equals(dest))
				return val.get(index);
			index++;
		}
		// return Long.MIN_VALUE;
		return getInitValue(dest);
	}

	public boolean setRegVal(X86MemoryOperand dest, long pop) {
		// TODO Auto-generated method stub
		int index = 0;
		for (X86MemoryOperand i : addr) {
			if (i.toString().equals(dest.toString())) {
				val.set(index, pop);
				return true;
			}
			index++;
		}

		addr.add(dest);
		val.add(pop);
		return true;
	}

	public void rl(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString())) {
				val.set(i, OldBitVector.rl(val.get(i), l));
				break;
			}
			i++;
		}
	}

	public void rr(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		int i = 0;
		for (X86MemoryOperand x : this.addr) {
			if (x.toString().equals(dest.toString())) {
				val.set(i, OldBitVector.rr(val.get(i), l));
				break;
			}
			i++;
		}
	}

}
