/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: DuplicateIcon.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HICON;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class DuplicateIcon extends Shell32API {
	public DuplicateIcon () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HINSTANCE hInst = null;
		if ( t0 != 0L ) {
			hInst = new HINSTANCE ();
			hInst.setPointer(new Pointer(t0));
		}
		HICON hIcon = null;
		if ( t1 != 0L ) {
			hIcon = new HICON ();
			hIcon.setPointer(new Pointer(t1));
		}

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.DuplicateIcon (hInst, hIcon);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}