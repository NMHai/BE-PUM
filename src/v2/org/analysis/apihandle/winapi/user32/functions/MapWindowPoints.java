/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: MapWindowPoints.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class MapWindowPoints extends User32API {
	public MapWindowPoints () {
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
		HWND hWndFrom = null;
		if ( t0 != 0L ) {
			hWndFrom = new HWND ();
			hWndFrom.setPointer(new Pointer(t0));
		}
		HWND hWndTo = null;
		if ( t1 != 0L ) {
			hWndTo = new HWND ();
			hWndTo.setPointer(new Pointer(t1));
		}
		POINT lpPoints = null;
		if ( t2 != 0L) {
			lpPoints = new POINT ();
			lpPoints.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lpPoints.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
		}
		UINT cPoints = new UINT (t3);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.MapWindowPoints (hWndFrom, hWndTo, lpPoints, cPoints);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}