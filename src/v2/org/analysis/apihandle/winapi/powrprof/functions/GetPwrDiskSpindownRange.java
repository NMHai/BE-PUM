/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.powrprof.functions
 * File name: GetPwrDiskSpindownRange.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.powrprof.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.powrprof.PowrprofAPI;
import v2.org.analysis.apihandle.winapi.powrprof.PowrprofDLL;
import v2.org.analysis.value.LongValue;
 
public class GetPwrDiskSpindownRange extends PowrprofAPI {
	public GetPwrDiskSpindownRange () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		IntByReference RangeMax = new IntByReference ((int) t0);
		IntByReference RangeMin = new IntByReference ((int) t1);

		// Step 3: call API function
		int ret = PowrprofDLL.INSTANCE.GetPwrDiskSpindownRange (RangeMax, RangeMin);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t0, new LongValue(RangeMax.getValue()));

		
		memory.setDoubleWordMemoryValue(t1, new LongValue(RangeMin.getValue()));

		

	}
}