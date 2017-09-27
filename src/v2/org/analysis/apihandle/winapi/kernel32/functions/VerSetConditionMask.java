/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: VerSetConditionMask.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.ULONGLONG;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class VerSetConditionMask extends Kernel32API {
	public VerSetConditionMask () {
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
		ULONGLONG dwlConditionMask = new ULONGLONG (t0);
		DWORD dwTypeBitMask = new DWORD (t1);
		BYTE dwConditionMask = new BYTE (t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.VerSetConditionMask (dwlConditionMask, dwTypeBitMask, dwConditionMask);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}