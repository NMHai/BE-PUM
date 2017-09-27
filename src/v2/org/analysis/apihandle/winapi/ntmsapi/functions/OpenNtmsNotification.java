/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ntmsapi.functions
 * File name: OpenNtmsNotification.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.ntmsapi.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.ntmsapi.NtmsapiAPI;
import v2.org.analysis.apihandle.winapi.ntmsapi.NtmsapiDLL;
import v2.org.analysis.value.LongValue;
 
public class OpenNtmsNotification extends NtmsapiAPI {
	public OpenNtmsNotification () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hSession = null;
		if ( t0 != 0L ) {
			hSession = new HANDLE ();
			hSession.setPointer(new Pointer(t0));
		}
		DWORD dwType = new DWORD (t1);

		// Step 3: call API function
		int ret = NtmsapiDLL.INSTANCE.OpenNtmsNotification (hSession, dwType);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}