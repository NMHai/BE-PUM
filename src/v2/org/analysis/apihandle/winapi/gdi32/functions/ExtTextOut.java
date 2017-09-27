/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: ExtTextOut.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class ExtTextOut extends Gdi32API {
	public ExtTextOut () {
		super();
		NUM_OF_PARMS = 8;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		long t6 = this.params.get(6);
		long t7 = this.params.get(7);
		
		// Step 2: type conversion from C++ to Java
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		int X = (int) t1;
		int Y = (int) t2;
		UINT fuOptions = new UINT (t3);
		RECT lprc = null;
		if ( t4 != 0L) {
			lprc = new RECT ();
			lprc.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
			lprc.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
			lprc.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
			lprc.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
		}
		String lpString = null;
		if ( t5 != 0L ) lpString = memory.getText(this, t5);
		UINT cbCount = new UINT (t6);
		IntByReference lpDx = new IntByReference ((int) t7);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.ExtTextOut (hdc, X, Y, fuOptions, lprc, lpString, cbCount, lpDx);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}