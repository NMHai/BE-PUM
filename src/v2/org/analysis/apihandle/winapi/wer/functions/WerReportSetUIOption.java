/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wer.functions
 * File name: WerReportSetUIOption.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.wer.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.wer.WerAPI;
import v2.org.analysis.apihandle.winapi.wer.WerDLL;
import v2.org.analysis.value.LongValue;
 
public class WerReportSetUIOption extends WerAPI {
	public WerReportSetUIOption () {
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
		HANDLE hReportHandle = null;
		if ( t0 != 0L ) {
			hReportHandle = new HANDLE ();
			hReportHandle.setPointer(new Pointer(t0));
		}
		int repUITypeID = (int) t1;
		CHARByReference pwzValue = new CHARByReference (new CHAR(t2));

		// Step 3: call API function
		int ret = WerDLL.INSTANCE.WerReportSetUIOption (hReportHandle, repUITypeID, pwzValue);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}