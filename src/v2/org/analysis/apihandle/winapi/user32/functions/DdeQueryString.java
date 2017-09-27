/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DdeQueryString.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DdeQueryString extends User32API {
	public DdeQueryString () {
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
		DWORD idInst = new DWORD (t0);
		HANDLE hsz = null;
		if ( t1 != 0L ) {
			hsz = new HANDLE ();
			hsz.setPointer(new Pointer(t1));
		}
		char[] psz = null;
		if ( t2 != 0L ) psz = new char[(int) t3];
		for (int i = 0; i < psz.length; i++) {
			psz [i] = (char) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		DWORD cchMax = new DWORD (t3);
		int iCodePage = (int) t4;

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DdeQueryString (idInst, hsz, psz, cchMax, iCodePage);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < psz.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(psz [i]));
			t2 += 1;
		}
	}
}