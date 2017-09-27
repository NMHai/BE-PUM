/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: RaiseException.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
 
public class RaiseException extends Kernel32API {
	public RaiseException () {
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
		DWORD dwExceptionCode = new DWORD (t0);
		DWORD dwExceptionFlags = new DWORD (t1);
		DWORD nNumberOfArguments = new DWORD (t2);
		IntByReference lpArguments = new IntByReference ((int) t3);

		// Step 3: call API function
		Kernel32DLL.INSTANCE.RaiseException (dwExceptionCode, dwExceptionFlags, nNumberOfArguments, lpArguments);
		
		// Step 4: update environment (memory & eax register)

	}
}