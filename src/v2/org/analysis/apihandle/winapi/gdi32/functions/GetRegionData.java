/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetRegionData.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HRGN;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinGDI.RGNDATA;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetRegionData extends Gdi32API {
	public GetRegionData () {
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
		HRGN hRgn = null;
		if ( t0 != 0L ) {
			hRgn = new HRGN ();
			hRgn.setPointer(new Pointer(t0));
		}
		DWORD dwCount = new DWORD (t1);
		RGNDATA lpRgnData = new RGNDATA ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetRegionData (hRgn, dwCount, lpRgnData);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		// Nested Structure
			lpRgnData.rdh.dwSize = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue();
			lpRgnData.rdh.iType = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			lpRgnData.rdh.nCount = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			lpRgnData.rdh.nRgnSize = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			lpRgnData.rdh.rcBound = new RECT ();
			// Nested Structure
			lpRgnData.rdh.rcBound.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue();
			lpRgnData.rdh.rcBound.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			lpRgnData.rdh.rcBound.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			lpRgnData.rdh.rcBound.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
		for (int i = 0; i < lpRgnData.Buffer.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(lpRgnData.Buffer [i]));
			t2 += 1;
		}

	}
}