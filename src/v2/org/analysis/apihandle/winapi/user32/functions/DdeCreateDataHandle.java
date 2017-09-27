/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DdeCreateDataHandle.java
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
 
public class DdeCreateDataHandle extends User32API {
	public DdeCreateDataHandle () {
		super();
		NUM_OF_PARMS = 7;
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
		long t6 = this.params.get(6);
		
		// Step 2: type conversion from C++ to Java
		DWORD idInst = new DWORD (t0);
		byte pSrc = (byte) t1;
		DWORD cb = new DWORD (t2);
		DWORD cbOff = new DWORD (t3);
		HANDLE hszItem = null;
		if ( t4 != 0L ) {
			hszItem = new HANDLE ();
			hszItem.setPointer(new Pointer(t4));
		}
		UINT wFmt = new UINT (t5);
		UINT afCmd = new UINT (t6);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DdeCreateDataHandle (idInst, pSrc, cb, cbOff, hszItem, wFmt, afCmd);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}