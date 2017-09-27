/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegQueryMultipleValues.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.structures.VALENT;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegQueryMultipleValues extends Advapi32API {
	public RegQueryMultipleValues () {
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
		HKEY hKey = null;
		if ( t0 != 0L ) {
			hKey = new HKEY ();
			hKey.setPointer(new Pointer(t0));
		}
		VALENT val_list = new VALENT ();
		DWORD num_vals = new DWORD (t2);
		String lpValueBuf = null;
		if ( t3 != 0L ) lpValueBuf = memory.getText(this, t3);
		IntByReference ldwTotsize = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegQueryMultipleValues (hKey, val_list, num_vals, lpValueBuf, ldwTotsize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setText(this, t1, new String(val_list.ve_valuename));
		memory.setDoubleWordMemoryValue (t1, new LongValue(val_list.ve_valuelen.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(val_list.ve_valueptr.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(val_list.ve_type.longValue()));
		t1 += 4;
		memory.setText(this, t3, new String(lpValueBuf));
	}
}