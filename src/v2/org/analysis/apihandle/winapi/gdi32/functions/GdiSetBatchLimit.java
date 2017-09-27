/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GdiSetBatchLimit.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GdiSetBatchLimit extends Gdi32API {
	public GdiSetBatchLimit () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		DWORD dwLimit = new DWORD (t0);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GdiSetBatchLimit (dwLimit);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}