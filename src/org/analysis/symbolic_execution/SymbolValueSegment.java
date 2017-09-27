package org.analysis.symbolic_execution;

import org.analysis.formula.Value;

public class SymbolValueSegment {
	// private LinearExpression regVal[];
	SymbolSegmentList symbolSegmentList;

	// private int numVar;
	// Map <X86MemoryOperand, LinearExpression>memoryOperandMap;
	// SymbolMemoryOperandList memoryOperandList;

	// private List<>
	// reg[0]: eax
	// reg[1]: ebx
	// reg[2]: ecx
	// reg[4]: edx

	// public SymbolVariable(LinearExpression[] regVal, int numVar) {
	// super();
	// // this.regVal = regVal;
	// // this.numVar = numVar;
	// // memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
	// this.regSymbol = new SymbolRegisterList(regVal, numVar);
	// this.memoOperandSymbol = new SymbolMemoryOperandList();
	// }

	public SymbolValueSegment clone() {
		return null;
	}

	public SymbolValueSegment() {
		super();
		// this.numVar = 4;
		// this.regVal = new LinearExpression[4];
		// regVal[0] = eax;
		// regVal[1] = ebx;
		// regVal[2] = ecx;
		// regVal[3] = edx;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
		this.symbolSegmentList = new SymbolSegmentList();
		// this.memoryOperandList = new SymbolMemoryOperandList();
	}

	public Value[] getRegVal() {
		return this.symbolSegmentList.getRegVal();
	}

	public void setRegVal(Value[] regVal) {
		this.symbolSegmentList.setRegVal(regVal);
	}

	public int getNumVar() {
		return this.symbolSegmentList.getNumVar();
	}

	public void setNumVar(int numVar) {
		this.symbolSegmentList.setNumVar(numVar);
	}

	public Value get(int index) {
		return this.symbolSegmentList.get(index);
	}

	// private

	// private int getReg(String name) {
	// if (name.contains("eax"))
	// return 0;
	// else if (name.contains("ebx"))
	// return 1;
	// else if (name.contains("ecx"))
	// return 2;
	// else if (name.contains("edx"))
	// return 3;
	// else
	// return -1;
	// }

	public void mov(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.mov(dest, src);

	}

	public void add(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.add(dest, src);
	}

	public void add(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.add(dest, intValue);
	}

	public void divide(String dest, long intValue) {
		this.symbolSegmentList.divide(dest, intValue);
	}

	public void divide(String dest, Value intValue) {
		this.symbolSegmentList.divide(dest, intValue);
	}

	public void mul(String dest, long e) {
		this.symbolSegmentList.mul(dest, e);
	}

	public void mul(String dest, Value e) {
		this.symbolSegmentList.mul(dest, e);
	}

	public void sub(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.sub(dest, src);
	}

	public void sub(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.sub(dest, intValue);
	}

	public void sub(String dest, Value intValue) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.sub(dest, intValue);
	}

	public void mov(String dest, int intValue) {
		// TODO Auto-generated method stub
		// int s = this.getReg(src);
		this.symbolSegmentList.mov(dest, intValue);
	}

	public Value getRegVal(String dest) {
		// TODO Auto-generated method stub
		return this.symbolSegmentList.getRegVal(dest);
	}

	public void printInfo() {
		// TODO Auto-generated method stub
		// System.out.println("Information of Register");
		// System.out.println("Information of Register");
		this.symbolSegmentList.printInfo();
		System.out.println();

		// this.memoryOperandList.printInfo();
	}

	public boolean isValue(String reg) {
		return this.symbolSegmentList.isValue(reg);
	}

	// public double getValueOperand(String reg) {
	// return this.regSymbol.getRegVal(reg).getCoeff();
	// }

	public void and(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.and(dest, src);
	}

	public void and(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.and(dest, intValue);
	}

	public void or(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.or(dest, src);
	}

	public void or(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.or(dest, intValue);
	}

	public void or(String dest, Value intValue) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.or(dest, intValue);
	}

	public boolean setSymbolSegmentValue(String substring, Value pop) {
		// TODO Auto-generated method stub
		return symbolSegmentList.setRegVal(substring, pop);
	}

	public void add(String dest, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.add(dest, regVal);
	}

	public void and(String string, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.and(string, regVal);
	}

	public void mov(String string, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.mov(string, regVal);
	}

	public void xor(String string, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.xor(string, regVal);
	}

	public void xor(String string, int regVal) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.xor(string, regVal);
	}

	public void rr(String string, int y) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.rr(string, y);
	}

	public void rl(String string, int y) {
		// TODO Auto-generated method stub
		this.symbolSegmentList.rl(string, y);
	}

}
