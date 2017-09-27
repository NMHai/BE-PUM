/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetTabbedTextExtent.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetTabbedTextExtent extends User32API {
	public GetTabbedTextExtent () {
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
		HDC hDC = null;
		if ( t0 != 0L ) {
			hDC = new HDC ();
			hDC.setPointer(new Pointer(t0));
		}
		String lpString = null;
		if ( t1 != 0L ) lpString = memory.getText(this, t1);
		int nCount = (int) t2;
		int nTabPositions = (int) t3;
		IntByReference lpnTabStopPositions = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetTabbedTextExtent (hDC, lpString, nCount, nTabPositions, lpnTabStopPositions);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}