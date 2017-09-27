/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetQueuedCompletionStatusEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.structures.OVERLAPPED_ENTRY;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
 
public class GetQueuedCompletionStatusEx extends Kernel32API {
	public GetQueuedCompletionStatusEx () {
		super();
		NUM_OF_PARMS = 6;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		
		// Step 2: type conversion from C++ to Java
		HANDLE CompletionPort = null;
		if ( t0 != 0L ) {
			CompletionPort = new HANDLE ();
			CompletionPort.setPointer(new Pointer(t0));
		}
		OVERLAPPED_ENTRY lpCompletionPortEntries = new OVERLAPPED_ENTRY ();
		ULONG ulCount = new ULONG ((int) t2);
		IntByReference ulNumEntriesRemoved = new IntByReference ((int) t3);
		DWORD dwMilliseconds = new DWORD (t4);
		BOOL fAlertable = new BOOL (t5);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetQueuedCompletionStatusEx (CompletionPort, lpCompletionPortEntries, ulCount, ulNumEntriesRemoved, dwMilliseconds, fAlertable);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCompletionPortEntries.lpCompletionKey.longValue()));
		t1 += 4;
		// Nested Structure
			lpCompletionPortEntries.lpOverlapped.Internal = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue());
			lpCompletionPortEntries.lpOverlapped.InternalHigh = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue());
			lpCompletionPortEntries.lpOverlapped.Offset = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpCompletionPortEntries.lpOverlapped.OffsetHigh = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpCompletionPortEntries.lpOverlapped.hEvent = new HANDLE (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue()));
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCompletionPortEntries.Internal.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCompletionPortEntries.dwNumberOfBytesTransferred.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue(t3, new LongValue(ulNumEntriesRemoved.getValue()));

		

	}
}