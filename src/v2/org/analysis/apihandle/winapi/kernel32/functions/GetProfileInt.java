/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetProfileInt.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetProfileInt extends Kernel32API {
	public GetProfileInt () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		String lpAppName = null;
		if ( t0 != 0L ) lpAppName = memory.getText(this, t0);
		String lpKeyName = null;
		if ( t1 != 0L ) lpKeyName = memory.getText(this, t1);
		int nDefault = (int) t2;

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetProfileInt (lpAppName, lpKeyName, nDefault);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}