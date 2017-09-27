/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHUpdateImage.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
 
public class SHUpdateImage extends Shell32API {
	public SHUpdateImage () {
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
		String pszHashItem = null;
		if ( t0 != 0L ) pszHashItem = memory.getText(this, t0);
		int iIndex = (int) t1;
		UINT uFlags = new UINT (t2);
		int iImageIndex = (int) t3;

		// Step 3: call API function
		Shell32DLL.INSTANCE.SHUpdateImage (pszHashItem, iIndex, uFlags, iImageIndex);
		
		// Step 4: update environment (memory & eax register)

	}
}