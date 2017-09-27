/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetAltTabInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.ALTTABINFO;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetAltTabInfo extends User32API {
	public GetAltTabInfo () {
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
		HWND hwnd = null;
		if ( t0 != 0L ) {
			hwnd = new HWND ();
			hwnd.setPointer(new Pointer(t0));
		}
		int iItem = (int) t1;
		ALTTABINFO pati = null;
		if ( t2 != 0L) {
			pati = new ALTTABINFO ();
			pati.cbSize = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			pati.cItems = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			pati.cColumns = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			pati.cRows = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			pati.iColFocus = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			pati.iRowFocus = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			pati.cxItem = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			pati.cyItem = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			pati.ptStart = new POINT ();
			// Nested Structure
			pati.ptStart.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue();
			pati.ptStart.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
		}
		String pszItemText = null;
		if ( t3 != 0L ) pszItemText = memory.getText(this, t3);
		UINT cchItemText = new UINT (t4);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetAltTabInfo (hwnd, iItem, pati, pszItemText, cchItemText);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t3, new String(pszItemText));
	}
}