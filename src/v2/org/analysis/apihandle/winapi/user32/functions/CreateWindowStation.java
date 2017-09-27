/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CreateWindowStation.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateWindowStation extends User32API {
	public CreateWindowStation () {
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
		String lpwinsta = null;
		if ( t0 != 0L ) lpwinsta = memory.getText(this, t0);
		DWORD dwFlags = new DWORD (t1);
		int dwDesiredAccess = (int) t2;
		SECURITY_ATTRIBUTES lpsa = null;
		if ( t3 != 0L) {
			lpsa = new SECURITY_ATTRIBUTES ();
			lpsa.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpsa.lpSecurityDescriptor = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpsa.bInheritHandle = (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue() != 0L ) ? true : false;
			t3 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CreateWindowStation (lpwinsta, dwFlags, dwDesiredAccess, lpsa);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}