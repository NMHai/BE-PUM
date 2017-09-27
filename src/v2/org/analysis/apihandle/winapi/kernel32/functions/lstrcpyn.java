/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.WString;

/**
 * Copies a specified number of characters from a source string into a buffer.
 * 
 * @param lpString1
 *            The destination buffer, which receives the copied characters. The
 *            buffer must be large enough to contain the number of TCHAR values
 *            specified by iMaxLength, including room for a terminating null
 *            character.
 * 
 * @param lpString2
 *            The source string from which the function is to copy characters.
 * 
 * @param iMaxLength
 *            The number of TCHAR values to be copied from the string pointed to
 *            by lpString2 into the buffer pointed to by lpString1, including a
 *            terminating null character.
 * 
 * @return If the function succeeds, the return value is a pointer to the
 *         buffer. The function can succeed even if the source string is greater
 *         than iMaxLength characters. If the function fails, the
 * 
 * @author Yen Nguyen
 *
 */
public class lstrcpyn extends Kernel32API {

	public lstrcpyn() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long destAddr = this.params.get(0);
		long scrAddr = this.params.get(1);
		int n = this.params.get(2).intValue();

		String dest = memory.getText(this, destAddr);
		String src = memory.getText(this, scrAddr);

		System.out.println("Destination String:" + dest + ", Source String:" + src);

		WString ret = Kernel32DLL.INSTANCE.lstrcpyn(new WString(dest), new WString(src), n);
		memory.setText(this, destAddr, ret.toString());
		register.mov("eax", new LongValue(1));
	}

}
