/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHFreeNameMappings.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
 
public class SHFreeNameMappings extends Shell32API {
	public SHFreeNameMappings () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hNameMappings = null;
		if ( t0 != 0L ) {
			hNameMappings = new HANDLE ();
			hNameMappings.setPointer(new Pointer(t0));
		}

		// Step 3: call API function
		Shell32DLL.INSTANCE.SHFreeNameMappings (hNameMappings);
		
		// Step 4: update environment (memory & eax register)

	}
}