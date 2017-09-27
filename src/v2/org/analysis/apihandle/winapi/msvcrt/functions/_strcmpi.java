/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _strcmpi.java
 * Created date: Sep 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Performs a case-insensitive comparison of strings.
 * 
 * @param _Str1
 *            Null-terminated strings to compare.
 * 
 * @param _Str2
 *            Null-terminated strings to compare.
 * 
 * @return The return value indicates the relation of string1 to string2 as
 *         follows.
 * 
 *         < 0 string1 less than string2
 * 
 *         0 string1 identical to string2
 * 
 *         > 0 string1 greater than string2
 * 
 * @author Yen Nguyen
 *
 */
public class _strcmpi extends MSVCRTAPI {

	public _strcmpi() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String _Str1 = memory.getText(this, t1);
		String _Str2 = memory.getText(this, t2);

		System.out.println(String.format("Str1: %s, Str2 %s", _Str1, _Str2));

		int ret = MSVCRTDLL.INSTANCE._strcmpi(_Str1, _Str2);

		register.mov("eax", new LongValue(ret));
	}

}
