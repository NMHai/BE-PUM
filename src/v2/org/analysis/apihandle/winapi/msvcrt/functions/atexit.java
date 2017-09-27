/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: atexit.java
 * Created date: Jul 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Set function to be executed on exit The function pointed by func is
 * automatically called without arguments when the program terminates normally.
 * 
 * If more than one atexit function has been specified by different calls to
 * this function, they are all executed in reverse order as a stack (i.e. the
 * last function specified is the first to be executed at exit).
 * 
 * A single function can be registered to be executed at exit more than once.
 * 
 * If atexit is called after exit, the call may or may not succeed depending on
 * the particular system and library implementation (unspecified behavior).
 * 
 * If a function registered with atexit throws an exception for which it does
 * not provide a handler when called on termination, terminate is automatically
 * called (C++).
 * 
 * Particular library implementations may impose a limit on the number of
 * functions call that can be registered with atexit, but this cannot be less
 * than 32 function calls.
 * 
 * @param func
 *            Function to be called. The function shall return no value and take
 *            no arguments.
 * 
 * @return A zero value is returned if the function was successfully registered.
 *         If it failed, a non-zero value is returned.
 * 
 * @author Yen Nguyen
 *
 */
public class atexit extends MSVCRTAPI {

	public atexit() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		System.out.println("\t\tSPECIAL API: CALLBACK - Run on exit");
		
		int ret = MSVCRTDLL.INSTANCE.atexit(null);

		register.mov("eax", new LongValue(ret));
		System.out.println("Return Value: " + ret);
	}
}
