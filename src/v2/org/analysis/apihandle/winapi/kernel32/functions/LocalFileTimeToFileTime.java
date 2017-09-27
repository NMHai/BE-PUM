/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: LocalFileTimeToFileTime.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinBase.FILETIME;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class LocalFileTimeToFileTime extends Kernel32API {
	public LocalFileTimeToFileTime () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		FILETIME lpLocalFileTime = null;
		if ( t0 != 0L) {
			lpLocalFileTime = new FILETIME ();
			lpLocalFileTime.dwLowDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			lpLocalFileTime.dwHighDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
		}
		FILETIME lpFileTime = new FILETIME ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.LocalFileTimeToFileTime (lpLocalFileTime, lpFileTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpFileTime.dwLowDateTime));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpFileTime.dwHighDateTime));
		t1 += 4;

	}
}