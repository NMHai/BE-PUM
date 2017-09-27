/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: OpenBackupEventLog.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class OpenBackupEventLog extends Advapi32API {
	public OpenBackupEventLog () {
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
		String lpFileName = null;
		if ( t1 != 0L ) lpFileName = memory.getText(this, t1);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.OpenBackupEventLog (lpUNCServerName, lpFileName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}