/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CopyImage.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CopyImage extends User32API {
	public CopyImage () {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hImage = null;
		if ( t0 != 0L ) {
			hImage = new HANDLE ();
			hImage.setPointer(new Pointer(t0));
		}
		UINT uType = new UINT (t1);
		int cxDesired = (int) t2;
		int cyDesired = (int) t3;
		UINT fuFlags = new UINT (t4);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CopyImage (hImage, uType, cxDesired, cyDesired, fuFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}