/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CopyRect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CopyRect extends User32API {
	public CopyRect () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		RECT lprcDst = new RECT ();
		RECT lprcSrc = null;
		if ( t1 != 0L) {
			lprcSrc = new RECT ();
			lprcSrc.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lprcSrc.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lprcSrc.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lprcSrc.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CopyRect (lprcDst, lprcSrc);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		memory.setDoubleWordMemoryValue (t0, new LongValue(lprcDst.left));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lprcDst.top));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lprcDst.right));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lprcDst.bottom));
		t0 += 4;

	}
}