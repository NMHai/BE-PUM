/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegConnectRegistry.java
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
 
public class RegConnectRegistry extends Advapi32API {
	public RegConnectRegistry () {
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
		String lpMachineName = null;
		if ( t0 != 0L ) lpMachineName = memory.getText(this, t0);
		HKEY hKey = null;
		if ( t1 != 0L ) {
			hKey = new HKEY ();
			hKey.setPointer(new Pointer(t1));
		}
		HANDLEByReference phkResult = new HANDLEByReference (new HANDLE(new Pointer(t2)));

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegConnectRegistry (lpMachineName, hKey, phkResult);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t2, new LongValue(Pointer.nativeValue(phkResult.getValue().getPointer())));

		

	}
}