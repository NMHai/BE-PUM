/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mpr.functions
 * File name: WNetUseConnection.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mpr.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.structures.NETRESOURCE;
import v2.org.analysis.apihandle.winapi.mpr.MprAPI;
import v2.org.analysis.apihandle.winapi.mpr.MprDLL;
import v2.org.analysis.value.LongValue;
 
public class WNetUseConnection extends MprAPI {
	public WNetUseConnection () {
		super();
		NUM_OF_PARMS = 8;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		long t6 = this.params.get(6);
		long t7 = this.params.get(7);
		
		// Step 2: type conversion from C++ to Java
		HWND hwndOwner = null;
		if ( t0 != 0L ) {
			hwndOwner = new HWND ();
			hwndOwner.setPointer(new Pointer(t0));
		}
		NETRESOURCE lpNetResource = null;
		if ( t1 != 0L) {
			lpNetResource = new NETRESOURCE ();
			lpNetResource.dwScope = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpNetResource.dwType = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpNetResource.dwDisplayType = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpNetResource.dwUsage = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpNetResource.lpLocalName = memory.getText(this, t1);
			t1 += 4;
			lpNetResource.lpRemoteName = memory.getText(this, t1);
			t1 += 4;
			lpNetResource.lpComment = memory.getText(this, t1);
			t1 += 4;
			lpNetResource.lpProvider = memory.getText(this, t1);
			t1 += 4;
		}
		String lpPassword = null;
		if ( t2 != 0L ) lpPassword = memory.getText(this, t2);
		String lpUserID = null;
		if ( t3 != 0L ) lpUserID = memory.getText(this, t3);
		DWORD dwFlags = new DWORD (t4);
		char[] lpAccessName = null;
		if ( t5 != 0L ) lpAccessName = new char[(int) t6];
		for (int i = 0; i < lpAccessName.length; i++) {
			lpAccessName [i] = (char) ((LongValue) memory.getByteMemoryValue (t5)).getValue();
			t5 += 1;
		}
		IntByReference lpBufferSize = new IntByReference ((int) t6);
		IntByReference lpResult = new IntByReference ((int) t7);

		// Step 3: call API function
		int ret = MprDLL.INSTANCE.WNetUseConnection (hwndOwner, lpNetResource, lpPassword, lpUserID, dwFlags, lpAccessName, lpBufferSize, lpResult);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t5 = this.params.get(5);
		for (int i = 0; i < lpAccessName.length; i++) {
			memory.setByteMemoryValue (t5, new LongValue(lpAccessName [i]));
			t5 += 1;
		}		memory.setDoubleWordMemoryValue(t7, new LongValue(lpResult.getValue()));

		

	}
}