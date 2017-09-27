/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHLoadNonloadedIconOverlayIdentifiers.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;

 
public class SHLoadNonloadedIconOverlayIdentifiers extends Shell32API {
	public SHLoadNonloadedIconOverlayIdentifiers () {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		
		// Step 2: type conversion from C++ to Java

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.SHLoadNonloadedIconOverlayIdentifiers ();
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}