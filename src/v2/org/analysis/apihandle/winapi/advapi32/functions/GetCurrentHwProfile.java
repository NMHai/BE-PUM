/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: GetCurrentHwProfile.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.structures.HW_PROFILE_INFO;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetCurrentHwProfile extends Advapi32API {
	public GetCurrentHwProfile () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		HW_PROFILE_INFO lpHwProfileInfo = new HW_PROFILE_INFO ();

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.GetCurrentHwProfile (lpHwProfileInfo);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpHwProfileInfo.dwDockInfo.longValue()));
		t0 += 4;
		for (int i = 0; i < lpHwProfileInfo.szHwProfileGuid.length; i++) {
			memory.setWordMemoryValue (t0, new LongValue(lpHwProfileInfo.szHwProfileGuid [i]));
			t0 += 2;
		}
		for (int i = 0; i < lpHwProfileInfo.szHwProfileName.length; i++) {
			memory.setWordMemoryValue (t0, new LongValue(lpHwProfileInfo.szHwProfileName [i]));
			t0 += 2;
		}

	}
}