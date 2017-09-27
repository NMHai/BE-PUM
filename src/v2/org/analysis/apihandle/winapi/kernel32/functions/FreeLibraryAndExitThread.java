/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FreeLibraryAndExitThread.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMODULE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
 
public class FreeLibraryAndExitThread extends Kernel32API {
	public FreeLibraryAndExitThread () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HMODULE hModule = null;
		if ( t0 != 0L ) {
			hModule = new HMODULE ();
			hModule.setPointer(new Pointer(t0));
		}
		DWORD dwExitCode = new DWORD (t1);

		// Step 3: call API function
		Kernel32DLL.INSTANCE.FreeLibraryAndExitThread (hModule, dwExitCode);
		
		// Step 4: update environment (memory & eax register)

	}
}