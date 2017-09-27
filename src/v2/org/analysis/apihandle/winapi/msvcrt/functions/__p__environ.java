/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: __p__environ.java
 * Created date: Jul 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * I have no idea for this function
 * 
 * @author Yen Nguyen
 *
 */
public class __p__environ extends MSVCRTAPI {

	public __p__environ() {
		super();
		NUM_OF_PARMS = 0;
	}

	
	@Override
	public void execute() {
		int ret = MSVCRTDLL.INSTANCE.__p__environ();

		register.mov("eax", new LongValue(ret));
	}

}
