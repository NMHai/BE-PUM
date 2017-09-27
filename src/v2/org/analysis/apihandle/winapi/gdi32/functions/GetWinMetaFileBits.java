/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetWinMetaFileBits.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetWinMetaFileBits extends Gdi32API {
	public GetWinMetaFileBits () {
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
		HANDLE hemf = null;
		if ( t0 != 0L ) {
			hemf = new HANDLE ();
			hemf.setPointer(new Pointer(t0));
		}
		UINT cbBuffer = new UINT (t1);
		byte[] lpbBuffer = null;
		if ( t2 != 0L ) lpbBuffer = new byte[(int) t3];
		for (int i = 0; i < lpbBuffer.length; i++) {
			lpbBuffer [i] = (byte) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		int fnMapMode = (int) t3;
		HDC hdcRef = null;
		if ( t4 != 0L ) {
			hdcRef = new HDC ();
			hdcRef.setPointer(new Pointer(t4));
		}

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetWinMetaFileBits (hemf, cbBuffer, lpbBuffer, fnMapMode, hdcRef);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < lpbBuffer.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(lpbBuffer [i]));
			t2 += 1;
		}
	}
}