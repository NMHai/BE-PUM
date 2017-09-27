/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: CreateBoundaryDescriptor.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.ULONG;
 
public class CreateBoundaryDescriptor extends Kernel32API {
	public CreateBoundaryDescriptor () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String Name = null;
		if ( t0 != 0L ) {
			Name = memory.getText(this, t0);
		}
		ULONG Flags = new ULONG ((int) t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.CreateBoundaryDescriptor (Name, Flags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}