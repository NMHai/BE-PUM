/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetProcessIoCounters.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.IO_COUNTERS;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetProcessIoCounters extends Kernel32API {
	public GetProcessIoCounters () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hProcess = null;
		if ( t0 != 0L ) {
			hProcess = new HANDLE ();
			hProcess.setPointer(new Pointer(t0));
		}
		IO_COUNTERS lpIoCounters = new IO_COUNTERS ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetProcessIoCounters (hProcess, lpIoCounters);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpIoCounters.ReadOperationCount.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpIoCounters.WriteOperationCount.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpIoCounters.OtherOperationCount.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpIoCounters.ReadTransferCount.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpIoCounters.WriteTransferCount.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpIoCounters.OtherTransferCount.longValue()));
		t1 += 4;

	}
}