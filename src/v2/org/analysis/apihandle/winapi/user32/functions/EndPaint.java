/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: EndPaint.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.structures.PAINTSTRUCT;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class EndPaint extends User32API {
	public EndPaint () {
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
		PAINTSTRUCT lpPaint = null;
		if ( t1 != 0L) {
			lpPaint = new PAINTSTRUCT ();
			lpPaint.hdc = new HDC (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue()));
			t1 += 4;
			lpPaint.fErase = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpPaint.rcPaint = new RECT ();
			// Nested Structure
			lpPaint.rcPaint.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue();
			lpPaint.rcPaint.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpPaint.rcPaint.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpPaint.rcPaint.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpPaint.fRestore = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpPaint.fIncUpdate = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			for (int i = 0; i < lpPaint.rgbReserved.length; i++) {
				lpPaint.rgbReserved [i] = new BYTE (((LongValue) memory.getByteMemoryValue (t1)).getValue());
				t1 += 1;
			}
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.EndPaint (hWnd, lpPaint);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}