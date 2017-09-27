/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: GetServiceDisplayName.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Winsvc.SC_HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetServiceDisplayName extends Advapi32API {
	public GetServiceDisplayName () {
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
		SC_HANDLE hSCManager = null;
		if ( t0 != 0L ) {
			hSCManager = new SC_HANDLE ();
			hSCManager.setPointer(new Pointer(t0));
		}
		String lpServiceName = null;
		if ( t1 != 0L ) lpServiceName = memory.getText(this, t1);
		char[] lpDisplayName = null;
		if ( t2 != 0L ) lpDisplayName = new char[(int) t3];
		for (int i = 0; i < lpDisplayName.length; i++) {
			lpDisplayName [i] = (char) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		IntByReference lpcchBuffer = new IntByReference ((int) t3);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.GetServiceDisplayName (hSCManager, lpServiceName, lpDisplayName, lpcchBuffer);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < lpDisplayName.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(lpDisplayName [i]));
			t2 += 1;
		}
	}
}