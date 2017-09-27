/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: CreateSemaphoreEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateSemaphoreEx extends Kernel32API {
	public CreateSemaphoreEx () {
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
		SECURITY_ATTRIBUTES lpSemaphoreAttributes = null;
		if ( t0 != 0L) {
			lpSemaphoreAttributes = new SECURITY_ATTRIBUTES ();
			lpSemaphoreAttributes.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpSemaphoreAttributes.lpSecurityDescriptor = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpSemaphoreAttributes.bInheritHandle = (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue() != 0L ) ? true : false;
			t0 += 4;
		}
		LONG lInitialCount = new LONG (t1);
		LONG lMaximumCount = new LONG (t2);
		String lpName = null;
		if ( t3 != 0L ) lpName = memory.getText(this, t3);
		DWORD dwFlags = new DWORD (t4);
		DWORD dwDesiredAccess = new DWORD (t5);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.CreateSemaphoreEx (lpSemaphoreAttributes, lInitialCount, lMaximumCount, lpName, dwFlags, dwDesiredAccess);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}