/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetWindowInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinUser.WINDOWINFO;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetWindowInfo extends User32API {
	public GetWindowInfo () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HWND hwnd = null;
		if ( t0 != 0L ) {
			hwnd = new HWND ();
			hwnd.setPointer(new Pointer(t0));
		}
		WINDOWINFO pwi = null;
		if ( t1 != 0L) {
			pwi = new WINDOWINFO ();
			pwi.cbSize = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			pwi.rcWindow = new RECT ();
			// Nested Structure
			pwi.rcWindow.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue();
			pwi.rcWindow.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			pwi.rcWindow.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			pwi.rcWindow.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			pwi.rcClient = new RECT ();
			// Nested Structure
			pwi.rcClient.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue();
			pwi.rcClient.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			pwi.rcClient.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			pwi.rcClient.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			pwi.dwStyle = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			pwi.dwExStyle = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			pwi.dwWindowStatus = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			pwi.cxWindowBorders = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			pwi.cyWindowBorders = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			pwi.atomWindowType = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			pwi.wCreatorVersion = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetWindowInfo (hwnd, pwi);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}