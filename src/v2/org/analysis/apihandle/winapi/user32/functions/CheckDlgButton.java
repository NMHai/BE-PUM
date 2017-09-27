/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CheckDlgButton.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CheckDlgButton extends User32API {
	public CheckDlgButton () {
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
		HWND hDlg = null;
		if ( t0 != 0L ) {
			hDlg = new HWND ();
			hDlg.setPointer(new Pointer(t0));
		}
		int nIDButton = (int) t1;
		UINT uCheck = new UINT (t2);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CheckDlgButton (hDlg, nIDButton, uCheck);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}