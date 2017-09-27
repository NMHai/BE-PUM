/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: AddPrinterDriverEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class AddPrinterDriverEx extends WinspoolAPI {
	public AddPrinterDriverEx () {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		
		// Step 2: type conversion from C++ to Java
		String pName = null;
		if ( t0 != 0L ) pName = memory.getText(this, t0);
		DWORD Level = new DWORD (t1);
		byte pDriverInfo = (byte) t2;
		DWORD dwFileCopyFlags = new DWORD (t3);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.AddPrinterDriverEx (pName, Level, pDriverInfo, dwFileCopyFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}