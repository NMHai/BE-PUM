/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: OpenSCManager.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class OpenSCManager extends Advapi32API {
	public OpenSCManager () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		String lpMachineName = null;
		if ( t0 != 0L ) lpMachineName = memory.getText(this, t0);
		String lpDatabaseName = null;
		if ( t1 != 0L ) lpDatabaseName = memory.getText(this, t1);
		DWORD dwDesiredAccess = new DWORD (t2);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.OpenSCManager (lpMachineName, lpDatabaseName, dwDesiredAccess);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}