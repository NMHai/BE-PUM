/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: QueryServiceStatus.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Winsvc.SC_HANDLE;
import com.sun.jna.platform.win32.Winsvc.SERVICE_STATUS;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class QueryServiceStatus extends Advapi32API {
	public QueryServiceStatus () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		SC_HANDLE hService = null;
		if ( t0 != 0L ) {
			hService = new SC_HANDLE ();
			hService.setPointer(new Pointer(t0));
		}
		SERVICE_STATUS lpServiceStatus = new SERVICE_STATUS ();

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.QueryServiceStatus (hService, lpServiceStatus);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpServiceStatus.dwServiceType));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpServiceStatus.dwCurrentState));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpServiceStatus.dwControlsAccepted));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpServiceStatus.dwWin32ExitCode));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpServiceStatus.dwServiceSpecificExitCode));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpServiceStatus.dwCheckPoint));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpServiceStatus.dwWaitHint));
		t1 += 4;

	}
}