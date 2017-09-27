/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: DeletePrinterData.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class DeletePrinterData extends WinspoolAPI {
	public DeletePrinterData () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hPrinter = null;
		if ( t0 != 0L ) {
			hPrinter = new HANDLE ();
			hPrinter.setPointer(new Pointer(t0));
		}
		String pValueName = null;
		if ( t1 != 0L ) pValueName = memory.getText(this, t1);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.DeletePrinterData (hPrinter, pValueName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}