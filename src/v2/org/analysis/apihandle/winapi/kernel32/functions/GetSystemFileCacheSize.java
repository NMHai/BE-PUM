/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetSystemFileCacheSize.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetSystemFileCacheSize extends Kernel32API {
	public GetSystemFileCacheSize () {
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
		IntByReference lpMinimumFileCacheSize = new IntByReference ((int) t0);
		IntByReference lpMaximumFileCacheSize = new IntByReference ((int) t1);
		IntByReference lpFlags = new IntByReference ((int) t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetSystemFileCacheSize (lpMinimumFileCacheSize, lpMaximumFileCacheSize, lpFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t0, new LongValue(lpMinimumFileCacheSize.getValue()));

		
		memory.setDoubleWordMemoryValue(t1, new LongValue(lpMaximumFileCacheSize.getValue()));

		
		memory.setDoubleWordMemoryValue(t2, new LongValue(lpFlags.getValue()));

		

	}
}