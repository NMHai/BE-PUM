/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: BitBlt.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HDC;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class BitBlt extends Gdi32API {
	public BitBlt () {
		super();
		NUM_OF_PARMS = 9;
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
		
		// Step 2: type conversion from C++ to Java
		HDC hdcDest = null;
		if ( t0 != 0L ) {
			hdcDest = new HDC ();
			hdcDest.setPointer(new Pointer(t0));
		}
		int nXDest = (int) t1;
		int nYDest = (int) t2;
		int nWidth = (int) t3;
		int nHeight = (int) t4;
		HDC hdcSrc = null;
		if ( t5 != 0L ) {
			hdcSrc = new HDC ();
			hdcSrc.setPointer(new Pointer(t5));
		}
		int nXSrc = (int) t6;
		int nYSrc = (int) t7;
		DWORD dwRop = new DWORD (t8);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.BitBlt (hdcDest, nXDest, nYDest, nWidth, nHeight, hdcSrc, nXSrc, nYSrc, dwRop);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}