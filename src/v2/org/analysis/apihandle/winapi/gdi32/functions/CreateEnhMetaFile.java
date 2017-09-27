/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: CreateEnhMetaFile.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateEnhMetaFile extends Gdi32API {
	public CreateEnhMetaFile () {
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
		HDC hdcRef = null;
		if ( t0 != 0L ) {
			hdcRef = new HDC ();
			hdcRef.setPointer(new Pointer(t0));
		}
		String lpFilename = null;
		if ( t1 != 0L ) lpFilename = memory.getText(this, t1);
		RECT lpRect = null;
		if ( t2 != 0L) {
			lpRect = new RECT ();
			lpRect.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lpRect.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lpRect.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			lpRect.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
		}
		String lpDescription = null;
		if ( t3 != 0L ) lpDescription = memory.getText(this, t3);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.CreateEnhMetaFile (hdcRef, lpFilename, lpRect, lpDescription);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}