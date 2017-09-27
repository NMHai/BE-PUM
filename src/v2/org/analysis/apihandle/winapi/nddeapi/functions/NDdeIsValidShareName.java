/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.nddeapi.functions
 * File name: NDdeIsValidShareName.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.nddeapi.functions;

import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiAPI;
import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiDLL;
import v2.org.analysis.value.LongValue;
 
public class NDdeIsValidShareName extends NddeapiAPI {
	public NDdeIsValidShareName () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		String shareName = null;
		if ( t0 != 0L ) shareName = memory.getText(this, t0);

		// Step 3: call API function
		int ret = NddeapiDLL.INSTANCE.NDdeIsValidShareName (shareName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}