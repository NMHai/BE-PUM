package org.analysis.symbolic_execution;

import org.analysis.complement.RegisterRelationship;
import org.analysis.formula.LongValueOld;
import org.analysis.formula.Value;

public class SymbolValueRegister {
	// private LinearExpression regVal[];
	SymbolRegisterList symbolRegisterList;
	private SymbolValueRegisterPart sv;
	private SymbolFlag sFlag;

	// private int numVar;
	// Map <X86MemoryOperand, LinearExpression>memoryOperandMap;
	// SymbolMemoryOperandList memoryOperandList;

	// private List<>
	// reg[0]: eax
	// reg[1]: ebx
	// reg[2]: ecx
	// reg[4]: edx
	// reg[6]: esi
	// reg[7]: edi
	// reg[8]: esp
	// reg[9]: edp

	// public SymbolVariable(LinearExpression[] regVal, int numVar) {
	// super();
	// // this.regVal = regVal;
	// // this.numVar = numVar;
	// // memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
	// this.regSymbol = new SymbolRegisterList(regVal, numVar);
	// this.memoOperandSymbol = new SymbolMemoryOperandList();
	// }

	public SymbolValueRegisterPart getSymbolValueRegisterPart() {
		return sv;
	}

	public void setSymbolValueRegisterPart(SymbolValueRegisterPart sv) {
		this.sv = sv;
	}

	public SymbolValueRegister() {
		super();
		// this.numVar = 4;
		// this.regVal = new LinearExpression[4];
		// regVal[0] = eax;
		// regVal[1] = ebx;
		// regVal[2] = ecx;
		// regVal[3] = edx;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
		this.symbolRegisterList = new SymbolRegisterList();
		// this.memoryOperandList = new SymbolMemoryOperandList();
	}

	public Value[] getRegVal() {
		return this.symbolRegisterList.getRegVal();
	}

	public void setRegVal(Value[] regVal) {
		this.symbolRegisterList.setRegVal(regVal);
	}

	public int getNumVar() {
		return this.symbolRegisterList.getNumVar();
	}

	public void setNumVar(int numVar) {
		this.symbolRegisterList.setNumVar(numVar);
	}

	public Value get(int index) {
		return this.symbolRegisterList.get(index);
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
		this.symbolRegisterList.mov(dest, src);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void add(String dest, String src) {
		// TODO Auto-generated method stub
		// this.sFlag.changeFlagWithADD(getRegVal(dest), getRegVal(src));
		this.symbolRegisterList.add(dest, src);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void add(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.sFlag.changeFlagWithADD(getRegVal(dest), new LongValueOld(intValue));
		this.symbolRegisterList.add(dest, intValue);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void divide(String dest, long intValue) {
		this.symbolRegisterList.divide(dest, intValue);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void divide(String dest, Value intValue) {
		this.symbolRegisterList.divide(dest, intValue);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void mul(String dest, long e) {
		this.symbolRegisterList.mul(dest, e);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void mul(String dest, Value e) {
		this.symbolRegisterList.mul(dest, e);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void sub(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.sub(dest, src);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void sub(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.sub(dest, intValue);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void sub(String dest, Value intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.sub(dest, intValue);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void mov(String dest, int intValue) {
		// TODO Auto-generated method stub
		// int s = this.getReg(src);
		this.symbolRegisterList.mov(dest, intValue);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public Value getRegVal(String dest) {
		// TODO Auto-generated method stub
		return this.symbolRegisterList.getRegVal(dest);
	}

	public void printInfo() {
		// TODO Auto-generated method stub
		// System.out.println("Information of Register");
		// System.out.println("Information of Register");
		this.symbolRegisterList.printInfo();
		// System.out.println();

		// this.memoryOperandList.printInfo();
	}

	public boolean isValue(String reg) {
		return this.symbolRegisterList.isValue(reg);
	}

	// public double getValueOperand(String reg) {
	// return this.regSymbol.getRegVal(reg).getCoeff();
	// }

	public void and(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.and(dest, src);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void and(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.and(dest, intValue);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void or(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.or(dest, src);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void or(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.or(dest, intValue);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void or(String dest, Value intValue) {
		// TODO Auto-generated method stub
		this.sFlag.changeFlagWithOR(this.getRegVal(dest), intValue);
		this.symbolRegisterList.or(dest, intValue);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public boolean setSymbolRegisterValue(String dest, Value pop) {
		// TODO Auto-generated method stub
		boolean ret = symbolRegisterList.setRegVal(dest, pop);
		RegisterRelationship.totalChangePart(sv, this, dest);
		return ret;
	}

	public void add(String dest, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.add(dest, regVal);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void and(String dest, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.and(dest, regVal);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void mov(String dest, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.mov(dest, regVal);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void movS(String dest, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.mov(dest, regVal);
		// RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void xor(String dest, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.xor(dest, regVal);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void xor(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.xor(dest, intValue);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void rr(String dest, int y) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.rr(dest, y);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void rl(String dest, int y) {
		// TODO Auto-generated method stub
		this.symbolRegisterList.rl(dest, y);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public SymbolFlag getSymbolFlag() {
		return sFlag;
	}

	public void setSymbolFlag(SymbolFlag sFlag) {
		this.sFlag = sFlag;
	}

	public void div(String dest, long e) {
		this.symbolRegisterList.div(dest, e);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public void div(String dest, Value e) {
		this.symbolRegisterList.div(dest, e);
		RegisterRelationship.totalChangePart(sv, this, dest);
	}

	public SymbolValueRegister clone() {
		return null;
	}

	public int length() {
		// TODO Auto-generated method stub
		return symbolRegisterList.getNumVar();
	}

}
