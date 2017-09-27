/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: ReleaseSemaphore.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class ReleaseSemaphore extends Kernel32API {
	public ReleaseSemaphore () {
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
		HANDLE hSemaphore = null;
		if ( t0 != 0L ) {
			hSemaphore = new HANDLE ();
			hSemaphore.setPointer(new Pointer(t0));
		}
		LONG lReleaseCount = new LONG (t1);
		IntByReference lpPreviousCount = new IntByReference ((int) t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.ReleaseSemaphore (hSemaphore, lReleaseCount, lpPreviousCount);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t2, new LongValue(lpPreviousCount.getValue()));

		

	}
}