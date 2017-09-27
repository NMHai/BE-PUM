/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CharLower.java
 * Created date: Mar 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves a pointer to the next character in a string. This function can
 * handle strings consisting of either single- or multi-byte characters.
 * 
 * @param lpszStart
 *            [in] The beginning of the string.
 * 
 * @param lpszCurrent
 *            [in] A character in a null-terminated string.
 * 
 * @return The return value is a pointer to the preceding character in the
 *         string, or to the first character in the string if the lpszCurrent
 *         parameter equals the lpszStart parameter.
 * 
 * @author Yen Nguyen
 *
 */
public class CharPrev extends User32API {

	public CharPrev() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String lpszStart = memory.getText(this, t1);

		long ret;

		if (t1 == t2) {
			ret = t1;
		} else if ((t1 + 2) <= t2 && t2 <= (t1 + lpszStart.length() * 2)) {
			ret = t2 - 1;
		} else {
			ret = t2 - 1;
		}

		register.mov("eax", new LongValue(ret));
	}
}
