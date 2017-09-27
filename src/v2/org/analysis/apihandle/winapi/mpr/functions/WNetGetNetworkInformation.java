/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mpr.functions
 * File name: WNetGetNetworkInformation.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mpr.functions;

import v2.org.analysis.apihandle.structures.NETINFOSTRUCT;
import v2.org.analysis.apihandle.winapi.mpr.MprAPI;
import v2.org.analysis.apihandle.winapi.mpr.MprDLL;
import v2.org.analysis.value.LongValue;
 
public class WNetGetNetworkInformation extends MprAPI {
	public WNetGetNetworkInformation () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String lpProvider = null;
		if ( t0 != 0L ) lpProvider = memory.getText(this, t0);
		NETINFOSTRUCT lpNetInfoStruct = new NETINFOSTRUCT ();

		// Step 3: call API function
		int ret = MprDLL.INSTANCE.WNetGetNetworkInformation (lpProvider, lpNetInfoStruct);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetInfoStruct.cbStructure.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetInfoStruct.dwProviderVersion.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetInfoStruct.dwStatus.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetInfoStruct.dwCharacteristics.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetInfoStruct.dwHandle.longValue()));
		t1 += 4;
		memory.setWordMemoryValue (t1, new LongValue(lpNetInfoStruct.wNetType.longValue()));
		t1 += 2;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetInfoStruct.dwPrinters.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpNetInfoStruct.dwDrives.longValue()));
		t1 += 4;

	}
}