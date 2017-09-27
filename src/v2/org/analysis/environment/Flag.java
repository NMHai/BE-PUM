/**
 *
 */
package v2.org.analysis.environment;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import v2.org.analysis.structure.BitVector;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.AnyValue;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.HybridBooleanValue;
import v2.org.analysis.value.HybridValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

import com.twmacinta.util.MD5;


/**
 * @author NMHai
 */
public class Flag {
	
	private final static int AFLAG_BIT = 4;
	private final static int CFLAG_BIT = 0;
	private final static int DFLAG_BIT = 10;
	private final static int IFLAG_BIT = 9;
	private final static int OFLAG_BIT = 11;
	private final static int PFLAG_BIT = 2;
	private final static int SFLAG_BIT = 7;
	private final static int TFLAG_BIT = 8;
	private final static int ZFLAG_BIT = 6;
	
	private Value aFlag, cFlag, dFlag, iFlag, oFlag, pFlag, sFlag, tFlag, zFlag;

	public Flag() {
		// aflag = new Exp();
		setAFlag(new SymbolValue("aFlag"));
		setCFlag(new SymbolValue("cFlag"));
		setDFlag(new SymbolValue("dFlag"));
		setIFlag(new SymbolValue("iFlag"));
		setOFlag(new SymbolValue("oFlag"));
		setPFlag(new SymbolValue("pFlag"));
		setSFlag(new SymbolValue("sFlag"));
		setTFlag(new SymbolValue("tFlag"));
		setZFlag(new SymbolValue("zFlag"));
	}

	public void changeFlagWithADC(Value dest, Value source, int cf, Environment env, int numBits) {
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = d + s + cf;

			d = Convert.convertSignedValue(d, numBits);
			s = Convert.convertSignedValue(s, numBits);
			t = Convert.convertSignedValue(t, numBits);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			cFlag = new BooleanValue(((d < 0) & (s < 0)) | ((t >= 0) & ((d < 0) | (s < 0))));
			// R_CF:bool =
			// extract:32:32:[pad:u33(T_orig1:u32) + pad:u33(T_orig2:u32) +
			// pad:u33(R_CF:bool)]
			// cFlag = new BooleanValue((d + s + 1) > Math.pow(2, 32) );

			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			oFlag = new BooleanValue(((d < 0) & (s < 0) & (t >= 0)) | ((d >= 0) & (s >= 0) & (t < 0)));

			// R_OF:bool =
			// high:bool((T_orig1:u32 ^ ~T_orig2:u32) & (T_orig1:u32 ^
			// R_EAX_32:u32))
			// oFlag = new BooleanValue((((d ^ -s) & (d ^ (d + s + cf))) >
			// Math.pow(2, 31)));

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			zFlag = new BooleanValue(t == 0);

			// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
			// T_t2:u64))
			// if (numBits == 8)
			aFlag = new BooleanValue(0x10 == (0x10 & ((d + s + cf) ^ d ^ s)));
			// else if (numBits == 16)
			// aFlag = new BooleanValue(0x1000 == (0x1000 & ((d + s + cf) ^ d ^
			// s)));
			// else if (numBits == 32)
			// aFlag = new BooleanValue(
			// 0x10000000 == (0x10000000 & ((d + s + cf) ^ d ^ s)));

		} else {
			Value t = new HybridValue(dest, "+", source);
			t = new HybridValue(t, "+", new LongValue(1));

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			HybridBooleanValue oc1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc2 = new HybridBooleanValue(source, "<", new LongValue(0));
			Value oc3 = new HybridBooleanValue(oc1, "and", oc2);

			Value oc4 = new HybridBooleanValue(t, ">=", new LongValue(0));
			Value oc5 = new HybridBooleanValue(source, "<", new LongValue(0));
			Value oc6 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "and", oc6);
			HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "or", oc7);

			cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			Value o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			Value o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			Value o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			Value o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			Value o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			Value o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridValue(t, "==", new LongValue(0));
		}
	}

	public void changeFlagWithADD(Value dest, Value source, Environment env, int numBits) {
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = d + s;

			d = Convert.convertSignedValue(d, numBits);
			s = Convert.convertSignedValue(s, numBits);
			t = Convert.convertSignedValue(t, numBits);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			cFlag = new BooleanValue(((d < 0) & (s < 0)) | ((t >= 0) & ((d < 0) | (s < 0))));
			// R_CF:bool =
			// extract:32:32:[pad:u33(T_orig1:u32) + pad:u33(T_orig2:u32) +
			// pad:u33(R_CF:bool)]
			// cFlag = new BooleanValue((d + s + 1) > Math.pow(2, 32) );

			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			oFlag = new BooleanValue(((d < 0) & (s < 0) & (t >= 0)) | ((d >= 0) & (s >= 0) & (t < 0)));

			// R_OF:bool =
			// high:bool((T_orig1:u32 ^ ~T_orig2:u32) & (T_orig1:u32 ^
			// R_EAX_32:u32))
			// oFlag = new BooleanValue((((d ^ -s) & (d ^ (d + s + cf))) >
			// Math.pow(2, 31)));

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			zFlag = new BooleanValue(t == 0);

			// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
			// T_t2:u64))
			// if (numBits == 8)
			aFlag = new BooleanValue(0x10 == (0x10 & ((d + s) ^ d ^ s)));
			// else if (numBits == 16)
			// aFlag = new BooleanValue(0x1000 == (0x1000 & ((d + s + cf) ^ d ^
			// s)));
			// else if (numBits == 32)
			// aFlag = new BooleanValue(
			// 0x10000000 == (0x10000000 & ((d + s + cf) ^ d ^ s)));

		} else {
			Value t = new HybridValue(dest, "+", source);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			HybridBooleanValue oc1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc2 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc3 = new HybridBooleanValue(oc1, "and", oc2);

			HybridBooleanValue oc4 = new HybridBooleanValue(t, ">=", new LongValue(0));
			HybridBooleanValue oc5 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc6 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "and", oc6);
			HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "or", oc7);

			cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	public void changeFlagWithAND(Value dest, Value source, Environment env, int numBits) {
		// AND, OR, and XOR clear OF and CF, leave AF undefined, and update SF,
		// ZF, and PF.
		// http://css.csail.mit.edu/6.858/2010/readings/i386/s03_04.htm
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = d & s;

			d = Convert.convertSignedValue(d, numBits);
			s = Convert.convertSignedValue(s, numBits);
			t = Convert.convertSignedValue(t, numBits);

			// Clear Carry Flag
			cFlag = new BooleanValue(false);

			// Clear Overflow Flag
			oFlag = new BooleanValue(false);

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			zFlag = new BooleanValue(t == 0);

			// Leave Auxilarry Flag undefined
			// if (t == 0)
			aFlag = new BooleanValue(false);
		} else {
			Value t = new HybridValue(dest, "and", source);

			// Clear Carry Flag
			cFlag = new BooleanValue(false);

			// Clear Overflow Flag
			oFlag = new BooleanValue(false);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	public void changeFlagWithCMP(Value dest, Value source, Environment env, int numBits) {
		// TODO Auto-generated method stub
		// addr 0x8 @asm "cmp    %eax,%ebx"
		// label pc_0x8
		// R_EBX:u32 = source
		// R_EAX:u32 = dest
		// T_t_84:u32 = R_EBX:u32 - R_EAX:u32
		// Exp t = s.subFunc(d);
		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = d - s;

			cFlag = new BooleanValue(d < s);

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			oFlag = new BooleanValue(((d < 0) & (s >= 0) & (t > 0)) | ((d >= 0) & (s < 0) & (t < 0)));

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new BooleanValue(t == 0);

		} else {

			Value t = new HybridValue(dest, "-", source);

			// R_CF:bool = R_EBX:u32 < R_EAX:u32
			cFlag = new HybridBooleanValue(dest, "<", source);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	public void changeFlagWithDEC(Value dest, Environment env, int numBits) {
		// TODO Auto-generated method stub
		// changeFlagWithSUB(true, d, new LongValue(1), env, numBits);
		Value source = new LongValue(1);
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			// cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = d - s;

			// if (d == 128)
			// System.out.println("Debug");

			d = Convert.convertSignedValue(d, numBits);
			s = Convert.convertSignedValue(s, numBits);
			t = Convert.convertSignedValue(t, numBits);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))

			// DEC does not change the carry flag
			// cFlag = new BooleanValue(((d < 0) & (s < 0))
			// | ((t >= 0) & ((d < 0) | (s < 0))));

			// PHONG: has something strange here
			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			oFlag = new BooleanValue(((d < 0) & (s >= 0) & (t > 0)) | ((d >= 0) & (s < 0) & (t < 0)));

			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new BooleanValue(t == 0);

			// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
			// T_t2:u64))

			aFlag = new BooleanValue(0x10 == (0x10 & (t ^ d ^ s)));

		} else {

			Value t = new HybridValue(dest, "+", source);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			/*
			 * HybridBooleanValue oc1 = new HybridBooleanValue(dest, "<", new
			 * LongValue(0)); HybridBooleanValue oc2 = new
			 * HybridBooleanValue(source, "<", new LongValue(0));
			 * HybridBooleanValue oc3 = new HybridBooleanValue(oc1, "and", oc2);
			 */

			// HybridBooleanValue oc4 = new HybridBooleanValue(t, ">=", new
			// LongValue(0));
			// HybridBooleanValue oc5 = new HybridBooleanValue(source, "<", new
			// LongValue(0));
			// HybridBooleanValue oc6 = new HybridBooleanValue(dest, "<", new
			// LongValue(0));
			// HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "and", oc6);
			// HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "or", oc7);

			// cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	@Deprecated
	public void changeFlagWithDIV(Value dest, Value source, Environment env, int numBits) {
		// The CF, OF, SF, ZF, AF, and PF flags are undefined.
		/*
		 * if (dest instanceof AnyValue || source instanceof AnyValue) { cFlag =
		 * new BooleanValue(false); sFlag = new BooleanValue(false); zFlag = new
		 * BooleanValue(true); } else if (dest instanceof LongValue && source
		 * instanceof LongValue) { long d = ((LongValue)
		 * dest).getValueOperand(); long s = ((LongValue)
		 * source).getValueOperand();
		 * 
		 * long t = d / s; cFlag = new BooleanValue(d < s);
		 * 
		 * // *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0)) // | ((op1 >=
		 * 0) & (op2 < 0) & (result < 0)) oFlag = new BooleanValue(((d < 0) & (s
		 * >= 0) & (t > 0)) | ((d >= 0) & (s < 0) & (t < 0)));
		 * 
		 * // R_SF:bool = high:bool(T_t_84:u32) sFlag = new BooleanValue(t < 0);
		 * // sFlag = t;
		 * 
		 * // R_ZF:bool = 0:u32 == T_t_84:u32 zFlag = new BooleanValue(t == 0);
		 * 
		 * } else {
		 * 
		 * Value t = new HybridBooleanValue(dest, "/", source);
		 * 
		 * // R_CF:bool = R_EBX:u32 < R_EAX:u32 cFlag = new
		 * HybridBooleanValue(dest, "<", source);
		 * 
		 * // R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^ //
		 * T_t_84:u32)) // OtherExp x = new OtherExp(new OtherExp(source, "xor",
		 * dest), // "and", // new OtherExp(source, "xor", t)); // oFlag = new
		 * OtherExp(x, ">", new ConstantLongExp(0));
		 * 
		 * // *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0)) // | ((op1 >=
		 * 0) & (op2 < 0) & (result < 0)) HybridBooleanValue o1 = new
		 * HybridBooleanValue(dest, "<", new LongValue(0)); HybridBooleanValue
		 * o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
		 * HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new
		 * LongValue(0)); HybridBooleanValue o4 = new HybridBooleanValue(o1,
		 * "and", new HybridBooleanValue(o2, "and", o3));
		 * 
		 * HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new
		 * LongValue(0)); HybridBooleanValue o6 = new HybridBooleanValue(source,
		 * "<", new LongValue(0)); HybridBooleanValue o7 = new
		 * HybridBooleanValue(t, "<", new LongValue(0)); HybridBooleanValue o8 =
		 * new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and",
		 * o7));
		 * 
		 * oFlag = new HybridBooleanValue(o4, "or", o8); // R_AF:bool = 0x10:u32
		 * == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^ // R_EAX:u32))
		 * 
		 * 
		 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
		 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
		 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
		 * 
		 * 
		 * // R_SF:bool = high:bool(T_t_84:u32) sFlag = new
		 * HybridBooleanValue(t, "<", new LongValue(0)); // sFlag = t;
		 * 
		 * // R_ZF:bool = 0:u32 == T_t_84:u32 zFlag = new HybridBooleanValue(t,
		 * "=", new LongValue(0)); }
		 */
	}

	@Deprecated
	public void changeFlagWithIMUL(Value dest, Value source, Environment env, int numBits) {
		// TODO Auto-generated method stub
		// The SF, ZF, AF, and PF flags are undefined
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = 0;

			d = Convert.convertSignedValue(d, numBits);
			s = Convert.convertSignedValue(s, numBits);
			t = Convert.convertSignedValue(d * s, numBits);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			cFlag = new BooleanValue(((d < 0) & (s < 0)) | ((t >= 0) & ((d < 0) | (s < 0))));
			// R_CF:bool =
			// extract:32:32:[pad:u33(T_orig1:u32) + pad:u33(T_orig2:u32) +
			// pad:u33(R_CF:bool)]
			// cFlag = new BooleanValue((d + s + 1) > Math.pow(2, 32) );

			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			oFlag = new BooleanValue(((d < 0) & (s < 0) & (t >= 0)) | ((d >= 0) & (s >= 0) & (t < 0)));

			// R_OF:bool =
			// high:bool((T_orig1:u32 ^ ~T_orig2:u32) & (T_orig1:u32 ^
			// R_EAX_32:u32))
			// oFlag = new BooleanValue((((d ^ -s) & (d ^ (d + s + cf))) >
			// Math.pow(2, 31)));

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			// sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			// zFlag = new BooleanValue(t == 0);

			// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
			// T_t2:u64))
			// if (numBits == 8)
			// aFlag = new BooleanValue(0x10 == (0x10 & ((d + s) ^ d ^ s)));
			// else if (numBits == 16)
			// aFlag = new BooleanValue(0x1000 == (0x1000 & ((d + s + cf) ^ d ^
			// s)));
			// else if (numBits == 32)
			// aFlag = new BooleanValue(
			// 0x10000000 == (0x10000000 & ((d + s + cf) ^ d ^ s)));

		} else {
			Value t = new HybridValue(dest, "*", source);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			HybridBooleanValue oc1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc2 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc3 = new HybridBooleanValue(oc1, "and", oc2);

			HybridBooleanValue oc4 = new HybridBooleanValue(t, ">=", new LongValue(0));
			HybridBooleanValue oc5 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc6 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "and", oc6);
			HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "or", oc7);

			cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			// sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	public void changeFlagWithINC(Value dest, Environment env, int numBits) {
		// TODO Auto-generated method stub
		// PHONG: change here
		Value source = new LongValue(1);
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			// cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = d + s;

			// if (d == 128)
			// System.out.println("Debug");

			d = Convert.convertSignedValue(d, numBits);
			s = Convert.convertSignedValue(s, numBits);
			t = Convert.convertSignedValue(t, numBits);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))

			// INC does not change the carry flag
			// cFlag = new BooleanValue(((d < 0) & (s < 0))
			// | ((t >= 0) & ((d < 0) | (s < 0))));

			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// PHONG: has something strange here
			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			oFlag = new BooleanValue(((d < 0) & (s >= 0) & (t > 0)) | ((d >= 0) & (s < 0) & (t < 0)));

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new BooleanValue(t == 0);

			// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
			// T_t2:u64))

			aFlag = new BooleanValue(0x10 == (0x10 & (t ^ d ^ s)));
		} else {

			Value t = new HybridValue(dest, "+", source);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			HybridBooleanValue oc1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc2 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc3 = new HybridBooleanValue(oc1, "and", oc2);

			// HybridBooleanValue oc4 = new HybridBooleanValue(t, ">=", new
			// LongValue(0));
			// HybridBooleanValue oc5 = new HybridBooleanValue(source, "<", new
			// LongValue(0));
			// HybridBooleanValue oc6 = new HybridBooleanValue(dest, "<", new
			// LongValue(0));
			// HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "and", oc6);
			// HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "or", oc7);

			// cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	@Deprecated
	public void changeFlagWithMUL(Value dest, Value source, Environment env, int numBits) {
		// TODO Auto-generated method stub
		// The SF, ZF, AF, and PF flags are undefined
		// SF need to be checked again
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = d * s;

			// d = convertSignedValue(d, numBits);
			// s = convertSignedValue(s, numBits);
			// t = convertSignedValue(t, numBits);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			cFlag = new BooleanValue(checkFit(t, numBits));
			// R_CF:bool =
			// extract:32:32:[pad:u33(T_orig1:u32) + pad:u33(T_orig2:u32) +
			// pad:u33(R_CF:bool)]
			// cFlag = new BooleanValue((d + s + 1) > Math.pow(2, 32) );

			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			oFlag = new BooleanValue(checkFit(t, numBits));

			// R_OF:bool =
			// high:bool((T_orig1:u32 ^ ~T_orig2:u32) & (T_orig1:u32 ^
			// R_EAX_32:u32))
			// oFlag = new BooleanValue((((d ^ -s) & (d ^ (d + s + cf))) >
			// Math.pow(2, 31)));

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			// sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			zFlag = new BooleanValue(false);

			// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
			// T_t2:u64))
			// if (numBits == 8)
			aFlag = new BooleanValue(false);
			// else if (numBits == 16)
			// aFlag = new BooleanValue(0x1000 == (0x1000 & ((d + s + cf) ^ d ^
			// s)));
			// else if (numBits == 32)
			// aFlag = new BooleanValue(
			// 0x10000000 == (0x10000000 & ((d + s + cf) ^ d ^ s)));

		} else {
			Value t = new HybridValue(dest, "*", source);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			HybridBooleanValue oc1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc2 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc3 = new HybridBooleanValue(oc1, "and", oc2);

			HybridBooleanValue oc4 = new HybridBooleanValue(t, ">=", new LongValue(0));
			HybridBooleanValue oc5 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc6 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "and", oc6);
			HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "or", oc7);

			cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			// sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	public void changeFlagWithNEG(Value temp, Environment env, int numBits) {
		// changeFlagWithSUB(dest, source, env, numBits);
		if (temp == null) {
			return;
		}

		if (temp instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (temp instanceof LongValue) {
			long t = ((LongValue) temp).getValue();

			t = Convert.convertSignedValue(t, numBits);

			// Clear Overflow Flag
			oFlag = new BooleanValue(false);

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			zFlag = new BooleanValue(t == 0);

			// Leave Auxilarry Flag undefined
			// if (t == 0)
			aFlag = new BooleanValue(false);			
		} else {
			// Clear Carry Flag
			cFlag = new BooleanValue(false);

			// Clear Overflow Flag
			oFlag = new BooleanValue(false);

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridBooleanValue(temp, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridBooleanValue(temp, "==", new LongValue(0));
			
			aFlag = new BooleanValue(false);
		}

		if (temp instanceof LongValue) {
			if (((LongValue) temp).getValue() == 0x0) {
				cFlag = new BooleanValue(false);
			} else {
				cFlag = new BooleanValue(true);
			}
		}
	}

	public void changeFlagWithOR(Value dest, Value source, Environment env, int numBits) {
		// AND, OR, and XOR clear OF and CF, leave AF undefined, and update SF,
		// ZF, and PF.
		// http://css.csail.mit.edu/6.858/2010/readings/i386/s03_04.htm
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = d | s;

			t = Convert.convertSignedValue(t, numBits);

			// Clear Carry Flag
			cFlag = new BooleanValue(false);

			// Clear Overflow Flag
			oFlag = new BooleanValue(false);

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			zFlag = new BooleanValue(t == 0);

			// Leave Auxilarry Flag undefined
			// aFlag = new BooleanValue(0x10 == (0x10 & ((d & s) ^ d ^ s)));
			aFlag = new BooleanValue(false);
		} else {
			Value t = new HybridValue(dest, "or", source);

			// Clear Carry Flag
			cFlag = new BooleanValue(false);

			// Clear Overflow Flag
			oFlag = new BooleanValue(false);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
			
			aFlag = new BooleanValue(false);
		}
	}

	@Deprecated
	public void changeFlagWithRL(Value registerValue, Value x, Environment env, int numBits) {
		// TODO Auto-generated method stub

	}

	@Deprecated
	public void changeFlagWithRR(Value registerValue, Value x, Environment env, int numBits) {
		// TODO Auto-generated method stub

	}

	@Deprecated
	public void changeFlagWithSAL(Value dest, Value source, Environment env, int numBits) {
		// The CF flag contains the value of the last bit shifted out of the
		// destination operand;
		// it is undefined for SHL and SHR instructions where the count is
		// greater than or equal to the size (in bits) of the destination
		// operand.
		// The OF flag is affected only for 1-bit shifts (see �Description�
		// above); otherwise, it is undefined.
		// The SF, ZF, and PF flags are set according to the result.
		// If the count is 0, the flags are not affected. For a non-zero count,
		// the AF flag is undefined.
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			if (s != 0) {
				long t = 0;
				cFlag = new BooleanValue(checkMSB(d * s, numBits));
				if (s == 2) {
					// boolean b = getMSB(d * s, numBits);
					// b = ((BooleanValue) cFlag).getValueOperand() ^ b;
					oFlag = new BooleanValue(((BooleanValue) cFlag).getValue() ^ getMSB(d * s, numBits));
				} else {
					oFlag = new BooleanValue(false);
				}

				d = Convert.convertSignedValue(d, numBits);
				s = Convert.convertSignedValue(s, numBits);
				t = Convert.convertSignedValue(d * s, numBits);
				// *1* %CF := ((op1 < 0) & (op2 < 0))
				// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))

				// cFlag = new BooleanValue(((d < 0) & (s < 0))
				// | ((t >= 0) & ((d < 0) | (s < 0))));
				// R_CF:bool =
				// extract:32:32:[pad:u33(T_orig1:u32) + pad:u33(T_orig2:u32) +
				// pad:u33(R_CF:bool)]
				// cFlag = new BooleanValue((d + s + 1) > Math.pow(2, 32) );

				// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
				// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
				// oFlag = new BooleanValue(checkFit(t, numBits));
				// oFlag = new BooleanValue(((d < 0) & (s < 0) & (t >= 0))
				// | ((d >= 0) & (s >= 0) & (t < 0)));

				// R_OF:bool =
				// high:bool((T_orig1:u32 ^ ~T_orig2:u32) & (T_orig1:u32 ^
				// R_EAX_32:u32))
				// oFlag = new BooleanValue((((d ^ -s) & (d ^ (d + s + cf))) >
				// Math.pow(2, 31)));

				// R_PF:bool =
				// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
				// R_EAX_32:u32 in
				// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32
				// in
				// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
				pFlag = new BooleanValue(BitVector.getParityBit(d * s));

				// R_SF:bool = high:bool(T_t_84:u32)
				// R_SF:bool = high:bool(R_EAX_32:u32)
				sFlag = new BooleanValue(t < 0);
				// sFlag = t;

				// R_ZF:bool = 0:u32 == T_t_84:u32
				// R_ZF:bool = 0:u32 == R_EAX_32:u32
				zFlag = new BooleanValue(t == 0);

				// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
				// T_t2:u64))
				// if (numBits == 8)
				aFlag = new BooleanValue(false);
				// else if (numBits == 16)
				// aFlag = new BooleanValue(0x1000 == (0x1000 & ((d + s + cf) ^
				// d ^
				// s)));
				// else if (numBits == 32)
				// aFlag = new BooleanValue(
				// 0x10000000 == (0x10000000 & ((d + s + cf) ^ d ^ s)));
			}
		} else {
			Value t = new HybridValue(dest, "*", source);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			HybridBooleanValue oc1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc2 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc3 = new HybridBooleanValue(oc1, "and", oc2);

			HybridBooleanValue oc4 = new HybridBooleanValue(t, ">=", new LongValue(0));
			HybridBooleanValue oc5 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc6 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "and", oc6);
			HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "or", oc7);

			cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			// sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	@Deprecated
	public void changeFlagWithSAR(Value dest, Value source, Value result, Environment env, int numBits) {
		// The CF flag contains the value of the last bit shifted out of the
		// destination operand;
		// it is undefined for SHL and SHR instructions where the count is
		// greater than or equal to the size (in bits) of the destination
		// operand.
		// The OF flag is affected only for 1-bit shifts (see �Description�
		// above); otherwise, it is undefined.
		// The SF, ZF, and PF flags are set according to the result.
		// If the count is 0, the flags are not affected. For a non-zero count,
		// the AF flag is undefined.
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();

			if (((LongValue) source).getValue() == 1) {
				oFlag = new BooleanValue(false);
			} else {
				oFlag = new BooleanValue(false);
			}

			long s = (long) Math.pow(2, ((LongValue) source).getValue());
			long t = ((LongValue) result).getValue();
			cFlag = new BooleanValue(checkLSB(d, ((LongValue) source).getValue(), numBits));

			d = Convert.convertSignedValue(d, numBits);
			s = Convert.convertSignedValue(s, numBits);
			t = Convert.convertSignedValue(t, numBits);
			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))

			// cFlag = new BooleanValue(((d < 0) & (s < 0))
			// | ((t >= 0) & ((d < 0) | (s < 0))));
			// R_CF:bool =
			// extract:32:32:[pad:u33(T_orig1:u32) + pad:u33(T_orig2:u32) +
			// pad:u33(R_CF:bool)]
			// cFlag = new BooleanValue((d + s + 1) > Math.pow(2, 32) );

			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			// oFlag = new BooleanValue(checkFit(t, numBits));

			// R_OF:bool =
			// high:bool((T_orig1:u32 ^ ~T_orig2:u32) & (T_orig1:u32 ^
			// R_EAX_32:u32))
			// oFlag = new BooleanValue((((d ^ -s) & (d ^ (d + s + cf))) >
			// Math.pow(2, 31)));

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			zFlag = new BooleanValue(t == 0);

			// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
			// T_t2:u64))
			// if (numBits == 8)
			// aFlag = new BooleanValue(true);
			// else if (numBits == 16)
			// aFlag = new BooleanValue(0x1000 == (0x1000 & ((d + s + cf) ^ d ^
			// s)));
			// else if (numBits == 32)
			// aFlag = new BooleanValue(
			// 0x10000000 == (0x10000000 & ((d + s + cf) ^ d ^ s)));

		} else {
			Value t = new HybridValue(dest, "/", source);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			HybridBooleanValue oc1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc2 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc3 = new HybridBooleanValue(oc1, "and", oc2);

			HybridBooleanValue oc4 = new HybridBooleanValue(t, ">=", new LongValue(0));
			HybridBooleanValue oc5 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc6 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "and", oc6);
			HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "or", oc7);

			cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			// sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	@Deprecated
	public void changeFlagWithSBB(Value dest, Value source, int cf, Environment env, int numBits) {
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = 0;

			d = Convert.convertSignedValue(d, numBits);
			s = Convert.convertSignedValue(s, numBits);
			t = Convert.convertSignedValue(d - (s + cf), numBits);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			cFlag = new BooleanValue(((d >= 0) & (s < 0)) | ((t < 0) & ((d >= 0) | (s < 0))));
			// R_CF:bool =
			// extract:32:32:[pad:u33(T_orig1:u32) + pad:u33(T_orig2:u32) +
			// pad:u33(R_CF:bool)]
			// cFlag = new BooleanValue((d + s + 1) > Math.pow(2, 32) );

			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			oFlag = new BooleanValue(((d < 0) & (s >= 0) & (t > 0)) | ((d >= 0) & (s < 0) & (t < 0)));

			// R_OF:bool =
			// high:bool((T_orig1:u32 ^ ~T_orig2:u32) & (T_orig1:u32 ^
			// R_EAX_32:u32))
			// oFlag = new BooleanValue((((d ^ -s) & (d ^ (d + s + cf))) >
			// Math.pow(2, 31)));

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			zFlag = new BooleanValue(t == 0);

			// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
			// T_t2:u64))
			// if (numBits == 8)
			aFlag = new BooleanValue(0x10 == (0x10 & (t ^ d ^ s)));
			// else if (numBits == 16)
			// aFlag = new BooleanValue(0x1000 == (0x1000 & ((d + s + cf) ^ d ^
			// s)));
			// else if (numBits == 32)
			// aFlag = new BooleanValue(
			// 0x10000000 == (0x10000000 & ((d + s + cf) ^ d ^ s)));

		} else {
			Value t = new HybridValue(dest, "-", source);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			HybridBooleanValue oc1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc2 = new HybridBooleanValue(source, ">", new LongValue(0));
			HybridBooleanValue oc3 = new HybridBooleanValue(oc1, "and", oc2);

			HybridBooleanValue oc4 = new HybridBooleanValue(t, ">=", new LongValue(0));
			HybridBooleanValue oc5 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue oc6 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "and", oc6);
			HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "or", oc7);

			cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	@Deprecated
	public void changeFlagWithSDIV(Value d, Value s, Environment env, int bitCount) {
		// Change Flag with Signed Division
		//
	}

	@Deprecated
	public void changeFlagWithSHL(Value dest, Value source, Environment env, int numBits) {
		// The CF flag contains the value of the last bit shifted out of the
		// destination operand;
		// it is undefined for SHL and SHR instructions where the count is
		// greater than or equal to the size (in bits) of the destination
		// operand.
		// The OF flag is affected only for 1-bit shifts (see �Description�
		// above); otherwise, it is undefined.
		// The SF, ZF, and PF flags are set according to the result.
		// If the count is 0, the flags are not affected. For a non-zero count,
		// the AF flag is undefined.
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			if (s != 0) {
				long t = 0;
				cFlag = new BooleanValue(checkMSB(d * s, numBits));
				if (s == 2) {
					// boolean b = getMSB(d * s, numBits);
					// b = ((BooleanValue) cFlag).getValueOperand() ^ b;
					oFlag = new BooleanValue(((BooleanValue) cFlag).getValue() ^ getMSB(d * s, numBits));
				} else {
					oFlag = new BooleanValue(false);
				}

				d = Convert.convertSignedValue(d, numBits);
				s = Convert.convertSignedValue(s, numBits);
				t = Convert.convertSignedValue(d * s, numBits);
				// *1* %CF := ((op1 < 0) & (op2 < 0))
				// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))

				// cFlag = new BooleanValue(((d < 0) & (s < 0))
				// | ((t >= 0) & ((d < 0) | (s < 0))));
				// R_CF:bool =
				// extract:32:32:[pad:u33(T_orig1:u32) + pad:u33(T_orig2:u32) +
				// pad:u33(R_CF:bool)]
				// cFlag = new BooleanValue((d + s + 1) > Math.pow(2, 32) );

				// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
				// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
				// oFlag = new BooleanValue(checkFit(t, numBits));
				// oFlag = new BooleanValue(((d < 0) & (s < 0) & (t >= 0))
				// | ((d >= 0) & (s >= 0) & (t < 0)));

				// R_OF:bool =
				// high:bool((T_orig1:u32 ^ ~T_orig2:u32) & (T_orig1:u32 ^
				// R_EAX_32:u32))
				// oFlag = new BooleanValue((((d ^ -s) & (d ^ (d + s + cf))) >
				// Math.pow(2, 31)));

				// R_PF:bool =
				// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
				// R_EAX_32:u32 in
				// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32
				// in
				// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
				pFlag = new BooleanValue(BitVector.getParityBit(d * s));

				// R_SF:bool = high:bool(T_t_84:u32)
				// R_SF:bool = high:bool(R_EAX_32:u32)
				sFlag = new BooleanValue(t < 0);
				// sFlag = t;

				// R_ZF:bool = 0:u32 == T_t_84:u32
				// R_ZF:bool = 0:u32 == R_EAX_32:u32
				zFlag = new BooleanValue(t == 0);

				// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
				// T_t2:u64))
				// if (numBits == 8)
				aFlag = new BooleanValue(false);
				// else if (numBits == 16)
				// aFlag = new BooleanValue(0x1000 == (0x1000 & ((d + s + cf) ^
				// d ^
				// s)));
				// else if (numBits == 32)
				// aFlag = new BooleanValue(
				// 0x10000000 == (0x10000000 & ((d + s + cf) ^ d ^ s)));
			}
		} else {
			Value t = new HybridValue(dest, "*", source);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			HybridBooleanValue oc1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc2 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc3 = new HybridBooleanValue(oc1, "and", oc2);

			HybridBooleanValue oc4 = new HybridBooleanValue(t, ">=", new LongValue(0));
			HybridBooleanValue oc5 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc6 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "and", oc6);
			HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "or", oc7);

			cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			// sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	@Deprecated
	public void changeFlagWithSHR(Value dest, Value source, Environment env, int numBits) {
		// The CF flag contains the value of the last bit shifted out of the
		// destination operand;
		// it is undefined for SHL and SHR instructions where the count is
		// greater than or equal to the size (in bits) of the destination
		// operand.
		// The OF flag is affected only for 1-bit shifts (see �Description�
		// above); otherwise, it is undefined.
		// The SF, ZF, and PF flags are set according to the result.
		// If the count is 0, the flags are not affected. For a non-zero count,
		// the AF flag is undefined.
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();

			if (((LongValue) source).getValue() == 1) {
				oFlag = new BooleanValue(getMSB(d, numBits));
			} else {
				oFlag = new BooleanValue(false);
			}

			long s = (long) Math.pow(2, ((LongValue) source).getValue());
			long t = d / s;
			cFlag = new BooleanValue(checkLSB(d, ((LongValue) source).getValue(), numBits));

			d = Convert.convertSignedValue(d, numBits);
			s = Convert.convertSignedValue(s, numBits);
			t = Convert.convertSignedValue(t, numBits);
			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))

			// cFlag = new BooleanValue(((d < 0) & (s < 0))
			// | ((t >= 0) & ((d < 0) | (s < 0))));
			// R_CF:bool =
			// extract:32:32:[pad:u33(T_orig1:u32) + pad:u33(T_orig2:u32) +
			// pad:u33(R_CF:bool)]
			// cFlag = new BooleanValue((d + s + 1) > Math.pow(2, 32) );

			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			// oFlag = new BooleanValue(checkFit(t, numBits));

			// R_OF:bool =
			// high:bool((T_orig1:u32 ^ ~T_orig2:u32) & (T_orig1:u32 ^
			// R_EAX_32:u32))
			// oFlag = new BooleanValue((((d ^ -s) & (d ^ (d + s + cf))) >
			// Math.pow(2, 31)));

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(d / s));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			zFlag = new BooleanValue(t == 0);

			// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
			// T_t2:u64))
			// if (numBits == 8)
			// aFlag = new BooleanValue(true);
			// else if (numBits == 16)
			// aFlag = new BooleanValue(0x1000 == (0x1000 & ((d + s + cf) ^ d ^
			// s)));
			// else if (numBits == 32)
			// aFlag = new BooleanValue(
			// 0x10000000 == (0x10000000 & ((d + s + cf) ^ d ^ s)));

		} else {
			Value t = new HybridValue(dest, "/", source);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			HybridBooleanValue oc1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc2 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc3 = new HybridBooleanValue(oc1, "and", oc2);

			HybridBooleanValue oc4 = new HybridBooleanValue(t, ">=", new LongValue(0));
			HybridBooleanValue oc5 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc6 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "and", oc6);
			HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "or", oc7);

			cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			// sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	/*
	 * public long convertSignedValue(long value, int numBits) { value =
	 * Convert.convetUnsignedValue(value, numBits);
	 * 
	 * if (numBits == 8) { if (value >= 128) return value - 256; } else if
	 * (numBits == 16) { if (value >= 32768) return value - 65536; } else if
	 * (numBits == 32) { if (value >= 2147483648l) return value - 4294967296l; }
	 * 
	 * return value; }
	 */

	@Deprecated
	public void changeFlagWithSpecialCase() {
		// TODO Auto-generated method stub
		zFlag = new BooleanValue(true);
		cFlag = new BooleanValue(false);
		sFlag = new BooleanValue(false);
		oFlag = new BooleanValue(false);
	}

	public void changeFlagWithSUB(Value dest, Value source, Environment env, int numBits) {
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = 0;

			d = Convert.convertSignedValue(d, numBits);
			s = Convert.convertSignedValue(s, numBits);
			t = Convert.convertSignedValue(d - s, numBits);

			// *1* %CF := ((op1 < 0) & (op2 < 0))
			// | ((result >= 0) & ((op1 < 0) | (op2 < 0)))
			cFlag = new BooleanValue(((d >= 0) & (s < 0)) | ((t < 0) & ((d >= 0) | (s < 0))));
			//cFlag = new BooleanValue(((d < 0) & (s < 0)) | ((t >= 0) & ((d < 0) | (s < 0))));
			// R_CF:bool =
			// extract:32:32:[pad:u33(T_orig1:u32) + pad:u33(T_orig2:u32) +
			// pad:u33(R_CF:bool)]
			// cFlag = new BooleanValue((d + s + 1) > Math.pow(2, 32) );

			// *1* %OF := ((op1 < 0) & (op2 < 0) & (result >= 0))
			// | ((op1 >= 0) & (op2 >= 0) & (result < 0))
			oFlag = new BooleanValue(((d < 0) & (s >= 0) & (t > 0)) | ((d >= 0) & (s < 0) & (t < 0)));
			//oFlag = new BooleanValue(((d < 0) & (s < 0) & (t >= 0)) | ((d >= 0) & (s >= 0) & (t < 0)));

			// R_OF:bool =
			// high:bool((T_orig1:u32 ^ ~T_orig2:u32) & (T_orig1:u32 ^
			// R_EAX_32:u32))
			// oFlag = new BooleanValue((((d ^ -s) & (d ^ (d + s + cf))) >
			// Math.pow(2, 31)));

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			zFlag = new BooleanValue(t == 0);

			// R_AF:bool = 0x10:u64 == (0x10:u64 & (R_RBX:u64 ^ T_t1:u64 ^
			// T_t2:u64))
			// if (numBits == 8)
			aFlag = new BooleanValue(0x10 == (0x10 & (t ^ d ^ s)));
			// else if (numBits == 16)
			// aFlag = new BooleanValue(0x1000 == (0x1000 & ((d + s + cf) ^ d ^
			// s)));
			// else if (numBits == 32)
			// aFlag = new BooleanValue(
			// 0x10000000 == (0x10000000 & ((d + s + cf) ^ d ^ s)));

		} else {
			Value t = new HybridValue(dest, "-", source);

			// cFlag = new BooleanValue(((d >= 0) & (s < 0))
			// | ((t < 0) & ((d >= 0) | (s < 0))));
			HybridBooleanValue oc1 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue oc2 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc3 = new HybridBooleanValue(oc1, "and", oc2);

			HybridBooleanValue oc4 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue oc5 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue oc6 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue oc7 = new HybridBooleanValue(oc5, "or", oc6);
			HybridBooleanValue oc8 = new HybridBooleanValue(oc4, "and", oc7);

			cFlag = new HybridBooleanValue(oc3, "or", oc8);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			// *1* %OF := ((op1 < 0) & (op2 >= 0) & (result > 0))
			// | ((op1 >= 0) & (op2 < 0) & (result < 0))
			HybridBooleanValue o1 = new HybridBooleanValue(dest, "<", new LongValue(0));
			HybridBooleanValue o2 = new HybridBooleanValue(source, ">=", new LongValue(0));
			HybridBooleanValue o3 = new HybridBooleanValue(t, ">", new LongValue(0));
			HybridBooleanValue o4 = new HybridBooleanValue(o1, "and", new HybridBooleanValue(o2, "and", o3));

			HybridBooleanValue o5 = new HybridBooleanValue(dest, ">=", new LongValue(0));
			HybridBooleanValue o6 = new HybridBooleanValue(source, "<", new LongValue(0));
			HybridBooleanValue o7 = new HybridBooleanValue(t, "<", new LongValue(0));
			HybridBooleanValue o8 = new HybridBooleanValue(o5, "and", new HybridBooleanValue(o6, "and", o7));

			oFlag = new HybridBooleanValue(o4, "or", o8);
			// R_AF:bool = 0x10:u32 == (0x10:u32 & (T_t_84:u32 ^ R_EBX:u32 ^
			// R_EAX:u32))

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
		}
	}

	public void changeFlagWithTEST(Value d, Value s, Environment env, int numBits) {
		// TODO Auto-generated method stub
		this.changeFlagWithAND(d, s, env, numBits);
	}

	public void changeFlagWithXOR(Value dest, Value source, Environment env, int numBits) {
		// AND, OR, and XOR clear OF and CF, leave AF undefined, and update SF,
		// ZF, and PF.
		// http://css.csail.mit.edu/6.858/2010/readings/i386/s03_04.htm
		if (dest == null || source == null) {
			return;
		}

		if (dest instanceof AnyValue || source instanceof AnyValue) {
			cFlag = new BooleanValue(false);
			sFlag = new BooleanValue(false);
			zFlag = new BooleanValue(true);
		} else if (dest instanceof LongValue && source instanceof LongValue) {
			long d = ((LongValue) dest).getValue();
			long s = ((LongValue) source).getValue();
			long t = d ^ s;

			d = Convert.convertSignedValue(d, numBits);
			s = Convert.convertSignedValue(s, numBits);
			t = Convert.convertSignedValue(t, numBits);

			// Clear Carry Flag
			cFlag = new BooleanValue(false);

			// Clear Overflow Flag
			oFlag = new BooleanValue(false);

			// R_PF:bool =
			// ~low:bool(let T_acc_174:u32 := R_EAX_32:u32 >> 4:u32 ^
			// R_EAX_32:u32 in
			// let T_acc_174:u32 := T_acc_174:u32 >> 2:u32 ^ T_acc_174:u32 in
			// T_acc_174:u32 >> 1:u32 ^ T_acc_174:u32)
			pFlag = new BooleanValue(BitVector.getParityBit(t));

			// R_SF:bool = high:bool(T_t_84:u32)
			// R_SF:bool = high:bool(R_EAX_32:u32)
			sFlag = new BooleanValue(t < 0);
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			// R_ZF:bool = 0:u32 == R_EAX_32:u32
			zFlag = new BooleanValue(t == 0);

			// Leave Auxilarry Flag undefined
			// aFlag = new BooleanValue(0x10 == (0x10 & ((d & s) ^ d ^ s)));
			aFlag = new BooleanValue(false);
		} else {
			Value t = new HybridValue(dest, "xor", source);

			// Clear Carry Flag
			cFlag = new BooleanValue(false);

			// Clear Overflow Flag
			oFlag = new BooleanValue(false);

			// R_OF:bool = high:bool((R_EBX:u32 ^ R_EAX:u32) & (R_EBX:u32 ^
			// T_t_84:u32))
			// OtherExp x = new OtherExp(new OtherExp(source, "xor", dest),
			// "and",
			// new OtherExp(source, "xor", t));
			// oFlag = new OtherExp(x, ">", new ConstantLongExp(0));

			/*
			 * R_PF:bool = ~low:bool(T_t_84:u32 >> 7:u32 ^ T_t_84:u32 >> 6:u32 ^
			 * T_t_84:u32 >> 5:u32 ^ T_t_84:u32 >> 4:u32 ^ T_t_84:u32 >> 3:u32 ^
			 * T_t_84:u32 >> 2:u32 ^ T_t_84:u32 >> 1:u32 ^ T_t_84:u32)
			 */

			// R_SF:bool = high:bool(T_t_84:u32)
			sFlag = new HybridBooleanValue(t, "<", new LongValue(0));
			// sFlag = t;

			// R_ZF:bool = 0:u32 == T_t_84:u32
			zFlag = new HybridBooleanValue(t, "==", new LongValue(0));
			
			aFlag = new BooleanValue(false);
		}
	}

	private boolean checkFit(long t, int numBits) {
		// TODO Auto-generated method stub
		if (numBits == 8) {
			return t > Math.pow(2, 8);
		} else if (numBits == 16) {
			return t > Math.pow(2, 16);
		} else if (numBits == 32) {
			return t > Math.pow(2, 32);
		}

		return false;
	}

	/*
	 * private boolean getParityBit(long t) { // TODO Auto-generated method stub
	 * int k = (int) (t % Math.pow(2, 8)); String s = Integer.toBinaryString(k);
	 * int r = 0; for (int i = 0; i < s.length(); i++) if (s.charAt(i) == '1')
	 * r++; return r % 2 == 0; }
	 */

	private boolean checkLSB(long t, long v, int numBits) {
		// TODO Auto-generated method stub
		long x = (long) (t % Math.pow(2, v));
		long y = (long) (x / Math.pow(2, v - 1));
		return y == 1;
	}

	private boolean checkMSB(long t, int numBits) {
		// TODO Auto-generated method stub
		if (numBits == 8) {
			long x = (long) (t % Math.pow(2, 9));
			long y = (long) (x / Math.pow(2, 8));
			return y == 1;
		} else if (numBits == 16) {
			long x = (long) (t % Math.pow(2, 17));
			long y = (long) (x / Math.pow(2, 16));
			return y == 1;
		} else if (numBits == 32) {
			long x = (long) (t % Math.pow(2, 33));
			long y = (long) (x / Math.pow(2, 32));
			return y == 1;
		}

		return false;
	}

	@Override
	public Flag clone() {
		Flag c = new Flag();
		c.setAFlag(aFlag.clone());
		c.setCFlag(cFlag.clone());
		c.setDFlag(dFlag.clone());
		c.setIFlag(iFlag.clone());
		c.setOFlag(oFlag.clone());
		c.setPFlag(pFlag.clone());
		c.setSFlag(sFlag.clone());
		c.setTFlag(tFlag.clone());
		c.setZFlag(zFlag.clone());

		return c;
	}

	public boolean equal(Flag f) {
		return cFlag.equal(f.getCFlag()) && oFlag.equal(f.getOFlag()) && sFlag.equal(f.getSFlag())
				&& zFlag.equal(f.getZFlag());
	}

	public Value getAFlag() {
		return aFlag;
	}

	public Value getCFlag() {
		return cFlag;
	}

	public Value getDFlag() {
		return dFlag;
	}

	// PHONG - 21/04/2015: EFLAG -----------------------------------------------
	public long getEFlags() {
		long efl = 0x0;
		efl |= 1 << 1;
		// Set bit for flag
		long bit0, bit2, bit4, bit6, bit7, bit8, bit9, bit10, bit11;
		if (cFlag != null && cFlag instanceof BooleanValue) {
			bit0 = ((BooleanValue) cFlag).getValue() ? 1 : 0;
			efl |= bit0 << 0;
		}
		if (pFlag != null && pFlag instanceof BooleanValue) {
			bit2 = ((BooleanValue) pFlag).getValue() ? 1 : 0;
			efl |= bit2 << 2;
		}
		if (aFlag != null && aFlag instanceof BooleanValue) {
			bit4 = ((BooleanValue) aFlag).getValue() ? 1 : 0;
			efl |= bit4 << 4;
		}
		if (zFlag != null && zFlag instanceof BooleanValue) {
			bit6 = ((BooleanValue) zFlag).getValue() ? 1 : 0;
			efl |= bit6 << 6;
		}
		if (sFlag != null && sFlag instanceof BooleanValue) {
			bit7 = ((BooleanValue) sFlag).getValue() ? 1 : 0;
			efl |= bit7 << 7;
		}
		if (tFlag != null && tFlag instanceof BooleanValue) {
			bit8 = ((BooleanValue) tFlag).getValue() ? 1 : 0;
			efl |= bit8 << 8;
		}
		if (iFlag != null && iFlag instanceof BooleanValue) {
			bit9 = ((BooleanValue) iFlag).getValue() ? 1 : 0;
			efl |= bit9 << 9;
		}
		if (dFlag != null && dFlag instanceof BooleanValue) {
			bit10 = ((BooleanValue) dFlag).getValue() ? 1 : 0;
			efl |= bit10 << 10;
		}
		if (oFlag != null && oFlag instanceof BooleanValue) {
			bit11 = ((BooleanValue) oFlag).getValue() ? 1 : 0;
			efl |= bit11 << 11;
		}
		return efl;
	}

	public Value getEFLAGS() {
		int cf = 0, pf = 0, af = 0, zf = 0, sf = 0, tf = 0, ifl = 0, df = 0, of = 0;
		if (cFlag != null && cFlag instanceof BooleanValue) {
			cf = (((BooleanValue) cFlag).getValue() ? 1 : 0);
		}
		if (pFlag != null && pFlag instanceof BooleanValue) {
			pf = (((BooleanValue) pFlag).getValue() ? 1 : 0);
		}
		if (aFlag != null && aFlag instanceof BooleanValue) {
			af = (((BooleanValue) aFlag).getValue() ? 1 : 0);
		}
		if (zFlag != null && zFlag instanceof BooleanValue) {
			zf = (((BooleanValue) zFlag).getValue() ? 1 : 0);
		}
		if (sFlag != null && sFlag instanceof BooleanValue) {
			sf = (((BooleanValue) sFlag).getValue() ? 1 : 0);
		}
		if (tFlag != null && tFlag instanceof BooleanValue) {
			tf = (((BooleanValue) tFlag).getValue() ? 1 : 0);
		}
		if (iFlag != null && iFlag instanceof BooleanValue) {
			ifl = (((BooleanValue) iFlag).getValue() ? 1 : 0);
		}
		if (dFlag != null && dFlag instanceof BooleanValue) {
			df = (((BooleanValue) dFlag).getValue() ? 1 : 0);
		}
		if (oFlag != null && oFlag instanceof BooleanValue) {
			of = (((BooleanValue) oFlag).getValue() ? 1 : 0);
		}

		long ret = cf + 2 + 4 * pf + 16 * af + 64 * zf + 128 * sf + 256 * tf + 512 * ifl + 1024 * df + 2048 * of;
		return new LongValue(ret);
	}

	public Value getIFlag() {
		return iFlag;
	}

	private boolean getMSB(long d, int numBits) {
		// TODO Auto-generated method stub
		d = Convert.convetUnsignedValue(d, numBits);

		if (numBits == 8) {
			return d >= Math.pow(2, 7);
		} else if (numBits == 16) {
			return d >= Math.pow(2, 15);
		} else if (numBits == 32) {
			return d >= Math.pow(2, 31);
		}

		return false;
	}

	public Value getOFlag() {
		return oFlag;
	}

	/**
	 * @return the pFlag
	 */
	public Value getPFlag() {
		return pFlag;
	}

	/**
	 * @return the sFlag
	 */
	public Value getSFlag() {
		return sFlag;
	}

	/**
	 * @return the tFlag
	 */
	public Value getTFlag() {
		return tFlag;
	}

	/**
	 * @return the zFlag
	 */
	public Value getZFlag() {
		return zFlag;
	}

	public void init() {
		// TODO Auto-generated method stub
		setAFlag(new BooleanValue(false));
		setCFlag(new BooleanValue(false));
		setDFlag(new BooleanValue(false));
		setIFlag(new BooleanValue(true));
		setOFlag(new BooleanValue(false));
		setPFlag(new BooleanValue(true));
		setSFlag(new BooleanValue(false));
		setTFlag(new BooleanValue(false));
		setZFlag(new BooleanValue(true));
	}

	public void setAFlag(Value aFlag) {
		this.aFlag = aFlag;
	}

	public void setCFlag(Value cFlag) {
		this.cFlag = cFlag;
	}

	public void setDFlag(Value dFlag) {
		this.dFlag = dFlag;
	}

	public void setEFLAGS(long value) {
		setflags(value);
	}

	// Set flags from eflags
	public void setflags(long efl) {
		this.cFlag = new BooleanValue(((efl >> 0) & 1) != 0);
		this.pFlag = new BooleanValue(((efl >> 2) & 1) != 0);
		this.aFlag = new BooleanValue(((efl >> 4) & 1) != 0);
		this.zFlag = new BooleanValue(((efl >> 6) & 1) != 0);
		this.sFlag = new BooleanValue(((efl >> 7) & 1) != 0);
		this.tFlag = new BooleanValue(((efl >> 8) & 1) != 0);
		this.iFlag = new BooleanValue(((efl >> 9) & 1) != 0);
		this.dFlag = new BooleanValue(((efl >> 10) & 1) != 0);
		this.oFlag = new BooleanValue(((efl >> 11) & 1) != 0);
	}

	// -------------------------------------------------------------------------------

	public void setIFlag(Value iFlag) {
		this.iFlag = iFlag;
	}

	public void setOFlag(Value oFlag) {
		this.oFlag = oFlag;
	}

	/**
	 * @param pFlag
	 *            the pFlag to set
	 */
	public void setPFlag(Value pFlag) {
		this.pFlag = pFlag;
	}

	/**
	 * @param sFlag
	 *            the sFlag to set
	 */
	public void setSFlag(Value sFlag) {
		this.sFlag = sFlag;
	}

	/**
	 * @param tFlag
	 *            the tFlag to set
	 */
	public void setTFlag(Value tFlag) {
		this.tFlag = tFlag;
	}

	/**
	 * @param zFlag
	 *            the zFlag to set
	 */
	public void setZFlag(Value zFlag) {
		this.zFlag = zFlag;
	}

	@Override
	public String toString() {
		return "cf=" + cFlag.toString() + ", " + "pf=" + pFlag.toString() + ", " + "af=" + aFlag.toString() + ", "
				+ "zf=" + zFlag.toString() + ", " + "sf=" + sFlag.toString() + ", " + "tf=" + tFlag.toString() + ", "
				+ "df=" + dFlag.toString() + ", " + "of=" + oFlag.toString() + ", " + "if=" + iFlag.toString();
	}

	public void setFlagValue(String flag, Value value) {
		// TODO Auto-generated method stub
		String temp = flag.toLowerCase();
		
		if (temp.contains("cf")) {
			cFlag = value;
		} else if (temp.contains("pf")) {
			pFlag = value;
		} else if (temp.contains("af")) {
			aFlag = value;
		} else if (temp.contains("zf")) {
			zFlag = value;
		} else if (temp.contains("sf")) {
			sFlag = value;
		} else if (temp.contains("tf")) {
			tFlag = value;
		} else if (temp.contains("df")) {
			dFlag = value;
		} else if (temp.contains("of")) {
			oFlag = value;
		} else if (temp.contains("if")) {
			iFlag = value;
		}
	}

	public void resetValue(Map<String, Long> z3Value) {
		// TODO Auto-generated method stub
		setFlagValue("af", aFlag.evaluate(z3Value));
		setFlagValue("cf", cFlag.evaluate(z3Value));
		setFlagValue("df", dFlag.evaluate(z3Value));
		setFlagValue("sf", sFlag.evaluate(z3Value));
		setFlagValue("of", oFlag.evaluate(z3Value));
		setFlagValue("if", iFlag.evaluate(z3Value));
		setFlagValue("tf", tFlag.evaluate(z3Value));
		setFlagValue("pf", pFlag.evaluate(z3Value));
		setFlagValue("zf", zFlag.evaluate(z3Value));
	}
	
	// PHONG - 20150916
	public void setAllFlagValue (Value value)
	{
		if (value instanceof LongValue)
		{
			long eflag = ((LongValue) value).getValue();
			cFlag = new BooleanValue((eflag & (1L << CFLAG_BIT)) != 0);
			pFlag = new BooleanValue((eflag & (1L << PFLAG_BIT)) != 0);
			aFlag = new BooleanValue((eflag & (1L << AFLAG_BIT)) != 0);
			zFlag = new BooleanValue((eflag & (1L << ZFLAG_BIT)) != 0);
			sFlag = new BooleanValue((eflag & (1L << SFLAG_BIT)) != 0);
			tFlag = new BooleanValue((eflag & (1L << TFLAG_BIT)) != 0);
			dFlag = new BooleanValue((eflag & (1L << DFLAG_BIT)) != 0);
			oFlag = new BooleanValue((eflag & (1L << OFLAG_BIT)) != 0);
			iFlag = new BooleanValue((eflag & (1L << IFLAG_BIT)) != 0);
		}
	}

	public String toHashString() {
		// TODO Auto-generated method stub
		MD5 md5 = new MD5();
//		String memoryStr = getOrderedStringContent();
		try {
			md5.Update(toString(), null);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return md5.asHex();
	}
}
