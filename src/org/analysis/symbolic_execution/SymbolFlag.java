package org.analysis.symbolic_execution;

import org.analysis.formula.*;

import v2.org.analysis.structure.BitVector;

public class SymbolFlag {
	private Value aFlag, cFlag, dFlag, iFlag, oFlag, pFlag, sFlag, tFlag, zFlag;

	public SymbolFlag clone() {
		SymbolFlag c = new SymbolFlag();
		c.setaFlag(aFlag.clone());
		c.setcFlag(cFlag.clone());
		c.setdFlag(dFlag.clone());
		c.setiFlag(iFlag.clone());
		c.setoFlag(oFlag.clone());
		c.setpFlag(pFlag.clone());
		c.setsFlag(sFlag.clone());
		c.settFlag(tFlag.clone());
		c.setzFlag(zFlag.clone());

		return c;
	}

	public SymbolFlag() {
		// aflag = new Exp();
		aFlag = new SymbolExp("aFlag");
		cFlag = new SymbolExp("cFlag");
		dFlag = new SymbolExp("dFlag");
		iFlag = new SymbolExp("iFlag");
		oFlag = new SymbolExp("oFlag");
		pFlag = new SymbolExp("pFlag");
		sFlag = new SymbolExp("sFlag");
		tFlag = new SymbolExp("tFlag");
		zFlag = new SymbolExp("zFlag");
	}

	public Value getaFlag() {
		return aFlag;
	}

	public void setaFlag(Value aFlag) {
		this.aFlag = aFlag;
	}

	public Value getcFlag() {
		return cFlag;
	}

	public void setcFlag(Value cFlag) {
		this.cFlag = cFlag;
	}

	public Value getdFlag() {
		return dFlag;
	}

	public void setdFlag(Value dFlag) {
		this.dFlag = dFlag;
	}

	public Value getiFlag() {
		return iFlag;
	}

	public void setiFlag(Value iFlag) {
		this.iFlag = iFlag;
	}

	public Value getoFlag() {
		return oFlag;
	}

	public void setoFlag(Value oFlag) {
		this.oFlag = oFlag;
	}

	public Value getpFlag() {
		return pFlag;
	}

	public void setpFlag(Value pFlag) {
		this.pFlag = pFlag;
	}

	public Value getsFlag() {
		return sFlag;
	}

	public void setsFlag(Value sFlag) {
		this.sFlag = sFlag;
	}

	public Value gettFlag() {
		return tFlag;
	}

	public void settFlag(Value tFlag) {
		this.tFlag = tFlag;
	}

	public Value getzFlag() {
		return zFlag;
	}

	public void setzFlag(Value zFlag) {
		this.zFlag = zFlag;
	}

	public void changeFlagWithCMP(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// addr 0x8 @asm "cmp    %eax,%ebx"
		// label pc_0x8
		// R_EBX:u32 = source
		// R_EAX:u32 = dest
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// Exp t = s.subFunc(d);
		if (dest instanceof AnyExp || source instanceof AnyExp) {
			cFlag = new BooleanExp(false);
			sFlag = new BooleanExp(false);
			zFlag = new BooleanExp(true);
		} else if (dest instanceof LongValueOld && source instanceof LongValueOld) {
			long d = ((LongValueOld) dest).getValue();
			long s = ((LongValueOld) source).getValue();

			long t = d - s;
			cFlag = new BooleanExp(d < s);

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			oFlag = new BooleanExp(((d < 0) & (s >= 0) & (t > 0)) | ((d >= 0) & (s < 0) & (t < 0)));

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new BooleanExp(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new BooleanExp(t == 0);

		} else {

			Value t = new HybridExp(dest, "-", source);

			// R_CF:bool = R_EBX:u32 < R_EAX:u32
			cFlag = new HybridExp(dest, "<", source);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridExp o1 = new HybridExp(dest, "<", new LongValueOld(0));
			HybridExp o2 = new HybridExp(source, ">=", new LongValueOld(0));
			HybridExp o3 = new HybridExp(t, ">", new LongValueOld(0));
			HybridBooleanExp o4 = new HybridBooleanExp(o1, "and", new HybridBooleanExp(o2, "and", o3));

			HybridExp o5 = new HybridExp(dest, ">=", new LongValueOld(0));
			HybridExp o6 = new HybridExp(source, "<", new LongValueOld(0));
			HybridExp o7 = new HybridExp(t, "<", new LongValueOld(0));
			HybridBooleanExp o8 = new HybridBooleanExp(o5, "and", new HybridBooleanExp(o6, "and", o7));

			oFlag = new HybridBooleanExp(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridExp(t, "<", new LongValueOld(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridExp(t, "=", new LongValueOld(0));
		}
	}

	public void changeFlagWithADD(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {

		Value t = new HybridExp(dest, "+", source);

		// *1* %CF := ((op1 < 0) & (op2 < 0))
		// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
		HybridExp oc1 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridExp oc2 = new HybridExp(source, "<", new LongValueOld(0));
		HybridBooleanExp oc3 = new HybridBooleanExp(oc1, "and", oc2);

		HybridExp oc4 = new HybridExp(t, ">=", new LongValueOld(0));
		HybridExp oc5 = new HybridExp(source, "<", new LongValueOld(0));
		HybridExp oc6 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridBooleanExp oc7 = new HybridBooleanExp(oc5, "and", oc6);
		HybridBooleanExp oc8 = new HybridBooleanExp(oc4, "or", oc7);

		cFlag = new HybridBooleanExp(oc3, "or", oc8);

		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest), "and",
		// new OtherExp(source, "xor", t));
		// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

		// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
		// | ((op1 >= 0) & (op2 >= 0) & (result < 0))

		HybridExp of1 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridExp of2 = new HybridExp(source, "<", new LongValueOld(0));
		HybridExp of3 = new HybridExp(t, ">=", new LongValueOld(0));
		HybridBooleanExp of4 = new HybridBooleanExp(of1, "and", new HybridBooleanExp(of2, "and", of3));

		HybridExp of5 = new HybridExp(dest, ">=", new LongValueOld(0));
		HybridExp of6 = new HybridExp(source, ">=", new LongValueOld(0));
		HybridExp of7 = new HybridExp(t, "<", new LongValueOld(0));
		HybridBooleanExp of8 = new HybridBooleanExp(of5, "and", new HybridBooleanExp(of6, "and", of7));

		oFlag = new HybridBooleanExp(of4, "or", of8);
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */

		// *1* %SF := (result < 0)
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// *1* %ZF := (result = 0)
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithMOVSB(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// addr 0x8 @asm "cmp    %eax,%ebx"
		// label pc_0x8
		// R_EBX:u32 = source
		// R_EAX:u32 = dest
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// Exp t = s.subFunc(d);
		Value t = new HybridExp(dest, "-", source);

		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = new HybridExp(dest, "<", source);

		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest), "and",
		// new OtherExp(source, "xor", t));
		// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

		// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
		// | ((op1 >= 0) & (op2 < 0) & (result < 0))
		HybridExp o1 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridExp o2 = new HybridExp(source, ">=", new LongValueOld(0));
		HybridExp o3 = new HybridExp(t, ">", new LongValueOld(0));
		HybridBooleanExp o4 = new HybridBooleanExp(o1, "and", new HybridBooleanExp(o2, "and", o3));

		HybridExp o5 = new HybridExp(dest, ">=", new LongValueOld(0));
		HybridExp o6 = new HybridExp(source, "<", new LongValueOld(0));
		HybridExp o7 = new HybridExp(t, "<", new LongValueOld(0));
		HybridBooleanExp o8 = new HybridBooleanExp(o5, "and", new HybridBooleanExp(o6, "and", o7));

		oFlag = new HybridBooleanExp(o4, "or", o8);
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */

		// R_SF:bool = high:bool(T_t_84:u32)
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithEXCHG(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// addr 0x8 @asm "cmp    %eax,%ebx"
		// label pc_0x8
		// R_EBX:u32 = source
		// R_EAX:u32 = dest
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// Exp t = s.subFunc(d);
		Value t = new HybridExp(dest, "-", source);

		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = new HybridExp(dest, "<", source);

		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest), "and",
		// new OtherExp(source, "xor", t));
		// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

		// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
		// | ((op1 >= 0) & (op2 < 0) & (result < 0))
		HybridExp o1 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridExp o2 = new HybridExp(source, ">=", new LongValueOld(0));
		HybridExp o3 = new HybridExp(t, ">", new LongValueOld(0));
		HybridBooleanExp o4 = new HybridBooleanExp(o1, "and", new HybridBooleanExp(o2, "and", o3));

		HybridExp o5 = new HybridExp(dest, ">=", new LongValueOld(0));
		HybridExp o6 = new HybridExp(source, "<", new LongValueOld(0));
		HybridExp o7 = new HybridExp(t, "<", new LongValueOld(0));
		HybridBooleanExp o8 = new HybridBooleanExp(o5, "and", new HybridBooleanExp(o6, "and", o7));

		oFlag = new HybridBooleanExp(o4, "or", o8);
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */

		// R_SF:bool = high:bool(T_t_84:u32)
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithLEA(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// addr 0x8 @asm "cmp    %eax,%ebx"
		// label pc_0x8
		// R_EBX:u32 = source
		// R_EAX:u32 = dest
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// Exp t = s.subFunc(d);
		Value t = new HybridExp(dest, "-", source);

		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = new HybridExp(dest, "<", source);

		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest), "and",
		// new OtherExp(source, "xor", t));
		// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

		// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
		// | ((op1 >= 0) & (op2 < 0) & (result < 0))
		HybridExp o1 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridExp o2 = new HybridExp(source, ">=", new LongValueOld(0));
		HybridExp o3 = new HybridExp(t, ">", new LongValueOld(0));
		HybridBooleanExp o4 = new HybridBooleanExp(o1, "and", new HybridBooleanExp(o2, "and", o3));

		HybridExp o5 = new HybridExp(dest, ">=", new LongValueOld(0));
		HybridExp o6 = new HybridExp(source, "<", new LongValueOld(0));
		HybridExp o7 = new HybridExp(t, "<", new LongValueOld(0));
		HybridBooleanExp o8 = new HybridBooleanExp(o5, "and", new HybridBooleanExp(o6, "and", o7));

		oFlag = new HybridBooleanExp(o4, "or", o8);
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */

		// R_SF:bool = high:bool(T_t_84:u32)
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithROR(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// *1* %CF := result@[31:31]
		// *1* %OF := [count = 1?(op1@[31:31]) ~= (op1@[30:30]):nondet(1)]

	}

	public void changeFlagWithROL(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// *1* %OF := [count = 1?(op1@[31:31]) ~= %CF:nondet(1)]
		// *1* %CF := result@[31:31]

		// Exp t = s.subFunc(d);
		Value t = new HybridExp(dest, "-", source);

		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = new HybridExp(dest, "<", source);

		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest), "and",
		// new OtherExp(source, "xor", t));
		// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

		// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
		// | ((op1 >= 0) & (op2 < 0) & (result < 0))
		HybridExp o1 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridExp o2 = new HybridExp(source, ">=", new LongValueOld(0));
		HybridExp o3 = new HybridExp(t, ">", new LongValueOld(0));
		HybridBooleanExp o4 = new HybridBooleanExp(o1, "and", new HybridBooleanExp(o2, "and", o3));

		HybridExp o5 = new HybridExp(dest, ">=", new LongValueOld(0));
		HybridExp o6 = new HybridExp(source, "<", new LongValueOld(0));
		HybridExp o7 = new HybridExp(t, "<", new LongValueOld(0));
		HybridBooleanExp o8 = new HybridBooleanExp(o5, "and", new HybridBooleanExp(o6, "and", o7));

		oFlag = new HybridBooleanExp(o4, "or", o8);
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */

		// R_SF:bool = high:bool(T_t_84:u32)
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithADC(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// addr 0x8 @asm "cmp    %eax,%ebx"
		// label pc_0x8
		// R_EBX:u32 = source
		// R_EAX:u32 = dest
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// Exp t = s.subFunc(d);
		Value t = new HybridExp(dest, "-", source);

		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = new HybridExp(dest, "<", source);

		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest), "and",
		// new OtherExp(source, "xor", t));
		// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

		// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
		// | ((op1 >= 0) & (op2 < 0) & (result < 0))
		HybridExp o1 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridExp o2 = new HybridExp(source, ">=", new LongValueOld(0));
		HybridExp o3 = new HybridExp(t, ">", new LongValueOld(0));
		HybridBooleanExp o4 = new HybridBooleanExp(o1, "and", new HybridBooleanExp(o2, "and", o3));

		HybridExp o5 = new HybridExp(dest, ">=", new LongValueOld(0));
		HybridExp o6 = new HybridExp(source, "<", new LongValueOld(0));
		HybridExp o7 = new HybridExp(t, "<", new LongValueOld(0));
		HybridBooleanExp o8 = new HybridBooleanExp(o5, "and", new HybridBooleanExp(o6, "and", o7));

		oFlag = new HybridBooleanExp(o4, "or", o8);
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */

		// R_SF:bool = high:bool(T_t_84:u32)
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithSHR(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// *1* %CF := combine@[(count - 1):(count - 1)]
		// *1* %OF := [count = 1?(result@[31:31]) ~= %CF:nondet(1)]
	}

	public void changeFlagWithSHL(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// *1* %CF := combine@[(32 - count):(32 - count)]
		// *1* %OF := [count = 1?(result@[31:31]) ~= %CF:nondet(1)]
	}

	public void changeFlagWithIMUL(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// *1* %CF := [(sgnex(1,31,result@[31:31]) = result@[32:63])?0:1]
		// *1* %OF := %CF

		// Exp t = s.subFunc(d);
		Value t = new HybridExp(dest, "-", source);

		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = new HybridExp(dest, "<", source);

		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest), "and",
		// new OtherExp(source, "xor", t));
		// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

		// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
		// | ((op1 >= 0) & (op2 < 0) & (result < 0))
		HybridExp o1 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridExp o2 = new HybridExp(source, ">=", new LongValueOld(0));
		HybridExp o3 = new HybridExp(t, ">", new LongValueOld(0));
		HybridBooleanExp o4 = new HybridBooleanExp(o1, "and", new HybridBooleanExp(o2, "and", o3));

		HybridExp o5 = new HybridExp(dest, ">=", new LongValueOld(0));
		HybridExp o6 = new HybridExp(source, "<", new LongValueOld(0));
		HybridExp o7 = new HybridExp(t, "<", new LongValueOld(0));
		HybridBooleanExp o8 = new HybridBooleanExp(o5, "and", new HybridBooleanExp(o6, "and", o7));

		oFlag = new HybridBooleanExp(o4, "or", o8);
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */

		// R_SF:bool = high:bool(T_t_84:u32)
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithXOR(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// *1* %CF := 0
		// *1* %OF := 0
		// *1* %ZF := (result = 0)
		// *1* %SF := (result < 0)

		// Exp t = dest xor source
		Value t = new HybridExp(dest, "xor", source);

		// CF := 0
		cFlag = new BooleanExp(0);

		// OF := 0
		oFlag = new BooleanExp(0);

		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		// result < 0
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// result = 0
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithOR(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// *1* %CF := 0
		// *1* %OF := 0
		// *1* %ZF := (result = 0)
		// *1* %SF := (result < 0)

		// Exp t = dest or source
		if (dest instanceof LongValueOld && source instanceof LongValueOld) {
			long d = ((LongValueOld) dest).getValue();
			long s = ((LongValueOld) source).getValue();

			long r = d | s;
			// CF := 0
			cFlag = new BooleanExp(0);

			// OF := 0
			oFlag = new BooleanExp(0);

			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			// result < 0
			sFlag = new BooleanExp(r < 0);
			// sFlag = t;

			// result = 0
			zFlag = new BooleanExp(r == 0);

		} else {

			Value t = new HybridExp(dest, "or", source);

			// CF := 0
			cFlag = new BooleanExp(0);

			// OF := 0
			oFlag = new BooleanExp(0);

			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			// result < 0
			sFlag = new HybridExp(t, "<", new LongValueOld(0));
			// sFlag = t;

			// result = 0
			zFlag = new HybridExp(t, "=", new LongValueOld(0));
		}
	}

	public void changeFlagWithAND(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// *1* %CF := 0
		// *1* %OF := 0
		// *1* %ZF := (result = 0)
		// *1* %SF := (result < 0)

		// Exp t = dest and source
		Value t = new HybridExp(dest, "and", source);

		// CF := 0
		cFlag = new BooleanExp(0);

		// OF := 0
		oFlag = new BooleanExp(0);

		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		// result < 0
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// result = 0
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithDEC(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// addr 0x8 @asm "cmp    %eax,%ebx"
		// label pc_0x8
		// R_EBX:u32 = source
		// R_EAX:u32 = dest
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// Exp t = s.subFunc(d);
		Value t = new HybridExp(dest, "-", source);

		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = new HybridExp(dest, "<", source);

		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest), "and",
		// new OtherExp(source, "xor", t));
		// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

		// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
		// | ((op1 >= 0) & (op2 < 0) & (result < 0))
		HybridExp o1 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridExp o2 = new HybridExp(source, ">=", new LongValueOld(0));
		HybridExp o3 = new HybridExp(t, ">", new LongValueOld(0));
		HybridBooleanExp o4 = new HybridBooleanExp(o1, "and", new HybridBooleanExp(o2, "and", o3));

		HybridExp o5 = new HybridExp(dest, ">=", new LongValueOld(0));
		HybridExp o6 = new HybridExp(source, "<", new LongValueOld(0));
		HybridExp o7 = new HybridExp(t, "<", new LongValueOld(0));
		HybridBooleanExp o8 = new HybridBooleanExp(o5, "and", new HybridBooleanExp(o6, "and", o7));

		oFlag = new HybridBooleanExp(o4, "or", o8);
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */

		// R_SF:bool = high:bool(T_t_84:u32)
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithSUB(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// addr 0x8 @asm "cmp    %eax,%ebx"
		// label pc_0x8
		// R_EBX:u32 = source
		// R_EAX:u32 = dest
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// Exp t = s.subFunc(d);
		Value t = new HybridExp(dest, "-", source);

		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = new HybridExp(dest, "<", source);

		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest), "and",
		// new OtherExp(source, "xor", t));
		// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

		// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
		// | ((op1 >= 0) & (op2 < 0) & (result < 0))
		HybridExp o1 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridExp o2 = new HybridExp(source, ">=", new LongValueOld(0));
		HybridExp o3 = new HybridExp(t, ">", new LongValueOld(0));
		HybridBooleanExp o4 = new HybridBooleanExp(o1, "and", new HybridBooleanExp(o2, "and", o3));

		HybridExp o5 = new HybridExp(dest, ">=", new LongValueOld(0));
		HybridExp o6 = new HybridExp(source, "<", new LongValueOld(0));
		HybridExp o7 = new HybridExp(t, "<", new LongValueOld(0));
		HybridBooleanExp o8 = new HybridBooleanExp(o5, "and", new HybridBooleanExp(o6, "and", o7));

		oFlag = new HybridBooleanExp(o4, "or", o8);
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */

		// R_SF:bool = high:bool(T_t_84:u32)
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithINC(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolMemoryOperandValueRegister, SymbolStack symbolStack, Value dest, Value source) {
		// addr 0x8 @asm "cmp    %eax,%ebx"
		// label pc_0x8
		// R_EBX:u32 = source
		// R_EAX:u32 = dest
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// Exp t = s.subFunc(d);
		Value t = new HybridExp(dest, "-", source);

		// R_CF:bool = R_EBX:u32 < R_EAX:u32
		cFlag = new HybridExp(dest, "<", source);

		// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
		// T_t_84:u32))
		// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest), "and",
		// new OtherExp(source, "xor", t));
		// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

		// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
		// | ((op1 >= 0) & (op2 < 0) & (result < 0))
		HybridExp o1 = new HybridExp(dest, "<", new LongValueOld(0));
		HybridExp o2 = new HybridExp(source, ">=", new LongValueOld(0));
		HybridExp o3 = new HybridExp(t, ">", new LongValueOld(0));
		HybridBooleanExp o4 = new HybridBooleanExp(o1, "and", new HybridBooleanExp(o2, "and", o3));

		HybridExp o5 = new HybridExp(dest, ">=", new LongValueOld(0));
		HybridExp o6 = new HybridExp(source, "<", new LongValueOld(0));
		HybridExp o7 = new HybridExp(t, "<", new LongValueOld(0));
		HybridBooleanExp o8 = new HybridBooleanExp(o5, "and", new HybridBooleanExp(o6, "and", o7));

		oFlag = new HybridBooleanExp(o4, "or", o8);
		// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
		// R_EAX:u32))

		/*
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 */

		// R_SF:bool = high:bool(T_t_84:u32)
		sFlag = new HybridExp(t, "<", new LongValueOld(0));
		// sFlag = t;

		// R_ZF:bool = 0:u32 == T_t_84:u32
		zFlag = new HybridExp(t, "=", new LongValueOld(0));
	}

	public void changeFlagWithADD(Value dest, Value source) {
		if (dest instanceof LongValueOld && source instanceof LongValueOld) {
			long d = ((LongValueOld) dest).getValue();
			long s = ((LongValueOld) source).getValue();

			long r = BitVector.add(d, s);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			cFlag = new BooleanExp(((d < 0) & (s < 0)) | ((r >= 0) & ((d < 0) | (s < 0))));
			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			oFlag = new BooleanExp(((d < 0) & (s < 0) & (r >= 0)) | ((d >= 0) & (s >= 0) & (r < 0)));
			// *1* %SF := (result < 0)
			sFlag = new BooleanExp(r < 0);
			// *1* %ZF := (result = 0)
			zFlag = new BooleanExp(r == 0);

		} else {
			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			// *1* %SF := (result < 0)
			// *1* %ZF := (result = 0)
		}

	}

	public void changeFlagWithINC(Value dest) {
		this.changeFlagWithADD(dest, new LongValueOld(1));

		// *1* %CF := ((op1 < 0) & (op2 < 0))
		// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
		// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
		// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
		// *1* %SF := (result < 0)
		// *1* %ZF := (result = 0)

	}

	public void changeFlagWithOR(Value dest, Value source) {
		// TODO Auto-generated method stub
		if (dest instanceof LongValueOld && source instanceof LongValueOld) {
			long d = ((LongValueOld) dest).getValue();
			long s = ((LongValueOld) source).getValue();

			long r = d | s;
			// CF := 0
			cFlag = new BooleanExp(0);

			// OF := 0
			oFlag = new BooleanExp(0);

			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			// result < 0
			sFlag = new BooleanExp(r < 0);
			// sFlag = t;

			// result = 0
			zFlag = new BooleanExp(r == 0);

		} else {

			Value t = new HybridExp(dest, "or", source);

			// CF := 0
			cFlag = new BooleanExp(0);

			// OF := 0
			oFlag = new BooleanExp(0);

			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			// result < 0
			sFlag = new HybridExp(t, "<", new LongValueOld(0));
			// sFlag = t;

			// result = 0
			zFlag = new HybridExp(t, "=", new LongValueOld(0));
		}

	}

	public void changeFlagWithTEST(SymbolValueRegister symbolValueRegister,
			SymbolValueMemoryOperand symbolValueMemoryOperand, SymbolStack symbolStack, Value dest, Value source) {
		// TODO Auto-generated method stub
		if (dest instanceof AnyExp || source instanceof AnyExp) {
			cFlag = new BooleanExp(false);
			sFlag = new BooleanExp(false);
			zFlag = new BooleanExp(true);
		} else if (dest instanceof LongValueOld && source instanceof LongValueOld) {
			long d = ((LongValueOld) dest).getValue();
			long s = ((LongValueOld) source).getValue();

			long t = d & s;
			cFlag = new BooleanExp(d < s);

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			oFlag = new BooleanExp(((d < 0) & (s >= 0) & (t > 0)) | ((d >= 0) & (s < 0) & (t < 0)));

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new BooleanExp(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new BooleanExp(t == 0);

		} else {

			Value t = new HybridExp(dest, "and", source);

			// R_CF:bool = R_EBX:u32 < R_EAX:u32
			cFlag = new HybridExp(dest, "<", source);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridExp o1 = new HybridExp(dest, "<", new LongValueOld(0));
			HybridExp o2 = new HybridExp(source, ">=", new LongValueOld(0));
			HybridExp o3 = new HybridExp(t, ">", new LongValueOld(0));
			HybridBooleanExp o4 = new HybridBooleanExp(o1, "and", new HybridBooleanExp(o2, "and", o3));

			HybridExp o5 = new HybridExp(dest, ">=", new LongValueOld(0));
			HybridExp o6 = new HybridExp(source, "<", new LongValueOld(0));
			HybridExp o7 = new HybridExp(t, "<", new LongValueOld(0));
			HybridBooleanExp o8 = new HybridBooleanExp(o5, "and", new HybridBooleanExp(o6, "and", o7));

			oFlag = new HybridBooleanExp(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridExp(t, "<", new LongValueOld(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridExp(t, "=", new LongValueOld(0));
		}
	}
}
