/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetScrollBarInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LONG;

import v2.org.analysis.apihandle.structures.SCROLLBARINFO;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetScrollBarInfo extends User32API {
	public GetScrollBarInfo () {
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
		HWND hwnd = null;
		if ( t0 != 0L ) {
			hwnd = new HWND ();
			hwnd.setPointer(new Pointer(t0));
		}
		LONG idObject = new LONG (t1);
		SCROLLBARINFO psbi = new SCROLLBARINFO ();

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetScrollBarInfo (hwnd, idObject, psbi);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setDoubleWordMemoryValue (t2, new LongValue(psbi.cbSize.longValue()));
		t2 += 4;
		// Nested Structure
			psbi.rcScrollBar.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue();
			psbi.rcScrollBar.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			psbi.rcScrollBar.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			psbi.rcScrollBar.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
		memory.setDoubleWordMemoryValue (t2, new LongValue(psbi.dxyLineButton));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(psbi.xyThumbTop));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(psbi.xyThumbBottom));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(psbi.reserved));
		t2 += 4;
		for (int i = 0; i < psbi.rgstate.length; i++) {
			memory.setDoubleWordMemoryValue (t2, new LongValue(psbi.rgstate [i].longValue()));
			t2 += 4;
		}

	}
}