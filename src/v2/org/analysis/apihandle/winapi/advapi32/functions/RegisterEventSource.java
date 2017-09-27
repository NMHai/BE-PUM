/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegisterEventSource.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegisterEventSource extends Advapi32API {
	public RegisterEventSource () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String lpUNCServerName = null;
		if ( t0 != 0L ) lpUNCServerName = memory.getText(this, t0);
		String lpSourceName = null;
		if ( t1 != 0L ) lpSourceName = memory.getText(this, t1);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegisterEventSource (lpUNCServerName, lpSourceName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}