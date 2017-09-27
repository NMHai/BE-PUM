/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DeferWindowPos.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DeferWindowPos extends User32API {
	public DeferWindowPos () {
		super();
		NUM_OF_PARMS = 8;
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
		
		// Step 2: type conversion from C++ to Java
		HANDLE hWinPosInfo = null;
		if ( t0 != 0L ) {
			hWinPosInfo = new HANDLE ();
			hWinPosInfo.setPointer(new Pointer(t0));
		}
		HWND hWnd = null;
		if ( t1 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t1));
		}
		HWND hWndInsertAfter = null;
		if ( t2 != 0L ) {
			hWndInsertAfter = new HWND ();
			hWndInsertAfter.setPointer(new Pointer(t2));
		}
		int x = (int) t3;
		int y = (int) t4;
		int cx = (int) t5;
		int cy = (int) t6;
		UINT uFlags = new UINT (t7);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DeferWindowPos (hWinPosInfo, hWnd, hWndInsertAfter, x, y, cx, cy, uFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}