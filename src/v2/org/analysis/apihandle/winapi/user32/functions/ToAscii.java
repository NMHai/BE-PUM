/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: ToAscii.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.ShortByReference;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class ToAscii extends User32API {
	public ToAscii () {
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
		UINT uVirtKey = new UINT (t0);
		UINT uScanCode = new UINT (t1);
		byte lpKeyState = (byte) t2;
		ShortByReference lpChar = new ShortByReference ((short) t3);
		UINT uFlags = new UINT (t4);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.ToAscii (uVirtKey, uScanCode, lpKeyState, lpChar, uFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t3, new LongValue(lpChar.getValue()));

		

	}
}