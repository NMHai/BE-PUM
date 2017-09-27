/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: CreateFont.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateFont extends Gdi32API {
	public CreateFont () {
		super();
		NUM_OF_PARMS = 14;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		long t6 = this.params.get(6);
		long t7 = this.params.get(7);
		long t8 = this.params.get(8);
		long t9 = this.params.get(9);
		long t10 = this.params.get(10);
		long t11 = this.params.get(11);
		long t12 = this.params.get(12);
		long t13 = this.params.get(13);
		
		// Step 2: type conversion from C++ to Java
		int nHeight = (int) t0;
		int nWidth = (int) t1;
		int nEscapement = (int) t2;
		int nOrientation = (int) t3;
		int fnWeight = (int) t4;
		DWORD fdwItalic = new DWORD (t5);
		DWORD fdwUnderline = new DWORD (t6);
		DWORD fdwStrikeOut = new DWORD (t7);
		DWORD fdwCharSet = new DWORD (t8);
		DWORD fdwOutputPrecision = new DWORD (t9);
		DWORD fdwClipPrecision = new DWORD (t10);
		DWORD fdwQuality = new DWORD (t11);
		DWORD fdwPitchAndFamily = new DWORD (t12);
		String lpszFace = null;
		if ( t13 != 0L ) lpszFace = memory.getText(this, t13);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.CreateFont (nHeight, nWidth, nEscapement, nOrientation, fnWeight, fdwItalic, fdwUnderline, fdwStrikeOut, fdwCharSet, fdwOutputPrecision, fdwClipPrecision, fdwQuality, fdwPitchAndFamily, lpszFace);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}