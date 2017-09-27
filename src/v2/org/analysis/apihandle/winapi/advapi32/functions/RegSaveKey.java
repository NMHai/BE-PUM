/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegSaveKey.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinReg.HKEY;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegSaveKey extends Advapi32API {
	public RegSaveKey () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		HKEY hKey = null;
		if ( t0 != 0L ) {
			hKey = new HKEY ();
			hKey.setPointer(new Pointer(t0));
		}
		String lpFile = null;
		if ( t1 != 0L ) lpFile = memory.getText(this, t1);
		SECURITY_ATTRIBUTES lpSecurityAttributes = null;
		if ( t2 != 0L) {
			lpSecurityAttributes = new SECURITY_ATTRIBUTES ();
			lpSecurityAttributes.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lpSecurityAttributes.lpSecurityDescriptor = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lpSecurityAttributes.bInheritHandle = (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue() != 0L ) ? true : false;
			t2 += 4;
		}

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegSaveKey (hKey, lpFile, lpSecurityAttributes);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}