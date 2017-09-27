/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mpr.functions
 * File name: WNetAddConnection2.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mpr.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.structures.NETRESOURCE;
import v2.org.analysis.apihandle.winapi.mpr.MprAPI;
import v2.org.analysis.apihandle.winapi.mpr.MprDLL;
import v2.org.analysis.value.LongValue;
 
public class WNetAddConnection2 extends MprAPI {
	public WNetAddConnection2 () {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		
		// Step 2: type conversion from C++ to Java
		NETRESOURCE lpNetResource = null;
		if ( t0 != 0L) {
			lpNetResource = new NETRESOURCE ();
			lpNetResource.dwScope = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpNetResource.dwType = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpNetResource.dwDisplayType = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpNetResource.dwUsage = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpNetResource.lpLocalName = memory.getText(this, t0);
			t0 += 4;
			lpNetResource.lpRemoteName = memory.getText(this, t0);
			t0 += 4;
			lpNetResource.lpComment = memory.getText(this, t0);
			t0 += 4;
			lpNetResource.lpProvider = memory.getText(this, t0);
			t0 += 4;
		}
		String lpPassword = null;
		if ( t1 != 0L ) lpPassword = memory.getText(this, t1);
		String lpUsername = null;
		if ( t2 != 0L ) lpUsername = memory.getText(this, t2);
		DWORD dwFlags = new DWORD (t3);

		// Step 3: call API function
		int ret = MprDLL.INSTANCE.WNetAddConnection2 (lpNetResource, lpPassword, lpUsername, dwFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}