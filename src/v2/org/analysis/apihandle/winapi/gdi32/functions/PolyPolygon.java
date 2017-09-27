/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: PolyPolygon.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.POINT;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class PolyPolygon extends Gdi32API {
	public PolyPolygon () {
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
		POINT lpPoints = null;
		if ( t1 != 0L) {
			lpPoints = new POINT ();
			lpPoints.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lpPoints.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
		}
		int[] lpPolyCounts = null;
		if ( t2 != 0L ) lpPolyCounts = new int[(int) t3];
		for (int i = 0; i < lpPolyCounts.length; i++) {
			lpPolyCounts [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
		}
		int nCount = (int) t3;

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.PolyPolygon (hdc, lpPoints, lpPolyCounts, nCount);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}