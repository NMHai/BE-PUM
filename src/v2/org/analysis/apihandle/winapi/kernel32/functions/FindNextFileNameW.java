/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FindNextFileNameW.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class FindNextFileNameW extends Kernel32API {
	public FindNextFileNameW () {
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
		HANDLE hFindStream = null;
		if ( t0 != 0L ) {
			hFindStream = new HANDLE ();
			hFindStream.setPointer(new Pointer(t0));
		}
		IntByReference StringLength = new IntByReference ((int) t1);
		char LinkName = (char) t2;

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.FindNextFileNameW (hFindStream, StringLength, LinkName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}