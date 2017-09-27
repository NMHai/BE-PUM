/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: DeletePrintProvidor.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class DeletePrintProvidor extends WinspoolAPI {
	public DeletePrintProvidor () {
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
		String pName = null;
		if ( t0 != 0L ) pName = memory.getText(this, t0);
		String pEnvironment = null;
		if ( t1 != 0L ) pEnvironment = memory.getText(this, t1);
		String pPrintProviderName = null;
		if ( t2 != 0L ) pPrintProviderName = memory.getText(this, t2);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.DeletePrintProvidor (pName, pEnvironment, pPrintProviderName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}