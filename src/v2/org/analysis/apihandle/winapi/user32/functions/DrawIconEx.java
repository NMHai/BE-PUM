/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DrawIconEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HBRUSH;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HICON;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DrawIconEx extends User32API {
	public DrawIconEx () {
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
		int xLeft = (int) t1;
		int yTop = (int) t2;
		HICON hIcon = null;
		if ( t3 != 0L ) {
			hIcon = new HICON ();
			hIcon.setPointer(new Pointer(t3));
		}
		int cxWidth = (int) t4;
		int cyWidth = (int) t5;
		UINT istepIfAniCur = new UINT (t6);
		HBRUSH hbrFlickerFreeDraw = null;
		if ( t7 != 0L ) {
			hbrFlickerFreeDraw = new HBRUSH ();
			hbrFlickerFreeDraw.setPointer(new Pointer(t7));
		}
		UINT diFlags = new UINT (t8);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DrawIconEx (hdc, xLeft, yTop, hIcon, cxWidth, cyWidth, istepIfAniCur, hbrFlickerFreeDraw, diFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}