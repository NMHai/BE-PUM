/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: LoadImage.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class LoadImage extends User32API {
	public LoadImage () {
		super();
		NUM_OF_PARMS = 6;
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
		
		// Step 2: type conversion from C++ to Java
		HINSTANCE hinst = null;
		if ( t0 != 0L ) {
			hinst = new HINSTANCE ();
			hinst.setPointer(new Pointer(t0));
		}
		String lpszName = null;
		if ( t1 != 0L ) lpszName = memory.getText(this, t1);
		UINT uType = new UINT (t2);
		int cxDesired = (int) t3;
		int cyDesired = (int) t4;
		UINT fuLoad = new UINT (t5);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.LoadImage (hinst, lpszName, uType, cxDesired, cyDesired, fuLoad);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}