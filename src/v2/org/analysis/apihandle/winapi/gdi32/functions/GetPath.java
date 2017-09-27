/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetPath.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.POINT;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetPath extends Gdi32API {
	public GetPath () {
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
		POINT lpPoints = new POINT ();
		byte[] lpTypes = null;
		if ( t2 != 0L ) lpTypes = new byte[(int) t3];
		for (int i = 0; i < lpTypes.length; i++) {
			lpTypes [i] = (byte) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		int nSize = (int) t3;

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetPath (hdc, lpPoints, lpTypes, nSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpPoints.x));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpPoints.y));
		t1 += 4;
		t2 = this.params.get(2);
		for (int i = 0; i < lpTypes.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(lpTypes [i]));
			t2 += 1;
		}
	}
}