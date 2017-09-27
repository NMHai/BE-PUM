/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetEnhMetaFileDescription.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetEnhMetaFileDescription extends Gdi32API {
	public GetEnhMetaFileDescription () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hemf = null;
		if ( t0 != 0L ) {
			hemf = new HANDLE ();
			hemf.setPointer(new Pointer(t0));
		}
		UINT cchBuffer = new UINT (t1);
		String lpszDescription = null;
		if ( t2 != 0L ) lpszDescription = memory.getText(this, t2);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetEnhMetaFileDescription (hemf, cchBuffer, lpszDescription);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t2, new String(lpszDescription));
	}
}