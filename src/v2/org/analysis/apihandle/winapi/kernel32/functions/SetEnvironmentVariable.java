/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetEnvironmentVariable.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetEnvironmentVariable extends Kernel32API {
	public SetEnvironmentVariable () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String lpName = null;
		if ( t0 != 0L ) lpName = memory.getText(this, t0);
		String lpValue = null;
		if ( t1 != 0L ) lpValue = memory.getText(this, t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetEnvironmentVariable (lpName, lpValue);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}