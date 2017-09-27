/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.lz32.functions
 * File name: LZInit.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.lz32.functions;

import v2.org.analysis.apihandle.winapi.lz32.Lz32API;
import v2.org.analysis.apihandle.winapi.lz32.Lz32DLL;
import v2.org.analysis.value.LongValue;

 
public class LZInit extends Lz32API {
	public LZInit () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		int hfSource = (int) t0;

		// Step 3: call API function
		int ret = Lz32DLL.INSTANCE.LZInit (hfSource);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}