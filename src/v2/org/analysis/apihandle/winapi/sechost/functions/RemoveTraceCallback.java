/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.sechost.functions
 * File name: RemoveTraceCallback.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.sechost.functions;

import com.sun.jna.platform.win32.Guid.GUID;

import v2.org.analysis.apihandle.winapi.sechost.SechostAPI;
import v2.org.analysis.apihandle.winapi.sechost.SechostDLL;
import v2.org.analysis.value.LongValue;
 
public class RemoveTraceCallback extends SechostAPI {
	public RemoveTraceCallback () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		GUID pGuid = null;
		if ( t0 != 0L) {
			pGuid = new GUID ();
			pGuid.Data1 = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			pGuid.Data2 = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
			pGuid.Data3 = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
			for (int i = 0; i < pGuid.Data4.length; i++) {
				pGuid.Data4 [i] = (byte) ((LongValue) memory.getByteMemoryValue (t0)).getValue();
				t0 += 1;
			}
		}

		// Step 3: call API function
		int ret = SechostDLL.INSTANCE.RemoveTraceCallback (pGuid);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}