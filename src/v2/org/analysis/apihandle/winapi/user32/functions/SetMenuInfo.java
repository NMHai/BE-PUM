/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SetMenuInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HBRUSH;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.MENUINFO;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetMenuInfo extends User32API {
	public SetMenuInfo () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HMENU hmenu = null;
		if ( t0 != 0L ) {
			hmenu = new HMENU ();
			hmenu.setPointer(new Pointer(t0));
		}
		MENUINFO lpcmi = null;
		if ( t1 != 0L) {
			lpcmi = new MENUINFO ();
			lpcmi.cbSize = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpcmi.fMask = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpcmi.dwStyle = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpcmi.cyMax = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpcmi.hbrBack = new HBRUSH (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue()));
			t1 += 4;
			lpcmi.dwContextHelpID = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpcmi.dwMenuData = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.SetMenuInfo (hmenu, lpcmi);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}