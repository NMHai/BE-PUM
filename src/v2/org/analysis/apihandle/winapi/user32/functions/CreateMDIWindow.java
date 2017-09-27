/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CreateMDIWindow.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateMDIWindow extends User32API {
	public CreateMDIWindow () {
		super();
		NUM_OF_PARMS = 10;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		long t6 = this.params.get(6);
		long t7 = this.params.get(7);
		long t8 = this.params.get(8);
		long t9 = this.params.get(9);
		
		// Step 2: type conversion from C++ to Java
		String lpClassName = null;
		if ( t0 != 0L ) lpClassName = memory.getText(this, t0);
		String lpWindowName = null;
		if ( t1 != 0L ) lpWindowName = memory.getText(this, t1);
		DWORD dwStyle = new DWORD (t2);
		int X = (int) t3;
		int Y = (int) t4;
		int nWidth = (int) t5;
		int nHeight = (int) t6;
		HWND hWndParent = null;
		if ( t7 != 0L ) {
			hWndParent = new HWND ();
			hWndParent.setPointer(new Pointer(t7));
		}
		HINSTANCE hInstance = null;
		if ( t8 != 0L ) {
			hInstance = new HINSTANCE ();
			hInstance.setPointer(new Pointer(t8));
		}
		LPARAM lParam = new LPARAM (t9);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CreateMDIWindow (lpClassName, lpWindowName, dwStyle, X, Y, nWidth, nHeight, hWndParent, hInstance, lParam);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}