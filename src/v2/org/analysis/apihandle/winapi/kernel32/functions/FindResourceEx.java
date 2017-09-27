/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FindResourceEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.WORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class FindResourceEx extends Kernel32API {
	public FindResourceEx () {
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
		HMODULE hModule = null;
		if ( t0 != 0L ) {
			hModule = new HMODULE ();
			hModule.setPointer(new Pointer(t0));
		}
		String lpType = null;
		if ( t1 != 0L ) lpType = memory.getText(this, t1);
		String lpName = null;
		if ( t2 != 0L ) lpName = memory.getText(this, t2);
		WORD wLanguage = new WORD (t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.FindResourceEx (hModule, lpType, lpName, wLanguage);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}