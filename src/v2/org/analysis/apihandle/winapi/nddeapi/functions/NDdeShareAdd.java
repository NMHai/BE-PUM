/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.nddeapi.functions
 * File name: NDdeShareAdd.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.nddeapi.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiAPI;
import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiDLL;
import v2.org.analysis.value.LongValue;
 
public class NDdeShareAdd extends NddeapiAPI {
	public NDdeShareAdd () {
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
		String lpszServer = null;
		if ( t0 != 0L ) lpszServer = memory.getText(this, t0);
		UINT nLevel = new UINT (t1);
		Pointer pSD = new Pointer (t2);
		byte lpBuffer = (byte) t3;
		DWORD cBufSize = new DWORD (t4);

		// Step 3: call API function
		int ret = NddeapiDLL.INSTANCE.NDdeShareAdd (lpszServer, nLevel, pSD, lpBuffer, cBufSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}