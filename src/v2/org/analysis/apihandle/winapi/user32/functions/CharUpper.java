/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CharUpper.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CharUpper extends User32API {
	public CharUpper () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);		
		
		// Step 2: type conversion from C++ to Java
		String lpsz = null;
		if ( t0 != 0L ) {
			lpsz = memory.getText(this, t0);
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CharUpper (lpsz);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		
		// HaiNM: Manually convert to upper case
		long t1 = Character.toUpperCase((char) t0);
		register.mov("eax", new LongValue(t1));
	}
}