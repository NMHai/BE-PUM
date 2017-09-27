/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CharLower.java
 * Created date: Mar 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Converts a character string or a single character to lowercase. If the
 * operand is a character string, the function converts the characters in place.
 * 
 * @param lpsz
 *            A null-terminated string, or specifies a single character. If the
 *            high-order word of this parameter is zero, the low-order word must
 *            contain a single character to be converted.
 * 
 * @return If the operand is a character string, the function returns a pointer
 *         to the converted string. Because the string is converted in place,
 *         the return value is equal to lpsz.
 * 
 *         If the operand is a single character, the return value is a 32-bit
 *         value whose high-order word is zero, and low-order word contains the
 *         converted character.
 * 
 * @author Yen Nguyen
 *
 */
public class CharLower extends User32API {

	public CharLower() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long x = this.params.get(0);

		char[] lpsz = memory.getText(this, x).toCharArray();
		User32DLL.INSTANCE.CharLower(lpsz);
		register.mov("eax", new LongValue(x));

		memory.setText(this, x, new String(lpsz));
	}

}
