/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shlwapi.functions
 * File name: StrChrI.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shlwapi.functions;

import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;

import v2.org.analysis.apihandle.winapi.shlwapi.ShlwapiAPI;
import v2.org.analysis.apihandle.winapi.shlwapi.ShlwapiDLL;
import v2.org.analysis.value.LongValue;
 
public class StrChrI extends ShlwapiAPI {
	public StrChrI () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		CHARByReference pszStart = new CHARByReference (new CHAR(t0));
		char wMatch = (char) t1;

		// Step 3: call API function
		int ret = ShlwapiDLL.INSTANCE.StrChrI (pszStart, wMatch);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}