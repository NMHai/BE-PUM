/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetBitmapDimensionEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinUser.SIZE;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetBitmapDimensionEx extends Gdi32API {
	public GetBitmapDimensionEx () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HBITMAP hBitmap = null;
		if ( t0 != 0L ) {
			hBitmap = new HBITMAP ();
			hBitmap.setPointer(new Pointer(t0));
		}
		SIZE lpDimension = new SIZE ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetBitmapDimensionEx (hBitmap, lpDimension);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpDimension.cx));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpDimension.cy));
		t1 += 4;

	}
}