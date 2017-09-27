/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mincore.functions
 * File name: GetFileVersionInfoSize.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mincore.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.mincore.MincoreAPI;
import v2.org.analysis.apihandle.winapi.mincore.MincoreDLL;
import v2.org.analysis.value.LongValue;
 
public class GetFileVersionInfoSize extends MincoreAPI {
	public GetFileVersionInfoSize () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String lptstrFilename = null;
		if ( t0 != 0L ) lptstrFilename = memory.getText(this, t0);
		IntByReference lpdwHandle = new IntByReference ((int) t1);

		// Step 3: call API function
		int ret = MincoreDLL.INSTANCE.GetFileVersionInfoSize (lptstrFilename, lpdwHandle);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(lpdwHandle.getValue()));

		

	}
}