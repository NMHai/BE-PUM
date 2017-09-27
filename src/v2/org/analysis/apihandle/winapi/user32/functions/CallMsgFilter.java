/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CallMsgFilter.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.MSG;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CallMsgFilter extends User32API {
	public CallMsgFilter () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		MSG lpMsg = null;
		if ( t0 != 0L) {
			lpMsg = new MSG ();
			lpMsg.hWnd = new HWND (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue()));
			t0 += 4;
			lpMsg.message = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			lpMsg.wParam = new WPARAM (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpMsg.lParam = new LPARAM (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpMsg.time = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			lpMsg.pt = new POINT ();
			// Nested Structure
			lpMsg.pt.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0 += 0)).getValue();
			lpMsg.pt.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0 += 4)).getValue();
		}
		int nCode = (int) t1;

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CallMsgFilter (lpMsg, nCode);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}