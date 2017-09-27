/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: PostQueuedCompletionStatus.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinBase.OVERLAPPED;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class PostQueuedCompletionStatus extends Kernel32API {
	public PostQueuedCompletionStatus () {
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
		HANDLE CompletionPort = null;
		if ( t0 != 0L ) {
			CompletionPort = new HANDLE ();
			CompletionPort.setPointer(new Pointer(t0));
		}
		DWORD dwNumberOfBytesTransferred = new DWORD (t1);
		ULONG_PTR dwCompletionKey = new ULONG_PTR (t2);
		OVERLAPPED lpOverlapped = null;
		if ( t3 != 0L) {
			lpOverlapped = new OVERLAPPED ();
			lpOverlapped.Internal = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpOverlapped.InternalHigh = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpOverlapped.Offset = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			lpOverlapped.OffsetHigh = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			lpOverlapped.hEvent = new HANDLE (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue()));
			t3 += 4;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.PostQueuedCompletionStatus (CompletionPort, dwNumberOfBytesTransferred, dwCompletionKey, lpOverlapped);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}