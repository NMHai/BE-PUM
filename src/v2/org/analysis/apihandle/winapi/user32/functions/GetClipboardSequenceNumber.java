/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetClipboardSequenceNumber.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

 
public class GetClipboardSequenceNumber extends User32API {
	public GetClipboardSequenceNumber () {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		
		// Step 2: type conversion from C++ to Java

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetClipboardSequenceNumber ();
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}