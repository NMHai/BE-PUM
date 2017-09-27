/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: FindWindowEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class FindWindowEx extends User32API {
	public FindWindowEx () {
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
		HWND hwndParent = null;
		if ( t0 != 0L ) {
			hwndParent = new HWND ();
			hwndParent.setPointer(new Pointer(t0));
		}
		HWND hwndChildAfter = null;
		if ( t1 != 0L ) {
			hwndChildAfter = new HWND ();
			hwndChildAfter.setPointer(new Pointer(t1));
		}
		String lpszClass = null;
		if ( t2 != 0L ) lpszClass = memory.getText(this, t2);
		String lpszWindow = null;
		if ( t3 != 0L ) lpszWindow = memory.getText(this, t3);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.FindWindowEx (hwndParent, hwndChildAfter, lpszClass, lpszWindow);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}