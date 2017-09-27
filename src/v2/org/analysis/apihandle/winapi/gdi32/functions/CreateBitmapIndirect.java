/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: CreateBitmapIndirect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.apihandle.winapi.structures.BITMAP;
import v2.org.analysis.value.LongValue;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
 
public class CreateBitmapIndirect extends Gdi32API {
	public CreateBitmapIndirect () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		BITMAP lpbm = null;
		if ( t0 != 0L) {
			lpbm = new v2.org.analysis.apihandle.winapi.structures.BITMAP ();
			lpbm.bmType = new NativeLong (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpbm.bmWidth = new NativeLong (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpbm.bmHeight = new NativeLong (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpbm.bmWidthBytes = new NativeLong (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpbm.bmPlanes = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
			lpbm.bmBitsPixel = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
			lpbm.bmBits = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.CreateBitmapIndirect (lpbm);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}