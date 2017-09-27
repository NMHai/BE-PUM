/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mpr.functions
 * File name: WNetCancelConnection.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mpr.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.apihandle.winapi.mpr.MprAPI;
import v2.org.analysis.apihandle.winapi.mpr.MprDLL;
import v2.org.analysis.value.LongValue;
 
public class WNetCancelConnection extends MprAPI {
	public WNetCancelConnection () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String lpName = null;
		if ( t0 != 0L ) lpName = memory.getText(this, t0);
		BOOL fForce = new BOOL (t1);

		// Step 3: call API function
		int ret = MprDLL.INSTANCE.WNetCancelConnection (lpName, fForce);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}