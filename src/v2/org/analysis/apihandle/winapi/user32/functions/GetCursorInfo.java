/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetCursorInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HCURSOR;
import com.sun.jna.platform.win32.WinDef.POINT;

import v2.org.analysis.apihandle.structures.CURSORINFO;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetCursorInfo extends User32API {
	public GetCursorInfo () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		CURSORINFO pci = null;
		if ( t0 != 0L) {
			pci = new CURSORINFO ();
			pci.cbSize = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			pci.flags = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			pci.hCursor = new HCURSOR (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue()));
			t0 += 4;
			pci.ptScreenPos = new POINT ();
			// Nested Structure
			pci.ptScreenPos.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0 += 0)).getValue();
			pci.ptScreenPos.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0 += 4)).getValue();
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetCursorInfo (pci);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}