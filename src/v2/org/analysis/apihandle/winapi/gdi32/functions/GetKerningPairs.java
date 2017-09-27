/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetKerningPairs.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HDC;

import v2.org.analysis.apihandle.structures.KERNINGPAIR;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetKerningPairs extends Gdi32API {
	public GetKerningPairs () {
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
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		DWORD nNumPairs = new DWORD (t1);
		KERNINGPAIR lpkrnpair = new KERNINGPAIR ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetKerningPairs (hdc, nNumPairs, lpkrnpair);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setWordMemoryValue (t2, new LongValue(lpkrnpair.wFirst.longValue()));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpkrnpair.wSecond.longValue()));
		t2 += 2;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpkrnpair.iKernAmount));
		t2 += 4;

	}
}