/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetFinalPathNameByHandle.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetFinalPathNameByHandle extends Kernel32API {
	public GetFinalPathNameByHandle () {
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
		char[] lpszFilePath = null;
		if ( t1 != 0L ) lpszFilePath = new char[(int) t2];
		for (int i = 0; i < lpszFilePath.length; i++) {
			lpszFilePath [i] = (char) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		DWORD cchFilePath = new DWORD (t2);
		DWORD dwFlags = new DWORD (t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetFinalPathNameByHandle (hFile, lpszFilePath, cchFilePath, dwFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < lpszFilePath.length; i++) {
			memory.setByteMemoryValue (t1, new LongValue(lpszFilePath [i]));
			t1 += 1;
		}
	}
}