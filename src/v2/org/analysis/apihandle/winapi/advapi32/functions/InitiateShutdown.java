/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: InitiateShutdown.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class InitiateShutdown extends Advapi32API {
	public InitiateShutdown () {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		
		// Step 2: type conversion from C++ to Java
		String lpMachineName = null;
		if ( t0 != 0L ) lpMachineName = memory.getText(this, t0);
		String lpMessage = null;
		if ( t1 != 0L ) lpMessage = memory.getText(this, t1);
		DWORD dwGracePeriod = new DWORD (t2);
		DWORD dwShutdownFlags = new DWORD (t3);
		DWORD dwReason = new DWORD (t4);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.InitiateShutdown (lpMachineName, lpMessage, dwGracePeriod, dwShutdownFlags, dwReason);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}