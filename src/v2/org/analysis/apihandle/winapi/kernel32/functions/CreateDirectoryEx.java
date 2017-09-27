/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: CreateDirectoryEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateDirectoryEx extends Kernel32API {
	public CreateDirectoryEx () {
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
		String lpTemplateDirectory = null;
		if ( t0 != 0L ) lpTemplateDirectory = memory.getText(this, t0);
		String lpNewDirectory = null;
		if ( t1 != 0L ) lpNewDirectory = memory.getText(this, t1);
		SECURITY_ATTRIBUTES lpSecurityAttributes = null;
		if ( t2 != 0L) {
			lpSecurityAttributes = new SECURITY_ATTRIBUTES ();
			lpSecurityAttributes.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lpSecurityAttributes.lpSecurityDescriptor = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lpSecurityAttributes.bInheritHandle = (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue() != 0L ) ? true : false;
			t2 += 4;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.CreateDirectoryEx (lpTemplateDirectory, lpNewDirectory, lpSecurityAttributes);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}