package org.analysis.concrete_execution;

import org.analysis.complement.RegisterRelationship;
import org.analysis.concolic_testing.TestCaseValue;

public class ConcreteValueRegister {
	// private LinearExpression regVal[];
	ConcreteRegisterList concreteRegisterList;
	private ConcreteValueRegisterPart cVRP;
	private ConcreteFlag cFlag;

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

	public ConcreteValueRegister() {
		super();
		// this.numVar = 4;
		// this.regVal = new LinearExpression[4];
		// regVal[0] = eax;
		// regVal[1] = ebx;
		// regVal[2] = ecx;
		// regVal[3] = edx;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
		this.concreteRegisterList = new ConcreteRegisterList();
		// this.memoryOperandList = new SymbolMemoryOperandList();
	}

	public ConcreteValueRegister(TestCaseValue var) {
		super();
		// this.numVar = 4;
		// this.regVal = new LinearExpression[4];
		// regVal[0] = eax;
		// regVal[1] = ebx;
		// regVal[2] = ecx;
		// regVal[3] = edx;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
		this.concreteRegisterList = new ConcreteRegisterList(var);
	}

	public void add(String dest, int intValue) {
		// TODO Auto-generated method stub
		// this.cFlag.changeFlagWithADD(getRegVal(dest), intValue);
		this.concreteRegisterList.add(dest, intValue);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void add(String dest, long value) {
		// TODO Auto-generated method stub
		this.cFlag.changeFlagWithADD(getRegVal(dest), value);
		this.concreteRegisterList.add(dest, value);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void add(String dest, String src) {
		// TODO Auto-generated method stub
		// this.cFlag.changeFlagWithADD(getRegVal(dest), getRegVal(src));
		this.concreteRegisterList.add(dest, src);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void and(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.and(dest, intValue);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void and(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.and(dest, val);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
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
		this.concreteRegisterList.and(dest, src);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void divide(String dest, long intValue) {
		this.concreteRegisterList.divide(dest, intValue);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public long get(int index) {
		return this.concreteRegisterList.get(index);
	}

	public int getNumVar() {
		return this.concreteRegisterList.getNumVar();
	}

	public long[] getRegVal() {
		return this.concreteRegisterList.getRegVal();
	}

	public long getRegVal(String dest) {
		// TODO Auto-generated method stub
		return this.concreteRegisterList.getRegVal(dest);
	}

	public boolean isValue(String reg) {
		return this.concreteRegisterList.isValue(reg);
	}

	public void mov(String dest, int intValue) {
		// TODO Auto-generated method stub
		// int s = this.getReg(src);
		this.concreteRegisterList.mov(dest, intValue);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void mov(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.mov(dest, src);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void mul(String dest, long e) {
		this.concreteRegisterList.mul(dest, e);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void rr(String dest, long e) {
		this.concreteRegisterList.rr(dest, e);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void rl(String dest, long e) {
		this.concreteRegisterList.rl(dest, e);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void or(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.or(dest, intValue);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
		this.cFlag.changeFlagWithOR(getRegVal(dest), intValue);
	}

	// public double getValueOperand(String reg) {
	// return this.regSymbol.getRegVal(reg).getCoeff();
	// }

	public void or(String dest, long intValue) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.or(dest, intValue);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
		this.cFlag.changeFlagWithOR(getRegVal(dest), intValue);
	}

	public void or(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.or(dest, src);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void printInfo() {
		// TODO Auto-generated method stub
		// System.out.println("Information of Register");
		// System.out.println("Information of Register");
		this.concreteRegisterList.printInfo();
		System.out.println();

		// this.memoryOperandList.printInfo();
	}

	public void setNumVar(int numVar) {
		this.concreteRegisterList.setNumVar(numVar);
	}

	public void setRegVal(long[] regVal) {
		this.concreteRegisterList.setRegVal(regVal);
	}

	public boolean setSymbolRegisterValue(String substring, long pop) {
		// TODO Auto-generated method stub
		return concreteRegisterList.setRegVal(substring, pop);
	}

	public void sub(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.sub(dest, intValue);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void sub(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.sub(dest, val);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void sub(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.sub(dest, src);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void mov(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.mov(dest, val);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void setRegVal(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.mov(dest, val);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public void movS(String dest, long l) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.setRegVal(dest, l);
	}

	public void xor(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteRegisterList.xor(dest, val);
		RegisterRelationship.totalChangePart(cVRP, this, dest);
	}

	public ConcreteValueRegisterPart getConcreteValueRegisterPart() {
		return cVRP;
	}

	public void setConcreteValueRegisterPart(ConcreteValueRegisterPart cVRP) {
		this.cVRP = cVRP;
	}

	public ConcreteFlag getConcreteFlag() {
		return cFlag;
	}

	public void setConcreteFlag(ConcreteFlag cFlag) {
		this.cFlag = cFlag;
	}

}
