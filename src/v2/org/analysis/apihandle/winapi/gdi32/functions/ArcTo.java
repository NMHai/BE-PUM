/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: ArcTo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class ArcTo extends Gdi32API {
	public ArcTo () {
		super();
		NUM_OF_PARMS = 9;
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
		
		// Step 2: type conversion from C++ to Java
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		int nLeftRect = (int) t1;
		int nTopRect = (int) t2;
		int nRightRect = (int) t3;
		int nBottomRect = (int) t4;
		int nXRadial1 = (int) t5;
		int nYRadial1 = (int) t6;
		int nXRadial2 = (int) t7;
		int nYRadial2 = (int) t8;

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.ArcTo (hdc, nLeftRect, nTopRect, nRightRect, nBottomRect, nXRadial1, nYRadial1, nXRadial2, nYRadial2);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}