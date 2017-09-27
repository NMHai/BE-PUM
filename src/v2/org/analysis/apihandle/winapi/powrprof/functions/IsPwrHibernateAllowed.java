/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.powrprof.functions
 * File name: IsPwrHibernateAllowed.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.powrprof.functions;

import v2.org.analysis.apihandle.winapi.powrprof.PowrprofAPI;
import v2.org.analysis.apihandle.winapi.powrprof.PowrprofDLL;
import v2.org.analysis.value.LongValue;

 
public class IsPwrHibernateAllowed extends PowrprofAPI {
	public IsPwrHibernateAllowed () {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		
		// Step 2: type conversion from C++ to Java

		// Step 3: call API function
		int ret = PowrprofDLL.INSTANCE.IsPwrHibernateAllowed ();
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}