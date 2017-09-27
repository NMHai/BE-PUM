/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: TabbedTextOut.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class TabbedTextOut extends User32API {
	public TabbedTextOut () {
		super();
		NUM_OF_PARMS = 8;
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
		
		// Step 2: type conversion from C++ to Java
		HDC hDC = null;
		if ( t0 != 0L ) {
			hDC = new HDC ();
			hDC.setPointer(new Pointer(t0));
		}
		int X = (int) t1;
		int Y = (int) t2;
		String lpString = null;
		if ( t3 != 0L ) lpString = memory.getText(this, t3);
		int nCount = (int) t4;
		int nTabPositions = (int) t5;
		int[] lpnTabStopPositions = null;
		if ( t6 != 0L ) lpnTabStopPositions = new int[(int) t7];
		for (int i = 0; i < lpnTabStopPositions.length; i++) {
			lpnTabStopPositions [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t6)).getValue();
			t6 += 4;
		}
		int nTabOrigin = (int) t7;

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.TabbedTextOut (hDC, X, Y, lpString, nCount, nTabPositions, lpnTabStopPositions, nTabOrigin);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}