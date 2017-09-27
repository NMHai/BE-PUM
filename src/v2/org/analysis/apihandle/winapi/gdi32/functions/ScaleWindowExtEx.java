/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: ScaleWindowExtEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinUser.SIZE;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class ScaleWindowExtEx extends Gdi32API {
	public ScaleWindowExtEx () {
		super();
		NUM_OF_PARMS = 6;
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
		
		// Step 2: type conversion from C++ to Java
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		int Xnum = (int) t1;
		int Xdenom = (int) t2;
		int Ynum = (int) t3;
		int Ydenom = (int) t4;
		SIZE lpSize = new SIZE ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.ScaleWindowExtEx (hdc, Xnum, Xdenom, Ynum, Ydenom, lpSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t5 = this.params.get(5);
		memory.setDoubleWordMemoryValue (t5, new LongValue(lpSize.cx));
		t5 += 4;
		memory.setDoubleWordMemoryValue (t5, new LongValue(lpSize.cy));
		t5 += 4;

	}
}