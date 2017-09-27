/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: SetBitmapDimensionEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinUser.SIZE;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetBitmapDimensionEx extends Gdi32API {
	public SetBitmapDimensionEx () {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		
		// Step 2: type conversion from C++ to Java
		HBITMAP hBitmap = null;
		if ( t0 != 0L ) {
			hBitmap = new HBITMAP ();
			hBitmap.setPointer(new Pointer(t0));
		}
		int nWidth = (int) t1;
		int nHeight = (int) t2;
		SIZE lpSize = new SIZE ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.SetBitmapDimensionEx (hBitmap, nWidth, nHeight, lpSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		memory.setDoubleWordMemoryValue (t3, new LongValue(lpSize.cx));
		t3 += 4;
		memory.setDoubleWordMemoryValue (t3, new LongValue(lpSize.cy));
		t3 += 4;

	}
}