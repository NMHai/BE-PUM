/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SendMessageTimeout.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class SendMessageTimeout extends User32API {
	public SendMessageTimeout () {
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
		HWND hWnd = null;
		if ( t0 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t0));
		}
		UINT Msg = new UINT (t1);
		WPARAM wParam = new WPARAM (t2);
		LPARAM lParam = new LPARAM (t3);
		UINT fuFlags = new UINT (t4);
		UINT uTimeout = new UINT (t5);
		IntByReference lpdwResult = new IntByReference ((int) t6);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.SendMessageTimeout (hWnd, Msg, wParam, lParam, fuFlags, uTimeout, lpdwResult);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t6, new LongValue(lpdwResult.getValue()));

		

	}
}