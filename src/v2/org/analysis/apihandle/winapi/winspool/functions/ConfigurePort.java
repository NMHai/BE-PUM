/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: ConfigurePort.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class ConfigurePort extends WinspoolAPI {
	public ConfigurePort () {
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
		String pName = null;
		if ( t0 != 0L ) pName = memory.getText(this, t0);
		HWND hWnd = null;
		if ( t1 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t1));
		}
		String pPortName = null;
		if ( t2 != 0L ) pPortName = memory.getText(this, t2);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.ConfigurePort (pName, hWnd, pPortName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}