/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetMenuItemRect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetMenuItemRect extends User32API {
	public GetMenuItemRect () {
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
		HWND hWnd = null;
		if ( t0 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t0));
		}
		HMENU hMenu = null;
		if ( t1 != 0L ) {
			hMenu = new HMENU ();
			hMenu.setPointer(new Pointer(t1));
		}
		UINT uItem = new UINT (t2);
		RECT lprcItem = new RECT ();

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetMenuItemRect (hWnd, hMenu, uItem, lprcItem);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		memory.setDoubleWordMemoryValue (t3, new LongValue(lprcItem.left));
		t3 += 4;
		memory.setDoubleWordMemoryValue (t3, new LongValue(lprcItem.top));
		t3 += 4;
		memory.setDoubleWordMemoryValue (t3, new LongValue(lprcItem.right));
		t3 += 4;
		memory.setDoubleWordMemoryValue (t3, new LongValue(lprcItem.bottom));
		t3 += 4;

	}
}