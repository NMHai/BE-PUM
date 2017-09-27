package v2.org.analysis.structure;

import java.math.BigInteger;

import org.jakstab.asm.Instruction;

public class Convert {
	public static String longToHex(long t) {
		// String v = Integer.toHexString(i) & 000000ff;
		return Long.toHexString(t);
	}

	public static long convetUnsignedValue(long v, int numCount) {
		// if (v == 4294967295l)
		// System.out.println("Debug " + 4294967295l);

		if (numCount == 8) {
			return v & 0x0FF;
		}
		if (numCount == 16) {
			return v & 0x0FFFF;
		}
		if (numCount == 32) {
			return v & 0x0FFFFFFFFl;
		}
		if (numCount == 64) {
			return v & 0x0FFFFFFFFFFFFFFFFl;
		}

		return v;
	}

	public static long convertSignedValue(long value, int numBits) {
		value = Convert.convetUnsignedValue(value, numBits);

		if (numBits == 8) {
			if (value >= 128) {
				return value - 256;
			}
		} else if (numBits == 16) {
			if (value >= 32768) {
				return value - 65536;
			}
		} else if (numBits == 32) {
			if (value >= 2147483648l) {
				return value - 4294967296l;
			}
		}

		return value;
	}

	/*public static String generateString(Operand op) {
		if (op.getClass().getSimpleName().equals("X86Register")
				|| op.getClass().getSimpleName().equals("X86RegisterPart")
				|| op.getClass().getSimpleName().equals("X86SegmentRegister")) {
			return op.toString().replace("%", "");
		} else if (op.getClass().getSimpleName().equals("X86MemoryOperand")) {
			X86MemoryOperand o = (X86MemoryOperand) op;
			String ret = "memory";
			if (o.getBase() != null) {
				ret += "_base_" + o.getBase().toString();
			}

			if (o.getDisplacement() != 0) {
				ret += "_disp_" + o.getDisplacement();
			}

			if (o.getIndex() != null) {
				ret += "_index_" + o.getIndex().toString();
			}

			if (o.getSegmentRegister() != null) {
				ret += "_segment_" + o.getSegmentRegister().toString();
			}

			ret = ret.replace("%", "");
			return ret;
		}

		return "";
	}*/

	public static String longToHex(long t, int d) {
		// String v = Integer.toHexString(i) & 000000ff;
		// int temp = t & 0xff;
		if (d == 8) {
			String temp = Long.toHexString(t & 0xff);
			if (temp.length() == 1) {
				temp = "0" + temp;
			}
			return temp;
		} else if (d == 16) {
			String temp = Long.toHexString(t & 0xffff);
			if (temp.length() == 1) {
				temp = "000" + temp;
			} else if (temp.length() == 2) {
				temp = "00" + temp;
			} else if (temp.length() == 3) {
				temp = "0" + temp;
			}
			return temp;

		} else if (d == 32) {
			String temp = Long.toHexString(t & 0xffffffff);

			if (temp.length() == 1) {
				temp = "00000000" + temp;
			} else if (temp.length() == 2) {
				temp = "000000" + temp;
			} else if (temp.length() == 3) {
				temp = "00000" + temp;
			} else if (temp.length() == 4) {
				temp = "0000" + temp;
			} else if (temp.length() == 5) {
				temp = "000" + temp;
			} else if (temp.length() == 6) {
				temp = "00" + temp;
			} else if (temp.length() == 7) {
				temp = "0" + temp;
			}

			return temp;
		}
		return Long.toHexString(t);
	}

	public static int hexToInt(String hex, int d) {
		return Integer.parseInt(hex, d);
	}

	public static long hexToLong(String index) {
		// TODO Auto-generated method stub
		return Long.parseLong(index, 16);
	}

	public static void main(String[] args) {
		String i = "f6410009";
		long v = 1357;
		String x = Convert.longToHex(v, 32);
		System.out.println(Long.parseLong(i, 16) + " " + x);

		// System.out.println(Convert.hexToInt(i));
	}

	public static int getBitCount(Instruction ins) {
		// TODO Auto-generated method stub
		if (ins.getName().endsWith("b")) {
			return 8;
		} else if (ins.getName().endsWith("l")) {
			return 32;
		} else if (ins.getName().endsWith("s") || ins.toString().endsWith("w")) {
			return 16;
		}
		return 0;
	}

	public static int getBitCount(String regName) {
		// TODO Auto-generated method stub
		if (regName.contains("eax") || regName.contains("ebx") || regName.contains("ecx") || regName.contains("edx")
				|| regName.contains("esi") || regName.contains("edi") || regName.contains("esp")
				|| regName.contains("ebp") || regName.contains("efl")) {
			return 32;
		} else if (regName.contains("ax") || regName.contains("bx") || regName.contains("cx") || regName.contains("dx")
				|| regName.contains("si") || regName.contains("di") || regName.contains("sp") || regName.contains("bp")) {
			return 16;
		} else if (regName.contains("al") || regName.contains("ah") || regName.contains("bh") || regName.contains("bl")
				|| regName.contains("cl") || regName.contains("ch") || regName.contains("dl") || regName.contains("dh")
		// || regName.contains("sp") || regName.contains("bp")
		) {
			return 8;
		}

		return 0;
	}

	public static int getSignBit(long d, int num) {
		// TODO Auto-generated method stub
		if ((num == 8 && d >= Math.pow(2, 7)) || (num == 16 && d >= Math.pow(2, 15))
				|| (num == 32 && d >= Math.pow(2, 31))) {
			return 1;
		}

		return 0;
	}

	public static String reduceText(String text) {
		int index = text.indexOf('\0');
		
		if (index == 0) {
			return "";
		} else if (index > 0) {
			return text.substring(0, index);
		}
		
		return text;
	}

	public static String reduceText(char[] text) {
		int index = -1;
		for (int i = 0; i < text.length; i++) {
			if (text[i] == '\0') {
				index = i;
				break;
			}
		}
		
		if (index == 0) {
			return "";
		} else if (index > 0) {
			return new String(text, 0, index);
		}
		
		return new String(text);
	}
	
	// Fix by khanh
	public static long convetUnsignedValue_Mbit_1(long v, int numCount) {
			// if (v == 4294967295l)
			// System.out.println("Debug " + 4294967295l);

			if (numCount == 8) {
				return v | 0x80;
			}
			if (numCount == 16) {
				return v | 0x8000;
			}
			if (numCount == 32) {
				return v | 0x80000000l;
			}
			if (numCount == 64) {
				return v & 0x8000000000000000l;
			}

			return v;
	}

	public static long parseLongFromString(String temp) {
		// TODO Auto-generated method stub
		return (new BigInteger(temp, 16)).longValue();
	}
}
