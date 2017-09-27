/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: SetServiceBits.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetServiceBits extends Advapi32API {
	public SetServiceBits () {
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
		HANDLE hServiceStatus = null;
		if ( t0 != 0L ) {
			hServiceStatus = new HANDLE ();
			hServiceStatus.setPointer(new Pointer(t0));
		}
		DWORD dwServiceBits = new DWORD (t1);
		BOOL bSetBitsOn = new BOOL (t2);
		BOOL bUpdateImmediately = new BOOL (t3);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.SetServiceBits (hServiceStatus, dwServiceBits, bSetBitsOn, bUpdateImmediately);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}