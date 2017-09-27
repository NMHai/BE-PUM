/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.powrprof.functions
 * File name: SetSuspendState.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.powrprof.functions;

import v2.org.analysis.apihandle.winapi.powrprof.PowrprofAPI;
import v2.org.analysis.apihandle.winapi.powrprof.PowrprofDLL;
import v2.org.analysis.value.LongValue;

 
public class SetSuspendState extends PowrprofAPI {
	public SetSuspendState () {
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
		byte Hibernate = (byte) t0;
		byte ForceCritical = (byte) t1;
		byte DisableWakeEvent = (byte) t2;

		// Step 3: call API function
		int ret = PowrprofDLL.INSTANCE.SetSuspendState (Hibernate, ForceCritical, DisableWakeEvent);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}