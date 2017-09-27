/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _cexit.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;

/**
 * Performs cleanup operations and returns without terminating the process.
 * 
 * @author Yen Nguyen
 *
 */
public class _cexit extends MSVCRTAPI {

	public _cexit() {
	}

	@Override
	public void execute() {
		System.out.println("No argument and return value.");
		MSVCRTDLL.INSTANCE._cexit();
	}

}
