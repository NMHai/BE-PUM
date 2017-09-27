package org.analysis.complement;

;

public class TestBitVec {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Add: " + OldBitVector.add(100, -2000));
		System.out.println("Long Max: " + Long.MAX_VALUE);
		// 4294965296
		long n = 4294965296l;
		System.out.println("Trunk: " + OldBitVector.cut32Bit(34359737728l));
		System.out.println("Trunk: " + OldBitVector.cut32Bit(3435978l));
		System.out.println("Add: " + OldBitVector.add(100, n));
		System.out.println("Sub: " + OldBitVector.sub(0, 4294967196l));
		System.out.println("Mul: " + OldBitVector.unsignedMul(4294967216l, 8));
		// 4294966496 = -80
		// 34359737728 --> -640

		System.out.println("Div: " + OldBitVector.unsignedDiv(4294965296l, 100));
		System.out.println("Greater Than: " + OldBitVector.gt(100, -2000));
		System.out.println("Less Than: " + OldBitVector.lt(100, -2000));
		System.out.println("Greater Than or Equal: " + OldBitVector.ge(100, -2000));
		System.out.println("Less Than or Equal: " + OldBitVector.le(100, -2000));

		long x = 8519680;
		long y = OldBitVector.rl(x, 5);
		System.out.println("Rotate Left: " + y);
		System.out.println("Rotate Right: " + OldBitVector.rr(y, 5));
	}

}
