/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SignalObjectAndWait.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SignalObjectAndWait extends Kernel32API {
	public SignalObjectAndWait () {
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
		HANDLE hObjectToSignal = null;
		if ( t0 != 0L ) {
			hObjectToSignal = new HANDLE ();
			hObjectToSignal.setPointer(new Pointer(t0));
		}
		HANDLE hObjectToWaitOn = null;
		if ( t1 != 0L ) {
			hObjectToWaitOn = new HANDLE ();
			hObjectToWaitOn.setPointer(new Pointer(t1));
		}
		DWORD dwMilliseconds = new DWORD (t2);
		BOOL bAlertable = new BOOL (t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SignalObjectAndWait (hObjectToSignal, hObjectToWaitOn, dwMilliseconds, bAlertable);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}