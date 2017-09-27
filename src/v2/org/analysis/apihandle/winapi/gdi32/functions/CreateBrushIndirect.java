/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: CreateBrushIndirect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.LOGBRUSH;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateBrushIndirect extends Gdi32API {
	public CreateBrushIndirect () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		LOGBRUSH lplb = null;
		if ( t0 != 0L) {
			lplb = new LOGBRUSH ();
			lplb.lbStyle = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lplb.lbColor = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			lplb.lbHatch = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.CreateBrushIndirect (lplb);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}