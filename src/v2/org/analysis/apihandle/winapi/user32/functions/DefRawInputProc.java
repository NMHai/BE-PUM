/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DefRawInputProc.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.PointerByReference;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DefRawInputProc extends User32API {
	public DefRawInputProc () {
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
		PointerByReference paRawInput = new PointerByReference (new Pointer(t0));
		int nInput = (int) t1;
		UINT cbSizeHeader = new UINT (t2);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DefRawInputProc (paRawInput, nInput, cbSizeHeader);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}