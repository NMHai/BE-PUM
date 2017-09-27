/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: strstr.java
 * Created date: Jul 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.LongValue;

/**
 * Locate substring
 * 
 * Returns a pointer to the first occurrence of str2 in str1, or a null pointer
 * if str2 is not part of str1.
 * 
 * The matching process does not include the terminating null-characters, but it
 * stops there.
 * 
 * @param str1
 *            C string to be scanned.
 * 
 * @param str2
 *            C string containing the sequence of characters to match.
 * 
 * @return A pointer to the first occurrence in str1 of the entire sequence of
 *         characters specified in str2, or a null pointer if the sequence is
 *         not present in str1.
 * 
 * @author Yen Nguyen
 *
 */
public class strstr extends MSVCRTAPI {

	public strstr() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String str1 = memory.getText(this, t1);
		String str2 = memory.getText(this, t2);

		int index = str1.indexOf(str2);

		// NULL pointer
		long ret = 0;
		// Or pointer to the first occurrence
		if (index > -1) {
			ret = t1 + index;
		}

		register.mov("eax", new LongValue(ret));
	}

}
