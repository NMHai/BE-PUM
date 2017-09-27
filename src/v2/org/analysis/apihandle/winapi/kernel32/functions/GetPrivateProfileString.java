/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetPrivateProfileString.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetPrivateProfileString extends Kernel32API {
	public GetPrivateProfileString () {
		super();
		NUM_OF_PARMS = 6;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		
		// Step 2: type conversion from C++ to Java
		String lpAppName = null;
		if ( t0 != 0L ) lpAppName = memory.getText(this, t0);
		String lpKeyName = null;
		if ( t1 != 0L ) lpKeyName = memory.getText(this, t1);
		String lpDefault = null;
		if ( t2 != 0L ) lpDefault = memory.getText(this, t2);
		char[] lpReturnedString = null;
		if ( t3 != 0L ) lpReturnedString = new char[(int) t4];
		for (int i = 0; i < lpReturnedString.length; i++) {
			lpReturnedString [i] = (char) ((LongValue) memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
		}
		DWORD nSize = new DWORD (t4);
		String lpFileName = null;
		if ( t5 != 0L ) lpFileName = memory.getText(this, t5);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetPrivateProfileString (lpAppName, lpKeyName, lpDefault, lpReturnedString, nSize, lpFileName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		for (int i = 0; i < lpReturnedString.length; i++) {
			memory.setByteMemoryValue (t3, new LongValue(lpReturnedString [i]));
			t3 += 1;
		}
	}
}