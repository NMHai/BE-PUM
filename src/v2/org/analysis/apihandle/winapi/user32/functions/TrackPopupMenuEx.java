/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: TrackPopupMenuEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.TPMPARAMS;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class TrackPopupMenuEx extends User32API {
	public TrackPopupMenuEx () {
		super();
		NUM_OF_PARMS = 6;
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
		
		// Step 2: type conversion from C++ to Java
		HMENU hmenu = null;
		if ( t0 != 0L ) {
			hmenu = new HMENU ();
			hmenu.setPointer(new Pointer(t0));
		}
		UINT fuFlags = new UINT (t1);
		int x = (int) t2;
		int y = (int) t3;
		HWND hwnd = null;
		if ( t4 != 0L ) {
			hwnd = new HWND ();
			hwnd.setPointer(new Pointer(t4));
		}
		TPMPARAMS lptpm = null;
		if ( t5 != 0L) {
			lptpm = new TPMPARAMS ();
			lptpm.cbSize = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t5)).getValue());
			t5 += 4;
			lptpm.rcExclude = new RECT ();
			// Nested Structure
			lptpm.rcExclude.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t5 += 0)).getValue();
			lptpm.rcExclude.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t5 += 4)).getValue();
			lptpm.rcExclude.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t5 += 4)).getValue();
			lptpm.rcExclude.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t5 += 4)).getValue();
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.TrackPopupMenuEx (hmenu, fuFlags, x, y, hwnd, lptpm);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}