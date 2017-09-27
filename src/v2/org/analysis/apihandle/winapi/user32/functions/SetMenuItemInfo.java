/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SetMenuItemInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.MENUITEMINFO;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetMenuItemInfo extends User32API {
	public SetMenuItemInfo () {
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
		HMENU hMenu = null;
		if ( t0 != 0L ) {
			hMenu = new HMENU ();
			hMenu.setPointer(new Pointer(t0));
		}
		UINT uItem = new UINT (t1);
		BOOL fByPosition = new BOOL (t2);
		MENUITEMINFO lpmii = null;
		if ( t3 != 0L) {
			lpmii = new MENUITEMINFO ();
			lpmii.cbSize = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpmii.fMask = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpmii.fType = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpmii.fState = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpmii.wID = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpmii.hSubMenu = new HMENU (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue()));
			t3 += 4;
			lpmii.hbmpChecked = new HBITMAP (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue()));
			t3 += 4;
			lpmii.hbmpUnchecked = new HBITMAP (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue()));
			t3 += 4;
			lpmii.dwItemData = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpmii.dwTypeData = memory.getText(this, t3);
			t3 += 4;
			lpmii.cch = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpmii.hbmpItem = new HBITMAP (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue()));
			t3 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.SetMenuItemInfo (hMenu, uItem, fByPosition, lpmii);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}