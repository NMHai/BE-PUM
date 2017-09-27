/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetUpdateRect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetUpdateRect extends User32API {
	public GetUpdateRect () {
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
		RECT lpRect = new RECT ();
		BOOL bErase = new BOOL (t2);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetUpdateRect (hWnd, lpRect, bErase);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpRect.left));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpRect.top));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpRect.right));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpRect.bottom));
		t1 += 4;

	}
}