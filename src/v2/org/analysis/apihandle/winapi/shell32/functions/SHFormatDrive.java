/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHFormatDrive.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class SHFormatDrive extends Shell32API {
	public SHFormatDrive () {
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
		UINT drive = new UINT (t1);
		UINT fmtID = new UINT (t2);
		UINT options = new UINT (t3);

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.SHFormatDrive (hwnd, drive, fmtID, options);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}