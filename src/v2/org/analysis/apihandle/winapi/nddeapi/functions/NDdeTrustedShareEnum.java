/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.nddeapi.functions
 * File name: NDdeTrustedShareEnum.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.nddeapi.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiAPI;
import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiDLL;
import v2.org.analysis.value.LongValue;
 
public class NDdeTrustedShareEnum extends NddeapiAPI {
	public NDdeTrustedShareEnum () {
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
		String lpszServer = null;
		if ( t0 != 0L ) lpszServer = memory.getText(this, t0);
		UINT nLevel = new UINT (t1);
		byte[] lpBuffer = null;
		if ( t2 != 0L ) lpBuffer = new byte[(int) t3];
		for (int i = 0; i < lpBuffer.length; i++) {
			lpBuffer [i] = (byte) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		DWORD cBufSize = new DWORD (t3);
		IntByReference lpnEntriesRead = new IntByReference ((int) t4);
		IntByReference lpcbTotalAvailable = new IntByReference ((int) t5);

		// Step 3: call API function
		int ret = NddeapiDLL.INSTANCE.NDdeTrustedShareEnum (lpszServer, nLevel, lpBuffer, cBufSize, lpnEntriesRead, lpcbTotalAvailable);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < lpBuffer.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(lpBuffer [i]));
			t2 += 1;
		}		memory.setDoubleWordMemoryValue(t4, new LongValue(lpnEntriesRead.getValue()));

		
		memory.setDoubleWordMemoryValue(t5, new LongValue(lpcbTotalAvailable.getValue()));

		

	}
}