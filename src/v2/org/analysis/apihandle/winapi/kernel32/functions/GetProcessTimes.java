/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetProcessTimes.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetProcessTimes extends Kernel32API {
	public GetProcessTimes () {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hProcess = null;
		if ( t0 != 0L ) {
			hProcess = new HANDLE ();
			hProcess.setPointer(new Pointer(t0));
		}
		FILETIME lpCreationTime = new FILETIME ();
		FILETIME lpExitTime = new FILETIME ();
		FILETIME lpKernelTime = new FILETIME ();
		FILETIME lpUserTime = new FILETIME ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetProcessTimes (hProcess, lpCreationTime, lpExitTime, lpKernelTime, lpUserTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCreationTime.dwLowDateTime));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCreationTime.dwHighDateTime));
		t1 += 4;
		t2 = this.params.get(2);
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpExitTime.dwLowDateTime));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpExitTime.dwHighDateTime));
		t2 += 4;
		t3 = this.params.get(3);
		memory.setDoubleWordMemoryValue (t3, new LongValue(lpKernelTime.dwLowDateTime));
		t3 += 4;
		memory.setDoubleWordMemoryValue (t3, new LongValue(lpKernelTime.dwHighDateTime));
		t3 += 4;
		t4 = this.params.get(4);
		memory.setDoubleWordMemoryValue (t4, new LongValue(lpUserTime.dwLowDateTime));
		t4 += 4;
		memory.setDoubleWordMemoryValue (t4, new LongValue(lpUserTime.dwHighDateTime));
		t4 += 4;

	}
}