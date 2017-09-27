/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: Beep.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.DWORD;
 
public class Beep extends Kernel32API {
	public Beep () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		DWORD dwFreq = new DWORD (t0);
		DWORD dwDuration = new DWORD (t1);

		// Step 3: call API function
//		BOOL ret = Kernel32DLL.INSTANCE.Beep (dwFreq, dwDuration);
		
		// Step 4: update environment (memory & eax register)
//		long value = ret;
		register.mov("eax", new LongValue(1));

	}
}