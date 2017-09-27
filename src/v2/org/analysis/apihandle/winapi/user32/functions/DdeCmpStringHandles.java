/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DdeCmpStringHandles.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DdeCmpStringHandles extends User32API {
	public DdeCmpStringHandles () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hsz1 = null;
		if ( t0 != 0L ) {
			hsz1 = new HANDLE ();
			hsz1.setPointer(new Pointer(t0));
		}
		HANDLE hsz2 = null;
		if ( t1 != 0L ) {
			hsz2 = new HANDLE ();
			hsz2.setPointer(new Pointer(t1));
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DdeCmpStringHandles (hsz1, hsz2);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}