/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHSetUnreadMailCount.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class SHSetUnreadMailCount extends Shell32API {
	public SHSetUnreadMailCount () {
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
		String pszMailAddress = null;
		if ( t0 != 0L ) pszMailAddress = memory.getText(this, t0);
		DWORD dwCount = new DWORD (t1);
		String pszShellExecuteCommand = null;
		if ( t2 != 0L ) pszShellExecuteCommand = memory.getText(this, t2);

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.SHSetUnreadMailCount (pszMailAddress, dwCount, pszShellExecuteCommand);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}