/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetTapePosition.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetTapePosition extends Kernel32API {
	public GetTapePosition () {
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
		HANDLE hDevice = null;
		if ( t0 != 0L ) {
			hDevice = new HANDLE ();
			hDevice.setPointer(new Pointer(t0));
		}
		DWORD dwPositionType = new DWORD (t1);
		IntByReference lpdwPartition = new IntByReference ((int) t2);
		IntByReference lpdwOffsetLow = new IntByReference ((int) t3);
		IntByReference lpdwOffsetHigh = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetTapePosition (hDevice, dwPositionType, lpdwPartition, lpdwOffsetLow, lpdwOffsetHigh);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t2, new LongValue(lpdwPartition.getValue()));

		
		memory.setDoubleWordMemoryValue(t3, new LongValue(lpdwOffsetLow.getValue()));

		
		memory.setDoubleWordMemoryValue(t4, new LongValue(lpdwOffsetHigh.getValue()));

		

	}
}