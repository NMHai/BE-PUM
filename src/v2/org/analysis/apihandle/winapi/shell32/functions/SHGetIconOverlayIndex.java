/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHGetIconOverlayIndex.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class SHGetIconOverlayIndex extends Shell32API {
	public SHGetIconOverlayIndex () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String pszIconPath = null;
		if ( t0 != 0L ) pszIconPath = memory.getText(this, t0);
		int iIconIndex = (int) t1;

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.SHGetIconOverlayIndex (pszIconPath, iIconIndex);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}