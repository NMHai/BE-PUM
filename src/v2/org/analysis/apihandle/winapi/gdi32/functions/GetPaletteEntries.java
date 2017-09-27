/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetPaletteEntries.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HPALETTE;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.PALETTEENTRY;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetPaletteEntries extends Gdi32API {
	public GetPaletteEntries () {
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
		HPALETTE hpal = null;
		if ( t0 != 0L ) {
			hpal = new HPALETTE ();
			hpal.setPointer(new Pointer(t0));
		}
		UINT iStartIndex = new UINT (t1);
		UINT nEntries = new UINT (t2);
		PALETTEENTRY lppe = new PALETTEENTRY ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetPaletteEntries (hpal, iStartIndex, nEntries, lppe);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		memory.setByteMemoryValue (t3, new LongValue(lppe.peRed.longValue()));
		t3 += 1;
		memory.setByteMemoryValue (t3, new LongValue(lppe.peGreen.longValue()));
		t3 += 1;
		memory.setByteMemoryValue (t3, new LongValue(lppe.peBlue.longValue()));
		t3 += 1;
		memory.setByteMemoryValue (t3, new LongValue(lppe.peFlags.longValue()));
		t3 += 1;

	}
}