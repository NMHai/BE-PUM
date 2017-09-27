/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetEnhMetaFilePaletteEntries.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.PALETTEENTRY;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetEnhMetaFilePaletteEntries extends Gdi32API {
	public GetEnhMetaFilePaletteEntries () {
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
		HANDLE hemf = null;
		if ( t0 != 0L ) {
			hemf = new HANDLE ();
			hemf.setPointer(new Pointer(t0));
		}
		UINT cEntries = new UINT (t1);
		PALETTEENTRY lppe = new PALETTEENTRY ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetEnhMetaFilePaletteEntries (hemf, cEntries, lppe);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setByteMemoryValue (t2, new LongValue(lppe.peRed.longValue()));
		t2 += 1;
		memory.setByteMemoryValue (t2, new LongValue(lppe.peGreen.longValue()));
		t2 += 1;
		memory.setByteMemoryValue (t2, new LongValue(lppe.peBlue.longValue()));
		t2 += 1;
		memory.setByteMemoryValue (t2, new LongValue(lppe.peFlags.longValue()));
		t2 += 1;

	}
}