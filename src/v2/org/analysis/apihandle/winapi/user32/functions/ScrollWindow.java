/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: ScrollWindow.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class ScrollWindow extends User32API {
	public ScrollWindow () {
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
		HWND hWnd = null;
		if ( t0 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t0));
		}
		int XAmount = (int) t1;
		int YAmount = (int) t2;
		RECT lpRect = null;
		if ( t3 != 0L) {
			lpRect = new RECT ();
			lpRect.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			lpRect.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			lpRect.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			lpRect.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
		}
		RECT lpClipRect = null;
		if ( t4 != 0L) {
			lpClipRect = new RECT ();
			lpClipRect.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
			lpClipRect.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
			lpClipRect.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
			lpClipRect.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.ScrollWindow (hWnd, XAmount, YAmount, lpRect, lpClipRect);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}