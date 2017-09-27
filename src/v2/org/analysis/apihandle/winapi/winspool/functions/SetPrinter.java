/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: SetPrinter.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class SetPrinter extends WinspoolAPI {
	public SetPrinter () {
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
		HANDLE hPrinter = null;
		if ( t0 != 0L ) {
			hPrinter = new HANDLE ();
			hPrinter.setPointer(new Pointer(t0));
		}
		DWORD Level = new DWORD (t1);
		byte[] pPrinter = null;
		if ( t2 != 0L ) pPrinter = new byte[(int) t3];
		for (int i = 0; i < pPrinter.length; i++) {
			pPrinter [i] = (byte) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		DWORD Command = new DWORD (t3);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.SetPrinter (hPrinter, Level, pPrinter, Command);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}