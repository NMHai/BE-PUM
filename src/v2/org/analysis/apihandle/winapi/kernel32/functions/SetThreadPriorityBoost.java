/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetThreadPriorityBoost.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetThreadPriorityBoost extends Kernel32API {
	public SetThreadPriorityBoost () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hThread = null;
		if ( t0 != 0L ) {
			hThread = new HANDLE ();
			hThread.setPointer(new Pointer(t0));
		}
		BOOL DisablePriorityBoost = new BOOL (t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetThreadPriorityBoost (hThread, DisablePriorityBoost);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}