/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: ExtractIcon.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class ExtractIcon extends Shell32API {
	public ExtractIcon () {
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
		HINSTANCE hInst = null;
		if ( t0 != 0L ) {
			hInst = new HINSTANCE ();
			hInst.setPointer(new Pointer(t0));
		}
		String lpszExeFileName = null;
		if ( t1 != 0L ) lpszExeFileName = memory.getText(this, t1);
		UINT nIconIndex = new UINT (t2);

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.ExtractIcon (hInst, lpszExeFileName, nIconIndex);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}