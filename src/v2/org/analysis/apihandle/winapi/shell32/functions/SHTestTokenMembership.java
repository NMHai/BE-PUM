/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHTestTokenMembership.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;
 
public class SHTestTokenMembership extends Shell32API {
	public SHTestTokenMembership () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hToken = null;
		if ( t0 != 0L ) {
			hToken = new HANDLE ();
			hToken.setPointer(new Pointer(t0));
		}
		ULONG ulRID = new ULONG ((int) t1);

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.SHTestTokenMembership (hToken, ulRID);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}