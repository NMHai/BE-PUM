/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetConsoleHistoryInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.structures.CONSOLE_HISTORY_INFO;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetConsoleHistoryInfo extends Kernel32API {
	public GetConsoleHistoryInfo () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		CONSOLE_HISTORY_INFO lpConsoleHistoryInfo = new CONSOLE_HISTORY_INFO ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetConsoleHistoryInfo (lpConsoleHistoryInfo);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpConsoleHistoryInfo.cbSize.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpConsoleHistoryInfo.HistoryBufferSize.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpConsoleHistoryInfo.NumberOfHistoryBuffers.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpConsoleHistoryInfo.dwFlags.longValue()));
		t0 += 4;

	}
}