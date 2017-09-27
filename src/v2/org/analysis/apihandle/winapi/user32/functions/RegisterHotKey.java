/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: RegisterHotKey.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegisterHotKey extends User32API {
	public RegisterHotKey () {
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
		HWND hWnd = null;
		if ( t0 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t0));
		}
		int id = (int) t1;
		UINT fsModifiers = new UINT (t2);
		UINT vk = new UINT (t3);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.RegisterHotKey (hWnd, id, fsModifiers, vk);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}