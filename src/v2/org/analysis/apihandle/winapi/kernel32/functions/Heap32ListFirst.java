/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: Heap32ListFirst.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.HEAPLIST32;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class Heap32ListFirst extends Kernel32API {
	public Heap32ListFirst () {
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
		HEAPLIST32 lphl = null;
		if ( t1 != 0L) {
			lphl = new HEAPLIST32 ();
			lphl.dwSize = new SIZE_T (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lphl.th32ProcessID = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lphl.th32HeapID = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lphl.dwFlags = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.Heap32ListFirst (hSnapshot, lphl);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}