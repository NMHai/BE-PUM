/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: TrackPopupMenu.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class TrackPopupMenu extends User32API {
	public TrackPopupMenu () {
		super();
		NUM_OF_PARMS = 7;
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
		
		// Step 2: type conversion from C++ to Java
		HMENU hMenu = null;
		if ( t0 != 0L ) {
			hMenu = new HMENU ();
			hMenu.setPointer(new Pointer(t0));
		}
		UINT uFlags = new UINT (t1);
		int x = (int) t2;
		int y = (int) t3;
		int nReserved = (int) t4;
		HWND hWnd = null;
		if ( t5 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t5));
		}
		RECT prcRect = null;
		if ( t6 != 0L) {
			prcRect = new RECT ();
			prcRect.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t6)).getValue();
			t6 += 4;
			prcRect.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t6)).getValue();
			t6 += 4;
			prcRect.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t6)).getValue();
			t6 += 4;
			prcRect.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t6)).getValue();
			t6 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.TrackPopupMenu (hMenu, uFlags, x, y, nReserved, hWnd, prcRect);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}