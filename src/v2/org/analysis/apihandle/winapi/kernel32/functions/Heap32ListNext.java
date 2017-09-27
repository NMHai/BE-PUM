/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: Heap32ListNext.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.HEAPLIST32;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class Heap32ListNext extends Kernel32API {
	public Heap32ListNext () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hSnapshot = null;
		if ( t0 != 0L ) {
			hSnapshot = new HANDLE ();
			hSnapshot.setPointer(new Pointer(t0));
		}
		HEAPLIST32 lphl = new HEAPLIST32 ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.Heap32ListNext (hSnapshot, lphl);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lphl.dwSize.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lphl.th32ProcessID.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lphl.th32HeapID.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lphl.dwFlags.longValue()));
		t1 += 4;

	}
}