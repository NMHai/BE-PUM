/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: NotifyChangeEventLog.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class NotifyChangeEventLog extends Advapi32API {
	public NotifyChangeEventLog () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hEventLog = null;
		if ( t0 != 0L ) {
			hEventLog = new HANDLE ();
			hEventLog.setPointer(new Pointer(t0));
		}
		HANDLE hEvent = null;
		if ( t1 != 0L ) {
			hEvent = new HANDLE ();
			hEvent.setPointer(new Pointer(t1));
		}

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.NotifyChangeEventLog (hEventLog, hEvent);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}