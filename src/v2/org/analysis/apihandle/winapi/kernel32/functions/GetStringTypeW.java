/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetStringTypeA.java
 * Created date: Mar 16, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;

/**
 * Retrieves character type information for the characters in the specified
 * Unicode source string. For each character in the string, the function sets
 * one or more bits in the corresponding 16-bit element of the output array.
 * Each bit identifies a given character type, for example, letter, digit, or
 * neither.
 * 
 * @param dwInfoType
 *            Flags specifying the character type information to retrieve. For
 *            possible flag values, see the dwInfoType parameter of
 *            GetStringTypeW. For detailed information about the character type
 *            bits, see Remarks for GetStringTypeW.
 * 
 * @param lpSrcStr
 *            Pointer to the ANSI string for which to retrieve the character
 *            types. The string can be a double-byte character set (DBCS) string
 *            if the supplied locale is appropriate for DBCS. The string is
 *            assumed to be null-terminated if cchSrc is set to any negative
 *            value.
 * 
 * @param cchSrc
 *            Size, in characters, of the string indicated by lpSrcStr. If the
 *            size includes a terminating null character, the function retrieves
 *            character type information for that character. If the application
 *            sets the size to any negative integer, the source string is
 *            assumed to be null-terminated and the function calculates the size
 *            automatically with an additional character for the null
 *            termination.
 * 
 * @param lpCharType
 *            Pointer to an array of 16-bit values. The length of this array
 *            must be large enough to receive one 16-bit value for each
 *            character in the source string. If cchSrc is not a negative
 *            number, lpCharType should be an array of words with cchSrc
 *            elements. If cchSrc is set to a negative number, lpCharType is an
 *            array of words with lpSrcStr + 1 elements. When the function
 *            returns, this array contains one word corresponding to each
 *            character in the source string.
 * 
 * @return Returns a nonzero value if successful, or 0 otherwise. To get
 *         extended error information, the application can call GetLastError,
 *         which can return one of the following error codes:
 *         ERROR_INVALID_FLAGS. The values supplied for flags were not valid.
 *         ERROR_INVALID_PARAMETER. Any of the parameter values was invalid.
 * 
 * @author Yen Nguyen
 *
 */
public class GetStringTypeW extends Kernel32API {

	public GetStringTypeW() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		DWORD dwInfoType = new DWORD(t1);
		WString lpSrcStr = new WString(memory.getText(this, t2));
		int cchSrc = (int) t3;
		short[] lpCharType = new short[cchSrc + 1];
		BOOL ret = Kernel32DLL.INSTANCE.GetStringTypeW(dwInfoType, lpSrcStr, cchSrc, lpCharType);

		register.mov("eax", new LongValue(ret.longValue()));

		for (int i = 0; i < lpCharType.length; i++) {
			memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT16, t4 + i), new LongValue(lpCharType[i]));
		}
	}

}
