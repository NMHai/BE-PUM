/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetWorldTransform.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;

import v2.org.analysis.apihandle.structures.XFORM;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetWorldTransform extends Gdi32API {
	public GetWorldTransform () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		XFORM lpXform = new XFORM ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetWorldTransform (hdc, lpXform);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpXform.eM11));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpXform.eM12));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpXform.eM21));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpXform.eM22));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpXform.eDx));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpXform.eDy));
		t1 += 4;

	}
}