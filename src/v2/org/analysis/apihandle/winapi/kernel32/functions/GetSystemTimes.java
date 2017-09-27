/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetSystemTimes.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinBase.FILETIME;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetSystemTimes extends Kernel32API {
	public GetSystemTimes () {
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
		FILETIME lpIdleTime = new FILETIME ();
		FILETIME lpKernelTime = new FILETIME ();
		FILETIME lpUserTime = new FILETIME ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetSystemTimes (lpIdleTime, lpKernelTime, lpUserTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpIdleTime.dwLowDateTime));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpIdleTime.dwHighDateTime));
		t0 += 4;
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpKernelTime.dwLowDateTime));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpKernelTime.dwHighDateTime));
		t1 += 4;
		t2 = this.params.get(2);
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpUserTime.dwLowDateTime));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpUserTime.dwHighDateTime));
		t2 += 4;

	}
}