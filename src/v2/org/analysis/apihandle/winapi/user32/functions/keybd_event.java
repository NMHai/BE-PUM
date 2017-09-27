/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: keybd_event.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
 
public class keybd_event extends User32API {
	public keybd_event () {
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
		BYTE bVk = new BYTE (t0);
		BYTE bScan = new BYTE (t1);
		DWORD dwFlags = new DWORD (t2);
		ULONG_PTR dwExtraInfo = new ULONG_PTR (t3);

		// Step 3: call API function
		User32DLL.INSTANCE.keybd_event (bVk, bScan, dwFlags, dwExtraInfo);
		
		// Step 4: update environment (memory & eax register)

	}
}