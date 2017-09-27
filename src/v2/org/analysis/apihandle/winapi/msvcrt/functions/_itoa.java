/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _itoa.java
 * Created date: Dec 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Converts an integer to a string. More secure versions of these functions are
 * available
 * 
 * @param value
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
public class _itoa extends MSVCRTAPI {

	public _itoa() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		
		int value = (int) t1;
		String str = new String(memory.getText(this, t2));
		int radix = (int) t3;
		String ret = MSVCRTDLL.INSTANCE._itoa(value, str, radix);
		
		register.mov("eax", new LongValue(t2));
		System.out.println("Return Value: " + ret);
		
		memory.setText(this, t2, ret.toString());
	}

}
