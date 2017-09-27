/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DrawCaption.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DrawCaption extends User32API {
	public DrawCaption () {
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
		HWND hwnd = null;
		if ( t0 != 0L ) {
			hwnd = new HWND ();
			hwnd.setPointer(new Pointer(t0));
		}
		HDC hdc = null;
		if ( t1 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t1));
		}
		RECT lprc = null;
		if ( t2 != 0L) {
			lprc = new RECT ();
			lprc.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lprc.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lprc.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lprc.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
		}
		UINT uFlags = new UINT (t3);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DrawCaption (hwnd, hdc, lprc, uFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}