/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.rstrtmgr.functions
 * File name: RmEndSession.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.rstrtmgr.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.rstrtmgr.RstrtmgrAPI;
import v2.org.analysis.apihandle.winapi.rstrtmgr.RstrtmgrDLL;
import v2.org.analysis.value.LongValue;
 
public class RmEndSession extends RstrtmgrAPI {
	public RmEndSession () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		DWORD dwSessionHandle = new DWORD (t0);

		// Step 3: call API function
		int ret = RstrtmgrDLL.INSTANCE.RmEndSession (dwSessionHandle);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}