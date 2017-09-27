/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _chkesp.java
 * Created date: Nov 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;

/**
 * @author Yen Nguyen
 *
 */
public class _chkesp extends MSVCRTAPI {

	/**
	 * 
	 */
	public _chkesp() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		// Do nothing
//		register.mov("eax", new LongValue(0));
		Kernel32.INSTANCE.SetLastError(0);
	}

}
