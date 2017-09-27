/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: ReuseDDElParam.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class ReuseDDElParam extends User32API {
	public ReuseDDElParam () {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		
		// Step 2: type conversion from C++ to Java
		LPARAM lParam = new LPARAM (t0);
		UINT msgIn = new UINT (t1);
		UINT msgOut = new UINT (t2);
		UINT_PTR uiLo = new UINT_PTR (t3);
		UINT_PTR uiHi = new UINT_PTR (t4);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.ReuseDDElParam (lParam, msgIn, msgOut, uiLo, uiHi);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}