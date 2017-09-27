/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegCreateKey.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.platform.win32.WinReg.HKEY;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegCreateKey extends Advapi32API {
	public RegCreateKey () {
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
		String lpSubKey = null;
		if ( t1 != 0L ) {
			lpSubKey = memory.getText(this, t1);
		}
		
		String handleKey = "UNKNOWN";
		switch (Long.toHexString(t0)) {
			case "80000000":
				handleKey = "HKEY_CLASSES_ROOT";
				break;
			case "80000001":
				handleKey = "HKEY_CURRENT_USER";
				break;
			case "80000002":
				handleKey = "HKEY_LOCAL_MACHINE";
				break;
			case "80000003":
				handleKey = "HKEY_USERS";
				break;
			case "80000005":
				handleKey = "HKEY_CURRENT_CONFIG";
				break;
			default: 
				break;
		}		
		
		System.out.println("Handle Key:" + handleKey + ", Subkey:" + lpSubKey);		
		
		HANDLEByReference phkResult = new HANDLEByReference (new HANDLE(new Pointer(t2)));

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegCreateKey (hKey, lpSubKey, phkResult);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t2, new LongValue(Pointer.nativeValue(phkResult.getValue().getPointer())));

		

	}
}