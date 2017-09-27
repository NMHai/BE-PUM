/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetTextMetrics.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;

import v2.org.analysis.apihandle.structures.TEXTMETRIC;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetTextMetrics extends Gdi32API {
	public GetTextMetrics () {
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
		TEXTMETRIC lptm = new TEXTMETRIC ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetTextMetrics (hdc, lptm);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lptm.tmHeight.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lptm.tmAscent.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lptm.tmDescent.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lptm.tmInternalLeading.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lptm.tmExternalLeading.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lptm.tmAveCharWidth.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lptm.tmMaxCharWidth.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lptm.tmWeight.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lptm.tmOverhang.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lptm.tmDigitizedAspectX.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lptm.tmDigitizedAspectY.longValue()));
		t1 += 4;
		memory.setByteMemoryValue (t1, new LongValue(lptm.tmFirstChar));
		t1 += 1;
		memory.setByteMemoryValue (t1, new LongValue(lptm.tmLastChar));
		t1 += 1;
		memory.setByteMemoryValue (t1, new LongValue(lptm.tmDefaultChar));
		t1 += 1;
		memory.setByteMemoryValue (t1, new LongValue(lptm.tmBreakChar));
		t1 += 1;
		memory.setByteMemoryValue (t1, new LongValue(lptm.tmItalic.longValue()));
		t1 += 1;
		memory.setByteMemoryValue (t1, new LongValue(lptm.tmUnderlined.longValue()));
		t1 += 1;
		memory.setByteMemoryValue (t1, new LongValue(lptm.tmStruckOut.longValue()));
		t1 += 1;
		memory.setByteMemoryValue (t1, new LongValue(lptm.tmPitchAndFamily.longValue()));
		t1 += 1;
		memory.setByteMemoryValue (t1, new LongValue(lptm.tmCharSet.longValue()));
		t1 += 1;

	}
}