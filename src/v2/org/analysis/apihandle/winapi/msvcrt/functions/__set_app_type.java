/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: __set_app_type.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Sets the current application type.
 * 
 * @param at
 *            A value that indicates the application type. The possible values
 *            are:
 * 
 *            _UNKNOWN_APP Unknown application type.
 * 
 *            _CONSOLE_APP Console (command-line) application.
 * 
 *            _GUI_APP GUI (Windows) application.
 * 
 * @author Yen Nguyen
 *
 */
public class __set_app_type extends MSVCRTAPI {

	public __set_app_type() {
		super();
		NUM_OF_PARMS = 1;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		// Re-push because it doesn't pop
//		stack.push(new LongValue(t1));

		int at = (int) t1;
		MSVCRTDLL.INSTANCE.__set_app_type(at);
		register.mov("eax", new LongValue(at));
	}

}
