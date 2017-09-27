/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetMailslotInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetMailslotInfo extends Kernel32API {
	public GetMailslotInfo () {
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
		HANDLE hMailslot = null;
		if ( t0 != 0L ) {
			hMailslot = new HANDLE ();
			hMailslot.setPointer(new Pointer(t0));
		}
		IntByReference lpMaxMessageSize = new IntByReference ((int) t1);
		IntByReference lpNextSize = new IntByReference ((int) t2);
		IntByReference lpMessageCount = new IntByReference ((int) t3);
		IntByReference lpReadTimeout = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetMailslotInfo (hMailslot, lpMaxMessageSize, lpNextSize, lpMessageCount, lpReadTimeout);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(lpMaxMessageSize.getValue()));

		
		memory.setDoubleWordMemoryValue(t2, new LongValue(lpNextSize.getValue()));

		
		memory.setDoubleWordMemoryValue(t3, new LongValue(lpMessageCount.getValue()));

		
		memory.setDoubleWordMemoryValue(t4, new LongValue(lpReadTimeout.getValue()));

		

	}
}