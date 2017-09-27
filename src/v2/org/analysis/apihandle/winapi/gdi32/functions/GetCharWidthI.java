/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetCharWidthI.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetCharWidthI extends Gdi32API {
	public GetCharWidthI () {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		
		// Step 2: type conversion from C++ to Java
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		UINT giFirst = new UINT (t1);
		UINT cgi = new UINT (t2);
		ShortByReference pgi = new ShortByReference ((short) t3);
		IntByReference lpBuffer = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetCharWidthI (hdc, giFirst, cgi, pgi, lpBuffer);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t4, new LongValue(lpBuffer.getValue()));

		

	}
}