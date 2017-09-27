/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetTextExtentExPointI.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinUser.SIZE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetTextExtentExPointI extends Gdi32API {
	public GetTextExtentExPointI () {
		super();
		NUM_OF_PARMS = 7;
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
		
		// Step 2: type conversion from C++ to Java
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		short[] pgiIn = null;
		if ( t1 != 0L ) pgiIn = new short[(int) t2];
		for (int i = 0; i < pgiIn.length; i++) {
			pgiIn [i] = (short) ((LongValue) memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
		}
		int cgi = (int) t2;
		int nMaxExtent = (int) t3;
		IntByReference lpnFit = new IntByReference ((int) t4);
		int[] alpDx = null;
		if ( t5 != 0L ) alpDx = new int[(int) t6];
		for (int i = 0; i < alpDx.length; i++) {
			alpDx [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t5)).getValue();
			t5 += 4;
		}
		SIZE lpSize = new SIZE ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetTextExtentExPointI (hdc, pgiIn, cgi, nMaxExtent, lpnFit, alpDx, lpSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t4, new LongValue(lpnFit.getValue()));

		
		t5 = this.params.get(5);
		for (int i = 0; i < alpDx.length; i++) {
			memory.setDoubleWordMemoryValue (t5, new LongValue(alpDx [i]));
			t5 += 4;
		}		t6 = this.params.get(6);
		memory.setDoubleWordMemoryValue (t6, new LongValue(lpSize.cx));
		t6 += 4;
		memory.setDoubleWordMemoryValue (t6, new LongValue(lpSize.cy));
		t6 += 4;

	}
}