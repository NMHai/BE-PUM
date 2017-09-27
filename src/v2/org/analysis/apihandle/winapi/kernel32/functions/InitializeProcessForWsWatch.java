/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: InitializeProcessForWsWatch.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class InitializeProcessForWsWatch extends Kernel32API {
	public InitializeProcessForWsWatch () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hProcess = null;
		if ( t0 != 0L ) {
			hProcess = new HANDLE ();
			hProcess.setPointer(new Pointer(t0));
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.InitializeProcessForWsWatch (hProcess);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}