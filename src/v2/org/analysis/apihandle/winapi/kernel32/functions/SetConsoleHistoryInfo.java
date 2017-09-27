/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetConsoleHistoryInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.CONSOLE_HISTORY_INFO;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetConsoleHistoryInfo extends Kernel32API {
	public SetConsoleHistoryInfo () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		CONSOLE_HISTORY_INFO lpConsoleHistoryInfo = null;
		if ( t0 != 0L) {
			lpConsoleHistoryInfo = new CONSOLE_HISTORY_INFO ();
			lpConsoleHistoryInfo.cbSize = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpConsoleHistoryInfo.HistoryBufferSize = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpConsoleHistoryInfo.NumberOfHistoryBuffers = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpConsoleHistoryInfo.dwFlags = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetConsoleHistoryInfo (lpConsoleHistoryInfo);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}