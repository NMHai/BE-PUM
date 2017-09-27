/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _fpreset.java
 * Created date: Jul 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;

/**
 * Resets the floating-point package.
 * 
 * @author Yen Nguyen
 *
 */
public class _fpreset extends MSVCRTAPI {

	public _fpreset() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		MSVCRTDLL.INSTANCE._fpreset();
	}

}
