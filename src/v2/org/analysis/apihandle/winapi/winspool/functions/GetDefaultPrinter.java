/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: GetDefaultPrinter.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class GetDefaultPrinter extends WinspoolAPI {
	public GetDefaultPrinter () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		char[] pszBuffer = null;
		if ( t0 != 0L ) pszBuffer = new char[(int) t1];
		for (int i = 0; i < pszBuffer.length; i++) {
			pszBuffer [i] = (char) ((LongValue) memory.getByteMemoryValue (t0)).getValue();
			t0 += 1;
		}
		IntByReference pcchBuffer = new IntByReference ((int) t1);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.GetDefaultPrinter (pszBuffer, pcchBuffer);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}