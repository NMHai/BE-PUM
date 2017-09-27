/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: Heap32First.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.HEAPENTRY32;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class Heap32First extends Kernel32API {
	public Heap32First () {
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
		HEAPENTRY32 lphe = null;
		if ( t0 != 0L) {
			lphe = new HEAPENTRY32 ();
			lphe.dwSize = new SIZE_T (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lphe.hHandle = new HANDLE (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue()));
			t0 += 4;
			lphe.dwAddress = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lphe.dwBlockSize = new SIZE_T (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lphe.dwFlags = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lphe.dwLockCount = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lphe.dwResvd = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lphe.th32ProcessID = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lphe.th32HeapID = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}
		DWORD th32ProcessID = new DWORD (t1);
		ULONG_PTR th32HeapID = new ULONG_PTR (t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.Heap32First (lphe, th32ProcessID, th32HeapID);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}