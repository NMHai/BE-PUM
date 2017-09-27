package org.analysis.concrete_execution;

import org.analysis.concolic_testing.TestCaseValue;

public class ConcreteValueSegment {
	// private LinearExpression regVal[];
	ConcreteSegmentList concreteSegmentList;

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

	public ConcreteValueSegment() {
		super();
		// this.numVar = 4;
		// this.regVal = new LinearExpression[4];
		// regVal[0] = eax;
		// regVal[1] = ebx;
		// regVal[2] = ecx;
		// regVal[3] = edx;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
		this.concreteSegmentList = new ConcreteSegmentList();
		// this.memoryOperandList = new SymbolMemoryOperandList();
	}

	public ConcreteValueSegment(TestCaseValue var) {
		super();
		// this.numVar = 4;
		// this.regVal = new LinearExpression[4];
		// regVal[0] = eax;
		// regVal[1] = ebx;
		// regVal[2] = ecx;
		// regVal[3] = edx;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
		this.concreteSegmentList = new ConcreteSegmentList(var);
	}

	public void add(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.add(dest, intValue);
	}

	public void add(String dest, long value) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.add(dest, value);
	}

	public void add(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.add(dest, src);
	}

	public void and(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.and(dest, intValue);
	}

	public void and(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.and(dest, val);
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

	public void and(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.and(dest, src);
	}

	public void divide(String dest, long intValue) {
		this.concreteSegmentList.divide(dest, intValue);
	}

	public long get(int index) {
		return this.concreteSegmentList.get(index);
	}

	public int getNumVar() {
		return this.concreteSegmentList.getNumVar();
	}

	public long[] getRegVal() {
		return this.concreteSegmentList.getRegVal();
	}

	public long getRegVal(String dest) {
		// TODO Auto-generated method stub
		return this.concreteSegmentList.getRegVal(dest);
	}

	public boolean isValue(String reg) {
		return this.concreteSegmentList.isValue(reg);
	}

	public void mov(String dest, int intValue) {
		// TODO Auto-generated method stub
		// int s = this.getReg(src);
		this.concreteSegmentList.mov(dest, intValue);
	}

	public void mov(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.mov(dest, src);

	}

	public void mul(String dest, long e) {
		this.concreteSegmentList.mul(dest, e);
	}

	public void or(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.or(dest, intValue);
	}

	// public double getValueOperand(String reg) {
	// return this.regSymbol.getRegVal(reg).getCoeff();
	// }

	public void or(String dest, long intValue) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.or(dest, intValue);
	}

	public void or(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.or(dest, src);
	}

	public void printInfo() {
		// TODO Auto-generated method stub
		// System.out.println("Information of Register");
		// System.out.println("Information of Register");
		this.concreteSegmentList.printInfo();
		System.out.println();

		// this.memoryOperandList.printInfo();
	}

	public void setNumVar(int numVar) {
		this.concreteSegmentList.setNumVar(numVar);
	}

	public void setRegVal(long[] regVal) {
		this.concreteSegmentList.setRegVal(regVal);
	}

	public boolean setSymbolRegisterValue(String substring, long pop) {
		// TODO Auto-generated method stub
		return concreteSegmentList.setRegVal(substring, pop);
	}

	public void sub(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.sub(dest, intValue);
	}

	public void sub(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.sub(dest, val);
	}

	public void sub(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.sub(dest, src);
	}

	public void mov(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.mov(dest, val);
	}

	public void xor(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.xor(dest, val);
	}

	public void rl(String dest, int val) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.rl(dest, val);
	}

	public void rr(String dest, int val) {
		// TODO Auto-generated method stub
		this.concreteSegmentList.rr(dest, val);
	}

}
