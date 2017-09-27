/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.lz32.functions
 * File name: LZRead.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.lz32.functions;

import v2.org.analysis.apihandle.winapi.lz32.Lz32API;
import v2.org.analysis.apihandle.winapi.lz32.Lz32DLL;
import v2.org.analysis.value.LongValue;

 
public class LZRead extends Lz32API {
	public LZRead () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		int hFile = (int) t0;
		byte[] lpBuffer = null;
		if ( t1 != 0L ) lpBuffer = new byte[(int) t2];
		for (int i = 0; i < lpBuffer.length; i++) {
			lpBuffer [i] = (byte) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		int cbRead = (int) t2;

		// Step 3: call API function
		int ret = Lz32DLL.INSTANCE.LZRead (hFile, lpBuffer, cbRead);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < lpBuffer.length; i++) {
			memory.setByteMemoryValue (t1, new LongValue(lpBuffer [i]));
			t1 += 1;
		}
	}
}