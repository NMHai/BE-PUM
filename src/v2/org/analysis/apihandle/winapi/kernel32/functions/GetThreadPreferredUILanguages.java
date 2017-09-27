/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetThreadPreferredUILanguages.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetThreadPreferredUILanguages extends Kernel32API {
	public GetThreadPreferredUILanguages () {
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
		DWORD dwFlags = new DWORD (t0);
		IntByReference pulNumLanguages = new IntByReference ((int) t1);
		CHARByReference pwszLanguagesBuffer = new CHARByReference (new CHAR(t2));
		IntByReference pcchLanguagesBuffer = new IntByReference ((int) t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetThreadPreferredUILanguages (dwFlags, pulNumLanguages, pwszLanguagesBuffer, pcchLanguagesBuffer);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(pulNumLanguages.getValue()));

		
		memory.setDoubleWordMemoryValue(t2, new LongValue(pwszLanguagesBuffer.getValue().longValue()));

		

	}
}