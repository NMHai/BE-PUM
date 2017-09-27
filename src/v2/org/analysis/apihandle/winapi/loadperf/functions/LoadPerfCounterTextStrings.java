/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.loadperf.functions
 * File name: LoadPerfCounterTextStrings.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.loadperf.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.apihandle.winapi.loadperf.LoadperfAPI;
import v2.org.analysis.apihandle.winapi.loadperf.LoadperfDLL;
import v2.org.analysis.value.LongValue;
 
public class LoadPerfCounterTextStrings extends LoadperfAPI {
	public LoadPerfCounterTextStrings () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String commandLine = null;
		if ( t0 != 0L ) commandLine = memory.getText(this, t0);
		BOOL bQuietModeArg = new BOOL (t1);

		// Step 3: call API function
		int ret = LoadperfDLL.INSTANCE.LoadPerfCounterTextStrings (commandLine, bQuietModeArg);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}