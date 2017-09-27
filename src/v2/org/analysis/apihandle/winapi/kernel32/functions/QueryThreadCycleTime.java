/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: QueryThreadCycleTime.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.LongByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class QueryThreadCycleTime extends Kernel32API {
	public QueryThreadCycleTime () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE ThreadHandle = null;
		if ( t0 != 0L ) {
			ThreadHandle = new HANDLE ();
			ThreadHandle.setPointer(new Pointer(t0));
		}
		LongByReference CycleTime = new LongByReference ((long) t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.QueryThreadCycleTime (ThreadHandle, CycleTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(CycleTime.getValue()));

		

	}
}