/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetConsoleProcessList.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetConsoleProcessList extends Kernel32API {
	public GetConsoleProcessList () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		int[] lpdwProcessList = null;
		if ( t0 != 0L ) lpdwProcessList = new int[(int) t1];
		for (int i = 0; i < lpdwProcessList.length; i++) {
			lpdwProcessList [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
		}
		DWORD dwProcessCount = new DWORD (t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetConsoleProcessList (lpdwProcessList, dwProcessCount);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		for (int i = 0; i < lpdwProcessList.length; i++) {
			memory.setDoubleWordMemoryValue (t0, new LongValue(lpdwProcessList [i]));
			t0 += 4;
		}
	}
}