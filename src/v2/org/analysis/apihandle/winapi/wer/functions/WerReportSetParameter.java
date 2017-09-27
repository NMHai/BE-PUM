/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wer.functions
 * File name: WerReportSetParameter.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.wer.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.wer.WerAPI;
import v2.org.analysis.apihandle.winapi.wer.WerDLL;
import v2.org.analysis.value.LongValue;
 
public class WerReportSetParameter extends WerAPI {
	public WerReportSetParameter () {
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
		HANDLE hReportHandle = null;
		if ( t0 != 0L ) {
			hReportHandle = new HANDLE ();
			hReportHandle.setPointer(new Pointer(t0));
		}
		DWORD dwparamID = new DWORD (t1);
		CHARByReference pwzName = new CHARByReference (new CHAR(t2));
		CHARByReference pwzValue = new CHARByReference (new CHAR(t3));

		// Step 3: call API function
		int ret = WerDLL.INSTANCE.WerReportSetParameter (hReportHandle, dwparamID, pwzName, pwzValue);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}