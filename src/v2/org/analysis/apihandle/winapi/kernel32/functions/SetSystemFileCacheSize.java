/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetSystemFileCacheSize.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetSystemFileCacheSize extends Kernel32API {
	public SetSystemFileCacheSize () {
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
		SIZE_T MinimumFileCacheSize = new SIZE_T (t0);
		SIZE_T MaximumFileCacheSize = new SIZE_T (t1);
		DWORD Flags = new DWORD (t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetSystemFileCacheSize (MinimumFileCacheSize, MaximumFileCacheSize, Flags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}