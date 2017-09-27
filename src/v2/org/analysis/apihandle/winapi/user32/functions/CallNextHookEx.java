/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CallNextHookEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CallNextHookEx extends User32API {
	public CallNextHookEx () {
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
		HHOOK hhk = null;
		if ( t0 != 0L ) {
			hhk = new HHOOK ();
			hhk.setPointer(new Pointer(t0));
		}
		int nCode = (int) t1;
		WPARAM wParam = new WPARAM (t2);
		LPARAM lParam = new LPARAM (t3);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CallNextHookEx (hhk, nCode, wParam, lParam);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}