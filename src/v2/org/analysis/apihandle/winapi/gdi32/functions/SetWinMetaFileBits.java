/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: SetWinMetaFileBits.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.METAFILEPICT;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetWinMetaFileBits extends Gdi32API {
	public SetWinMetaFileBits () {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		
		// Step 2: type conversion from C++ to Java
		UINT cbBuffer = new UINT (t0);
		byte[] lpbBuffer = null;
		if ( t1 != 0L ) lpbBuffer = new byte[(int) t2];
		for (int i = 0; i < lpbBuffer.length; i++) {
			lpbBuffer [i] = (byte) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		HDC hdcRef = null;
		if ( t2 != 0L ) {
			hdcRef = new HDC ();
			hdcRef.setPointer(new Pointer(t2));
		}
		METAFILEPICT lpmfp = null;
		if ( t3 != 0L) {
			lpmfp = new METAFILEPICT ();
			lpmfp.mm = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpmfp.xExt = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpmfp.yExt = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpmfp.hMF = new HANDLE (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue()));
			t3 += 4;
		}

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.SetWinMetaFileBits (cbBuffer, lpbBuffer, hdcRef, lpmfp);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}