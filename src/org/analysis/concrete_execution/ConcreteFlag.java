package org.analysis.concrete_execution;

import org.analysis.complement.OldBitVector;
import org.analysis.concolic_testing.TestCaseValue;

public class ConcreteFlag {
	boolean aFlag, cFlag, dFlag, iFlag, oFlag, pFlag, sFlag, tFlag, zFlag;
	TestCaseValue sval;

	public ConcreteFlag() {
		// aflag = new Exp();
		aFlag = false;
		cFlag = false;
		dFlag = false;
		iFlag = false;
		oFlag = false;
		pFlag = false;
		sFlag = false;
		tFlag = false;
		zFlag = false;
	}

	public ConcreteFlag(TestCaseValue sval) {
		this.sval = sval;
		aFlag = sval.getVarValue("aFlag") == 1;
		cFlag = sval.getVarValue("cFlag") == 1;
		dFlag = sval.getVarValue("dFlag") == 1;
		iFlag = sval.getVarValue("iFlag") == 1;
		pFlag = sval.getVarValue("pFlag") == 1;
		sFlag = sval.getVarValue("sFlag") == 1;
		tFlag = sval.getVarValue("tFlag") == 1;
		zFlag = sval.getVarValue("zFlag") == 1;
		oFlag = sval.getVarValue("oFlag") == 1;

	}

	private long getBitVec(long value) {
		if (value < 0)
			return (long) (Math.pow(2, 32) + value);
		else
			return value;
	}

	private long getBitVecSub(long value) {
		if (value > 0)
			return (long) (Math.pow(2, 32) - value);
		else
			return Math.abs(value);
	}

	public long subtractBitVec(long dest, long source) {
		long d = getBitVec(dest);
		long s = getBitVecSub(source);
		long r = d + s;

		if (r >= Math.pow(2, 31))
			return (long) (r - Math.pow(2, 32));
		else
			return r;
	}

	public long getRealVal(long val) {
		if (val >= Math.pow(2, 31))
			return (long) (val - Math.pow(2, 32));
		return val;
	}

	public void changeFlagWithCMP(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);
		long result = OldBitVector.sub(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}

	public void changeFlagWithLEA(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

	}

	public void changeFlagWithEXCHG(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

	}

	public void changeFlagWithMOVSB(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

		long result = OldBitVector.sub(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}

	public void changeFlagWithROR(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

		long result = OldBitVector.sub(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}

	public void changeFlagWithROL(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

		long result = OldBitVector.sub(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}

	public void changeFlagWithADC(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

		long result = OldBitVector.sub(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}

	public void changeFlagWithSHR(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

		long result = OldBitVector.sub(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}

	public void changeFlagWithSHL(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

		long result = OldBitVector.sub(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}

	public void changeFlagWithXOR(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {

		long result = OldBitVector.xor(dest, source);

		// CF := 0
		cFlag = false;

		// oFlag = 0
		oFlag = false;

		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// zf = (result == 0)
		zFlag = (result == 0);
	}

	public void changeFlagWithOR(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		long result = OldBitVector.or(dest, source);

		// CF := 0
		cFlag = false;

		// oFlag = 0
		oFlag = false;

		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// zf = (result == 0)
		zFlag = (result == 0);
	}

	public void changeFlagWithOR(long dest, long source) {
		long result = OldBitVector.or(dest, source);

		// CF := 0
		cFlag = false;

		// oFlag = 0
		oFlag = false;

		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// zf = (result == 0)
		zFlag = (result == 0);
	}

	public void changeFlagWithAND(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {

		long result = OldBitVector.and(dest, source);

		// CF := 0
		cFlag = false;

		// oFlag = 0
		oFlag = false;

		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// zf = (result == 0)
		zFlag = (result == 0);
	}

	public void changeFlagWithIMUL(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

		long result = OldBitVector.sub(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}

	public void changeFlagWithDEC(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

		long result = OldBitVector.sub(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}

	public void changeFlagWithSUB(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

		long result = OldBitVector.sub(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}

	public void changeFlagWithINC(ConcreteValueRegister symbolValueRegister,
			ConcreteValueMemoryOperand symbolMemoryOperandValueRegister, ConcreteStack symbolStack, long dest,
			long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

		long result = OldBitVector.sub(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}

	public void changeFlagWithINC(long dest) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);
		this.changeFlagWithADD(dest, 1);
	}

	public void changeFlagWithADD(long dest, long source) {
		// TODO Auto-generated method stub
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// long result = subtractBitVec(dest, source);

		long result = OldBitVector.add(dest, source);
		// *1* %CF := ((op1 < 0) & (op2 < 0))
		// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
		cFlag = (OldBitVector.lt(dest, 0) & OldBitVector.lt(source, 0))
				| ((OldBitVector.ge(result, 0)) & ((OldBitVector.lt(dest, 0)) | (OldBitVector.lt(source, 0))));

		// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
		// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.ge(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// *1* %SF := (result < 0)
		sFlag = OldBitVector.lt(result, 0);

		// *1* %ZF := (result = 0)
		zFlag = (result == 0);
	}

	public boolean getaFlag() {
		return aFlag;
	}

	public void setaFlag(boolean aFlag) {
		this.aFlag = aFlag;
	}

	public boolean getcFlag() {
		return cFlag;
	}

	public void setcFlag(boolean cFlag) {
		this.cFlag = cFlag;
	}

	public boolean getdFlag() {
		return dFlag;
	}

	public void setdFlag(boolean dFlag) {
		this.dFlag = dFlag;
	}

	public boolean getiFlag() {
		return iFlag;
	}

	public void setiFlag(boolean iFlag) {
		this.iFlag = iFlag;
	}

	public boolean getoFlag() {
		return oFlag;
	}

	public void setoFlag(boolean oFlag) {
		this.oFlag = oFlag;
	}

	public boolean getpFlag() {
		return pFlag;
	}

	public void setpFlag(boolean pFlag) {
		this.pFlag = pFlag;
	}

	public boolean getsFlag() {
		return sFlag;
	}

	public void setsFlag(boolean sFlag) {
		this.sFlag = sFlag;
	}

	public boolean gettFlag() {
		return tFlag;
	}

	public void settFlag(boolean tFlag) {
		this.tFlag = tFlag;
	}

	public boolean getzFlag() {
		return zFlag;
	}

	public void setzFlag(boolean zFlag) {
		this.zFlag = zFlag;
	}

	public void changeFlagWithTEST(ConcreteValueRegister concreteValueRegister,
			ConcreteValueMemoryOperand concreteValueMemoryOperand, ConcreteStack concreteStack, long dest, long source) {
		// TODO Auto-generated method stub
		long result = OldBitVector.and(dest, source);
		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = OldBitVector.lt(dest, source);
		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// oFlag = ((s ^ d) & (s ^ t)) > 0;

		// oFlag = (dest < 0 & source >= 0 & result > 0) | (dest >= 0 & source <
		// 0 & result < 0);
		oFlag = (OldBitVector.lt(dest, 0) & OldBitVector.ge(source, 0) & OldBitVector.gt(result, 0))
				| (OldBitVector.ge(dest, 0) & OldBitVector.lt(source, 0) & OldBitVector.lt(result, 0));
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))
		// aFlag = (10 == (10 & (t ^ d ^ s)));
		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */
		// pFlag = ((t >> 7) ^ (t >> 6) ^ (t >> 5) ^ (t >> 4) ^ (t >> 3) ^ (t >>
		// 2) ^ (t >> 1) ^ t) == 0;
		// R_SF:bool = high:bool(T_t_84:u32)
		// sFlag = (result < 0);
		sFlag = OldBitVector.lt(result, 0);
		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = (result == 0);
	}
}
