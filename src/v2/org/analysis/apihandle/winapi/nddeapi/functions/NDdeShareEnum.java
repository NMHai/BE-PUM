/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.nddeapi.functions
 * File name: NDdeShareEnum.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.nddeapi.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiAPI;
import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiDLL;
import v2.org.analysis.value.LongValue;
 
public class NDdeShareEnum extends NddeapiAPI {
	public NDdeShareEnum () {
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
		int[] lpcbTotalAvailable = null;
		if ( t5 != 0L ) lpcbTotalAvailable = new int[(int) t3];
		for (int i = 0; i < lpcbTotalAvailable.length; i++) {
			lpcbTotalAvailable [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t5)).getValue();
			t5 += 4;
		}

		// Step 3: call API function
		int ret = NddeapiDLL.INSTANCE.NDdeShareEnum (lpszServer, nLevel, lpBuffer, cBufSize, lpnEntriesRead, lpcbTotalAvailable);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < lpBuffer.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(lpBuffer [i]));
			t2 += 1;
		}		memory.setDoubleWordMemoryValue(t4, new LongValue(lpnEntriesRead.getValue()));

		
		t5 = this.params.get(5);
		for (int i = 0; i < lpcbTotalAvailable.length; i++) {
			memory.setDoubleWordMemoryValue (t5, new LongValue(lpcbTotalAvailable [i]));
			t5 += 4;
		}
	}
}