/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FatalAppExit.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
 
public class FatalAppExit extends Kernel32API {
	public FatalAppExit () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		UINT uAction = new UINT (t0);
		String lpMessageText = null;
		if ( t1 != 0L ) lpMessageText = memory.getText(this, t1);

		// Step 3: call API function
		Kernel32DLL.INSTANCE.FatalAppExit (uAction, lpMessageText);
		
		// Step 4: update environment (memory & eax register)

	}
}