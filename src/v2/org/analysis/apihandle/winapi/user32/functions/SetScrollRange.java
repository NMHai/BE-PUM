/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SetScrollRange.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetScrollRange extends User32API {
	public SetScrollRange () {
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
		HWND hWnd = null;
		if ( t0 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t0));
		}
		int nBar = (int) t1;
		int nMinPos = (int) t2;
		int nMaxPos = (int) t3;
		BOOL bRedraw = new BOOL (t4);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.SetScrollRange (hWnd, nBar, nMinPos, nMaxPos, bRedraw);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}