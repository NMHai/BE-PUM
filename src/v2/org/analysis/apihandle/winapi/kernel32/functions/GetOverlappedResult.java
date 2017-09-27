/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetOverlappedResult.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinBase.OVERLAPPED;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetOverlappedResult extends Kernel32API {
	public GetOverlappedResult () {
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
		HANDLE hFile = null;
		if ( t0 != 0L ) {
			hFile = new HANDLE ();
			hFile.setPointer(new Pointer(t0));
		}
		OVERLAPPED lpOverlapped = null;
		if ( t1 != 0L) {
			lpOverlapped = new OVERLAPPED ();
			lpOverlapped.Internal = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpOverlapped.InternalHigh = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpOverlapped.Offset = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpOverlapped.OffsetHigh = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpOverlapped.hEvent = new HANDLE (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue()));
			t1 += 4;
		}
		IntByReference lpNumberOfBytesTransferred = new IntByReference ((int) t2);
		BOOL bWait = new BOOL (t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetOverlappedResult (hFile, lpOverlapped, lpNumberOfBytesTransferred, bWait);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t2, new LongValue(lpNumberOfBytesTransferred.getValue()));

		

	}
}