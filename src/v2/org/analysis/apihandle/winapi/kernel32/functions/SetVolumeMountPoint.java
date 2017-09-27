/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetVolumeMountPoint.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetVolumeMountPoint extends Kernel32API {
	public SetVolumeMountPoint () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String lpszVolumeMountPoint = null;
		if ( t0 != 0L ) lpszVolumeMountPoint = memory.getText(this, t0);
		String lpszVolumeName = null;
		if ( t1 != 0L ) lpszVolumeName = memory.getText(this, t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetVolumeMountPoint (lpszVolumeMountPoint, lpszVolumeName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}