/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: SetColorAdjustment.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinDef.WORD;

import v2.org.analysis.apihandle.structures.COLORADJUSTMENT;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetColorAdjustment extends Gdi32API {
	public SetColorAdjustment () {
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
		COLORADJUSTMENT lpca = null;
		if ( t1 != 0L) {
			lpca = new COLORADJUSTMENT ();
			lpca.caSize = new WORD (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpca.caFlags = new WORD (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpca.caIlluminantIndex = new WORD (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpca.caRedGamma = new WORD (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpca.caGreenGamma = new WORD (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpca.caBlueGamma = new WORD (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpca.caReferenceBlack = new WORD (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpca.caReferenceWhite = new WORD (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpca.caContrast = new SHORT (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpca.caBrightness = new SHORT (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpca.caColorfulness = new SHORT (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpca.caRedGreenTint = new SHORT (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
		}

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.SetColorAdjustment (hdc, lpca);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}