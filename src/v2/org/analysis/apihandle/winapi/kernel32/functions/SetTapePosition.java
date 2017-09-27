/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetTapePosition.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetTapePosition extends Kernel32API {
	public SetTapePosition () {
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
		HANDLE hDevice = null;
		if ( t0 != 0L ) {
			hDevice = new HANDLE ();
			hDevice.setPointer(new Pointer(t0));
		}
		DWORD dwPositionMethod = new DWORD (t1);
		DWORD dwPartition = new DWORD (t2);
		DWORD dwOffsetLow = new DWORD (t3);
		DWORD dwOffsetHigh = new DWORD (t4);
		BOOL bImmediate = new BOOL (t5);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetTapePosition (hDevice, dwPositionMethod, dwPartition, dwOffsetLow, dwOffsetHigh, bImmediate);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}