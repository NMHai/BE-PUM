/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetPrivateProfileSectionNames.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetPrivateProfileSectionNames extends Kernel32API {
	public GetPrivateProfileSectionNames () {
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
		char[] lpszReturnBuffer = null;
		if ( t0 != 0L ) lpszReturnBuffer = new char[(int) t1];
		for (int i = 0; i < lpszReturnBuffer.length; i++) {
			lpszReturnBuffer [i] = (char) ((LongValue) memory.getByteMemoryValue (t0)).getValue();
			t0 += 1;
		}
		DWORD nSize = new DWORD (t1);
		String lpFileName = null;
		if ( t2 != 0L ) lpFileName = memory.getText(this, t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetPrivateProfileSectionNames (lpszReturnBuffer, nSize, lpFileName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		for (int i = 0; i < lpszReturnBuffer.length; i++) {
			memory.setByteMemoryValue (t0, new LongValue(lpszReturnBuffer [i]));
			t0 += 1;
		}
	}
}