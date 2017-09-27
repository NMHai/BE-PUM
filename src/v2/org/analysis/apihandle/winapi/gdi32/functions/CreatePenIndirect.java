/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: CreatePenIndirect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.LOGPEN;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreatePenIndirect extends Gdi32API {
	public CreatePenIndirect () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		LOGPEN lplgpn = null;
		if ( t0 != 0L) {
			lplgpn = new LOGPEN ();
			lplgpn.lopnStyle = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lplgpn.lopnWidth = new POINT ();
			// Nested Structure
			lplgpn.lopnWidth.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0 += 0)).getValue();
			lplgpn.lopnWidth.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0 += 4)).getValue();
			lplgpn.lopnColor = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
		}

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.CreatePenIndirect (lplgpn);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}