package org.analysis.concrete_execution;

import org.analysis.complement.OldBitVector;
import org.analysis.concolic_testing.TestCaseValue;

public class ConcreteRegisterPartList {
	private String[] regName;
	private long regVal[];
	// SymbolRegister regSymbol;
	private int numVar;
	public static int MAX_NUM_REG = 20;
	TestCaseValue var;

	// Map <X86MemoryOperand, LinearExp>memoryOperandMap;
	// SymbolMemoryOperand memoOperandSymbol;
	// private List<>
	// reg[0]: eax
	// reg[1]: ebx
	// reg[2]: ecx
	// reg[4]: edx

	public ConcreteRegisterPartList() {
		this.numVar = 0;
		this.regName = new String[ConcreteRegisterPartList.MAX_NUM_REG];
		this.regVal = new long[ConcreteRegisterPartList.MAX_NUM_REG];
	}

	public ConcreteRegisterPartList(long[] regVal, String[] regName, int numVar) {
		super();
		this.regVal = regVal;
		this.regName = regName;
		this.numVar = numVar;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExp>();
		// this.regSymbol = new SymbolRegister();
		// this.memoOperandSymbol = new SymbolMemoryOperand();
	}

	// public SymbolRegisterList(LinearExp eax, LinearExp ebx,
	// LinearExp ecx, LinearExp edx) {
	// super();
	// this.numVar = 4;
	// this.regVal = new LinearExp[SymbolRegisterList.MAX_NUM_REG];
	// regVal[0] = eax;
	// regVal[1] = ebx;
	// regVal[2] = ecx;
	// regVal[3] = edx;
	// regName[0] = "eax";
	// regName[1] = "ebx";
	// regName[2] = "ecx";
	// regName[3] = "edx";
	// // memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExp>();
	// // this.regSymbol = new SymbolRegister();
	// // this.memoOperandSymbol = new SymbolMemoryOperand();
	// }

	public ConcreteRegisterPartList(TestCaseValue var) {
		// TODO Auto-generated constructor stub
		this.var = var;
		this.numVar = 0;
		this.regName = new String[ConcreteRegisterPartList.MAX_NUM_REG];
		this.regVal = new long[ConcreteRegisterPartList.MAX_NUM_REG];
	}

	public long[] getRegVal() {
		return regVal;
	}

	public void setRegVal(long[] regVal) {
		this.regVal = regVal;
	}

	public int getNumVar() {
		return numVar;
	}

	public void setNumVar(int numVar) {
		this.numVar = numVar;
	}

	public long get(int index) {
		if (index >= numVar)
			return Long.MIN_VALUE;
		return this.regVal[index];
	}

	// private

	private int getReg(String name) {
		for (int i = 0; i < this.numVar; i++)
			if (this.regName[i].equals(name))
				return i;
		return -1;
	}

	private long getInitValue(String name) {
		long t = var.getVarValue(name);
		if (t != Long.MIN_VALUE)
			return t;
		else
			return 0;
	}

	public void mov(String dest, String src) {
		// TODO Auto-generated method stub
		int s = this.getReg(src);
		if (s < 0) {
			this.regName[this.numVar] = src;
			this.regVal[this.numVar] = getInitValue(src);
			s = this.numVar;
			this.numVar++;
		}

		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[s];

	}

	public void add(String dest, String src) {
		// TODO Auto-generated method stub
		int s = this.getReg(src);
		if (s < 0) {
			this.regName[this.numVar] = src;
			this.regVal[this.numVar] = getInitValue(src);
			s = this.numVar;
			this.numVar++;
		}

		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = OldBitVector.add(regVal[d], regVal[s]);
	}

	public void add(String dest, int intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}
		regVal[d] = OldBitVector.add(this.regVal[d], intValue);
	}

	public void add(String dest, long intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}
		regVal[d] = OldBitVector.add(this.regVal[d], intValue);
	}

	public void divide(String dest, long value) {
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}
		// for (int i=0; i<this.numVar; i++)
		// if (regVal[d] >= 0)
		regVal[d] = OldBitVector.unsignedDiv(regVal[d], value);
		// else
		// regVal[d] = (long) ((regVal[d] + Math.pow(2, 32)) / value);
	}

	public void mul(String dest, long e) {
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}
		// for (int i=0; i<this.numVar; i++)

		regVal[d] = OldBitVector.unsignedMul(regVal[d], e);
	}

	public void sub(String dest, String src) {
		// TODO Auto-generated method stub
		int s = this.getReg(src);
		if (s < 0) {
			this.regName[this.numVar] = src;
			this.regVal[this.numVar] = getInitValue(src);
			s = this.numVar;
			this.numVar++;
		}

		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = OldBitVector.sub(regVal[d], regVal[s]);
	}

	public void sub(String dest, int value) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = OldBitVector.sub(regVal[d], value);
	}

	// public void update(String dest, int intValue) {
	// // TODO Auto-generated method stub
	// // int s = this.getReg(src);
	// int d = this.getReg(dest);
	//
	// regVal[d].update(new LinearExp(0, 0, 0, 0, intValue));
	// }

	public long getRegVal(String dest) {
		// TODO Auto-generated method stub
		int t = this.getReg(dest);
		if (t < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			t = this.numVar;
			this.numVar++;
			// return Long.MIN_VALUE;
		}
		return this.regVal[t];
	}

	public boolean setRegVal(String dest, long value) {
		// TODO Auto-generated method stub
		long x = value;
		if (dest.contains("si") || dest.contains("di") || dest.contains("sp") || dest.contains("bp")) {
			x = (long) (value % Math.pow(2, 16));
		}

		int t = this.getReg(dest);
		if (t < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = x;
			// t = this.numVar;
			this.numVar++;
		} else
			this.regVal[t] = x;

		return true;
	}

	public void printInfo() {
		// TODO Auto-generated method stub
		System.out.println("Information of Register");
		// String []reg = new String[] {"eax", "ebx", "ecx", "edx"};
		for (int i = 0; i < this.numVar; i++) {
			System.out.println("Register " + regName[i] + ": ");
			System.out.println(this.regVal[i]);
			System.out.println();
		}
		// System.out.println("+++++++++++++");
		// System.out.println("Information of Memory Operand");
		// this.memoOperandSymbol.printInfo();
	}

	public boolean isValue(String reg) {
		int t = this.getReg(reg);
		if (t >= 0 && t < this.numVar)
			return true;
		else
			return false;
	}

	public void mov(String dest, int intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = intValue;
			d = this.numVar;
			this.numVar++;
		}
		regVal[d] = intValue;
	}

	public void and(String dest, String src) {
		// TODO Auto-generated method stub
		int s = this.getReg(src);
		if (s < 0) {
			this.regName[this.numVar] = src;
			this.regVal[this.numVar] = getInitValue(src);
			s = this.numVar;
			this.numVar++;
		}

		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d] & regVal[s];
	}

	public void and(String dest, int intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d] & intValue;
	}

	public void or(String dest, String src) {
		// TODO Auto-generated method stub
		int s = this.getReg(src);
		if (s < 0) {
			this.regName[this.numVar] = src;
			this.regVal[this.numVar] = getInitValue(src);
			s = this.numVar;
			this.numVar++;
		}

		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d] | regVal[s];
	}

	public void or(String dest, int intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d] | intValue;
	}

	public void or(String dest, long intValue) {
		// TODO Auto-generated method stub
		intValue = getValue(dest, intValue);
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d] | intValue;
	}

	public void and(String dest, long val) {
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d] & val;
	}

	public void sub(String dest, long val) {
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = OldBitVector.sub(regVal[d], val);
	}

	private long getValue(String dest, long val) {
		long r = val;
		if (r < 0)
			r += Math.pow(2, 32);
		if (dest.contains("al") || dest.contains("bl") || dest.contains("cl") || dest.contains("dl")
				|| dest.contains("ah") || dest.contains("bh") || dest.contains("ch") || dest.contains("dh"))
			r = (long) (r % Math.pow(2, 8));
		else if (dest.equals("%ax") || dest.equals("%bx") || dest.equals("%cx") || dest.equals("%dx"))
			r = (long) (r % Math.pow(2, 16));
		return r;
	}

	public void mov(String dest, long val) {
		val = getValue(dest, val);

		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = val;
			d = this.numVar;
			this.numVar++;
		}
		regVal[d] = val;

	}

	public void xor(String dest, long val) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = OldBitVector.xor(regVal[d], val);
	}

	public void rl(String dest, int val) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = OldBitVector.rl(regVal[d], val);
	}

	public void rr(String dest, int val) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = getInitValue(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = OldBitVector.rr(regVal[d], val);
	}

	// public double getValueOperand(String reg) {
	// int t = this.getReg(reg);
	// return this.regVal[t].getB();
	// }

}
