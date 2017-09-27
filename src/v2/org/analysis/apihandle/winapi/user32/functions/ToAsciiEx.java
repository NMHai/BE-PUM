/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: ToAsciiEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class ToAsciiEx extends User32API {
	public ToAsciiEx () {
		super();
		NUM_OF_PARMS = 6;
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
		
		// Step 2: type conversion from C++ to Java
		UINT uVirtKey = new UINT (t0);
		UINT uScanCode = new UINT (t1);
		byte lpKeyState = (byte) t2;
		short[] lpChar = null;
		if ( t3 != 0L ) lpChar = new short[(int) t4];
		for (int i = 0; i < lpChar.length; i++) {
			lpChar [i] = (short) ((LongValue) memory.getWordMemoryValue (t3)).getValue();
			t3 += 2;
		}
		UINT uFlags = new UINT (t4);
		HANDLE dwhkl = null;
		if ( t5 != 0L ) {
			dwhkl = new HANDLE ();
			dwhkl.setPointer(new Pointer(t5));
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.ToAsciiEx (uVirtKey, uScanCode, lpKeyState, lpChar, uFlags, dwhkl);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		for (int i = 0; i < lpChar.length; i++) {
			memory.setWordMemoryValue (t3, new LongValue(lpChar [i]));
			t3 += 2;
		}
	}
}