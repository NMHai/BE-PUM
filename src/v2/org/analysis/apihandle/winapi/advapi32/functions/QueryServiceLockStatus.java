/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: QueryServiceLockStatus.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.Winsvc.SC_HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.structures.QUERY_SERVICE_LOCK_STATUS;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class QueryServiceLockStatus extends Advapi32API {
	public QueryServiceLockStatus () {
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
		SC_HANDLE hSCManager = null;
		if ( t0 != 0L ) {
			hSCManager = new SC_HANDLE ();
			hSCManager.setPointer(new Pointer(t0));
		}
		QUERY_SERVICE_LOCK_STATUS lpLockStatus = new QUERY_SERVICE_LOCK_STATUS ();
		DWORD cbBufSize = new DWORD (t2);
		IntByReference pcbBytesNeeded = new IntByReference ((int) t3);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.QueryServiceLockStatus (hSCManager, lpLockStatus, cbBufSize, pcbBytesNeeded);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpLockStatus.fIsLocked.longValue()));
		t1 += 4;
		memory.setText(this, t1, new String(lpLockStatus.lpLockOwner));
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpLockStatus.dwLockDuration.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue(t3, new LongValue(pcbBytesNeeded.getValue()));

		

	}
}