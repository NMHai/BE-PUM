/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CopyAcceleratorTable.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.ACCEL;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CopyAcceleratorTable extends User32API {
	public CopyAcceleratorTable () {
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
		HANDLE hAccelSrc = null;
		if ( t0 != 0L ) {
			hAccelSrc = new HANDLE ();
			hAccelSrc.setPointer(new Pointer(t0));
		}
		ACCEL lpAccelDst = new ACCEL ();
		int cAccelEntries = (int) t2;

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CopyAcceleratorTable (hAccelSrc, lpAccelDst, cAccelEntries);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setByteMemoryValue (t1, new LongValue(lpAccelDst.fVirt.longValue()));
		t1 += 1;
		memory.setWordMemoryValue (t1, new LongValue(lpAccelDst.key.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpAccelDst.cmd.longValue()));
		t1 += 2;

	}
}