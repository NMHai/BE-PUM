/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: LockFileEx.java
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
 
public class LockFileEx extends Kernel32API {
	public LockFileEx () {
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
		HANDLE hFile = null;
		if ( t0 != 0L ) {
			hFile = new HANDLE ();
			hFile.setPointer(new Pointer(t0));
		}
		DWORD dwFlags = new DWORD (t1);
		DWORD dwReserved = new DWORD (t2);
		DWORD nNumberOfBytesToLockLow = new DWORD (t3);
		DWORD nNumberOfBytesToLockHigh = new DWORD (t4);
		OVERLAPPED lpOverlapped = null;
		if ( t5 != 0L) {
			lpOverlapped = new OVERLAPPED ();
			lpOverlapped.Internal = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t5)).getValue());
			t5 += 4;
			lpOverlapped.InternalHigh = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t5)).getValue());
			t5 += 4;
			lpOverlapped.Offset = (int) ((LongValue)memory.getDoubleWordMemoryValue (t5)).getValue();
			t5 += 4;
			lpOverlapped.OffsetHigh = (int) ((LongValue)memory.getDoubleWordMemoryValue (t5)).getValue();
			t5 += 4;
			lpOverlapped.hEvent = new HANDLE (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t5)).getValue()));
			t5 += 4;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.LockFileEx (hFile, dwFlags, dwReserved, nNumberOfBytesToLockLow, nNumberOfBytesToLockHigh, lpOverlapped);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}