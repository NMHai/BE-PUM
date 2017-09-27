/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: mouse_event.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
 
public class mouse_event extends User32API {
	public mouse_event () {
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
		DWORD dwFlags = new DWORD (t0);
		DWORD dx = new DWORD (t1);
		DWORD dy = new DWORD (t2);
		DWORD dwData = new DWORD (t3);
		ULONG_PTR dwExtraInfo = new ULONG_PTR (t4);

		// Step 3: call API function
		User32DLL.INSTANCE.mouse_event (dwFlags, dx, dy, dwData, dwExtraInfo);
		
		// Step 4: update environment (memory & eax register)

	}
}