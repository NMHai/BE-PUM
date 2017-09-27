/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: MonitorFromPoint.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.POINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class MonitorFromPoint extends User32API {
	public MonitorFromPoint () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		POINT pt = null;
		if ( t0 != 0L) {
			pt = new POINT ();
			pt.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			pt.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
		}
		DWORD dwFlags = new DWORD (t1);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.MonitorFromPoint (pt, dwFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}