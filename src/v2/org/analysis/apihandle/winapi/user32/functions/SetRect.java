/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SetRect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetRect extends User32API {
	public SetRect () {
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
		RECT lprc = new RECT ();
		int xLeft = (int) t1;
		int yTop = (int) t2;
		int xRight = (int) t3;
		int yBottom = (int) t4;

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.SetRect (lprc, xLeft, yTop, xRight, yBottom);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		memory.setDoubleWordMemoryValue (t0, new LongValue(lprc.left));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lprc.top));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lprc.right));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lprc.bottom));
		t0 += 4;

	}
}