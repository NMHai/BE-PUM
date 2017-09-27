/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: SetRectRgn.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HRGN;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetRectRgn extends Gdi32API {
	public SetRectRgn () {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		
		// Step 2: type conversion from C++ to Java
		HRGN hrgn = null;
		if ( t0 != 0L ) {
			hrgn = new HRGN ();
			hrgn.setPointer(new Pointer(t0));
		}
		int nLeftRect = (int) t1;
		int nTopRect = (int) t2;
		int nRightRect = (int) t3;
		int nBottomRect = (int) t4;

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.SetRectRgn (hrgn, nLeftRect, nTopRect, nRightRect, nBottomRect);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}