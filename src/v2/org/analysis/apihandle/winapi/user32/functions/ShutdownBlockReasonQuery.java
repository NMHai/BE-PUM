/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: ShutdownBlockReasonQuery.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class ShutdownBlockReasonQuery extends User32API {
	public ShutdownBlockReasonQuery () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		HWND hWnd = null;
		if ( t0 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t0));
		}
		char[] pwszBuff = null;
		if ( t1 != 0L ) pwszBuff = new char[(int) t2];
		for (int i = 0; i < pwszBuff.length; i++) {
			pwszBuff [i] = (char) ((LongValue) memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
		}
		IntByReference pcchBuff = new IntByReference ((int) t2);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.ShutdownBlockReasonQuery (hWnd, pwszBuff, pcchBuff);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < pwszBuff.length; i++) {
			memory.setWordMemoryValue (t1, new LongValue(pwszBuff [i]));
			t1 += 2;
		}
	}
}