/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: SetViewportExtEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinUser.SIZE;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetViewportExtEx extends Gdi32API {
	public SetViewportExtEx () {
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
		int nXExtent = (int) t1;
		int nYExtent = (int) t2;
		SIZE lpSize = new SIZE ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.SetViewportExtEx (hdc, nXExtent, nYExtent, lpSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		memory.setDoubleWordMemoryValue (t3, new LongValue(lpSize.cx));
		t3 += 4;
		memory.setDoubleWordMemoryValue (t3, new LongValue(lpSize.cy));
		t3 += 4;

	}
}