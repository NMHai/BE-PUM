package org.analysis.concrete_execution;

import org.analysis.concolic_testing.TestCaseValue;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Register;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86Register;
import org.jakstab.asm.x86.X86RegisterPart;
import org.jakstab.asm.x86.X86SegmentRegister;

import v2.org.analysis.structure.BitVector;
import v2.org.analysis.structure.Convert;

public class ConcreteValueMemoryOperand {
	ConcreteMemoryOperandList memoryOperandList;
	Program program;
	ConcreteValueRegister conReg;
	ConcreteValueRegisterPart conRegPart;
	ConcreteValueSegment conSegReg;
	TestCaseValue var;

	public ConcreteValueMemoryOperand() {
		super();
		// this.numVar = 4;
		// this.regVal = new LinearExpression[4];
		// regVal[0] = eax;
		// regVal[1] = ebx;
		// regVal[2] = ecx;
		// regVal[3] = edx;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
		this.memoryOperandList = new ConcreteMemoryOperandList();
	}

	/*
	 * public ConcreteValueMemoryOperand(SymbolValue var) { // TODO
	 * Auto-generated constructor stub this.memoryOperandList = new
	 * ConcreteMemoryOperandList(var); }
	 */

	/*
	 * public ConcreteValueMemoryOperand(SymbolValue var, Program program) { //
	 * TODO Auto-generated constructor stub this.memoryOperandList = new
	 * ConcreteMemoryOperandList(var); this.program = program; }
	 */

	public ConcreteValueMemoryOperand(TestCaseValue var, Program program, ConcreteValueRegister concreteValueRegister,
			ConcreteValueRegisterPart concreteValueRegisterPart, ConcreteValueSegment concreteValueSegment,
			TestCaseValue var1) {
		// TODO Auto-generated constructor stub
		this.memoryOperandList = new ConcreteMemoryOperandList(var1);
		this.program = program;
		this.conReg = concreteValueRegister;
		this.conRegPart = concreteValueRegisterPart;
		this.var = var1;
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

	private long getMemoryOperandDoubleWord(X86MemoryOperand dest) {
		long result = 0;
		result = var.getVarValue(dest.toString());
		if (result == Long.MIN_VALUE) {
			Register base = dest.getBase();
			long addr = 0;
			if (base != null) {
				if (base instanceof X86Register) {
					addr = this.conReg.getRegVal(base.toString());
				} else if (base instanceof X86RegisterPart) {
					addr = this.conRegPart.getRegVal(base.toString());
				} else if (base instanceof X86SegmentRegister) {
					addr = this.conSegReg.getRegVal(base.toString());
				}
			}

			addr = BitVector.add(addr, dest.getDisplacement());

			result = program.getDoubleWordValueMemory(new AbsoluteAddress(addr));
			/*
			 * System.out.println("32 Bit Value of Memory Operand at " + new
			 * AbsoluteAddress(addr).toString() + " is " + result);
			 */
		}

		return result;
	}

	private long getMemoryOperandByte(X86MemoryOperand dest) {
		long result = 0;
		result = var.getVarValue(dest.toString());
		if (result == Long.MIN_VALUE) {
			Register base = dest.getBase();
			long addr = 0;
			if (base != null) {
				if (base instanceof X86Register) {
					addr = this.conReg.getRegVal(base.toString());
				} else if (base instanceof X86RegisterPart) {
					addr = this.conRegPart.getRegVal(base.toString());
				} else if (base instanceof X86SegmentRegister) {
					addr = this.conSegReg.getRegVal(base.toString());
				}
			}

			addr = BitVector.add(addr, dest.getDisplacement());

			result = program.getByteValueMemory(new AbsoluteAddress(addr));
			/*
			 * System.out.println("8 Bit Value of Memory Operand at " + new
			 * AbsoluteAddress(addr).toString() + " is " + result);
			 */
		}

		return result;
	}

	public void add(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			this.memoryOperandList.add(dest, l);
		} else {
			long t = this.getMemoryOperandDoubleWord(dest);
			this.memoryOperandList.addNew(dest, t);
			this.memoryOperandList.add(dest, l);
		}
	}

	public void sub(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			this.memoryOperandList.sub(dest, l);
		} else {
			// this.memoryOperandList.addNew(dest, 0);
			long t = this.getMemoryOperandDoubleWord(dest);
			this.memoryOperandList.addNew(dest, t);
			this.memoryOperandList.sub(dest, l);
		}
	}

	public void mul(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			this.memoryOperandList.mul(dest, l);
		} else {
			// this.memoryOperandList.addNew(dest, 0);
			long t = this.getMemoryOperandDoubleWord(dest);
			this.memoryOperandList.addNew(dest, t);
			this.memoryOperandList.mul(dest, l);
		}
	}

	public void div(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			this.memoryOperandList.div(dest, l);
		} else {
			// this.memoryOperandList.addNew(dest, 0);
			long t = this.getMemoryOperandDoubleWord(dest);
			this.memoryOperandList.addNew(dest, t);
			this.memoryOperandList.div(dest, l);
		}
	}

	public void and(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			this.memoryOperandList.and(dest, l);
		} else {
			// this.memoryOperandList.addNew(dest, 0);
			long t = this.getMemoryOperandDoubleWord(dest);
			this.memoryOperandList.addNew(dest, t);
			this.memoryOperandList.and(dest, l);
		}
	}

	public void or(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			this.memoryOperandList.or(dest, l);
		} else {
			// this.memoryOperandList.addNew(dest, 0);
			long t = this.getMemoryOperandDoubleWord(dest);
			this.memoryOperandList.addNew(dest, t);
			this.memoryOperandList.or(dest, l);
		}
	}

	public void xor(X86MemoryOperand dest, long l) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			this.memoryOperandList.xor(dest, l);
		} else {
			// this.memoryOperandList.addNew(dest, 0);
			long t = this.getMemoryOperandDoubleWord(dest);
			this.memoryOperandList.addNew(dest, t);
			this.memoryOperandList.xor(dest, l);
		}
	}

	public long getMemoryOperandVal(X86MemoryOperand dest) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			return this.memoryOperandList.getVal(dest);
		} else {
			long t = this.getMemoryOperandByte(dest);
			// long t = this.getMemoryOperand(dest);
			// Chinh sua cai nay, neu sai thi nen chinh lai
			this.memoryOperandList.addNew(dest, t);
			// this.memoryOperandList.addNew(dest);
		}
		return this.memoryOperandList.getVal(dest);
	}

	public long getMemoryOperandValByte(X86MemoryOperand dest) {
		// TODO Auto-generated method stub
		if (this.memoryOperandList.contain(dest)) {
			return this.memoryOperandList.getVal(dest);
		} else {
			long t = this.getMemoryOperandByte(dest);
			this.memoryOperandList.addNew(dest, t);
			// this.memoryOperandList.addNew(dest);
		}
		return this.memoryOperandList.getVal(dest);
	}

	public long getMemoryOperandVal(String dest) {
		// TODO Auto-generated method stub
		return this.memoryOperandList.getVal(dest);
	}

	public boolean setSymbolMemoryOperandValue(X86MemoryOperand dest, long pop) {
		// TODO Auto-generated method stub
		return memoryOperandList.setRegVal(dest, pop);
	}

	public long getRegVal(X86MemoryOperand s) {
		// TODO Auto-generated method stub
		long result = 0;
		if (this.memoryOperandList.contain(s)) {
			result = this.memoryOperandList.getVal(s);
		} else {
			result = getMemoryOperandDoubleWord(s);
		}
		return result;
	}

	public void mov(X86MemoryOperand dest, long t) {
		// TODO Auto-generated method stub
		memoryOperandList.setRegVal(dest, t);
	}

	public void rl(X86MemoryOperand dest, long x) {
		// TODO Auto-generated method stub
		memoryOperandList.rl(dest, x);
	}

	public void rr(X86MemoryOperand dest, long x) {
		// TODO Auto-generated method stub
		memoryOperandList.rr(dest, x);
	}

	private long getMemoryOperandVal(long displacement) {
		// TODO Auto-generated method stub
		X86MemoryOperand t = new X86MemoryOperand(DataType.UINT32, displacement);
		return this.getMemoryOperandVal(t);
	}

	public long getMemoryOperandValWord(X86MemoryOperand x86MemoryOperand) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getMemoryOperandValDoubleWord(X86MemoryOperand x86MemoryOperand) {
		// TODO Auto-generated method stub
		long x = getMemoryOperandVal(x86MemoryOperand.getDisplacement());
		long y = getMemoryOperandVal(x86MemoryOperand.getDisplacement() + 1);
		long z = getMemoryOperandVal(x86MemoryOperand.getDisplacement() + 2);
		long t = getMemoryOperandVal(x86MemoryOperand.getDisplacement() + 3);

		String temp = Convert.longToHex((int) t, 8);
		temp += Convert.longToHex((int) z, 8);
		;
		temp += Convert.longToHex((int) y, 8);
		;
		temp += Convert.longToHex((int) x, 8);
		;
		return Convert.hexToLong(temp);
	}

	public void movDoubleWord(X86MemoryOperand m, long x) {
		// TODO Auto-generated method stub
		long t = m.getDisplacement();
		if (m.getBase() == null) {
			// if (x instanceof ValueLongExp) {
			// long x1 = ((ValueLongExp)x).getValueOperand();

			String hex = Convert.longToHex(x, 32);
			int l = hex.length();
			mov(m, Convert.hexToLong(hex.substring(l - 2, l)));
			mov(new X86MemoryOperand(m.getDataType(), t + 1), Convert.hexToLong(hex.substring(l - 4, l - 2)));
			mov(new X86MemoryOperand(m.getDataType(), t + 2), Convert.hexToLong(hex.substring(l - 6, l - 4)));
			mov(new X86MemoryOperand(m.getDataType(), t + 3), Convert.hexToLong(hex.substring(l - 8, l - 6)));
			return;
		}

		this.mov(m, x);
	}

	public void movWord(X86MemoryOperand m, long x) {
		// TODO Auto-generated method stub
		long t = m.getDisplacement();
		if (m.getBase() == null) {
			// if (x instanceof ValueLongExp) {
			// long x1 = ((ValueLongExp)x).getValueOperand();

			String hex = Convert.longToHex(x, 16);
			int l = hex.length();
			mov(m, Convert.hexToLong(hex.substring(l - 2, l)));
			mov(new X86MemoryOperand(m.getDataType(), t + 1), Convert.hexToLong(hex.substring(l - 4, l - 2)));
			/*
			 * mov(new X86MemoryOperand(m.getDataType(), t + 2),
			 * Convert.hexToLong(hex.substring(l - 6, l - 4))); mov(new
			 * X86MemoryOperand(m.getDataType(), t + 3),
			 * Convert.hexToLong(hex.substring(l - 8, l - 6)));
			 */
			return;
		}

		this.mov(m, x);
	}

	public void movByte(X86MemoryOperand m, long x) {
		// TODO Auto-generated method stub
		/*
		 * long t = m.getDisplacement(); if (m.getBase() == null) { // if (x
		 * instanceof ValueLongExp) { // long x1 =
		 * ((ValueLongExp)x).getValueOperand();
		 * 
		 * String hex = Convert.longToHex(x, 16); int l = hex.length(); mov(m,
		 * Convert.hexToLong(hex.substring(l - 2, l))); mov(new
		 * X86MemoryOperand(m.getDataType(), t + 1),
		 * Convert.hexToLong(hex.substring(l - 4, l - 2))); mov(new
		 * X86MemoryOperand(m.getDataType(), t + 2),
		 * Convert.hexToLong(hex.substring(l - 6, l - 4))); mov(new
		 * X86MemoryOperand(m.getDataType(), t + 3),
		 * Convert.hexToLong(hex.substring(l - 8, l - 6))); return; }
		 */
		this.mov(m, x);
	}

	public boolean contain(X86MemoryOperand s) {
		// TODO Auto-generated method stub
		return this.memoryOperandList.contain(s);
	}

	public void setText(X86MemoryOperand x86MemoryOperand, String s) {
		// TODO Auto-generated method stub
		char[] t = s.toCharArray();
		long disp = x86MemoryOperand.getDisplacement();

		for (int i = 0; i < t.length; i++) {
			int x = t[i];
			if (x == 47) {
				x = 92;
			}

			this.movByte(new X86MemoryOperand(x86MemoryOperand.getDataType(), disp), x);
			disp++;
		}
	}

	public String getText(X86MemoryOperand x86MemoryOperand) {
		// TODO Auto-generated method stub
		String ret = "";
		long v = getMemoryOperandValByte(x86MemoryOperand);
		long disp = x86MemoryOperand.getDisplacement();

		while (true) {
			disp++;

			if (v == 0) {
				return ret;
			}

			ret += (char) v;
			v = getMemoryOperandValByte(new X86MemoryOperand(x86MemoryOperand.getDataType(), disp));
		}
	}

	public long setText(X86MemoryOperand x86MemoryOperand, String s, long size) {
		// TODO Auto-generated method stub
		char[] t = s.toCharArray();
		long l = Math.min(t.length, size);

		long disp = x86MemoryOperand.getDisplacement();

		for (int i = 0; i < l; i++) {
			int x = t[i];
			if (x == 47) {
				x = 92;
			}

			this.movByte(new X86MemoryOperand(x86MemoryOperand.getDataType(), disp), x);
			disp++;
		}

		return l;
	}

	public String getText(X86MemoryOperand x86MemoryOperand, long size) {
		// TODO Auto-generated method stub
		String ret = "";
		long v = getMemoryOperandValByte(x86MemoryOperand);
		long disp = x86MemoryOperand.getDisplacement();

		while (true) {
			disp++;

			if (size < 1) {
				return ret;
			}

			ret += (char) v;
			v = getMemoryOperandValByte(new X86MemoryOperand(x86MemoryOperand.getDataType(), disp));
			size--;
		}
	}
}
