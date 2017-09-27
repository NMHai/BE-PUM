/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetMenuBarInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.structures.MENUBARINFO;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetMenuBarInfo extends User32API {
	public GetMenuBarInfo () {
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
		HWND hwnd = null;
		if ( t0 != 0L ) {
			hwnd = new HWND ();
			hwnd.setPointer(new Pointer(t0));
		}
		LONG idObject = new LONG (t1);
		LONG idItem = new LONG (t2);
		MENUBARINFO pmbi = null;
		if ( t3 != 0L) {
			pmbi = new MENUBARINFO ();
			pmbi.cbSize = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			pmbi.rcBar = new RECT ();
			// Nested Structure
			pmbi.rcBar.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3 += 0)).getValue();
			pmbi.rcBar.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3 += 4)).getValue();
			pmbi.rcBar.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3 += 4)).getValue();
			pmbi.rcBar.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3 += 4)).getValue();
			pmbi.hMenu = new HMENU (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue()));
			t3 += 4;
			pmbi.hwndMenu = new HWND (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue()));
			t3 += 4;
			pmbi.fBarFocused = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			pmbi.fFocused = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetMenuBarInfo (hwnd, idObject, idItem, pmbi);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}