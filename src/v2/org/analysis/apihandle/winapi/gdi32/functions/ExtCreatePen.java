/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: ExtCreatePen.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.structures.LOGBRUSH;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class ExtCreatePen extends Gdi32API {
	public ExtCreatePen () {
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
		DWORD dwPenStyle = new DWORD (t0);
		DWORD dwWidth = new DWORD (t1);
		LOGBRUSH lplb = null;
		if ( t2 != 0L) {
			lplb = new LOGBRUSH ();
			lplb.lbStyle = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lplb.lbColor = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lplb.lbHatch = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
		}
		DWORD dwStyleCount = new DWORD (t3);
		IntByReference lpStyle = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.ExtCreatePen (dwPenStyle, dwWidth, lplb, dwStyleCount, lpStyle);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}