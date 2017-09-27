/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: PtInRect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class PtInRect extends User32API {
	public PtInRect () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		RECT lprc = null;
		if ( t0 != 0L) {
			lprc = new RECT ();
			lprc.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			lprc.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			lprc.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			lprc.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
		}
		POINT pt = null;
		if ( t1 != 0L) {
			pt = new POINT ();
			pt.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			pt.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.PtInRect (lprc, pt);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}