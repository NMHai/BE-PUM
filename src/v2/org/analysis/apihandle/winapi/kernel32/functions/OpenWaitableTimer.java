/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: OpenWaitableTimer.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class OpenWaitableTimer extends Kernel32API {
	public OpenWaitableTimer () {
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
		DWORD dwDesiredAccess = new DWORD (t0);
		BOOL bInheritHandle = new BOOL (t1);
		String lpTimerName = null;
		if ( t2 != 0L ) lpTimerName = memory.getText(this, t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.OpenWaitableTimer (dwDesiredAccess, bInheritHandle, lpTimerName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}