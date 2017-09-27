package org.analysis.symbolic_execution;

import org.analysis.complement.RegisterRelationship;
import org.analysis.formula.Value;

public class SymbolValueRegisterPart {
	// private LinearExpression regVal[];
	SymbolRegisterPartList symbolRegisterPartList;
	private SymbolValueRegister sv;

	public SymbolValueRegisterPart clone() {
		return null;
	}

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

	public SymbolValueRegisterPart() {
		super();
		// this.numVar = 4;
		// this.regVal = new LinearExpression[4];
		// regVal[0] = eax;
		// regVal[1] = ebx;
		// regVal[2] = ecx;
		// regVal[3] = edx;
		// memoryOperandMap = new TreeMap<X86MemoryOperand, LinearExpression>();
		this.symbolRegisterPartList = new SymbolRegisterPartList();
		// this.memoryOperandList = new SymbolMemoryOperandList();
	}

	public Value[] getRegVal() {
		return this.symbolRegisterPartList.getRegVal();
	}

	public void setRegVal(Value[] regVal) {
		this.symbolRegisterPartList.setRegVal(regVal);
	}

	public int getNumVar() {
		return this.symbolRegisterPartList.getNumVar();
	}

	public void setNumVar(int numVar) {
		this.symbolRegisterPartList.setNumVar(numVar);
	}

	public Value get(int index) {
		return this.symbolRegisterPartList.get(index);
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
		this.symbolRegisterPartList.mov(dest, src);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void add(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.add(dest, src);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void add(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.add(dest, intValue);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void divide(String dest, long intValue) {
		this.symbolRegisterPartList.divide(dest, intValue);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void divide(String dest, Value intValue) {
		this.symbolRegisterPartList.divide(dest, intValue);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void mul(String dest, long e) {
		this.symbolRegisterPartList.mul(dest, e);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void mul(String dest, Value e) {
		this.symbolRegisterPartList.mul(dest, e);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void sub(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.sub(dest, src);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void sub(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.sub(dest, intValue);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void sub(String dest, Value intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.sub(dest, intValue);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void mov(String dest, int intValue) {
		// TODO Auto-generated method stub
		// int s = this.getReg(src);
		this.symbolRegisterPartList.mov(dest, intValue);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public Value getRegVal(String dest) {
		// TODO Auto-generated method stub
		return this.symbolRegisterPartList.getRegVal(dest);
	}

	public void printInfo() {
		// TODO Auto-generated method stub
		// System.out.println("Information of Register");
		// System.out.println("Information of Register");
		this.symbolRegisterPartList.printInfo();
		System.out.println();

		// this.memoryOperandList.printInfo();
	}

	public boolean isValue(String reg) {
		return this.symbolRegisterPartList.isValue(reg);
	}

	// public double getValueOperand(String reg) {
	// return this.regSymbol.getRegVal(reg).getCoeff();
	// }

	public void and(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.and(dest, src);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void and(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.and(dest, intValue);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void or(String dest, String src) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.or(dest, src);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void or(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.or(dest, intValue);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void or(String dest, Value intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.or(dest, intValue);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public boolean setSymbolRegisterPartValue(String dest, Value pop) {
		// TODO Auto-generated method stub
		boolean ret = symbolRegisterPartList.setRegVal(dest, pop);
		RegisterRelationship.partChangeTotal(this, sv, dest);
		return ret;
	}

	public void add(String dest, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.add(dest, regVal);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void and(String dest, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.and(dest, regVal);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void mov(String dest, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.mov(dest, regVal);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void movS(String dest, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.mov(dest, regVal);
		// RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void xor(String dest, Value regVal) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.xor(dest, regVal);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void xor(String dest, int intValue) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.xor(dest, intValue);
		RegisterRelationship.partChangeTotal(this, sv, dest);
	}

	public void rr(String string, int y) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.rr(string, y);
		RegisterRelationship.partChangeTotal(this, sv, string);
	}

	public void rl(String string, int y) {
		// TODO Auto-generated method stub
		this.symbolRegisterPartList.rl(string, y);
		RegisterRelationship.partChangeTotal(this, sv, string);
	}

	public SymbolValueRegister getSymbolValueRegister() {
		return sv;
	}

	public void setSymbolValueRegister(SymbolValueRegister sv) {
		this.sv = sv;
	}

}
