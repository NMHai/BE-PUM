/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mpr.functions
 * File name: WNetGetUser.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mpr.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.mpr.MprAPI;
import v2.org.analysis.apihandle.winapi.mpr.MprDLL;
import v2.org.analysis.value.LongValue;
 
public class WNetGetUser extends MprAPI {
	public WNetGetUser () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		String lpName = null;
		if ( t0 != 0L ) lpName = memory.getText(this, t0);
		char[] lpUserName = null;
		if ( t1 != 0L ) lpUserName = new char[(int) t2];
		for (int i = 0; i < lpUserName.length; i++) {
			lpUserName [i] = (char) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		IntByReference lpnLength = new IntByReference ((int) t2);

		// Step 3: call API function
		int ret = MprDLL.INSTANCE.WNetGetUser (lpName, lpUserName, lpnLength);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < lpUserName.length; i++) {
			memory.setByteMemoryValue (t1, new LongValue(lpUserName [i]));
			t1 += 1;
		}
	}
}