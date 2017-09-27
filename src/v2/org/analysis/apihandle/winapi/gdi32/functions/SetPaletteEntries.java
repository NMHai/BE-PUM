/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: SetPaletteEntries.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.HPALETTE;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.PALETTEENTRY;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetPaletteEntries extends Gdi32API {
	public SetPaletteEntries () {
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
		UINT iStart = new UINT (t1);
		UINT cEntries = new UINT (t2);
		PALETTEENTRY lppe = null;
		if ( t3 != 0L) {
			lppe = new PALETTEENTRY ();
			lppe.peRed = new BYTE (((LongValue)memory.getByteMemoryValue (t3)).getValue());
			t3 += 1;
			lppe.peGreen = new BYTE (((LongValue)memory.getByteMemoryValue (t3)).getValue());
			t3 += 1;
			lppe.peBlue = new BYTE (((LongValue)memory.getByteMemoryValue (t3)).getValue());
			t3 += 1;
			lppe.peFlags = new BYTE (((LongValue)memory.getByteMemoryValue (t3)).getValue());
			t3 += 1;
		}

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.SetPaletteEntries (hpal, iStart, cEntries, lppe);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}