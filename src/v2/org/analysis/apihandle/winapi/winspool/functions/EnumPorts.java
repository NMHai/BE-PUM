/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: EnumPorts.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class EnumPorts extends WinspoolAPI {
	public EnumPorts () {
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
		String pName = null;
		if ( t0 != 0L ) pName = memory.getText(this, t0);
		DWORD Level = new DWORD (t1);
		byte[] pPorts = null;
		if ( t2 != 0L ) pPorts = new byte[(int) t3];
		for (int i = 0; i < pPorts.length; i++) {
			pPorts [i] = (byte) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		DWORD cbBuf = new DWORD (t3);
		IntByReference pcbNeeded = new IntByReference ((int) t4);
		IntByReference pcReturned = new IntByReference ((int) t5);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.EnumPorts (pName, Level, pPorts, cbBuf, pcbNeeded, pcReturned);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < pPorts.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(pPorts [i]));
			t2 += 1;
		}		memory.setDoubleWordMemoryValue(t4, new LongValue(pcbNeeded.getValue()));

		
		memory.setDoubleWordMemoryValue(t5, new LongValue(pcReturned.getValue()));

		

	}
}