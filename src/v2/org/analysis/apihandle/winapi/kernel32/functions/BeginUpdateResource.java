/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: BeginUpdateResource.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class BeginUpdateResource extends Kernel32API {
	public BeginUpdateResource () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String pFileName = null;
		if ( t0 != 0L ) pFileName = memory.getText(this, t0);
		BOOL bDeleteExistingResources = new BOOL (t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.BeginUpdateResource (pFileName, bDeleteExistingResources);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}