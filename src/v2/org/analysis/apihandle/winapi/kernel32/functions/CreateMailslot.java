/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: CreateMailslot.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateMailslot extends Kernel32API {
	public CreateMailslot () {
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
		String lpName = null;
		if ( t0 != 0L ) lpName = memory.getText(this, t0);
		DWORD nMaxMessageSize = new DWORD (t1);
		DWORD lReadTimeout = new DWORD (t2);
		SECURITY_ATTRIBUTES lpSecurityAttributes = null;
		if ( t3 != 0L) {
			lpSecurityAttributes = new SECURITY_ATTRIBUTES ();
			lpSecurityAttributes.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpSecurityAttributes.lpSecurityDescriptor = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpSecurityAttributes.bInheritHandle = (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue() != 0L ) ? true : false;
			t3 += 4;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.CreateMailslot (lpName, nMaxMessageSize, lReadTimeout, lpSecurityAttributes);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}