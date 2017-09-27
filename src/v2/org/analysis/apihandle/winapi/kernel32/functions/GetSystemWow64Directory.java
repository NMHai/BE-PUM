/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetSystemWow64Directory.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetSystemWow64Directory extends Kernel32API {
	public GetSystemWow64Directory () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		char[] lpBuffer = null;
		if ( t0 != 0L ) lpBuffer = new char[(int) t1];
		for (int i = 0; i < lpBuffer.length; i++) {
			lpBuffer [i] = (char) ((LongValue) memory.getByteMemoryValue (t0)).getValue();
			t0 += 1;
		}
		UINT uSize = new UINT (t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetSystemWow64Directory (lpBuffer, uSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		for (int i = 0; i < lpBuffer.length; i++) {
			memory.setByteMemoryValue (t0, new LongValue(lpBuffer [i]));
			t0 += 1;
		}
	}
}