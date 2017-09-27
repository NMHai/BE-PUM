/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: OpenDesktop.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class OpenDesktop extends User32API {
	public OpenDesktop () {
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
		String lpszDesktop = null;
		if ( t0 != 0L ) lpszDesktop = memory.getText(this, t0);
		DWORD dwFlags = new DWORD (t1);
		BOOL fInherit = new BOOL (t2);
		int dwDesiredAccess = (int) t3;

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.OpenDesktop (lpszDesktop, dwFlags, fInherit, dwDesiredAccess);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}