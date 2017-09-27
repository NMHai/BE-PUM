/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetCharABCWidthsFloat.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.ABCFLOAT;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetCharABCWidthsFloat extends Gdi32API {
	public GetCharABCWidthsFloat () {
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
		UINT iFirstChar = new UINT (t1);
		UINT iLastChar = new UINT (t2);
		ABCFLOAT lpABCF = new ABCFLOAT ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetCharABCWidthsFloat (hdc, iFirstChar, iLastChar, lpABCF);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		memory.setDoubleWordMemoryValue (t3, new LongValue(lpABCF.abcfA));
		t3 += 4;
		memory.setDoubleWordMemoryValue (t3, new LongValue(lpABCF.abcfB));
		t3 += 4;
		memory.setDoubleWordMemoryValue (t3, new LongValue(lpABCF.abcfC));
		t3 += 4;

	}
}