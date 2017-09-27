/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegLoadAppKey.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegLoadAppKey extends Advapi32API {
	public RegLoadAppKey () {
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
		String lpFile = null;
		if ( t0 != 0L ) lpFile = memory.getText(this, t0);
		HANDLEByReference phkResult = new HANDLEByReference (new HANDLE(new Pointer(t1)));
		int samDesired = (int) t2;
		DWORD dwOptions = new DWORD (t3);
		DWORD Reserved = new DWORD (t4);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegLoadAppKey (lpFile, phkResult, samDesired, dwOptions, Reserved);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(Pointer.nativeValue(phkResult.getValue().getPointer())));

		

	}
}