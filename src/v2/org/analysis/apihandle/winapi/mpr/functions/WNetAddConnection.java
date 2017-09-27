/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mpr.functions
 * File name: WNetAddConnection.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mpr.functions;

import v2.org.analysis.apihandle.winapi.mpr.MprAPI;
import v2.org.analysis.apihandle.winapi.mpr.MprDLL;
import v2.org.analysis.value.LongValue;
 
public class WNetAddConnection extends MprAPI {
	public WNetAddConnection () {
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
		String lpRemoteName = null;
		if ( t0 != 0L ) lpRemoteName = memory.getText(this, t0);
		String lpPassword = null;
		if ( t1 != 0L ) lpPassword = memory.getText(this, t1);
		String lpLocalName = null;
		if ( t2 != 0L ) lpLocalName = memory.getText(this, t2);

		// Step 3: call API function
		int ret = MprDLL.INSTANCE.WNetAddConnection (lpRemoteName, lpPassword, lpLocalName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}