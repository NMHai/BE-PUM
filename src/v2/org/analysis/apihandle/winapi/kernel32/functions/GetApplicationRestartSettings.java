/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetApplicationRestartSettings.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetApplicationRestartSettings extends Kernel32API {
	public GetApplicationRestartSettings () {
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
		char[] pwzCommandline = null;
		if ( t1 != 0L ) pwzCommandline = new char[(int) t2];
		for (int i = 0; i < pwzCommandline.length; i++) {
			pwzCommandline [i] = (char) ((LongValue) memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
		}
		IntByReference pcchSize = new IntByReference ((int) t2);
		IntByReference pdwFlags = new IntByReference ((int) t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetApplicationRestartSettings (hProcess, pwzCommandline, pcchSize, pdwFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < pwzCommandline.length; i++) {
			memory.setWordMemoryValue (t1, new LongValue(pwzCommandline [i]));
			t1 += 2;
		}		memory.setDoubleWordMemoryValue(t3, new LongValue(pdwFlags.getValue()));

		

	}
}