/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _itow.java
 * Created date: Aug 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.WString;

/**
 * Converts an integer to a string. More secure versions of these functions are
 * available
 * 
 * @param second
 *            Number to be converted.
 * 
 * @param str
 *            String result.
 * 
 * @param radix
 *            Base of value; which must be in the range 236.
 * 
 * @return Each of these functions returns a pointer to str. There is no error
 *         return.
 * 
 * @author Yen Nguyen
 *
 */
public class _itow extends MSVCRTAPI {
	public _itow() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		
		int value = (int) t1;
		WString str = new WString(memory.getText(this, t2));
		int radix = (int) t3;
		WString ret = MSVCRTDLL.INSTANCE._itow(value, str, radix);
		
		register.mov("eax", new LongValue(t2));
		System.out.println("Return Value: " + ret);
		
		memory.setText(this, t2, ret.toString());
	}
}
