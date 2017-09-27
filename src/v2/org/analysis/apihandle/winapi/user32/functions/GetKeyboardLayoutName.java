/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetKeyboardLayoutName.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetKeyboardLayoutName extends User32API {
	public GetKeyboardLayoutName () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		String pwszKLID = null;
		if ( t0 != 0L ) pwszKLID = memory.getText(this, t0);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetKeyboardLayoutName (pwszKLID);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t0, new String(pwszKLID));
	}
}