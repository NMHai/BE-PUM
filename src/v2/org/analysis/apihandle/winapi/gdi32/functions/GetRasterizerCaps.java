/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetRasterizerCaps.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.RASTERIZER_STATUS;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetRasterizerCaps extends Gdi32API {
	public GetRasterizerCaps () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		RASTERIZER_STATUS lprs = new RASTERIZER_STATUS ();
		UINT cb = new UINT (t1);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetRasterizerCaps (lprs, cb);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		memory.setWordMemoryValue (t0, new LongValue(lprs.nSize));
		t0 += 2;
		memory.setWordMemoryValue (t0, new LongValue(lprs.wFlags));
		t0 += 2;
		memory.setWordMemoryValue (t0, new LongValue(lprs.nLanguageID));
		t0 += 2;

	}
}