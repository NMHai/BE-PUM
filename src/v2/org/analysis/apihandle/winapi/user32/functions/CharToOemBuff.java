/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CharToOemBuff.java
 * Created date: Mar 18, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Translates a specified number of characters in a string into the OEM-defined
 * character set.
 * 
 * @param lpszSrc
 *            The null-terminated string to be translated.
 * 
 * @param lpszDst
 *            The buffer for the translated string. If the CharToOemBuff
 *            function is being used as an ANSI function, the string can be
 *            translated in place by setting the lpszDst parameter to the same
 *            address as the lpszSrc parameter. This cannot be done if
 *            CharToOemBuff is being used as a wide-character function.
 * 
 * @param cchDstLength
 *            The number of characters to translate in the string identified by
 *            the lpszSrc parameter.
 * 
 * @return The return value is always nonzero except when you pass the same
 *         address to lpszSrc and lpszDst in the wide-character version of the
 *         function. In this case the function returns zero and GetLastError
 *         returns ERROR_INVALID_ADDRESS.
 * 
 * @author Yen Nguyen
 *
 */
public class CharToOemBuff extends User32API {

	public CharToOemBuff() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		String lpszSrc = (t1 == 0L) ? null : memory.getText(this, t1);
		int cchDstLength = (int) t3;
		byte[] lpszDst = new byte[cchDstLength];

		boolean ret = User32DLL.INSTANCE.CharToOemBuff(lpszSrc, lpszDst, cchDstLength);

		register.mov("eax", new LongValue(ret ? 1 : 0));

		for (byte b : lpszDst) {
			memory.setByteMemoryValue(t2, new LongValue(b));
			t2++;
		}
	}

}
