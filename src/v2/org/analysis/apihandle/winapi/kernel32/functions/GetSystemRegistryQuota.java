/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetSystemRegistryQuota.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetSystemRegistryQuota extends Kernel32API {
	public GetSystemRegistryQuota () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		IntByReference pdwQuotaAllowed = new IntByReference ((int) t0);
		IntByReference pdwQuotaUsed = new IntByReference ((int) t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetSystemRegistryQuota (pdwQuotaAllowed, pdwQuotaUsed);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t0, new LongValue(pdwQuotaAllowed.getValue()));

		
		memory.setDoubleWordMemoryValue(t1, new LongValue(pdwQuotaUsed.getValue()));

		

	}
}