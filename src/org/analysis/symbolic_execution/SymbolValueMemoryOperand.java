package org.analysis.symbolic_execution;

import org.analysis.concolic_testing.TestCaseValue;
import org.analysis.formula.LongValueOld;
import org.analysis.formula.Value;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86ArithmeticInstruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.structure.Convert;

public class SymbolValueMemoryOperand {
	SymbolMemoryOperandList memoryOperandList;
	Program program;
	SymbolValueRegister conReg;
	SymbolValueRegisterPart conRegPart;
	SymbolValueSegment conSegReg;
	TestCaseValue var;

	@Override
	public SymbolValueMemoryOperand clone() {
		return null;
	}

	public SymbolValueMemoryOperand() {
		super();
		// this.numVar = 4;
		// this.regVal = new LinearExpression[4];
		// regVal[0] = eax;
		// regVal[1] = ebx;
		// regVal[2] = ecx;
		// regVal[3] = edx;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
		this.memoryOperandList = new SymbolMemoryOperandList();
	}

	public SymbolValueMemoryOperand(TestCaseValue var, Program program, SymbolValueRegister concreteValueRegister,
			SymbolValueRegisterPart concreteValueRegisterPart, SymbolValueSegment concreteValueSegment) {
		// TODO Auto-generated constructor stub
		this.memoryOperandList = new SymbolMemoryOperandList();
		this.program = program;
		this.conReg = concreteValueRegister;
		this.conRegPart = concreteValueRegisterPart;
		this.var = var;
		this.conSegReg = concreteValueSegment;
	}

	public void printInfo() {
		// TODO Auto-generated method stub
		// System.out.println("Information of Register");
		// System.out.println("Information of Register");
		// this.symbolRegisterList.printInfo();
		// System.out.println();

		this.memoryOperandList.printInfo();
	}

	// public double getValueOperand(String reg) {
	// return this.regSymbol.getRegVal(reg).getCoeff();
	// }

	public void add(X86MemoryOperand dest, Value l) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			this.memoryOperandList.add(dest, l);
		} else {
			this.memoryOperandList.addNew(dest, l);
		}
	}

	public void sub(X86MemoryOperand dest, Value l) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			this.memoryOperandList.sub(dest, l);
		} else {
			this.memoryOperandList.addNew(dest, new LongValueOld(0));
			this.memoryOperandList.sub(dest, l);
		}
	}

	public Value getMemoryOperandValByte(X86MemoryOperand dest) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			return this.memoryOperandList.getVal(dest);
		} else {
			Value d = new LongValueOld(program.getByteValueMemory(new AbsoluteAddress(dest.getDisplacement())));
			this.memoryOperandList.addNew(dest, d);
			// this.memoryOperandList.add
		}
		return this.memoryOperandList.getVal(dest);
	}

	public Value getMemoryOperandVal(String dest) {
		// TODO Auto-generated method stub
		return this.memoryOperandList.getVal(dest);
	}

	public boolean setSymbolMemoryOperandValue(X86MemoryOperand dest, Value pop) {
		// TODO Auto-generated method stub
		return memoryOperandList.setRegVal(dest, pop);
	}

	public void mov(X86MemoryOperand string, Value t) {
		// TODO Auto-generated method stub
		memoryOperandList.setRegVal(string, t);
	}

	public void mul(X86MemoryOperand dest, Value l) {
		// TODO Auto-generated method stub
		if (memoryOperandList.contain(dest)) {
			memoryOperandList.mul(dest, l);
		} else {
			memoryOperandList.addNew(dest, new LongValueOld(0));
			memoryOperandList.mul(dest, l);
		}
	}

	public Value getMemoryOperandValWord(X86MemoryOperand x86MemoryOperand) {
		// TODO Auto-generated method stub
		// Exp result = null;
		Value x = getMemoryOperandVal(x86MemoryOperand.getDisplacement());
		Value y = getMemoryOperandVal(x86MemoryOperand.getDisplacement() + 1);

		String temp = Convert.longToHex((int) ((LongValueOld) y).getValue(), 8);
		temp += Convert.longToHex((int) ((LongValueOld) x).getValue(), 8);
		;
		return new LongValueOld(Convert.hexToLong(temp));
	}

	private Value getMemoryOperandVal(long displacement) {
		// TODO Auto-generated method stub
		X86MemoryOperand t = new X86MemoryOperand(DataType.UINT32, displacement);
		return this.getMemoryOperandValByte(t);
	}

	public Value getMemoryOperandValDoubleWord(X86MemoryOperand x86MemoryOperand) {
		// TODO Auto-generated method stub
		Value x = getMemoryOperandVal(x86MemoryOperand.getDisplacement());
		Value y = getMemoryOperandVal(x86MemoryOperand.getDisplacement() + 1);
		Value z = getMemoryOperandVal(x86MemoryOperand.getDisplacement() + 2);
		Value t = getMemoryOperandVal(x86MemoryOperand.getDisplacement() + 3);
		String temp;
		if (t instanceof LongValueOld) {
			temp = Convert.longToHex((int) ((LongValueOld) t).getValue(), 8);
		} else {
			return this.getMemoryOperandValByte(x86MemoryOperand);
		}

		if (z instanceof LongValueOld) {
			temp += Convert.longToHex((int) ((LongValueOld) z).getValue(), 8);
		} else {
			return this.getMemoryOperandValByte(x86MemoryOperand);
		}

		if (y instanceof LongValueOld) {
			temp += Convert.longToHex((int) ((LongValueOld) y).getValue(), 8);
		} else {
			return this.getMemoryOperandValByte(x86MemoryOperand);
		}

		// System.out.println("Debug:" + x.toString());
		if (x instanceof LongValueOld) {
			temp += Convert.longToHex((int) ((LongValueOld) x).getValue(), 8);
		} else {
			return this.getMemoryOperandValByte(x86MemoryOperand);
		}

		return new LongValueOld(Convert.hexToLong(temp));
	}

	public void movDoubleWord(X86MemoryOperand m, Value x) {
		// TODO Auto-generated method stub
		long t = m.getDisplacement();
		if (m.getBase() == null) {
			if (x instanceof LongValueOld) {
				long x1 = ((LongValueOld) x).getValue();

				String hex = Convert.longToHex(x1, 32);
				int l = hex.length();
				if (l < 8) {
					System.out.println("String out of range:" + l + " " + x1 + " " + hex);
					return;
				}

				mov(m, new LongValueOld(Convert.hexToLong(hex.substring(l - 2, l))));
				mov(new X86MemoryOperand(m.getDataType(), t + 1),
						new LongValueOld(Convert.hexToLong(hex.substring(l - 4, l - 2))));
				mov(new X86MemoryOperand(m.getDataType(), t + 2),
						new LongValueOld(Convert.hexToLong(hex.substring(l - 6, l - 4))));
				mov(new X86MemoryOperand(m.getDataType(), t + 3),
						new LongValueOld(Convert.hexToLong(hex.substring(l - 8, l - 6))));
				return;
			}
		}

		this.mov(m, x);
	}

	public void movWord(X86MemoryOperand m, Value x) {
		// TODO Auto-generated method stub
		long t = m.getDisplacement();
		if (m.getBase() == null) {
			if (x instanceof LongValueOld) {
				long x1 = ((LongValueOld) x).getValue();

				String hex = Convert.longToHex(x1, 16);
				int l = hex.length();
				mov(m, new LongValueOld(Convert.hexToLong(hex.substring(l - 2, l))));
				mov(new X86MemoryOperand(m.getDataType(), t + 1),
						new LongValueOld(Convert.hexToLong(hex.substring(l - 4, l - 2))));
				// mov(new X86MemoryOperand(m.getDataType(), t + 2), new
				// ValueLongExp(Convert.hexToLong(hex.substring(l-6, l-4))));
				// mov(new X86MemoryOperand(m.getDataType(), t + 3), new
				// ValueLongExp(Convert.hexToLong(hex.substring(l-8, l-6))));
				return;
			}
		}

		this.mov(m, x);
	}

	public void movByte(X86MemoryOperand m, Value x) {
		// TODO Auto-generated method stub
		// long t = m.getDisplacement();
		if (m.getBase() == null) {
			if (x instanceof LongValueOld) {
				long x1 = ((LongValueOld) x).getValue();

				String hex = Convert.longToHex(x1, 8);
				int l = hex.length();
				mov(m, new LongValueOld(Convert.hexToLong(hex.substring(l - 2, l))));
				// mov(new X86MemoryOperand(m.getDataType(), t + 1), new
				// ValueLongExp(Convert.hexToLong(hex.substring(l-4, l-2))));
				// mov(new X86MemoryOperand(m.getDataType(), t + 2), new
				// ValueLongExp(Convert.hexToLong(hex.substring(l-6, l-4))));
				// mov(new X86MemoryOperand(m.getDataType(), t + 3), new
				// ValueLongExp(Convert.hexToLong(hex.substring(l-8, l-6))));
				return;
			}
		}

		this.mov(m, x);
	}

	public boolean contain(X86MemoryOperand s) {
		// TODO Auto-generated method stub
		return this.memoryOperandList.contain(s);
	}

	public int length() {
		// TODO Auto-generated method stub
		return this.memoryOperandList.length();
	}

	public String getText(X86MemoryOperand x86MemoryOperand) {
		// TODO Auto-generated method stub
		String ret = "";
		Value v = getMemoryOperandValByte(x86MemoryOperand);
		long disp = x86MemoryOperand.getDisplacement();

		while (v != null && v instanceof LongValueOld) {
			disp++;
			if (((LongValueOld) v).getValue() == 0) {
				return ret;
			}

			ret += (char) ((LongValueOld) v).getValue();
			v = getMemoryOperandValByte(new X86MemoryOperand(x86MemoryOperand.getDataType(), disp));
		}
		return ret;
	}

	public void setText(X86MemoryOperand x86MemoryOperand, String str) {
		// TODO Auto-generated method stub
		char[] t = str.toCharArray();
		long disp = x86MemoryOperand.getDisplacement();

		for (int i = 0; i < t.length; i++) {
			int x = t[i];
			if (x == 47) {
				x = 92;
			}

			this.movByte(new X86MemoryOperand(x86MemoryOperand.getDataType(), disp), new LongValueOld(x));
			disp++;
		}
	}

	public long setText(X86MemoryOperand x86MemoryOperand, String str, long size) {
		// TODO Auto-generated method stub
		char[] t = str.toCharArray();
		long disp = x86MemoryOperand.getDisplacement();
		long l = Math.min(t.length, size);
		for (int i = 0; i < l; i++) {
			int x = t[i];
			if (x == 47) {
				x = 92;
			}

			this.movByte(new X86MemoryOperand(x86MemoryOperand.getDataType(), disp), new LongValueOld(x));
			disp++;
		}
		return l;
	}

	public String getText(X86MemoryOperand x86MemoryOperand, long size) {
		// TODO Auto-generated method stub
		String ret = "";
		Value v = getMemoryOperandValByte(x86MemoryOperand);
		long disp = x86MemoryOperand.getDisplacement();

		while (v != null && v instanceof LongValueOld) {
			disp++;
			if (size < 1) {
				return ret;
			}

			ret += (char) ((LongValueOld) v).getValue();
			v = getMemoryOperandValByte(new X86MemoryOperand(x86MemoryOperand.getDataType(), disp));
			size--;
		}
		return ret;
	}

	public void xor(X86MemoryOperand dest, Value memoryOperandValByte) {
		// TODO Auto-generated method stub

	}

	public void setSymbolMemoryOperandValue(X86MemoryOperand t, long v, X86ArithmeticInstruction ins) {
		// TODO Auto-generated method stub
		if (ins.getName().endsWith("l")) {
			String x = Convert.longToHex(v, 32);
			int l = x.length();
			if (l < 8) {
				return;
			}
			setSymbolMemoryOperandValue(t, new LongValueOld(Convert.hexToLong(x.substring(l - 2, l))));
			setSymbolMemoryOperandValue(new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 1), new LongValueOld(
					Convert.hexToLong(x.substring(l - 4, l - 2))));
			setSymbolMemoryOperandValue(new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 2), new LongValueOld(
					Convert.hexToLong(x.substring(l - 6, l - 4))));
			setSymbolMemoryOperandValue(new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 3), new LongValueOld(
					Convert.hexToLong(x.substring(l - 8, l - 6))));
		} else if (ins.getName().endsWith("w") || ins.getName().endsWith("s")) {
			String x = Convert.longToHex(v, 16);
			int l = x.length();
			if (l < 4) {
				return;
			}
			setSymbolMemoryOperandValue(t, new LongValueOld(Convert.hexToLong(x.substring(l - 2, l))));
			setSymbolMemoryOperandValue(new X86MemoryOperand(t.getDataType(), t.getDisplacement() + 1), new LongValueOld(
					Convert.hexToLong(x.substring(l - 4, l - 2))));

		} else if (ins.getName().endsWith("b")) {
			String x = Convert.longToHex(v, 8);
			int l = x.length();
			if (l < 2) {
				return;
			}
			setSymbolMemoryOperandValue(t, new LongValueOld(Convert.hexToLong(x.substring(l - 2, l))));
		}
	}
}
