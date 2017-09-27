/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: SetDIBColorTable.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinGDI.RGBQUAD;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetDIBColorTable extends Gdi32API {
	public SetDIBColorTable () {
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
		UINT uStartIndex = new UINT (t1);
		UINT cEntries = new UINT (t2);
		RGBQUAD pColors = null;
		if ( t3 != 0L) {
			pColors = new RGBQUAD ();
			pColors.rgbBlue = (byte) ((LongValue)memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
			pColors.rgbGreen = (byte) ((LongValue)memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
			pColors.rgbRed = (byte) ((LongValue)memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
			pColors.rgbReserved = (byte) ((LongValue)memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
		}

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.SetDIBColorTable (hdc, uStartIndex, cEntries, pColors);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}