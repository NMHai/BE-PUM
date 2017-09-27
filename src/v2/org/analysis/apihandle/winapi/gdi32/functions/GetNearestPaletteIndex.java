/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetNearestPaletteIndex.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HPALETTE;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetNearestPaletteIndex extends Gdi32API {
	public GetNearestPaletteIndex () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HPALETTE hpal = null;
		if ( t0 != 0L ) {
			hpal = new HPALETTE ();
			hpal.setPointer(new Pointer(t0));
		}
		int crColor = (int) t1;

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetNearestPaletteIndex (hpal, crColor);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}