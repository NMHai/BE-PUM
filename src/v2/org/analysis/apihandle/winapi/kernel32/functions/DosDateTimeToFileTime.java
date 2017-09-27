/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: DosDateTimeToFileTime.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinDef.WORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class DosDateTimeToFileTime extends Kernel32API {
	public DosDateTimeToFileTime () {
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
		WORD wFatDate = new WORD (t0);
		WORD wFatTime = new WORD (t1);
		FILETIME lpFileTime = new FILETIME ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.DosDateTimeToFileTime (wFatDate, wFatTime, lpFileTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpFileTime.dwLowDateTime));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpFileTime.dwHighDateTime));
		t2 += 4;

	}
}