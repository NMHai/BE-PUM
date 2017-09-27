/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetQueuedCompletionStatus.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetQueuedCompletionStatus extends Kernel32API {
	public GetQueuedCompletionStatus () {
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
		HANDLE CompletionPort = null;
		if ( t0 != 0L ) {
			CompletionPort = new HANDLE ();
			CompletionPort.setPointer(new Pointer(t0));
		}
		IntByReference lpNumberOfBytes = new IntByReference ((int) t1);
		IntByReference lpCompletionKey = new IntByReference ((int) t2);
		PointerByReference lpOverlapped = new PointerByReference (new Pointer(t3));
		DWORD dwMilliseconds = new DWORD (t4);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetQueuedCompletionStatus (CompletionPort, lpNumberOfBytes, lpCompletionKey, lpOverlapped, dwMilliseconds);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(lpNumberOfBytes.getValue()));

		
		memory.setDoubleWordMemoryValue(t2, new LongValue(lpCompletionKey.getValue()));

		
		memory.setDoubleWordMemoryValue(t3, new LongValue(Pointer.nativeValue(lpOverlapped.getValue())));

		

	}
}