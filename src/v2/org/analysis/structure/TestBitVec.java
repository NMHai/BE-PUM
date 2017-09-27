package v2.org.analysis.structure;

public class TestBitVec {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Add: " + BitVector.add(100, -2000));
		System.out.println("Long Max: " + Long.MAX_VALUE);
		// 4294965296
		long n = 4294965296l;
		System.out.println("Trunk: " + BitVector.cut32Bit(34359737728l));
		System.out.println("Trunk: " + BitVector.cut32Bit(3435978l));
		System.out.println("Add: " + BitVector.add(100, n));
		System.out.println("Sub: " + BitVector.sub(0, 4294967196l));
		System.out.println("Mul: " + BitVector.signedMul(4294967216l, 8));
		// 4294966496 = -80
		// 34359737728 --> -640

		System.out.println("Div: " + BitVector.unsignedDiv(4294965296l, 100));
		System.out.println("Greater Than: " + BitVector.gt(100, -2000));
		System.out.println("Less Than: " + BitVector.lt(100, -2000));
		System.out.println("Greater Than or Equal: " + BitVector.ge(100, -2000));
		System.out.println("Less Than or Equal: " + BitVector.le(100, -2000));
		long x = 8519680;
		System.out.println("Rotate Left: " + BitVector.rl(x, 5));
		System.out.println("Rotate Right: " + BitVector.rr(x, 5));
	}

}
