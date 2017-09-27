/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.lz32.functions
 * File name: LZSeek.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.lz32.functions;

import com.sun.jna.platform.win32.WinDef.LONG;

import v2.org.analysis.apihandle.winapi.lz32.Lz32API;
import v2.org.analysis.apihandle.winapi.lz32.Lz32DLL;
import v2.org.analysis.value.LongValue;
 
public class LZSeek extends Lz32API {
	public LZSeek () {
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
		LONG lOffset = new LONG (t1);
		int iOrigin = (int) t2;

		// Step 3: call API function
		int ret = Lz32DLL.INSTANCE.LZSeek (hFile, lOffset, iOrigin);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}