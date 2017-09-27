/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mpr.functions
 * File name: MultinetGetConnectionPerformance.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mpr.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.structures.NETCONNECTINFOSTRUCT;
import v2.org.analysis.apihandle.structures.NETRESOURCE;
import v2.org.analysis.apihandle.winapi.mpr.MprAPI;
import v2.org.analysis.apihandle.winapi.mpr.MprDLL;
import v2.org.analysis.value.LongValue;
 
public class MultinetGetConnectionPerformance extends MprAPI {
	public MultinetGetConnectionPerformance () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
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
		NETCONNECTINFOSTRUCT lpNetConnectInfoStruct = new NETCONNECTINFOSTRUCT ();

		// Step 3: call API function
		int ret = MprDLL.INSTANCE.MultinetGetConnectionPerformance (lpNetResource, lpNetConnectInfoStruct);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetConnectInfoStruct.cbStructure.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetConnectInfoStruct.dwFlags.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetConnectInfoStruct.dwSpeed.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetConnectInfoStruct.dwDelay.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetConnectInfoStruct.dwOptDataSize.longValue()));
		t1 += 4;

	}
}