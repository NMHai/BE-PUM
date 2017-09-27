/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: StretchBlt.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HDC;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class StretchBlt extends Gdi32API {
	public StretchBlt () {
		super();
		NUM_OF_PARMS = 11;
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
		long t10 = this.params.get(10);
		
		// Step 2: type conversion from C++ to Java
		HDC hdcDest = null;
		if ( t0 != 0L ) {
			hdcDest = new HDC ();
			hdcDest.setPointer(new Pointer(t0));
		}
		int nXOriginDest = (int) t1;
		int nYOriginDest = (int) t2;
		int nWidthDest = (int) t3;
		int nHeightDest = (int) t4;
		HDC hdcSrc = null;
		if ( t5 != 0L ) {
			hdcSrc = new HDC ();
			hdcSrc.setPointer(new Pointer(t5));
		}
		int nXOriginSrc = (int) t6;
		int nYOriginSrc = (int) t7;
		int nWidthSrc = (int) t8;
		int nHeightSrc = (int) t9;
		DWORD dwRop = new DWORD (t10);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.StretchBlt (hdcDest, nXOriginDest, nYOriginDest, nWidthDest, nHeightDest, hdcSrc, nXOriginSrc, nYOriginSrc, nWidthSrc, nHeightSrc, dwRop);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}