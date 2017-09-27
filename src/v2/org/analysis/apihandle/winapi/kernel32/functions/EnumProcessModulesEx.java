/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: EnumProcessModulesEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class EnumProcessModulesEx extends Kernel32API {
	public EnumProcessModulesEx () {
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
		HANDLE hProcess = null;
		if ( t0 != 0L ) {
			hProcess = new HANDLE ();
			hProcess.setPointer(new Pointer(t0));
		}
		HANDLEByReference lphModule = new HANDLEByReference (new HANDLE(new Pointer(t1)));
		DWORD cb = new DWORD (t2);
		IntByReference lpcbNeeded = new IntByReference ((int) t3);
		DWORD dwFilterFlag = new DWORD (t4);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.EnumProcessModulesEx (hProcess, lphModule, cb, lpcbNeeded, dwFilterFlag);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(Pointer.nativeValue(lphModule.getValue().getPointer())));

		
		memory.setDoubleWordMemoryValue(t3, new LongValue(lpcbNeeded.getValue()));

		

	}
}