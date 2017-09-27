/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetWindowModuleFileName.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetWindowModuleFileName extends User32API {
	public GetWindowModuleFileName () {
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
		HWND hwnd = null;
		if ( t0 != 0L ) {
			hwnd = new HWND ();
			hwnd.setPointer(new Pointer(t0));
		}
		String lpszFileName = null;
		if ( t1 != 0L ) lpszFileName = memory.getText(this, t1);
		UINT cchFileNameMax = new UINT (t2);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetWindowModuleFileName (hwnd, lpszFileName, cchFileNameMax);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t1, new String(lpszFileName));
	}
}