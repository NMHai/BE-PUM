/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetFileBandwidthReservation.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetFileBandwidthReservation extends Kernel32API {
	public GetFileBandwidthReservation () {
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
		HANDLE hFile = null;
		if ( t0 != 0L ) {
			hFile = new HANDLE ();
			hFile.setPointer(new Pointer(t0));
		}
		IntByReference lpPeriodMilliseconds = new IntByReference ((int) t1);
		IntByReference lpBytesPerPeriod = new IntByReference ((int) t2);
		IntByReference pDiscardable = new IntByReference ((int) t3);
		IntByReference lpTransferSize = new IntByReference ((int) t4);
		IntByReference lpNumOutstandingRequests = new IntByReference ((int) t5);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetFileBandwidthReservation (hFile, lpPeriodMilliseconds, lpBytesPerPeriod, pDiscardable, lpTransferSize, lpNumOutstandingRequests);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(lpPeriodMilliseconds.getValue()));

		
		memory.setDoubleWordMemoryValue(t2, new LongValue(lpBytesPerPeriod.getValue()));

		
		memory.setDoubleWordMemoryValue(t3, new LongValue(pDiscardable.getValue()));

		
		memory.setDoubleWordMemoryValue(t4, new LongValue(lpTransferSize.getValue()));

		
		memory.setDoubleWordMemoryValue(t5, new LongValue(lpNumOutstandingRequests.getValue()));

		

	}
}