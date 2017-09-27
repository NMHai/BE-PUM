/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: SetServiceStatus.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.Winsvc.SERVICE_STATUS;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetServiceStatus extends Advapi32API {
	public SetServiceStatus () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hServiceStatus = null;
		if ( t0 != 0L ) {
			hServiceStatus = new HANDLE ();
			hServiceStatus.setPointer(new Pointer(t0));
		}
		SERVICE_STATUS lpServiceStatus = null;
		if ( t1 != 0L) {
			lpServiceStatus = new SERVICE_STATUS ();
			lpServiceStatus.dwServiceType = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpServiceStatus.dwCurrentState = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpServiceStatus.dwControlsAccepted = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpServiceStatus.dwWin32ExitCode = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpServiceStatus.dwServiceSpecificExitCode = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpServiceStatus.dwCheckPoint = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpServiceStatus.dwWaitHint = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
		}

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.SetServiceStatus (hServiceStatus, lpServiceStatus);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}