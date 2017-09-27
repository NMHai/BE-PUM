/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: MoveToEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.POINT;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class MoveToEx extends Gdi32API {
	public MoveToEx () {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		
		// Step 2: type conversion from C++ to Java
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		int X = (int) t1;
		int Y = (int) t2;
		POINT lpPoint = new POINT ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.MoveToEx (hdc, X, Y, lpPoint);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		memory.setDoubleWordMemoryValue (t3, new LongValue(lpPoint.x));
		t3 += 4;
		memory.setDoubleWordMemoryValue (t3, new LongValue(lpPoint.y));
		t3 += 4;

	}
}