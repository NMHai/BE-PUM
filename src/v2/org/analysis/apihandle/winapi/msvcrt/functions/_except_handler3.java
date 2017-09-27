/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _except_handler3.java
 * Created date: Aug 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.SymbolValue;

/**
 * Internal CRT function. Used by a framework to find the appropriate exception
 * handler to process the current exception..
 * 
 * @param exception_record
 *            [in] Information about the specific exception.
 * 
 * @param registration
 *            [in] The record that indicates which scope table should be used to
 *            find the exception handler.
 * 
 * @param context
 *            [in] Reserved.
 * 
 * @param dispatcher
 *            [in] Reserved.
 * 
 * @return If an exception should be dismissed, returns DISPOSITION_DISMISS. If
 *         the exception should be passed up a level to the encapsulating
 *         exception handlers, returns DISPOSITION_CONTINUE_SEARCH.
 * 
 * @author Yen Nguyen
 *
 */
public class _except_handler3 extends MSVCRTAPI {

	public _except_handler3() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		register.mov("eax", new SymbolValue("api_eax_" + getAPIName()));
	}

}
