/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegEnumKeyEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegEnumKeyEx extends Advapi32API {
	public RegEnumKeyEx () {
		super();
		NUM_OF_PARMS = 8;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		long t6 = this.params.get(6);
		long t7 = this.params.get(7);
		
		// Step 2: type conversion from C++ to Java
		HKEY hKey = null;
		if ( t0 != 0L ) {
			hKey = new HKEY ();
			hKey.setPointer(new Pointer(t0));
		}
		DWORD dwIndex = new DWORD (t1);
		String lpName = null;
		if ( t2 != 0L ) lpName = memory.getText(this, t2);
		IntByReference lpcName = new IntByReference ((int) t3);
		IntByReference lpReserved = new IntByReference ((int) t4);
		String lpClass = null;
		if ( t5 != 0L ) lpClass = memory.getText(this, t5);
		IntByReference lpcClass = new IntByReference ((int) t6);
		FILETIME lpftLastWriteTime = new FILETIME ();

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegEnumKeyEx (hKey, dwIndex, lpName, lpcName, lpReserved, lpClass, lpcClass, lpftLastWriteTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t2, new String(lpName));		t7 = this.params.get(7);
		memory.setDoubleWordMemoryValue (t7, new LongValue(lpftLastWriteTime.dwLowDateTime));
		t7 += 4;
		memory.setDoubleWordMemoryValue (t7, new LongValue(lpftLastWriteTime.dwHighDateTime));
		t7 += 4;

	}
}