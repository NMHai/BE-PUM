/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetModuleBaseName.java
 * Author: Vinh Le, YenNLH
 * Comment: 2017-04-25: Adding exception case which trying to call GetModuleBaseName function from Psapi library
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.psapi.PsapiDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinNT.HANDLE;
 
public class GetModuleBaseName extends Kernel32API {
	public GetModuleBaseName () {
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
		char[] lpBaseName = null;
		if ( t2 != 0L ) {
			lpBaseName = new char[(int) t3];
		}
		for (int i = 0; i < lpBaseName.length; i++) {
			lpBaseName [i] = (char) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		int nSize = (int) (t3);

		// Step 3: call API function
		long ret = 0;
		try {
			ret = Kernel32DLL.INSTANCE.GetModuleBaseName (hProcess, hModule, lpBaseName, nSize);
		} catch (java.lang.UnsatisfiedLinkError ex) {
			ret = PsapiDLL.INSTANCE.GetModuleBaseName (hProcess, hModule, lpBaseName, nSize);
		}
		
		// Step 4: update environment (memory & eax register)
		register.mov("eax", new LongValue(ret));
		
		t2 = this.params.get(2);
		for (int i = 0; i < lpBaseName.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(lpBaseName [i]));
			t2 += 1;
		}
	}
}