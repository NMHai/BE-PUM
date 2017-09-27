/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: isalpha.java
 * Created date: Sep 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Check if character is alphabetic
 * 
 * Checks whether c is an alphabetic letter.
 * 
 * Notice that what is considered a letter depends on the locale being used; In
 * the default "C" locale, what constitutes a letter is only what returns true
 * by either isupper or islower.
 * 
 * @param c
 *            Character to be checked, casted to an int, or EOF.
 * 
 * @return A value different from zero (i.e., true) if indeed c is an alphabetic
 *         letter. Zero (i.e., false) otherwise.
 * 
 * @author Yen Nguyen
 *
 */
public class isalpha extends MSVCRTAPI {

	public isalpha() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		int c = (int) t1;
		int ret = MSVCRTDLL.INSTANCE.isalpha(c);

		register.mov("eax", new LongValue(ret));
	}

}
