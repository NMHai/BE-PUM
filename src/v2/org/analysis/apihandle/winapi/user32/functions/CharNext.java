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
 * Retrieves a pointer to the next character in a string. This function can
 * handle strings consisting of either single- or multi-byte characters.
 * 
 * @param lpsz
 *            A character in a null-terminated string.
 * 
 * @return The return value is a pointer to the next character in the string, or
 *         to the terminating null character if at the end of the string. If
 *         lpsz points to the terminating null character, the return value is
 *         equal to lpsz.
 * 
 * @author Yen Nguyen
 *
 */
public class CharNext extends User32API {

	public CharNext() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long x = this.params.get(0);

		char[] lpsz = memory.getText(this, x).toCharArray();
		User32DLL.INSTANCE.CharNext(lpsz);

		System.out.println(new String(lpsz));

		register.mov("eax", new LongValue((lpsz.length == 0) ? x : (x + (this.is64bit() ? 2 : 1))));
	}

}
