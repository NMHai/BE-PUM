/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: CreateJobObject.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateJobObject extends Kernel32API {
	public CreateJobObject () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		SECURITY_ATTRIBUTES lpJobAttributes = null;
		if ( t0 != 0L) {
			lpJobAttributes = new SECURITY_ATTRIBUTES ();
			lpJobAttributes.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpJobAttributes.lpSecurityDescriptor = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpJobAttributes.bInheritHandle = (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue() != 0L ) ? true : false;
			t0 += 4;
		}
		String lpName = null;
		if ( t1 != 0L ) lpName = memory.getText(this, t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.CreateJobObject (lpJobAttributes, lpName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}