/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegCopyTree.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinReg.HKEY;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegCopyTree extends Advapi32API {
	public RegCopyTree () {
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
		HKEY hKeySrc = null;
		if ( t0 != 0L ) {
			hKeySrc = new HKEY ();
			hKeySrc.setPointer(new Pointer(t0));
		}
		String lpSubKey = null;
		if ( t1 != 0L ) lpSubKey = memory.getText(this, t1);
		HKEY hKeyDest = null;
		if ( t2 != 0L ) {
			hKeyDest = new HKEY ();
			hKeyDest.setPointer(new Pointer(t2));
		}

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegCopyTree (hKeySrc, lpSubKey, hKeyDest);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}