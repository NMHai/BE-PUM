package v2.org.analysis.structure;

import v2.org.analysis.apihandle.winapi.APIHandle;

public class BitVector {
	public static final int MAX_NUM_BIT = 32;

	public static void main(String[] args) {
		long t = -2004820559;
		int[] x = BitVector.longToBytes(t, 4);
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i] + ", ");
		}

		System.out.println(t + " = " + BitVector.bytesToLong(x, 4));
	}

	/**
	 * Check if the caller is APIHandle
	 * 
	 * @author Yen Nguyen
	 * 
	 * @return TRUE if the caller is APIHandle, otherwise return FALSE
	 */
	private static boolean isAPIHandle() {
		String className = APIHandle.class.getName();
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		for (StackTraceElement element : stackTraceElements) {
			if (element.getClassName().equals(className)) {
				return true;
			}
		}
		return false;
	}

	public static int[] longToBytes(long l, int num) {
		int[] result = new int[num];
		for (int i = num - 1; i >= 0; i--) {
			int x = (byte) (l & 0x0FF);
			if (x < 0) {
				x += 256;
			}
			result[i] = x;
			l >>= 8;
		}
		return result;
	}

	public static long bytesToLong(int[] b, int num) {
		long result = 0;
		for (int i = 0; i < num; i++) {
			result <<= 8;
			result |= (b[i] & 0xFF);
		}

		// YenNguyen: API Simulator just use unsigned long value
//		result = (BitVector.isAPIHandle()) ? Convert.convetUnsignedValue(result, num * 8) : Convert.convertSignedValue(
//				result, num * 8);
//
//		return result;
		return Convert.convetUnsignedValue(result, num * 8);
	}

	public static long bytesToLong(int b1, int b2, int b3, int b4) {
		long result = 0;
		result <<= 8;
		result |= (b4 & 0xFF);
		result <<= 8;
		result |= (b3 & 0xFF);
		result <<= 8;
		result |= (b2 & 0xFF);
		result <<= 8;
		result |= (b1 & 0xFF);

		// YenNguyen: API Simulator just use unsigned long value
//		result = (BitVector.isAPIHandle()) ? Convert.convetUnsignedValue(result, 32) : Convert.convertSignedValue(
//				result, 32);

//		return result;
		return Convert.convetUnsignedValue(result, 32);
	}

	public static long bytesToLong(int b1, int b2, int b3, int b4,
			int b5, int b6, int b7, int b8) {
		long result = 0;
		result <<= 8;
		result |= (b8 & 0xFF);
		result <<= 8;
		result |= (b7 & 0xFF);
		result <<= 8;
		result |= (b6 & 0xFF);
		result <<= 8;
		result |= (b5 & 0xFF);
		
		result <<= 8;
		result |= (b4 & 0xFF);
		result <<= 8;
		result |= (b3 & 0xFF);
		result <<= 8;
		result |= (b2 & 0xFF);
		result <<= 8;
		result |= (b1 & 0xFF);

		// YenNguyen: API Simulator just use unsigned long value
		return Convert.convetUnsignedValue(result, 64);
	}

	public static long bytesToLong(int b1, int b2) {
		long result = 0;
		result <<= 8;
		result |= (b2 & 0xFF);
		result <<= 8;
		result |= (b1 & 0xFF);

		// YenNguyen: API Simulator just uses unsigned long value
//		result = (BitVector.isAPIHandle()) ? Convert.convetUnsignedValue(result, 16) : Convert.convertSignedValue(
//				result, 16);
//		return result;
		return Convert.convetUnsignedValue(result, 16);
	}

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
		for (int i = 0; i < MAX_NUM_BIT; i++) {
			if (bits[i]) {
				result += Math.pow(2, i);
			}
		}

		return result;
	}

	private static long negate(long x) {
		return add(~x, 1);
	}

	public static long sub(long dest, long source) {
		// http://stackoverflow.com/questions/1149929/how-to-add-two-numbers-without-using-or-or-another-arithmetic-operator
		return add(dest, negate(source));
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

	private static long getRealVal(long val) {
		val = cut32Bit(val);

		if (val >= Math.pow(2, 31)) {
			return (long) (val - Math.pow(2, 32));
		}
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
		return (original << bits) | (original >> (32 - bits));
	}

	public static long rr(long original, long bits) {
		// TODO Auto-generated method stub
		return (original >> bits) | (original << (32 - bits));
	}

	public static long mod(long dest, long source) {
		// TODO Auto-generated method stub
		return cut32Bit(dest % source);
	}

	public static byte getMSB(long val, long bits) {

		if (bits == 8) {
			return (byte) ((val & 0x80) >>> 7);
		} else if (bits == 16) {
			return (byte) ((val & 0x8000) >>> 15);
		} else if (bits == 32) {
			return (byte) ((val & 0x80000000) >>> 31);
		} else if (bits == 64) {
			return (byte) ((val & 0x8000000000000000l) >>> 63);
		}

		return 0;
	}

	public static byte getLSB(long val, long bits) {
		return (byte) (val & 1);
	}

	public static boolean getParityBit(long t) {
		// TODO Auto-generated method stub
		int k = (int) (t % Math.pow(2, 8));
		String s = Integer.toBinaryString(k);
		int r = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				r++;
			}
		}
		return r % 2 == 0;
	}

	public static long pow(long value, long value1) {
		return (long) Math.pow(value, value1);
	}

	public static byte getSMSB(long val, int bits) {
		if (bits == 8) {
			return (byte) (((val & 0xC0) >>> 6) & 1);
		} else if (bits == 16) {
			return (byte) (((val & 0xC000) >>> 14) & 1);
		} else if (bits == 32) {
			return (byte) (((val & 0xc0000000) >>> 30) & 1);
		} else if (bits == 64) {
			return (byte) (((val & 0xC000000000000000l) >>> 62) & 1);
		}

		return 0;
	}

	public static long signExtend(long val, int num) {
		long temp = val;

		if (num == 16) {
			int t = getMSB(val, 8);
			temp += 255 * Math.pow(2, 8) * t;
		} else if (num == 32) {
			int t = getMSB(val, 16);
			temp += 65535 * Math.pow(2, 16) * t;
		}

		return temp;
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

	public static long rr8(long original, long bits) {
		// TODO Auto-generated method stub
		// return Long.rotateRight(original, (int) bits);
		// return 10;
		// return (int) (original >> bits) | (original << (32 - bits));
		int b = (int) (bits & 0x07);
		long t = (int) (original >> b) | (original << (32 - b));
		if (t < 0) {
			t += Math.pow(2, 32);
		}
		return t;
	}

	public static long normalizeNumber(long l, int size) {
		return (long) (l % Math.pow(2, size));
	}

	public static long signedMul(long n1, long n2, int size) {
		// http://stackoverflow.com/questions/4895173/bitwise-multiply-and-add-in-java
		long a = Convert.convertSignedValue(n1, size);
		long b = Convert.convertSignedValue(n2, size);

		long result = a * b;

		return result;
	}

	public static long extend(long t, int i, int opSize1, int opSize2) {
		t = Convert.convetUnsignedValue(t, opSize1);
		
		if (i == 0) {
			return t;
		} else if (i == 1) {
			switch (opSize2) {
			case 16:
				return (long) (t + 255 * Math.pow(2, 8));
			case 32:
				if (opSize1 == 16) {
					return (long) (t + 65535 * Math.pow(2, 16));
				}
				if (opSize1 == 8) {
					return (long) (t + 65535 * Math.pow(2, 16) + 65280);
				}
			}
		}

		return t;
	}
}
