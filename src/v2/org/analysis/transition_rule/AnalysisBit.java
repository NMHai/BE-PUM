package v2.org.analysis.transition_rule;

public class AnalysisBit {
	public long RDTSC_EAX(long number){
		long array[] = new long [64];
		int temp = 0, i = 0;
		long result = 0;

		//xet mang bit ban dau
		while (temp < 64){
			array[temp]=0;
			temp++;
		}

		//chuuyen dest sang Bit
		while( number != 0) {
			array[i] = number % 2;
			number = number / 2;
			i++;
		}
		
		int count=0;       

		for(int j=0; j<= 31; j++)
		{
			if ( array[j] == 1 ) {
				result += array[j]*Math.pow(2,count);
			}
			count++;
		}

		return result;        	
	}
	
	public long RDTSC_EDX(long number){
		long array[] = new long[64];
		int temp = 0, i = 0;
		long result = 0;

		//xet mang bit ban dau
		while (temp < 64){
			array[temp]=0;
			temp++;
		}

		//chuuyen dest sang Bit
		while( number != 0) {
			array[i] = number % 2;
			number = number / 2;
			i++;
		}
		
		int count=0;       

		for(int j=32; j<= 63; j++)
		{
			if ( array[j] == 1 ) {
				result += array[j]*Math.pow(2,count);
			}
			count++;
		}

		return result;        	
	}
	public long SwapBit32(long n) {

		int i = 0;
		int temp = 0;
		int temp_16 = 16;
		int temp_24 = 24;
		long result = 0;
		long array[] = new long[33];

		// xet chuoi 32 bit ban dau
		while (temp < 32) {
			array[temp] = 0;
			temp++;
		}

		// tim chuoi 32 bit
		while (n != 0) {
			array[i] = n % 2;
			n = n / 2;
			i++;
		}
		/*
		 * i = 31; for(int j=i;j>=0;j--) System.out.print(array[j]);
		 */

		// dao chuoi [0...7] vs [24...31]
		i = 0;
		while (i <= 7) {
			long temp_array = array[temp_24];
			array[temp_24] = array[i];
			array[i] = temp_array;
			temp_24++;
			i++;
		}

		// dao chuoi [8...15] vs [16...23]
		i = 8;
		while (i <= 15) {
			long temp_array = array[temp_16];
			array[temp_16] = array[i];
			array[i] = temp_array;
			temp_16++;
			i++;
		}

		result = Bit2Dec(array, 31);
		return result;
	}

	// chuyen bit sang so nguyen
	public long Bit2Dec(long array[], int range) {

		int count = 0;
		long result = 0;

		for (int j = 0; j <= range; j++) {
			if (array[j] == 1) {
				result += array[j] * Math.pow(2, count);
			}
			count++;
		}

		return result;
	}

	public long MOVS(int get_bit, long temp_s) {
		// TODO Auto-generated method stub

		int temp = 0;
		int i = 0;
		long result = 0;
		long array[] = new long[get_bit];
		boolean bit_sign = true; // true la duong, false la am

		// xet bit dau
		if (temp_s > 127 && temp_s <= 255) {
			bit_sign = false;
		} else if (temp_s > 32767 && temp_s <= 65535) {
			bit_sign = false;
		} else if (temp_s > 2147483647) {
			bit_sign = false;
		}

		// xet chuoi bit ban dau
		if (bit_sign == true) {
			while (temp < get_bit) {
				array[temp] = 0;
				temp++;
			}
		} else {
			while (temp < get_bit) {
				array[temp] = 1;
				temp++;
			}
		}

		// tim chuoi bit
		while (temp_s != 0) {
			array[i] = temp_s % 2;
			temp_s = temp_s / 2;
			i++;
		}

		/*
		 * System.out.println(" "); int j = 0; for (j = get_bit-1; j >= 0; j--){
		 * System.out.print(array[j]); }
		 */
		// System.out.println(" ");
		// System.out.println(result);

		result = Bit2Dec(array, get_bit - 1);
		return result;
	}

	// 64 bit duoc bieu thi duoi dang mang [64...32 31...0]
	public long Bit64toDec(long bit0, long bit32) {
		// TODO Auto-generated method stub
		int temp = 0;
		int i = 0;
		int j = 0;
		int count = 0;

		long result = 0;
		long array0[] = new long[33];
		long array32[] = new long[33];
		long array64[] = new long[65];

		// xet dat mang ban dau
		while (temp <= 32) {
			array0[temp] = 0;
			array32[temp] = 0;
			temp++;
		}

		// tim 32 bit dau
		while ((bit0 != 0)) {
			array0[i] = bit0 % 2;
			bit0 = bit0 / 2;
			i++;
		}

		// tim 32 bit sau
		i = 0;
		while ((bit32 != 0)) {
			array32[i] = bit32 % 2;
			bit32 = bit32 / 2;
			i++;
		}

		// noi thanh 64 bit
		i = 0;
		while (j < 64) {
			if (j < 32) {
				array64[j] = array0[j];
			} else {
				array64[j] = array32[i];
				i++;
			}
			j++;
		}

		/*
		 * System.out.println(" "); i = 31; for(int jj=0; jj<=31; jj++)
		 * System.out.print(array0[jj]);
		 * 
		 * System.out.println(" "); i = 31; for(int jj=0; jj<=31; jj++)
		 * System.out.print(array32[jj]);
		 * 
		 * System.out.println(" "); i = 63; for(int jj=0; jj<=63; jj++)
		 * System.out.print(array64[jj]);
		 * 
		 * System.out.println(" "); i = 63; for(int jj=i; jj>=0; jj--)
		 * System.out.print(array64[jj]);
		 */

		// chuyen ve so nguyen
		for (j = 0; j <= 63; j++) {
			if (array64[j] == 1) {
				result += array64[j] * Math.pow(2, count);
			}
			count++;
		}
		// System.out.println(" ");
		// System.out.println(result);
		return result;
	}

	// lay 32 bit dau cua chuoi 64 bit
	public long Bit0FromIm(long number) {
		// TODO Auto-generated method stub
		long array64[] = new long[65];
		long result = 0;
		int temp = 0;
		int i = 0;
		int count = 0;

		// xet chuoi 64 bit ban dau
		while (temp <= 63) {
			array64[temp] = 0;
			temp++;
		}

		// tim chuoi 64 bit
		while (number != 0) {
			array64[i] = number % 2;
			number = number / 2;
			i++;
		}

		// chuyen chuoi 32 bit dau thanh so nguyen
		for (int j = 0; j <= 31; j++) {
			if (array64[j] == 1) {
				result += array64[j] * Math.pow(2, count);
			}
			count++;
		}

		// System.out.print(result);
		return result;
	}

	// lay 32 bit sau cua chuoi 64 bit
	public long Bit32FromIm(long number) {
		// TODO Auto-generated method stub

		int temp = 0;
		int i = 0;
		int count = 0;

		long array64[] = new long[65];
		long result = 0;

		// xet chuoi 64 bit ban dau
		while (temp <= 64) {
			array64[temp] = 0;
			temp++;
		}

		// tim chuoi 64 bit
		while (number != 0) {
			array64[i] = number % 2;
			number = number / 2;
			i++;
		}

		// chuyen chuoi 32bit sau thanh so nguyen
		for (int j = 32; j <= 63; j++) {
			if (array64[j] == 1) {
				result += array64[j] * Math.pow(2, count);
			}
			count++;
		}
		// System.out.print(result);
		return result;
	}

	// xet bit dau
	public long CWD_CDQ(long AX) {
		// TODO Auto-generated method stub

		long result = 0;

		// xet bit dau
		if (AX > 32767 && AX <= 65535) {
			result = 65535;
		} else if (AX > 2147483647) {
			int temp = 0;
			long array[] = new long[65];

			while (temp < 64) {
				array[temp] = 1;
				temp++;
			}
			result = Bit2Dec(array, 63);
		}

		return result;
	}

	public long SHLD(long dest, long src, long count, int get_bit) {

		long array_dest[] = new long[get_bit];
		long array_src[] = new long[get_bit];
		int temp = 0, i = 0;
		long result = 0;

		// xet mang bit ban dau
		while (temp < get_bit) {
			array_dest[temp] = 0;
			array_src[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (dest != 0) {
			array_dest[i] = dest % 2;
			dest = dest / 2;
			i++;
		}

		// chuyen src sang Bit
		i = 0;
		while (src != 0) {
			array_src[i] = src % 2;
			src = src / 2;
			i++;
		}

		// Truong hop cout nho hon so bit.
		if (count <= get_bit) {
			for (i = get_bit - 1; i >= count; --i) {
				array_dest[i] = array_dest[(int) (i - count)];
			}
			for (i = (int) (count - 1); i >= 0; --i) {
				array_dest[i] = array_src[(int) (i - count + get_bit)];
			}
			result = Bit2Dec(array_dest, get_bit - 1);
		}
		// truong hop count lon hon so bit
		else {
			// truong hop count = get_bit. dest = src
			if (count == get_bit) {
				result = src;
			} else {
				long array[] = new long[(int) count]; // array voi kich thuoc
														// count, array tam cua
														// src

				// thiet lap chuoi cho array
				temp = 0;
				while (temp < count) {
					array[temp] = 0;
					temp++;
				}

				// mo rong chuoi src
				int sub = (int) (count - get_bit);
				int jj = 0;
				for (int j = sub; j < count; j++) {
					array[j] = array_src[jj];
					jj++;
				}

				// chuyen bit sang so nguyen
				int chiso = 0;
				for (int j = 0; j < get_bit; j++) {
					if (array[j] == 1) {
						result += array[j] * Math.pow(2, chiso);
					}
					chiso++;
				}
			}
		}
		return result;
	}

	public long SHRD(long dest, long src, long count, int get_bit) {
		long array_dest[] = new long[get_bit];
		long array_src[] = new long[get_bit];
		int temp = 0, i = 0;
		long result = 0;

		// xet mang bit ban dau
		while (temp < get_bit) {
			array_dest[temp] = 0;
			array_src[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (dest != 0) {
			array_dest[i] = dest % 2;
			dest = dest / 2;
			i++;
		}

		// chuyen src sang Bit
		i = 0;
		while (src != 0) {
			array_src[i] = src % 2;
			src = src / 2;
			i++;
		}

		// Truong hop cout nho hon so bit.
		// PHONG: change here
		// count < get_bit, it is not <=
		if (/* count <= get_bit */count < get_bit) {
			for (i = 0; i < get_bit - count; i++) {
				// CONFIRM for i + count or i - count
				array_dest[i] = array_dest[(int) (i + count)];
			}
			for (i = (int) (get_bit - count); i <= get_bit - 1; i++) {
				array_dest[i] = array_src[(int) (i + count - get_bit)];
			}
			result = Bit2Dec(array_dest, get_bit - 1);
		}
		// truong hop count lon hon so bit
		else {
			if (count == get_bit) {
				result = dest;
			} else {
				long array[] = new long[(int) count]; // array voi kich thuoc
														// count, array tam cua
														// src

				// thiet lap chuoi cho array
				temp = 0;
				while (temp < count) {
					array[temp] = 1;
					temp++;
				}

				// mo rong chuoi src sang ben phai
				i = 0;
				for (i = 0; i < get_bit; i++) {
					array[i] = array_src[i];
				}

				// lay chuoi dest can tim
				int sub = (int) (count - get_bit);
				i = 0;
				for (int j = sub; j < count; j++) {
					array_dest[i] = array[j];
					i++;
				}

				// chuyen bit sang so nguyen
				int chiso = 0;
				for (int j = 0; j < get_bit; j++) {
					if (array_dest[j] == 1) {
						result += array_dest[j] * Math.pow(2, chiso);
					}
					chiso++;
				}

			}
		}
		return result;
	}

	public long RCL(long reg, int CF, long count, int get_bit) {
		long array[] = new long[get_bit + 1];
		long array_temp[] = new long[get_bit + 1];
		int temp = 0, i = 0;
		long result = 0;

		// xet mang bit ban dau
		while (temp <= get_bit) {
			array[temp] = 0;
			array_temp[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (reg != 0) {
			array[i] = reg % 2;
			reg = reg / 2;
			i++;
		}

		array[get_bit] = CF;
		while (count > 0) {
			// chen mang
			for (i = 0; i <= get_bit; i++) {
				if (i == get_bit) {
					array_temp[0] = array[i];
				} else {
					array_temp[i + 1] = array[i];
				}
			}

			// gan mang
			for (i = 0; i <= get_bit; i++) {
				array[i] = array_temp[i];
			}
			count--;
		}

		// chuyen bit sang so nguyen
		int chiso = 0;
		for (int j = 0; j < get_bit; j++) {
			if (array[j] == 1) {
				result += array[j] * Math.pow(2, chiso);
			}
			chiso++;
		}
		return result;
	}

	public int changeCFofRCL(long reg, int CF, long count, int get_bit) {
		long array[] = new long[get_bit + 1];
		long array_temp[] = new long[get_bit + 1];
		int temp = 0, i = 0;
		int result = 0;

		// xet mang bit ban dau
		while (temp <= get_bit) {
			array[temp] = 0;
			array_temp[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (reg != 0) {
			array[i] = reg % 2;
			reg = reg / 2;
			i++;
		}

		array[get_bit] = CF;
		while (count > 0) {
			for (i = 0; i <= get_bit; i++) {
				if (i == get_bit) {
					array_temp[0] = array[i];
				} else {
					array_temp[i + 1] = array[i];
				}
			}
			// gan mang
			for (i = 0; i <= get_bit; i++) {
				array[i] = array_temp[i];
			}
			count--;
		}

		result = (int) array[get_bit];
		return result;
	}

	public int changeOFofRCL(long number, int CF, int get_bit) {
		long array[] = new long[get_bit + 1];
		int temp = 0, i = 0;
		int result = 1;

		// xet mang bit ban dau
		while (temp <= get_bit) {
			array[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (number != 0) {
			array[i] = number % 2;
			number = number / 2;
			i++;
		}

		if (CF == array[get_bit - 1]) {
			result = 0;
		}

		return result;
	}

	public long RCR(long reg, int CF, long count, int get_bit) {
		long array[] = new long[get_bit + 1];
		long array_temp[] = new long[get_bit + 1];
		int temp = 0, i = 0;
		long result = 0;

		// xet mang bit ban dau
		while (temp <= get_bit) {
			array[temp] = 0;
			array_temp[temp] = 0;
			temp++;
		}

		i = 1;
		// chuuyen dest sang Bit
		while (reg != 0) {
			array[i] = reg % 2;
			reg = reg / 2;
			i++;
		}

		while (count > 0) {
			// chen mang
			array_temp[get_bit] = array[0];
			for (i = 1; i <= get_bit; i++) {
				array_temp[i - 1] = array[i];
			}
			// gan mang
			for (i = 0; i <= get_bit; i++) {
				array[i] = array_temp[i];
			}
			count--;
		}

		// chuyen bit sang so nguyen
		int chiso = 0;
		for (int j = 1; j <= get_bit; j++) {
			if (array[j] == 1) {
				result += array[j] * Math.pow(2, chiso);
			}
			chiso++;
		}

		return result;
	}

	public int changeCFofRCR(long reg, int CF, long count, int get_bit) {
		long array[] = new long[get_bit + 1];
		long array_temp[] = new long[get_bit + 1];
		int temp = 0, i = 0;
		int result = 0;

		// xet mang bit ban dau
		while (temp <= get_bit) {
			array[temp] = 0;
			array_temp[temp] = 0;
			temp++;
		}

		i = 1;
		// chuuyen dest sang Bit
		while (reg != 0) {
			array[i] = reg % 2;
			reg = reg / 2;
			i++;
		}

		while (count > 0) {
			// chen mang
			array_temp[get_bit] = array[0];
			for (i = 1; i <= get_bit; i++) {
				array_temp[i - 1] = array[i];
			}
			// gan mang
			for (i = 0; i <= get_bit; i++) {
				array[i] = array_temp[i];
			}
			count--;
		}

		result = (int) array[0];
		return result;
	}

	public int changeOFofRCR(long number, int get_bit) {
		long array[] = new long[get_bit + 1];
		int temp = 0, i = 0;
		int result = 1;

		// xet mang bit ban dau
		while (temp <= get_bit) {
			array[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (number != 0) {
			array[i] = number % 2;
			number = number / 2;
			i++;
		}

		if (array[get_bit] == 0 && array[get_bit - 1] == 0) {
			result = 0;
		}

		return result;
	}

	public long BT(long bit, long bitofset, int get_bit) {
		long array[] = new long[get_bit];
		int temp = 0, i = 0;
		long result = 0;
		long ofset = bitofset % get_bit;

		// xet mang bit ban dau
		while (temp < get_bit) {
			array[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (bit != 0) {
			array[i] = bit % 2;
			bit = bit / 2;
			i++;
		}
		result = array[(int) ofset];
		return result;
	}

	public long BTS(long bit, long bitofset, int get_bit) {
		long array[] = new long[get_bit];
		int temp = 0, i = 0;
		long result = 0;
		long ofset = bitofset % get_bit;

		// xet mang bit ban dau
		while (temp < get_bit) {
			array[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (bit != 0) {
			array[i] = bit % 2;
			bit = bit / 2;
			i++;
		}

		array[(int) ofset] = 1;

		// chuyen bit sang so nguyen
		int chiso = 0;
		for (int j = 0; j < get_bit; j++) {
			if (array[j] == 1) {
				result += array[j] * Math.pow(2, chiso);
			}
			chiso++;
		}

		return result;
	}

	public long BTR(long bit, long bitofset, int get_bit) {
		long array[] = new long[get_bit];
		int temp = 0, i = 0;
		long result = 0;
		long ofset = bitofset % get_bit;

		// xet mang bit ban dau
		while (temp < get_bit) {
			array[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (bit != 0) {
			array[i] = bit % 2;
			bit = bit / 2;
			i++;
		}

		array[(int) ofset] = 0;

		// chuyen bit sang so nguyen
		int chiso = 0;
		for (int j = 0; j < get_bit; j++) {
			if (array[j] == 1) {
				result += array[j] * Math.pow(2, chiso);
			}
			chiso++;
		}

		return result;
	}

	public long BTC(long bit, long bitofset, int get_bit) {
		long array[] = new long[get_bit];
		int temp = 0, i = 0;
		long result = 0;
		long ofset = bitofset % get_bit;

		// xet mang bit ban dau
		while (temp < get_bit) {
			array[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (bit != 0) {
			array[i] = bit % 2;
			bit = bit / 2;
			i++;
		}

		if (array[(int) ofset] == 1) {
			array[(int) ofset] = 0;
		} else {
			array[(int) ofset] = 1;
		}

		// chuyen bit sang so nguyen
		int chiso = 0;
		for (int j = 0; j < get_bit; j++) {
			if (array[j] == 1) {
				result += array[j] * Math.pow(2, chiso);
			}
			chiso++;
		}

		return result;
	}

	public long BSF(long src, int get_bit) {
		long array[] = new long[get_bit];
		int temp = 0, i = 0;
		int count = 0;

		// xet mang bit ban dau
		while (temp < get_bit) {
			array[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (src != 0) {
			array[i] = src % 2;
			src = src / 2;
			i++;
		}

		i = 0;
		while (i < get_bit && array[i] == 0) {
			count = count + 1;
			i++;
		}

		return count;
	}

	public long BSR(long src, int get_bit) {
		long array[] = new long[get_bit];
		int temp = 0, i = 0;
		int count = get_bit - 1;

		// xet mang bit ban dau
		while (temp < get_bit) {
			array[temp] = 0;
			temp++;
		}

		// chuuyen dest sang Bit
		while (src != 0) {
			array[i] = src % 2;
			src = src / 2;
			i++;
		}

		i = get_bit - 1;
		while (i >= 0 && array[i] == 0) {
			count = count - 1;
			i--;
		}

		// System.out.println(count);
		return count;
	}

}
