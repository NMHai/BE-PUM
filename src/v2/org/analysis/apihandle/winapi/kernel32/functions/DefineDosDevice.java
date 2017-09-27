/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: DefineDosDevice.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class DefineDosDevice extends Kernel32API {
	public DefineDosDevice () {
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
		DWORD dwFlags = new DWORD (t0);
		String lpDeviceName = null;
		if ( t1 != 0L ) lpDeviceName = memory.getText(this, t1);
		String lpTargetPath = null;
		if ( t2 != 0L ) lpTargetPath = memory.getText(this, t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.DefineDosDevice (dwFlags, lpDeviceName, lpTargetPath);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}