/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: CreateFontIndirect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.LONG;

import v2.org.analysis.apihandle.structures.LOGFONT;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateFontIndirect extends Gdi32API {
	public CreateFontIndirect () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		LOGFONT lplf = null;
		if ( t0 != 0L) {
			lplf = new LOGFONT ();
			lplf.lfHeight = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lplf.lfWidth = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lplf.lfEscapement = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lplf.lfOrientation = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lplf.lfWeight = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lplf.lfItalic = new BYTE (((LongValue)memory.getByteMemoryValue (t0)).getValue());
			t0 += 1;
			lplf.lfUnderline = new BYTE (((LongValue)memory.getByteMemoryValue (t0)).getValue());
			t0 += 1;
			lplf.lfStrikeOut = new BYTE (((LongValue)memory.getByteMemoryValue (t0)).getValue());
			t0 += 1;
			lplf.lfCharSet = new BYTE (((LongValue)memory.getByteMemoryValue (t0)).getValue());
			t0 += 1;
			lplf.lfOutPrecision = new BYTE (((LongValue)memory.getByteMemoryValue (t0)).getValue());
			t0 += 1;
			lplf.lfClipPrecision = new BYTE (((LongValue)memory.getByteMemoryValue (t0)).getValue());
			t0 += 1;
			lplf.lfQuality = new BYTE (((LongValue)memory.getByteMemoryValue (t0)).getValue());
			t0 += 1;
			lplf.lfPitchAndFamily = new BYTE (((LongValue)memory.getByteMemoryValue (t0)).getValue());
			t0 += 1;
			for (int i = 0; i < lplf.lfFaceName.length; i++) {
				lplf.lfFaceName [i] = (char) ((LongValue) memory.getWordMemoryValue (t0)).getValue();
				t0 += 2;
			}
		}

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.CreateFontIndirect (lplf);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}