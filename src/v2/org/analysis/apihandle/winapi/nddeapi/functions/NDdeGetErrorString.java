/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.nddeapi.functions
 * File name: NDdeGetErrorString.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.nddeapi.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiAPI;
import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiDLL;
import v2.org.analysis.value.LongValue;
 
public class NDdeGetErrorString extends NddeapiAPI {
	public NDdeGetErrorString () {
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
		UINT uErrorCode = new UINT (t0);
		char[] lpszErrorString = null;
		if ( t1 != 0L ) lpszErrorString = new char[(int) t2];
		for (int i = 0; i < lpszErrorString.length; i++) {
			lpszErrorString [i] = (char) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		DWORD cBufSize = new DWORD (t2);

		// Step 3: call API function
		int ret = NddeapiDLL.INSTANCE.NDdeGetErrorString (uErrorCode, lpszErrorString, cBufSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < lpszErrorString.length; i++) {
			memory.setByteMemoryValue (t1, new LongValue(lpszErrorString [i]));
			t1 += 1;
		}
	}
}