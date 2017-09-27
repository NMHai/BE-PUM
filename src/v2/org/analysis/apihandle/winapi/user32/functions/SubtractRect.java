/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SubtractRect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class SubtractRect extends User32API {
	public SubtractRect () {
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
		RECT lprcDst = new RECT ();
		RECT lprcSrc1 = null;
		if ( t1 != 0L) {
			lprcSrc1 = new RECT ();
			lprcSrc1.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lprcSrc1.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lprcSrc1.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lprcSrc1.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
		}
		RECT lprcSrc2 = null;
		if ( t2 != 0L) {
			lprcSrc2 = new RECT ();
			lprcSrc2.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lprcSrc2.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lprcSrc2.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lprcSrc2.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.SubtractRect (lprcDst, lprcSrc1, lprcSrc2);
		
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