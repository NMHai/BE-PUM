/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.avrt.functions
 * File name: AvRtWaitOnThreadOrderingGroup.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.avrt.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.avrt.AvrtAPI;
import v2.org.analysis.apihandle.winapi.avrt.AvrtDLL;
import v2.org.analysis.value.LongValue;
 
public class AvRtWaitOnThreadOrderingGroup extends AvrtAPI {
	public AvRtWaitOnThreadOrderingGroup () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		HANDLE Context = null;
		if ( t0 != 0L ) {
			Context = new HANDLE ();
			Context.setPointer(new Pointer(t0));
		}

		// Step 3: call API function
		int ret = AvrtDLL.INSTANCE.AvRtWaitOnThreadOrderingGroup (Context);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}