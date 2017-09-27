/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: GetPrinterDataEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class GetPrinterDataEx extends WinspoolAPI {
	public GetPrinterDataEx () {
		super();
		NUM_OF_PARMS = 7;
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
		long t6 = this.params.get(6);
		
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
		IntByReference pType = new IntByReference ((int) t3);
		byte[] pData = null;
		if ( t4 != 0L ) pData = new byte[(int) t5];
		for (int i = 0; i < pData.length; i++) {
			pData [i] = (byte) ((LongValue) memory.getByteMemoryValue (t4)).getValue();
			t4 += 1;
		}
		DWORD nSize = new DWORD (t5);
		IntByReference pcbNeeded = new IntByReference ((int) t6);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.GetPrinterDataEx (hPrinter, pKeyName, pValueName, pType, pData, nSize, pcbNeeded);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t3, new LongValue(pType.getValue()));

		
		t4 = this.params.get(4);
		for (int i = 0; i < pData.length; i++) {
			memory.setByteMemoryValue (t4, new LongValue(pData [i]));
			t4 += 1;
		}		memory.setDoubleWordMemoryValue(t6, new LongValue(pcbNeeded.getValue()));

		

	}
}