/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: GetLengthSid.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.PSID;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetLengthSid extends Advapi32API {
	public GetLengthSid () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		PSID pSid = null;
		if ( t0 != 0L) {
			pSid = new PSID ();
			pSid.sid = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.GetLengthSid (pSid);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}