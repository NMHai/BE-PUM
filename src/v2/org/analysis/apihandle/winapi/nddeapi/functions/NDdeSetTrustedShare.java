/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.nddeapi.functions
 * File name: NDdeSetTrustedShare.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.nddeapi.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiAPI;
import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiDLL;
import v2.org.analysis.value.LongValue;
 
public class NDdeSetTrustedShare extends NddeapiAPI {
	public NDdeSetTrustedShare () {
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
		String lpszServer = null;
		if ( t0 != 0L ) lpszServer = memory.getText(this, t0);
		String lpszShareName = null;
		if ( t1 != 0L ) lpszShareName = memory.getText(this, t1);
		DWORD dwTrustOptions = new DWORD (t2);

		// Step 3: call API function
		int ret = NddeapiDLL.INSTANCE.NDdeSetTrustedShare (lpszServer, lpszShareName, dwTrustOptions);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}