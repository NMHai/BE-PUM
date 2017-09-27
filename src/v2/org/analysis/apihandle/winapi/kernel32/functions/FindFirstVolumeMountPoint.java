/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FindFirstVolumeMountPoint.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class FindFirstVolumeMountPoint extends Kernel32API {
	public FindFirstVolumeMountPoint () {
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
		String lpszRootPathName = null;
		if ( t0 != 0L ) lpszRootPathName = memory.getText(this, t0);
		char[] lpszVolumeMountPoint = null;
		if ( t1 != 0L ) lpszVolumeMountPoint = new char[(int) t2];
		for (int i = 0; i < lpszVolumeMountPoint.length; i++) {
			lpszVolumeMountPoint [i] = (char) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		DWORD cchBufferLength = new DWORD (t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.FindFirstVolumeMountPoint (lpszRootPathName, lpszVolumeMountPoint, cchBufferLength);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < lpszVolumeMountPoint.length; i++) {
			memory.setByteMemoryValue (t1, new LongValue(lpszVolumeMountPoint [i]));
			t1 += 1;
		}
	}
}