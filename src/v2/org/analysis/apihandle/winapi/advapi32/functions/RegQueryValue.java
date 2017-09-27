/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegQueryValue.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegQueryValue extends Advapi32API {
	public RegQueryValue () {
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
		HKEY hKey = null;
		if ( t0 != 0L ) {
			hKey = new HKEY ();
			hKey.setPointer(new Pointer(t0));
		}
		String lpSubKey = null;
		if ( t1 != 0L ) lpSubKey = memory.getText(this, t1);
		String lpValue = null;
		if ( t2 != 0L ) lpValue = memory.getText(this, t2);
		IntByReference lpcbValue = new IntByReference ((int) t3);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegQueryValue (hKey, lpSubKey, lpValue, lpcbValue);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t2, new String(lpValue));
	}
}