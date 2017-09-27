/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetWindowPlacement.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.structures.WINDOWPLACEMENT;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.RECT;
 
public class GetWindowPlacement extends User32API {
	public GetWindowPlacement () {
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
		WINDOWPLACEMENT lpwndpl = null;
		if ( t1 != 0L) {
			lpwndpl = new WINDOWPLACEMENT ();
			lpwndpl.length = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpwndpl.flags = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpwndpl.showCmd = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpwndpl.ptMinPosition = new POINT ();
			// Nested Structure
			lpwndpl.ptMinPosition.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue();
			lpwndpl.ptMinPosition.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpwndpl.ptMaxPosition = new POINT ();
			// Nested Structure
			lpwndpl.ptMaxPosition.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue();
			lpwndpl.ptMaxPosition.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpwndpl.rcNormalPosition = new RECT ();
			// Nested Structure
			lpwndpl.rcNormalPosition.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue();
			lpwndpl.rcNormalPosition.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpwndpl.rcNormalPosition.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpwndpl.rcNormalPosition.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetWindowPlacement (hWnd, lpwndpl);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}