/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: UnregisterWaitEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class UnregisterWaitEx extends Kernel32API {
	public UnregisterWaitEx () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE WaitHandle = null;
		if ( t0 != 0L ) {
			WaitHandle = new HANDLE ();
			WaitHandle.setPointer(new Pointer(t0));
		}
		HANDLE CompletionEvent = null;
		if ( t1 != 0L ) {
			CompletionEvent = new HANDLE ();
			CompletionEvent.setPointer(new Pointer(t1));
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.UnregisterWaitEx (WaitHandle, CompletionEvent);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}