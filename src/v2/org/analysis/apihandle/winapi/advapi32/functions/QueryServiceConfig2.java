/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: QueryServiceConfig2.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.Winsvc.SC_HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class QueryServiceConfig2 extends Advapi32API {
	public QueryServiceConfig2 () {
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
		SC_HANDLE hService = null;
		if ( t0 != 0L ) {
			hService = new SC_HANDLE ();
			hService.setPointer(new Pointer(t0));
		}
		DWORD dwInfoLevel = new DWORD (t1);
		byte[] lpBuffer = null;
		if ( t2 != 0L ) lpBuffer = new byte[(int) t3];
		for (int i = 0; i < lpBuffer.length; i++) {
			lpBuffer [i] = (byte) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		DWORD cbBufSize = new DWORD (t3);
		IntByReference pcbBytesNeeded = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.QueryServiceConfig2 (hService, dwInfoLevel, lpBuffer, cbBufSize, pcbBytesNeeded);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < lpBuffer.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(lpBuffer [i]));
			t2 += 1;
		}		memory.setDoubleWordMemoryValue(t4, new LongValue(pcbBytesNeeded.getValue()));

		

	}
}