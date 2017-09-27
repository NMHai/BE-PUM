/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: QueryServiceConfig.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.Winsvc.SC_HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.structures.QUERY_SERVICE_CONFIG;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class QueryServiceConfig extends Advapi32API {
	public QueryServiceConfig () {
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
		SC_HANDLE hService = null;
		if ( t0 != 0L ) {
			hService = new SC_HANDLE ();
			hService.setPointer(new Pointer(t0));
		}
		QUERY_SERVICE_CONFIG lpServiceConfig = new QUERY_SERVICE_CONFIG ();
		DWORD cbBufSize = new DWORD (t2);
		IntByReference pcbBytesNeeded = new IntByReference ((int) t3);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.QueryServiceConfig (hService, lpServiceConfig, cbBufSize, pcbBytesNeeded);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpServiceConfig.dwServiceType.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpServiceConfig.dwStartType.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpServiceConfig.dwErrorControl.longValue()));
		t1 += 4;
		memory.setText(this, t1, new String(lpServiceConfig.lpBinaryPathName));
		memory.setText(this, t1, new String(lpServiceConfig.lpLoadOrderGroup));
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpServiceConfig.dwTagId.longValue()));
		t1 += 4;
		memory.setText(this, t1, new String(lpServiceConfig.lpDependencies));
		memory.setText(this, t1, new String(lpServiceConfig.lpServiceStartName));
		memory.setText(this, t1, new String(lpServiceConfig.lpDisplayName));
		memory.setDoubleWordMemoryValue(t3, new LongValue(pcbBytesNeeded.getValue()));

		

	}
}