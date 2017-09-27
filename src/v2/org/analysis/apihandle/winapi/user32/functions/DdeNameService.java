/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DdeNameService.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DdeNameService extends User32API {
	public DdeNameService () {
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
		DWORD idInst = new DWORD (t0);
		HANDLE hsz1 = null;
		if ( t1 != 0L ) {
			hsz1 = new HANDLE ();
			hsz1.setPointer(new Pointer(t1));
		}
		HANDLE hsz2 = null;
		if ( t2 != 0L ) {
			hsz2 = new HANDLE ();
			hsz2.setPointer(new Pointer(t2));
		}
		UINT afCmd = new UINT (t3);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DdeNameService (idInst, hsz1, hsz2, afCmd);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}