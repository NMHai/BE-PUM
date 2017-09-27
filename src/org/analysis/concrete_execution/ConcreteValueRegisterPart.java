package org.analysis.concrete_execution;

import org.analysis.complement.RegisterRelationship;
import org.analysis.concolic_testing.TestCaseValue;

public class ConcreteValueRegisterPart {
	// private LinearExpression regVal[];
	ConcreteRegisterPartList concreteRegisterPartList;
	private ConcreteValueRegister cRL;

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

	public ConcreteValueRegisterPart() {
		super();
		// this.numVar = 4;
		// this.regVal = new LinearExpression[4];
		// regVal[0] = eax;
		// regVal[1] = ebx;
		// regVal[2] = ecx;
		// regVal[3] = edx;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
		this.concreteRegisterPartList = new ConcreteRegisterPartList();
		// this.memoryOperandList = new SymbolMemoryOperandList();
	}

	public ConcreteValueRegisterPart(TestCaseValue var) {
		super();
		// this.numVar = 4;
		// this.regVal = new LinearExpression[4];
		// regVal[0] = eax;
		// regVal[1] = ebx;
		// regVal[2] = ecx;
		// regVal[3] = edx;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
		this.concreteRegisterPartList = new ConcreteRegisterPartList(var);
	}

	public void add(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.add(dest, intValue);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void add(String dest, long value) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.add(dest, value);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void add(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.add(dest, src);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void and(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.and(dest, intValue);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void and(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.and(dest, val);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
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
		this.concreteRegisterPartList.and(dest, src);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void divide(String dest, long intValue) {
		this.concreteRegisterPartList.divide(dest, intValue);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public long get(int index) {
		return this.concreteRegisterPartList.get(index);
	}

	public int getNumVar() {
		return this.concreteRegisterPartList.getNumVar();
	}

	public long[] getRegVal() {
		return this.concreteRegisterPartList.getRegVal();
	}

	public long getRegVal(String dest) {
		// TODO Auto-generated method stub
		return this.concreteRegisterPartList.getRegVal(dest);
	}

	public boolean isValue(String reg) {
		return this.concreteRegisterPartList.isValue(reg);
	}

	public void mov(String dest, int intValue) {
		// TODO Auto-generated method stub
		// int s = this.getReg(src);
		this.concreteRegisterPartList.mov(dest, intValue);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void mov(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.mov(dest, src);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void mul(String dest, long e) {
		this.concreteRegisterPartList.mul(dest, e);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void or(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.or(dest, intValue);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	// public double getValueOperand(String reg) {
	// return this.regSymbol.getRegVal(reg).getCoeff();
	// }

	public void or(String dest, long intValue) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.or(dest, intValue);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void or(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.or(dest, src);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void printInfo() {
		// TODO Auto-generated method stub
		// System.out.println("Information of Register");
		// System.out.println("Information of Register");
		this.concreteRegisterPartList.printInfo();
		System.out.println();

		// this.memoryOperandList.printInfo();
	}

	public void setNumVar(int numVar) {
		this.concreteRegisterPartList.setNumVar(numVar);
	}

	public void setRegVal(long[] regVal) {
		this.concreteRegisterPartList.setRegVal(regVal);
	}

	public boolean setConcreteRegisterPartValue(String substring, long pop) {
		// TODO Auto-generated method stub
		return concreteRegisterPartList.setRegVal(substring, pop);
	}

	public void sub(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.sub(dest, intValue);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void sub(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.sub(dest, val);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void sub(String dest, String src) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.sub(dest, src);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void mov(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.mov(dest, val);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void setRegVal(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.setRegVal(dest, val);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void xor(String dest, long val) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.xor(dest, val);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void rl(String dest, int y) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.rl(dest, y);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public void rr(String dest, int y) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.rr(dest, y);
		RegisterRelationship.partChangeTotal(this, this.cRL, dest);
	}

	public ConcreteValueRegister getConcreteValueRegister() {
		return cRL;
	}

	public void setConcreteValueRegister(ConcreteValueRegister cRL) {
		this.cRL = cRL;
	}

	public void movS(String dest, int intValue) {
		// TODO Auto-generated method stub
		// int s = this.getReg(src);
		this.concreteRegisterPartList.mov(dest, intValue);
	}

	public void movS(String dest, long si) {
		// TODO Auto-generated method stub
		this.concreteRegisterPartList.mov(dest, si);
	}

}
