/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: SetPrinterDataEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class SetPrinterDataEx extends WinspoolAPI {
	public SetPrinterDataEx () {
		super();
		NUM_OF_PARMS = 6;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hPrinter = null;
		if ( t0 != 0L ) {
			hPrinter = new HANDLE ();
			hPrinter.setPointer(new Pointer(t0));
		}
		String pKeyName = null;
		if ( t1 != 0L ) pKeyName = memory.getText(this, t1);
		String pValueName = null;
		if ( t2 != 0L ) pValueName = memory.getText(this, t2);
		DWORD Type = new DWORD (t3);
		byte[] pData = null;
		if ( t4 != 0L ) pData = new byte[(int) t5];
		for (int i = 0; i < pData.length; i++) {
			pData [i] = (byte) ((LongValue) memory.getByteMemoryValue (t4)).getValue();
			t4 += 1;
		}
		DWORD cbData = new DWORD (t5);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.SetPrinterDataEx (hPrinter, pKeyName, pValueName, Type, pData, cbData);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}