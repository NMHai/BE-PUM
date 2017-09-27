/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.nddeapi.functions
 * File name: NDdeGetTrustedShare.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.nddeapi.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiAPI;
import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiDLL;
import v2.org.analysis.value.LongValue;
 
public class NDdeGetTrustedShare extends NddeapiAPI {
	public NDdeGetTrustedShare () {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		
		// Step 2: type conversion from C++ to Java
		String lpszServer = null;
		if ( t0 != 0L ) lpszServer = memory.getText(this, t0);
		String lpszShareName = null;
		if ( t1 != 0L ) lpszShareName = memory.getText(this, t1);
		IntByReference lpdwTrustOptions = new IntByReference ((int) t2);
		IntByReference lpdwShareModId0 = new IntByReference ((int) t3);
		IntByReference lpdwShareModId1 = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = NddeapiDLL.INSTANCE.NDdeGetTrustedShare (lpszServer, lpszShareName, lpdwTrustOptions, lpdwShareModId0, lpdwShareModId1);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t2, new LongValue(lpdwTrustOptions.getValue()));

		
		memory.setDoubleWordMemoryValue(t3, new LongValue(lpdwShareModId0.getValue()));

		
		memory.setDoubleWordMemoryValue(t4, new LongValue(lpdwShareModId1.getValue()));

		

	}
}