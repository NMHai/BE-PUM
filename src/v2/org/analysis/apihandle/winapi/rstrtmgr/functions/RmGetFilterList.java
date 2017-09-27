/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.rstrtmgr.functions
 * File name: RmGetFilterList.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.rstrtmgr.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.rstrtmgr.RstrtmgrAPI;
import v2.org.analysis.apihandle.winapi.rstrtmgr.RstrtmgrDLL;
import v2.org.analysis.value.LongValue;
 
public class RmGetFilterList extends RstrtmgrAPI {
	public RmGetFilterList () {
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
		DWORD dwSessionHandle = new DWORD (t0);
		byte[] pbFilterBuf = null;
		if ( t1 != 0L ) pbFilterBuf = new byte[(int) t2];
		for (int i = 0; i < pbFilterBuf.length; i++) {
			pbFilterBuf [i] = (byte) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		DWORD cbFilterBuf = new DWORD (t2);
		IntByReference cbFilterBufNeeded = new IntByReference ((int) t3);

		// Step 3: call API function
		int ret = RstrtmgrDLL.INSTANCE.RmGetFilterList (dwSessionHandle, pbFilterBuf, cbFilterBuf, cbFilterBufNeeded);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < pbFilterBuf.length; i++) {
			memory.setByteMemoryValue (t1, new LongValue(pbFilterBuf [i]));
			t1 += 1;
		}		memory.setDoubleWordMemoryValue(t3, new LongValue(cbFilterBufNeeded.getValue()));

		

	}
}