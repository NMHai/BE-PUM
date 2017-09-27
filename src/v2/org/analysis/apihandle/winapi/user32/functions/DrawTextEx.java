/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DrawTextEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.DRAWTEXTPARAMS;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DrawTextEx extends User32API {
	public DrawTextEx () {
		super();
		NUM_OF_PARMS = 6;
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
		
		// Step 2: type conversion from C++ to Java
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		String lpchText = null;
		if ( t1 != 0L ) lpchText = memory.getText(this, t1);
		int cchText = (int) t2;
		RECT lprc = null;
		if ( t3 != 0L) {
			lprc = new RECT ();
			lprc.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			lprc.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			lprc.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			lprc.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
		}
		UINT dwDTFormat = new UINT (t4);
		DRAWTEXTPARAMS lpDTParams = null;
		if ( t5 != 0L) {
			lpDTParams = new DRAWTEXTPARAMS ();
			lpDTParams.cbSize = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t5)).getValue());
			t5 += 4;
			lpDTParams.iTabLength = (int) ((LongValue)memory.getDoubleWordMemoryValue (t5)).getValue();
			t5 += 4;
			lpDTParams.iLeftMargin = (int) ((LongValue)memory.getDoubleWordMemoryValue (t5)).getValue();
			t5 += 4;
			lpDTParams.iRightMargin = (int) ((LongValue)memory.getDoubleWordMemoryValue (t5)).getValue();
			t5 += 4;
			lpDTParams.uiLengthDrawn = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t5)).getValue());
			t5 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DrawTextEx (hdc, lpchText, cchText, lprc, dwDTFormat, lpDTParams);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}