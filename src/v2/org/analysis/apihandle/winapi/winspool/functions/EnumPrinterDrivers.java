/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: EnumPrinterDrivers.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class EnumPrinterDrivers extends WinspoolAPI {
	public EnumPrinterDrivers () {
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
		String pName = null;
		if ( t0 != 0L ) pName = memory.getText(this, t0);
		String pEnvironment = null;
		if ( t1 != 0L ) pEnvironment = memory.getText(this, t1);
		DWORD Level = new DWORD (t2);
		byte[] pDriverInfo = null;
		if ( t3 != 0L ) pDriverInfo = new byte[(int) t4];
		for (int i = 0; i < pDriverInfo.length; i++) {
			pDriverInfo [i] = (byte) ((LongValue) memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
		}
		DWORD cbBuf = new DWORD (t4);
		IntByReference pcbNeeded = new IntByReference ((int) t5);
		IntByReference pcReturned = new IntByReference ((int) t6);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.EnumPrinterDrivers (pName, pEnvironment, Level, pDriverInfo, cbBuf, pcbNeeded, pcReturned);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		for (int i = 0; i < pDriverInfo.length; i++) {
			memory.setByteMemoryValue (t3, new LongValue(pDriverInfo [i]));
			t3 += 1;
		}		memory.setDoubleWordMemoryValue(t5, new LongValue(pcbNeeded.getValue()));

		
		memory.setDoubleWordMemoryValue(t6, new LongValue(pcReturned.getValue()));

		

	}
}