/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: PostQuitMessage.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

 
public class PostQuitMessage extends User32API {
	public PostQuitMessage () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		int nExitCode = (int) t0;

		// Step 3: call API function
		User32DLL.INSTANCE.PostQuitMessage (nExitCode);
		
		// Step 4: update environment (memory & eax register)

	}
}