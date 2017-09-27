/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.faultrep.functions
 * File name: AddERExcludedApplication.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.faultrep.functions;

import v2.org.analysis.apihandle.winapi.faultrep.FaultrepAPI;
import v2.org.analysis.apihandle.winapi.faultrep.FaultrepDLL;
import v2.org.analysis.value.LongValue;
 
public class AddERExcludedApplication extends FaultrepAPI {
	public AddERExcludedApplication () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		String szApplication = null;
		if ( t0 != 0L ) szApplication = memory.getText(this, t0);

		// Step 3: call API function
		int ret = FaultrepDLL.INSTANCE.AddERExcludedApplication (szApplication);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}