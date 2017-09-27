/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHGetNewLinkInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class SHGetNewLinkInfo extends Shell32API {
	public SHGetNewLinkInfo () {
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
		String pszLinkTo = null;
		if ( t0 != 0L ) pszLinkTo = memory.getText(this, t0);
		String pszDir = null;
		if ( t1 != 0L ) pszDir = memory.getText(this, t1);
		String pszName = null;
		if ( t2 != 0L ) pszName = memory.getText(this, t2);
		IntByReference pfMustCopy = new IntByReference ((int) t3);
		UINT uFlags = new UINT (t4);

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.SHGetNewLinkInfo (pszLinkTo, pszDir, pszName, pfMustCopy, uFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t2, new String(pszName));		memory.setDoubleWordMemoryValue(t3, new LongValue(pfMustCopy.getValue()));

		

	}
}