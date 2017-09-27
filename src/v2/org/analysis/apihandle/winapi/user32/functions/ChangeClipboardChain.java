/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: ChangeClipboardChain.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class ChangeClipboardChain extends User32API {
	public ChangeClipboardChain () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HWND hWndRemove = null;
		if ( t0 != 0L ) {
			hWndRemove = new HWND ();
			hWndRemove.setPointer(new Pointer(t0));
		}
		HWND hWndNewNext = null;
		if ( t1 != 0L ) {
			hWndNewNext = new HWND ();
			hWndNewNext.setPointer(new Pointer(t1));
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.ChangeClipboardChain (hWndRemove, hWndNewNext);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}