/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.powrprof.functions
 * File name: DeletePwrScheme.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.powrprof.functions;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.powrprof.PowrprofAPI;
import v2.org.analysis.apihandle.winapi.powrprof.PowrprofDLL;
import v2.org.analysis.value.LongValue;
 
public class DeletePwrScheme extends PowrprofAPI {
	public DeletePwrScheme () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		UINT uiIndex = new UINT (t0);

		// Step 3: call API function
		int ret = PowrprofDLL.INSTANCE.DeletePwrScheme (uiIndex);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}