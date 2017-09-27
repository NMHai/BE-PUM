/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: ToUnicodeEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class ToUnicodeEx extends User32API {
	public ToUnicodeEx () {
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
		UINT wVirtKey = new UINT (t0);
		UINT wScanCode = new UINT (t1);
		byte lpKeyState = (byte) t2;
		CHARByReference pwszBuff = new CHARByReference (new CHAR(t3));
		int cchBuff = (int) t4;
		UINT wFlags = new UINT (t5);
		HANDLE dwhkl = null;
		if ( t6 != 0L ) {
			dwhkl = new HANDLE ();
			dwhkl.setPointer(new Pointer(t6));
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.ToUnicodeEx (wVirtKey, wScanCode, lpKeyState, pwszBuff, cchBuff, wFlags, dwhkl);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t3, new LongValue(pwszBuff.getValue().longValue()));

		

	}
}