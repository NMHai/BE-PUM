/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: TranslateAccelerator.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinUser.MSG;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class TranslateAccelerator extends User32API {
	public TranslateAccelerator () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		HWND hWnd = null;
		if ( t0 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t0));
		}
		HANDLE hAccTable = null;
		if ( t1 != 0L ) {
			hAccTable = new HANDLE ();
			hAccTable.setPointer(new Pointer(t1));
		}
		MSG lpMsg = null;
		if ( t2 != 0L) {
			lpMsg = new MSG ();
			lpMsg.hWnd = new HWND (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue()));
			t2 += 4;
			lpMsg.message = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lpMsg.wParam = new WPARAM (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lpMsg.lParam = new LPARAM (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lpMsg.time = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lpMsg.pt = new POINT ();
			// Nested Structure
			lpMsg.pt.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue();
			lpMsg.pt.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.TranslateAccelerator (hWnd, hAccTable, lpMsg);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}