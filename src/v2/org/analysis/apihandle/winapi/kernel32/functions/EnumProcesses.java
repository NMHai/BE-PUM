/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: EnumProcesses.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class EnumProcesses extends Kernel32API {
	public EnumProcesses () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		int[] pProcessIds = null;
		if ( t0 != 0L ) pProcessIds = new int[(int) t1];
		for (int i = 0; i < pProcessIds.length; i++) {
			pProcessIds [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
		}
		DWORD cb = new DWORD (t1);
		IntByReference pBytesReturned = new IntByReference ((int) t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.EnumProcesses (pProcessIds, cb, pBytesReturned);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		for (int i = 0; i < pProcessIds.length; i++) {
			memory.setDoubleWordMemoryValue (t0, new LongValue(pProcessIds [i]));
			t0 += 4;
		}		memory.setDoubleWordMemoryValue(t2, new LongValue(pBytesReturned.getValue()));

		

	}
}