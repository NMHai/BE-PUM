/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: LogonUser.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class LogonUser extends Advapi32API {
	public LogonUser () {
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
		String lpszUsername = null;
		if ( t0 != 0L ) lpszUsername = memory.getText(this, t0);
		String lpszDomain = null;
		if ( t1 != 0L ) lpszDomain = memory.getText(this, t1);
		String lpszPassword = null;
		if ( t2 != 0L ) lpszPassword = memory.getText(this, t2);
		DWORD dwLogonType = new DWORD (t3);
		DWORD dwLogonProvider = new DWORD (t4);
		HANDLEByReference phToken = new HANDLEByReference (new HANDLE(new Pointer(t5)));

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.LogonUser (lpszUsername, lpszDomain, lpszPassword, dwLogonType, dwLogonProvider, phToken);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t5, new LongValue(Pointer.nativeValue(phToken.getValue().getPointer())));

		

	}
}