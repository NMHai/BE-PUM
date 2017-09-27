/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DrawIcon.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HICON;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DrawIcon extends User32API {
	public DrawIcon () {
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
		HDC hDC = null;
		if ( t0 != 0L ) {
			hDC = new HDC ();
			hDC.setPointer(new Pointer(t0));
		}
		int X = (int) t1;
		int Y = (int) t2;
		HICON hIcon = null;
		if ( t3 != 0L ) {
			hIcon = new HICON ();
			hIcon.setPointer(new Pointer(t3));
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DrawIcon (hDC, X, Y, hIcon);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}