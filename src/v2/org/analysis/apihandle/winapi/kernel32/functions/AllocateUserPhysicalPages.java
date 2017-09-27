/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: AllocateUserPhysicalPages.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class AllocateUserPhysicalPages extends Kernel32API {
	public AllocateUserPhysicalPages () {
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
		HANDLE hProcess = null;
		if ( t0 != 0L ) {
			hProcess = new HANDLE ();
			hProcess.setPointer(new Pointer(t0));
		}
		IntByReference NumberOfPages = new IntByReference ((int) t1);
		int[] UserPfnArray = null;
		if ( t2 != 0L ) UserPfnArray = new int[(int) t1];
		for (int i = 0; i < UserPfnArray.length; i++) {
			UserPfnArray [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.AllocateUserPhysicalPages (hProcess, NumberOfPages, UserPfnArray);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < UserPfnArray.length; i++) {
			memory.setDoubleWordMemoryValue (t2, new LongValue(UserPfnArray [i]));
			t2 += 4;
		}
	}
}