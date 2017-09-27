/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: LoadAccelerators.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class LoadAccelerators extends User32API {
	public LoadAccelerators () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HINSTANCE hInstance = null;
		if ( t0 != 0L ) {
			hInstance = new HINSTANCE ();
			hInstance.setPointer(new Pointer(t0));
		}
		String lpTableName = null;
		if ( t1 != 0L ) lpTableName = memory.getText(this, t1);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.LoadAccelerators (hInstance, lpTableName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}