/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegDeleteValue.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinReg.HKEY;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegDeleteValue extends Advapi32API {
	public RegDeleteValue () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HKEY hKey = null;
		if ( t0 != 0L ) {
			hKey = new HKEY ();
			hKey.setPointer(new Pointer(t0));
		}
		String lpValueName = null;
		if ( t1 != 0L ) lpValueName = memory.getText(this, t1);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegDeleteValue (hKey, lpValueName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}