/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: EnumPrinterData.java
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
 
public class EnumPrinterData extends WinspoolAPI {
	public EnumPrinterData () {
		super();
		NUM_OF_PARMS = 9;
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
		long t7 = this.params.get(7);
		long t8 = this.params.get(8);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hPrinter = null;
		if ( t0 != 0L ) {
			hPrinter = new HANDLE ();
			hPrinter.setPointer(new Pointer(t0));
		}
		DWORD dwIndex = new DWORD (t1);
		char[] pValueName = null;
		if ( t2 != 0L ) pValueName = new char[(int) t3];
		for (int i = 0; i < pValueName.length; i++) {
			pValueName [i] = (char) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		DWORD cbValueName = new DWORD (t3);
		IntByReference pcbValueName = new IntByReference ((int) t4);
		IntByReference pType = new IntByReference ((int) t5);
		byte[] pData = null;
		if ( t6 != 0L ) pData = new byte[(int) t7];
		for (int i = 0; i < pData.length; i++) {
			pData [i] = (byte) ((LongValue) memory.getByteMemoryValue (t6)).getValue();
			t6 += 1;
		}
		DWORD cbData = new DWORD (t7);
		IntByReference pcbData = new IntByReference ((int) t8);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.EnumPrinterData (hPrinter, dwIndex, pValueName, cbValueName, pcbValueName, pType, pData, cbData, pcbData);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < pValueName.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(pValueName [i]));
			t2 += 1;
		}		memory.setDoubleWordMemoryValue(t4, new LongValue(pcbValueName.getValue()));

		
		memory.setDoubleWordMemoryValue(t5, new LongValue(pType.getValue()));

		
		t6 = this.params.get(6);
		for (int i = 0; i < pData.length; i++) {
			memory.setByteMemoryValue (t6, new LongValue(pData [i]));
			t6 += 1;
		}		memory.setDoubleWordMemoryValue(t8, new LongValue(pcbData.getValue()));

		

	}
}