/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetNamedPipeHandleState.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetNamedPipeHandleState extends Kernel32API {
	public SetNamedPipeHandleState () {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hNamedPipe = null;
		if ( t0 != 0L ) {
			hNamedPipe = new HANDLE ();
			hNamedPipe.setPointer(new Pointer(t0));
		}
		IntByReference lpMode = new IntByReference ((int) t1);
		IntByReference lpMaxCollectionCount = new IntByReference ((int) t2);
		IntByReference lpCollectDataTimeout = new IntByReference ((int) t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetNamedPipeHandleState (hNamedPipe, lpMode, lpMaxCollectionCount, lpCollectDataTimeout);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}