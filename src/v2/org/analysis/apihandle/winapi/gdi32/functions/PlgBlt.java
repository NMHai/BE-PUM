/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: PlgBlt.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.POINT;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class PlgBlt extends Gdi32API {
	public PlgBlt () {
		super();
		NUM_OF_PARMS = 10;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		long t6 = this.params.get(6);
		long t7 = this.params.get(7);
		long t8 = this.params.get(8);
		long t9 = this.params.get(9);
		
		// Step 2: type conversion from C++ to Java
		HDC hdcDest = null;
		if ( t0 != 0L ) {
			hdcDest = new HDC ();
			hdcDest.setPointer(new Pointer(t0));
		}
		POINT lpPoint = null;
		if ( t1 != 0L) {
			lpPoint = new POINT ();
			lpPoint.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpPoint.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
		}
		HDC hdcSrc = null;
		if ( t2 != 0L ) {
			hdcSrc = new HDC ();
			hdcSrc.setPointer(new Pointer(t2));
		}
		int nXSrc = (int) t3;
		int nYSrc = (int) t4;
		int nWidth = (int) t5;
		int nHeight = (int) t6;
		HBITMAP hbmMask = null;
		if ( t7 != 0L ) {
			hbmMask = new HBITMAP ();
			hbmMask.setPointer(new Pointer(t7));
		}
		int xMask = (int) t8;
		int yMask = (int) t9;

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.PlgBlt (hdcDest, lpPoint, hdcSrc, nXSrc, nYSrc, nWidth, nHeight, hbmMask, xMask, yMask);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}