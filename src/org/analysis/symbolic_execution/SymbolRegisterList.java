package org.analysis.symbolic_execution;

import org.analysis.formula.LongValueOld;
import org.analysis.formula.SymbolExp;
import org.analysis.formula.Value;

public class SymbolRegisterList {
	private String[] regName;
	private Value regVal[];
	// SymbolRegister regSymbol;
	private int numVar;
	public static int MAX_NUM_REG = 20;

	// Map <X86MemoryOperand, LinearExp>memoryOperandMap;
	// SymbolMemoryOperand memoOperandSymbol;
	// private List<>
	// reg[0]: eax
	// reg[1]: ebx
	// reg[2]: ecx
	// reg[4]: edx

	public SymbolRegisterList() {
		this.numVar = 0;
		this.regName = new String[SymbolRegisterList.MAX_NUM_REG];
		this.regVal = new Value[SymbolRegisterList.MAX_NUM_REG];
	}

	public SymbolRegisterList(Value[] regVal, String[] regName, int numVar) {
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

	public void add(String dest, Value val) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}
		regVal[d] = this.regVal[d].addFunc(val);
	}

	public void add(String dest, int intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}
		regVal[d] = this.regVal[d].addFunc(new LongValueOld(intValue));
	}

	public void add(String dest, String src) {
		// TODO Auto-generated method stub
		int s = this.getReg(src);
		if (s < 0) {
			this.regName[this.numVar] = src;
			this.regVal[this.numVar] = new SymbolExp(src);
			s = this.numVar;
			this.numVar++;
		}

		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].addFunc(regVal[s]);
	}

	public void and(String dest, Value intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].andFunc(intValue);
	}

	public void and(String dest, int intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].andFunc(new LongValueOld(intValue));
	}

	// private

	public void and(String dest, String src) {
		// TODO Auto-generated method stub
		int s = this.getReg(src);
		if (s < 0) {
			this.regName[this.numVar] = src;
			this.regVal[this.numVar] = new SymbolExp(src);
			s = this.numVar;
			this.numVar++;
		}

		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].andFunc(regVal[s]);
	}

	public void divide(String dest, long value) {
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}
		// for (int i=0; i<this.numVar; i++)

		regVal[d] = regVal[d].divFunc(new LongValueOld(value));
	}

	public void divide(String dest, Value value) {
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}
		// for (int i=0; i<this.numVar; i++)

		regVal[d] = regVal[d].divFunc(value);
	}

	public Value get(int index) {
		if (index >= numVar)
			return null;
		return this.regVal[index];
	}

	public int getNumVar() {
		return numVar;
	}

	private int getReg(String name) {
		for (int i = 0; i < this.numVar; i++)
			if (this.regName[i].equals(name))
				return i;
		return -1;
	}

	public Value[] getRegVal() {
		return regVal;
	}

	public Value getRegVal(String dest) {
		// TODO Auto-generated method stub
		int t = this.getReg(dest);
		if (t < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			t = this.numVar;
			this.numVar++;
		}
		return this.regVal[t];
	}

	public boolean isValue(String reg) {
		int t = this.getReg(reg);
		if (t >= 0 && t < this.numVar)
			return (this.regVal[t] instanceof LongValueOld);
		else
			return false;
	}

	public void mov(String dest, int intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new LongValueOld(intValue);
			d = this.numVar;
			this.numVar++;
		}
		regVal[d] = regVal[d].movFunc(new LongValueOld(intValue));
	}

	public void mov(String dest, Value intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = intValue;
			d = this.numVar;
			this.numVar++;
		}
		regVal[d] = regVal[d].movFunc(intValue);
	}

	// public void update(String dest, int intValue) {
	// // TODO Auto-generated method stub
	// // int s = this.getReg(src);
	// int d = this.getReg(dest);
	//
	// regVal[d].update(new LinearExp(0, 0, 0, 0, intValue));
	// }

	public void mov(String dest, String src) {
		// TODO Auto-generated method stub
		int s = this.getReg(src);
		if (s < 0) {
			this.regName[this.numVar] = src;
			this.regVal[this.numVar] = new SymbolExp(src);
			s = this.numVar;
			this.numVar++;
		}

		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].movFunc(regVal[s]);

	}

	public void mul(String dest, long e) {
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}
		// for (int i=0; i<this.numVar; i++)

		regVal[d] = regVal[d].mulFunc(new LongValueOld(e));
	}

	public void mul(String dest, Value e) {
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}
		// for (int i=0; i<this.numVar; i++)

		regVal[d] = regVal[d].mulFunc(e);
	}

	public void or(String dest, int intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].orFunc(new LongValueOld(intValue));
	}

	public void or(String dest, Value intValue) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].orFunc(intValue);
	}

	public void or(String dest, String src) {
		// TODO Auto-generated method stub
		int s = this.getReg(src);
		if (s < 0) {
			this.regName[this.numVar] = src;
			this.regVal[this.numVar] = new SymbolExp(src);
			s = this.numVar;
			this.numVar++;
		}

		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].orFunc(regVal[s]);
	}

	public void printInfo() {
		// TODO Auto-generated method stub
		System.out.println("Information of Register:");
		// String []reg = new String[] {"eax", "ebx", "ecx", "edx"};
		for (int i = 0; i < this.numVar; i++) {
			System.out.print("Register " + regName[i] + ": ");
			System.out.println(this.regVal[i].toString());
			// System.out.println();
		}
		// System.out.println("+++++++++++++");
		// System.out.println("Information of Memory Operand");
		// this.memoOperandSymbol.printInfo();
	}

	public void setNumVar(int numVar) {
		this.numVar = numVar;
	}

	public void setRegVal(Value[] regVal) {
		this.regVal = regVal;
	}

	public boolean setRegVal(String dest, Value value) {
		// TODO Auto-generated method stub
		if (value == null)
			value = new LongValueOld(0);

		int t = this.getReg(dest);
		if (t < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = value;
			// t = this.numVar;
			this.numVar++;
		} else
			this.regVal[t] = value;

		return true;
	}

	public void sub(String dest, int value) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].subFunc(new LongValueOld(value));
	}

	public void sub(String dest, Value value) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].subFunc(value);
	}

	public void sub(String dest, String src) {
		// TODO Auto-generated method stub
		int s = this.getReg(src);
		if (s < 0) {
			this.regName[this.numVar] = src;
			this.regVal[this.numVar] = new SymbolExp(src);
			s = this.numVar;
			this.numVar++;
		}

		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].subFunc(regVal[s]);
	}

	public void xor(String dest, Value value) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].xorFunc(value);
	}

	public void xor(String dest, int value) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].xorFunc(new LongValueOld(value));
	}

	public void rr(String dest, int value) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].rrFunc(new LongValueOld(value));
	}

	public void rl(String dest, int value) {
		// TODO Auto-generated method stub
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}

		regVal[d] = regVal[d].rlFunc(new LongValueOld(value));
	}

	public void div(String dest, long e) {
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}
		// for (int i=0; i<this.numVar; i++)

		regVal[d] = regVal[d].divFunc(new LongValueOld(e));
	}

	public void div(String dest, Value e) {
		int d = this.getReg(dest);
		if (d < 0) {
			this.regName[this.numVar] = dest;
			this.regVal[this.numVar] = new SymbolExp(dest);
			d = this.numVar;
			this.numVar++;
		}
		// for (int i=0; i<this.numVar; i++)

		regVal[d] = regVal[d].divFunc(e);
	}
	// public double getValueOperand(String reg) {
	// int t = this.getReg(reg);
	// return this.regVal[t].getB();
	// }

}
