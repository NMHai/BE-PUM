/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetBoundsRect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetBoundsRect extends Gdi32API {
	public GetBoundsRect () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		RECT lprcBounds = new RECT ();
		UINT flags = new UINT (t2);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetBoundsRect (hdc, lprcBounds, flags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lprcBounds.left));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lprcBounds.top));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lprcBounds.right));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lprcBounds.bottom));
		t1 += 4;

	}
}