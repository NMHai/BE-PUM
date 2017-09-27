/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mpr.functions
 * File name: WNetGetConnection.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mpr.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.mpr.MprAPI;
import v2.org.analysis.apihandle.winapi.mpr.MprDLL;
import v2.org.analysis.value.LongValue;
 
public class WNetGetConnection extends MprAPI {
	public WNetGetConnection () {
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
		String lpLocalName = null;
		if ( t0 != 0L ) lpLocalName = memory.getText(this, t0);
		String lpRemoteName = null;
		if ( t1 != 0L ) lpRemoteName = memory.getText(this, t1);
		IntByReference lpnLength = new IntByReference ((int) t2);

		// Step 3: call API function
		int ret = MprDLL.INSTANCE.WNetGetConnection (lpLocalName, lpRemoteName, lpnLength);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t1, new String(lpRemoteName));
	}
}