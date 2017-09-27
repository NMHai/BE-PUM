/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: CombineRgn.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HRGN;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class CombineRgn extends Gdi32API {
	public CombineRgn () {
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
		HRGN hrgnDest = null;
		if ( t0 != 0L ) {
			hrgnDest = new HRGN ();
			hrgnDest.setPointer(new Pointer(t0));
		}
		HRGN hrgnSrc1 = null;
		if ( t1 != 0L ) {
			hrgnSrc1 = new HRGN ();
			hrgnSrc1.setPointer(new Pointer(t1));
		}
		HRGN hrgnSrc2 = null;
		if ( t2 != 0L ) {
			hrgnSrc2 = new HRGN ();
			hrgnSrc2.setPointer(new Pointer(t2));
		}
		int fnCombineMode = (int) t3;

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.CombineRgn (hrgnDest, hrgnSrc1, hrgnSrc2, fnCombineMode);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}