/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: fclose.java
 * Created date: Nov 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Closes a file.
 * int _close( int handle );
 * 
 * @author HaiNM
 *
 */
public class _close extends MSVCRTAPI {

	public _close() {
		super();
		NUM_OF_PARMS = 1;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		int handle = (int) t1;
		int ret = MSVCRTDLL.INSTANCE._close(handle);

		register.mov("eax", new LongValue(ret));
	}

}
