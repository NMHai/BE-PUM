/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegSetValue.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinReg.HKEY;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegSetValue extends Advapi32API {
	public RegSetValue () {
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
		String lpSubKey = null;
		if ( t1 != 0L ) lpSubKey = memory.getText(this, t1);
		DWORD dwType = new DWORD (t2);
		String lpData = null;
		if ( t3 != 0L ) lpData = memory.getText(this, t3);
		DWORD cbData = new DWORD (t4);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegSetValue (hKey, lpSubKey, dwType, lpData, cbData);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}