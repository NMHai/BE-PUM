/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegLoadMUIString.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegLoadMUIString extends Advapi32API {
	public RegLoadMUIString () {
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
		HKEY hKey = null;
		if ( t0 != 0L ) {
			hKey = new HKEY ();
			hKey.setPointer(new Pointer(t0));
		}
		String pszValue = null;
		if ( t1 != 0L ) pszValue = memory.getText(this, t1);
		String pszOutBuf = null;
		if ( t2 != 0L ) pszOutBuf = memory.getText(this, t2);
		DWORD cbOutBuf = new DWORD (t3);
		IntByReference pcbData = new IntByReference ((int) t4);
		DWORD Flags = new DWORD (t5);
		String pszDirectory = null;
		if ( t6 != 0L ) pszDirectory = memory.getText(this, t6);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegLoadMUIString (hKey, pszValue, pszOutBuf, cbOutBuf, pcbData, Flags, pszDirectory);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t2, new String(pszOutBuf));		memory.setDoubleWordMemoryValue(t4, new LongValue(pcbData.getValue()));

		

	}
}