/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: CreateEventEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateEventEx extends Kernel32API {
	public CreateEventEx () {
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
		SECURITY_ATTRIBUTES lpEventAttributes = null;
		if ( t0 != 0L) {
			lpEventAttributes = new SECURITY_ATTRIBUTES ();
			lpEventAttributes.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpEventAttributes.lpSecurityDescriptor = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpEventAttributes.bInheritHandle = (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue() != 0L ) ? true : false;
			t0 += 4;
		}
		String lpName = null;
		if ( t1 != 0L ) lpName = memory.getText(this, t1);
		DWORD dwFlags = new DWORD (t2);
		DWORD dwDesiredAccess = new DWORD (t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.CreateEventEx (lpEventAttributes, lpName, dwFlags, dwDesiredAccess);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}