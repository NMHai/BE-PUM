package org.analysis.complement;

public class OldBitVector {
	public static final int MAX_NUM_BIT = 32;

	public static long add(long x, long y) {
		// http://stackoverflow.com/questions/4895173/bitwise-multiply-and-add-in-java
		// long result = 0;
		/*
		 * long x = n1, y = n2; long xor, and, temp; and = x & y; xor = x ^ y;
		 * 
		 * while (and != 0) { and <<= 1; temp = xor ^ and; and &= xor; xor =
		 * temp; } return cut32Bit(xor);
		 */
		long carry = 0;
		long result = 0;
		long i;

		for (i = 0; i < 32; ++i) {
			long a = (x >> i) & 1;
			long b = (y >> i) & 1;
			result |= ((a ^ b) ^ carry) << i;
			carry = (a & b) | (b & carry) | (carry & a);
		}
		return cut32Bit(result);
	}

	public static long cut32Bit(long input) {

		boolean[] bits = new boolean[64];
		for (int i = 63; i >= 0; i--) {
			bits[i] = (input & (1 << i)) != 0;
		}

		// System.out.println(input + " = " + Arrays.toString(bits));

		long result = 0;
		for (int i = 0; i < MAX_NUM_BIT; i++)
			if (bits[i])
				result += Math.pow(2, i);

		return result;
	}

	private static long negate(long x) {
		return add(~x, 1);
	}

	public static long sub(long dest, long source) {
		// http://stackoverflow.com/questions/1149929/how-to-add-two-numbers-without-using-or-or-another-arithmetic-operator
		return add(dest, negate(source));
	}

	public static long unsignedMul(long n1, long n2) {
		// http://stackoverflow.com/questions/4895173/bitwise-multiply-and-add-in-java
		/*
		 * This value will hold n1 * 2^i for varying values of i. It will start
		 * off holding n1 * 2^0 = n1, and after each iteration will be updated
		 * to hold the next term in the sequence.
		 */
		long a = n1;

		/*
		 * This value will be used to read the individual bits out of n2. We'll
		 * use the shifting trick to read the bits and will maintain the
		 * invariant that after i iterations, b is equal to n2 >> i.
		 */
		long b = n2;

		/* This value will hold the sum of the terms so far. */
		long result = 0;

		/*
		 * Continuously loop over more and more bits of n2 until we've consumed
		 * the last of them. Since after i iterations of the loop b = n2 >> i,
		 * this only reaches zero once we've used up all the bits of the
		 * original value of n2.
		 */
		while (b != 0) {
			/*
			 * Using the bitwise AND trick, determine whether the ith bit of b
			 * is a zero or one. If it's a zero, then the current term in our
			 * sum is zero and we don't do anything. Otherwise, then we should
			 * add n1 * 2^i.
			 */
			if ((b & 1) != 0) {
				/*
				 * Recall that a = n1 * 2^i at this point, so we're adding in
				 * the next term in the sum.
				 */
				result = result + a;
			}

			/*
			 * To maintain that a = n1 * 2^i after i iterations, scale it by a
			 * factor of two by left shifting one position.
			 */
			a <<= 1;

			/*
			 * To maintain that b = n2 >> i after i iterations, shift it one
			 * spot over.
			 */
			b >>>= 1;
		}
		return cut32Bit(result);
	}

	public static long signedMul(long n1, long n2) {
		// http://stackoverflow.com/questions/4895173/bitwise-multiply-and-add-in-java
		/*
		 * This value will hold n1 * 2^i for varying values of i. It will start
		 * off holding n1 * 2^0 = n1, and after each iteration will be updated
		 * to hold the next term in the sequence.
		 */
		long a = n1;

		/*
		 * This value will be used to read the individual bits out of n2. We'll
		 * use the shifting trick to read the bits and will maintain the
		 * invariant that after i iterations, b is equal to n2 >> i.
		 */
		long b = n2;

		/* This value will hold the sum of the terms so far. */
		long result = 0;

		/*
		 * Continuously loop over more and more bits of n2 until we've consumed
		 * the last of them. Since after i iterations of the loop b = n2 >> i,
		 * this only reaches zero once we've used up all the bits of the
		 * original value of n2.
		 */
		while (b != 0) {
			/*
			 * Using the bitwise AND trick, determine whether the ith bit of b
			 * is a zero or one. If it's a zero, then the current term in our
			 * sum is zero and we don't do anything. Otherwise, then we should
			 * add n1 * 2^i.
			 */
			if ((b & 1) != 0) {
				/*
				 * Recall that a = n1 * 2^i at this point, so we're adding in
				 * the next term in the sum.
				 */
				result = result + a;
			}

			/*
			 * To maintain that a = n1 * 2^i after i iterations, scale it by a
			 * factor of two by left shifting one position.
			 */
			a <<= 1;

			/*
			 * To maintain that b = n2 >> i after i iterations, shift it one
			 * spot over.
			 */
			b >>>= 1;
		}
		return cut32Bit(result);
	}

	private static long getRealVal(long val) {
		val = cut32Bit(val);

		if (val >= Math.pow(2, 31))
			return (long) (val - Math.pow(2, 32));
		return val;
	}

	public static long unsignedDiv(long dest, long source) {
		// long result = 0;
		return cut32Bit(dest / source);
	}

	public static long signedDiv(long dest, long source) {
		// long result = 0;
		return cut32Bit(dest / source);
	}

	public static boolean gt(long dest, long source) {
		return getRealVal(dest) > getRealVal(source);
	}

	public static boolean lt(long dest, long source) {
		return getRealVal(dest) < getRealVal(source);
	}

	public static boolean ge(long dest, long source) {
		return getRealVal(dest) >= getRealVal(source);
	}

	public static boolean le(long dest, long source) {
		return getRealVal(dest) <= getRealVal(source);
	}

	public static Long and(Long long1, long l) {
		// TODO Auto-generated method stub
		long result = 0;
		result = long1 & l;
		return result;
	}

	public static Long or(Long long1, long l) {
		// TODO Auto-generated method stub
		long result = 0;
		result = long1 | l;
		return result;
	}

	public static Long xor(Long long1, long l) {
		// TODO Auto-generated method stub
		long result = 0;
		result = long1 ^ l;
		return result;
	}

	public static long rl(long original, long bits) {
		// TODO Auto-generated method stub
		// long r = Long.rotateLeft(original, (int) bits);
		long t = (int) (original << bits) | (original >> (32 - bits));
		if (t < 0)
			t += Math.pow(2, 32);
		return t;
		// return (int) (original << bits) | (original >> (32 - bits));
	}

	public static long rl8(long original, long bits) {
		// TODO Auto-generated method stub
		// long r = Long.rotateLeft(original, (int) bits);
		int b = (int) (bits & 0x07);

		long t = (int) (original << b) | (original >> (8 - b));
		// if (t < 0)
		// t += Math.pow(2, 8);
		return t & 0xFF;
		// return (int) (original << bits) | (original >> (32 - bits));
	}

	public static long rr(long original, long bits) {
		// TODO Auto-generated method stub
		// return Long.rotateRight(original, (int) bits);
		// return 10;
		// return (int) (original >> bits) | (original << (32 - bits));
		long t = (int) (original >> bits) | (original << (32 - bits));
		if (t < 0)
			t += Math.pow(2, 32);
		return t;
	}

	public static long rr8(long original, long bits) {
		// TODO Auto-generated method stub
		// return Long.rotateRight(original, (int) bits);
		// return 10;
		// return (int) (original >> bits) | (original << (32 - bits));
		int b = (int) (bits & 0x07);
		long t = (int) (original >> b) | (original << (32 - b));
		if (t < 0)
			t += Math.pow(2, 32);
		return t;
	}

	public static void main(String[] args) {
		int a = 159;
		int b = 156;

		// int e = ((byte) ((a & 0xFF) << (b & 0x07)) | ((a & 0xFF) >> (8 - (b &
		// 0x07)))) & 0xFF;;
		int e = (int) rl8(a, b);
		int f = e ^ 0xB5;

		int c = Integer.rotateLeft(a & 0xFF, b & 0xFF) & 0xFF;
		int d = c ^ 0xB5;

		System.out.println(c + " " + d);
		System.out.println(e + " " + f);
	}

	public static long pow(int i, long value) {
		// TODO Auto-generated method stub
		return (long) Math.pow(i, value);
	}
}
