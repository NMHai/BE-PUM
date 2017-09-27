/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DrawAnimatedRects.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DrawAnimatedRects extends User32API {
	public DrawAnimatedRects () {
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
		HWND hwnd = null;
		if ( t0 != 0L ) {
			hwnd = new HWND ();
			hwnd.setPointer(new Pointer(t0));
		}
		int idAni = (int) t1;
		RECT lprcFrom = null;
		if ( t2 != 0L) {
			lprcFrom = new RECT ();
			lprcFrom.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lprcFrom.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lprcFrom.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lprcFrom.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
		}
		RECT lprcTo = null;
		if ( t3 != 0L) {
			lprcTo = new RECT ();
			lprcTo.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			lprcTo.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			lprcTo.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			lprcTo.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DrawAnimatedRects (hwnd, idAni, lprcFrom, lprcTo);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}