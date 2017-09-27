/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ntmsapi.functions
 * File name: OpenNtmsSession.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.ntmsapi.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.ntmsapi.NtmsapiAPI;
import v2.org.analysis.apihandle.winapi.ntmsapi.NtmsapiDLL;
import v2.org.analysis.value.LongValue;
 
public class OpenNtmsSession extends NtmsapiAPI {
	public OpenNtmsSession () {
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
		String lpServer = null;
		if ( t0 != 0L ) lpServer = memory.getText(this, t0);
		String lpApplication = null;
		if ( t1 != 0L ) lpApplication = memory.getText(this, t1);
		DWORD dwOptions = new DWORD (t2);

		// Step 3: call API function
		int ret = NtmsapiDLL.INSTANCE.OpenNtmsSession (lpServer, lpApplication, dwOptions);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}