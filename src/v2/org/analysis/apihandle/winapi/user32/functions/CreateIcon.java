/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CreateIcon.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateIcon extends User32API {
	public CreateIcon () {
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
		HINSTANCE hInstance = null;
		if ( t0 != 0L ) {
			hInstance = new HINSTANCE ();
			hInstance.setPointer(new Pointer(t0));
		}
		int nWidth = (int) t1;
		int nHeight = (int) t2;
		BYTE cPlanes = new BYTE (t3);
		BYTE cBitsPixel = new BYTE (t4);
		byte lpbANDbits = (byte) t5;
		byte lpbXORbits = (byte) t6;

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CreateIcon (hInstance, nWidth, nHeight, cPlanes, cBitsPixel, lpbANDbits, lpbXORbits);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}