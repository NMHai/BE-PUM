/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetWindowRgnBox.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetWindowRgnBox extends User32API {
	public GetWindowRgnBox () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HWND hWnd = null;
		if ( t0 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t0));
		}
		RECT lprc = new RECT ();

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetWindowRgnBox (hWnd, lprc);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lprc.left));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lprc.top));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lprc.right));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lprc.bottom));
		t1 += 4;

	}
}