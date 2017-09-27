/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wer.functions
 * File name: WerRemoveExcludedApplication.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.wer.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;

import v2.org.analysis.apihandle.winapi.wer.WerAPI;
import v2.org.analysis.apihandle.winapi.wer.WerDLL;
import v2.org.analysis.value.LongValue;
 
public class WerRemoveExcludedApplication extends WerAPI {
	public WerRemoveExcludedApplication () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		CHARByReference pwzExeName = new CHARByReference (new CHAR(t0));
		BOOL bAllUsers = new BOOL (t1);

		// Step 3: call API function
		int ret = WerDLL.INSTANCE.WerRemoveExcludedApplication (pwzExeName, bAllUsers);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}