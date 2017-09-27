/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetPrivateProfileSection.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetPrivateProfileSection extends Kernel32API {
	public GetPrivateProfileSection () {
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
		String lpAppName = null;
		if ( t0 != 0L ) lpAppName = memory.getText(this, t0);
		char[] lpReturnedString = null;
		if ( t1 != 0L ) lpReturnedString = new char[(int) t2];
		for (int i = 0; i < lpReturnedString.length; i++) {
			lpReturnedString [i] = (char) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		DWORD nSize = new DWORD (t2);
		String lpFileName = null;
		if ( t3 != 0L ) lpFileName = memory.getText(this, t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetPrivateProfileSection (lpAppName, lpReturnedString, nSize, lpFileName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < lpReturnedString.length; i++) {
			memory.setByteMemoryValue (t1, new LongValue(lpReturnedString [i]));
			t1 += 1;
		}
	}
}