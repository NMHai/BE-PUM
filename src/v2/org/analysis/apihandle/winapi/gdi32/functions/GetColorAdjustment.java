/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetColorAdjustment.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;

import v2.org.analysis.apihandle.structures.COLORADJUSTMENT;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetColorAdjustment extends Gdi32API {
	public GetColorAdjustment () {
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
		COLORADJUSTMENT lpca = new COLORADJUSTMENT ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetColorAdjustment (hdc, lpca);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setWordMemoryValue (t1, new LongValue(lpca.caSize.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpca.caFlags.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpca.caIlluminantIndex.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpca.caRedGamma.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpca.caGreenGamma.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpca.caBlueGamma.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpca.caReferenceBlack.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpca.caReferenceWhite.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpca.caContrast.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpca.caBrightness.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpca.caColorfulness.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpca.caRedGreenTint.longValue()));
		t1 += 2;

	}
}