/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegNotifyChangeKeyValue.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinReg.HKEY;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegNotifyChangeKeyValue extends Advapi32API {
	public RegNotifyChangeKeyValue () {
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
		HKEY hKey = null;
		if ( t0 != 0L ) {
			hKey = new HKEY ();
			hKey.setPointer(new Pointer(t0));
		}
		BOOL bWatchSubtree = new BOOL (t1);
		DWORD dwNotifyFilter = new DWORD (t2);
		HANDLE hEvent = null;
		if ( t3 != 0L ) {
			hEvent = new HANDLE ();
			hEvent.setPointer(new Pointer(t3));
		}
		BOOL fAsynchronous = new BOOL (t4);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegNotifyChangeKeyValue (hKey, bWatchSubtree, dwNotifyFilter, hEvent, fAsynchronous);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}