/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: EnumJobs.java
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
 
public class EnumJobs extends WinspoolAPI {
	public EnumJobs () {
		super();
		NUM_OF_PARMS = 8;
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
		long t6 = this.params.get(6);
		long t7 = this.params.get(7);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hPrinter = null;
		if ( t0 != 0L ) {
			hPrinter = new HANDLE ();
			hPrinter.setPointer(new Pointer(t0));
		}
		DWORD FirstJob = new DWORD (t1);
		DWORD NoJobs = new DWORD (t2);
		DWORD Level = new DWORD (t3);
		byte[] pJob = null;
		if ( t4 != 0L ) pJob = new byte[(int) t5];
		for (int i = 0; i < pJob.length; i++) {
			pJob [i] = (byte) ((LongValue) memory.getByteMemoryValue (t4)).getValue();
			t4 += 1;
		}
		DWORD cbBuf = new DWORD (t5);
		IntByReference pcbNeeded = new IntByReference ((int) t6);
		IntByReference pcReturned = new IntByReference ((int) t7);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.EnumJobs (hPrinter, FirstJob, NoJobs, Level, pJob, cbBuf, pcbNeeded, pcReturned);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t4 = this.params.get(4);
		for (int i = 0; i < pJob.length; i++) {
			memory.setByteMemoryValue (t4, new LongValue(pJob [i]));
			t4 += 1;
		}		memory.setDoubleWordMemoryValue(t6, new LongValue(pcbNeeded.getValue()));

		
		memory.setDoubleWordMemoryValue(t7, new LongValue(pcReturned.getValue()));

		

	}
}