/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetModuleFileNameEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetModuleFileNameEx extends Kernel32API {
	public GetModuleFileNameEx () {
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
		HANDLE hProcess = null;
		if ( t0 != 0L ) {
			hProcess = new HANDLE ();
			hProcess.setPointer(new Pointer(t0));
		}
		HMODULE hModule = null;
		if ( t1 != 0L ) {
			hModule = new HMODULE ();
			hModule.setPointer(new Pointer(t1));
		}
		char[] lpFilename = null;
		if ( t2 != 0L ) lpFilename = new char[(int) t3];
		for (int i = 0; i < lpFilename.length; i++) {
			lpFilename [i] = (char) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		DWORD nSize = new DWORD (t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetModuleFileNameEx (hProcess, hModule, lpFilename, nSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < lpFilename.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(lpFilename [i]));
			t2 += 1;
		}
	}
}