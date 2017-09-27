/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: GetJob.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class GetJob extends WinspoolAPI {
	public GetJob () {
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
		HANDLE hPrinter = null;
		if ( t0 != 0L ) {
			hPrinter = new HANDLE ();
			hPrinter.setPointer(new Pointer(t0));
		}
		DWORD JobId = new DWORD (t1);
		DWORD Level = new DWORD (t2);
		byte[] pJob = null;
		if ( t3 != 0L ) pJob = new byte[(int) t4];
		for (int i = 0; i < pJob.length; i++) {
			pJob [i] = (byte) ((LongValue) memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
		}
		DWORD cbBuf = new DWORD (t4);
		IntByReference pcbNeeded = new IntByReference ((int) t5);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.GetJob (hPrinter, JobId, Level, pJob, cbBuf, pcbNeeded);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		for (int i = 0; i < pJob.length; i++) {
			memory.setByteMemoryValue (t3, new LongValue(pJob [i]));
			t3 += 1;
		}		memory.setDoubleWordMemoryValue(t5, new LongValue(pcbNeeded.getValue()));

		

	}
}