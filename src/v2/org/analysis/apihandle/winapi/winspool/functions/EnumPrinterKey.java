/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: EnumPrinterKey.java
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
 
public class EnumPrinterKey extends WinspoolAPI {
	public EnumPrinterKey () {
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
		String pKeyName = null;
		if ( t1 != 0L ) pKeyName = memory.getText(this, t1);
		char[] pSubkey = null;
		if ( t2 != 0L ) pSubkey = new char[(int) t3];
		for (int i = 0; i < pSubkey.length; i++) {
			pSubkey [i] = (char) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		DWORD cbSubkey = new DWORD (t3);
		IntByReference pcbSubkey = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.EnumPrinterKey (hPrinter, pKeyName, pSubkey, cbSubkey, pcbSubkey);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < pSubkey.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(pSubkey [i]));
			t2 += 1;
		}		memory.setDoubleWordMemoryValue(t4, new LongValue(pcbSubkey.getValue()));

		

	}
}