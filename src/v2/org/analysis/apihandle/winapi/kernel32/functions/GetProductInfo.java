/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetProductInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetProductInfo extends Kernel32API {
	public GetProductInfo () {
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
		DWORD dwOSMajorVersion = new DWORD (t0);
		DWORD dwOSMinorVersion = new DWORD (t1);
		DWORD dwSpMajorVersion = new DWORD (t2);
		DWORD dwSpMinorVersion = new DWORD (t3);
		IntByReference pdwReturnedProductType = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetProductInfo (dwOSMajorVersion, dwOSMinorVersion, dwSpMajorVersion, dwSpMinorVersion, pdwReturnedProductType);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t4, new LongValue(pdwReturnedProductType.getValue()));

		

	}
}