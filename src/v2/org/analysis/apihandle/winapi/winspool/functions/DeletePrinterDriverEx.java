/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: DeletePrinterDriverEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class DeletePrinterDriverEx extends WinspoolAPI {
	public DeletePrinterDriverEx () {
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
		String pName = null;
		if ( t0 != 0L ) pName = memory.getText(this, t0);
		String pEnvironment = null;
		if ( t1 != 0L ) pEnvironment = memory.getText(this, t1);
		String pDriverName = null;
		if ( t2 != 0L ) pDriverName = memory.getText(this, t2);
		DWORD dwDeleteFlag = new DWORD (t3);
		DWORD dwVersionFlag = new DWORD (t4);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.DeletePrinterDriverEx (pName, pEnvironment, pDriverName, dwDeleteFlag, dwVersionFlag);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}