/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: FileEncryptionStatus.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class FileEncryptionStatus extends Advapi32API {
	public FileEncryptionStatus () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String lpFileName = null;
		if ( t0 != 0L ) lpFileName = memory.getText(this, t0);
		IntByReference lpStatus = new IntByReference ((int) t1);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.FileEncryptionStatus (lpFileName, lpStatus);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(lpStatus.getValue()));

		

	}
}