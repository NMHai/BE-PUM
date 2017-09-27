/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: ApplicationRecoveryFinished.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
 
public class ApplicationRecoveryFinished extends Kernel32API {
	public ApplicationRecoveryFinished () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		BOOL bSuccess = new BOOL (t0);

		// Step 3: call API function
		Kernel32DLL.INSTANCE.ApplicationRecoveryFinished (bSuccess);
		
		// Step 4: update environment (memory & eax register)

	}
}