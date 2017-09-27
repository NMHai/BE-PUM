/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CreateIconFromResourceEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateIconFromResourceEx extends User32API {
	public CreateIconFromResourceEx () {
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
		byte pbIconBits = (byte) t0;
		DWORD cbIconBits = new DWORD (t1);
		BOOL fIcon = new BOOL (t2);
		DWORD dwVersion = new DWORD (t3);
		int cxDesired = (int) t4;
		int cyDesired = (int) t5;
		UINT uFlags = new UINT (t6);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CreateIconFromResourceEx (pbIconBits, cbIconBits, fIcon, dwVersion, cxDesired, cyDesired, uFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}