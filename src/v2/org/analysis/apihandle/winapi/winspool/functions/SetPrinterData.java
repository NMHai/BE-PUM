/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: SetPrinterData.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class SetPrinterData extends WinspoolAPI {
	public SetPrinterData () {
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
		HANDLE hPrinter = null;
		if ( t0 != 0L ) {
			hPrinter = new HANDLE ();
			hPrinter.setPointer(new Pointer(t0));
		}
		String pValueName = null;
		if ( t1 != 0L ) pValueName = memory.getText(this, t1);
		DWORD Type = new DWORD (t2);
		byte[] pData = null;
		if ( t3 != 0L ) pData = new byte[(int) t4];
		for (int i = 0; i < pData.length; i++) {
			pData [i] = (byte) ((LongValue) memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
		}
		DWORD cbData = new DWORD (t4);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.SetPrinterData (hPrinter, pValueName, Type, pData, cbData);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}