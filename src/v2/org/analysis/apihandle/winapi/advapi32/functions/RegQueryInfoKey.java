/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegQueryInfoKey.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class RegQueryInfoKey extends Advapi32API {
	public RegQueryInfoKey () {
		super();
		NUM_OF_PARMS = 12;
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
		long t8 = this.params.get(8);
		long t9 = this.params.get(9);
		long t10 = this.params.get(10);
		long t11 = this.params.get(11);
		
		// Step 2: type conversion from C++ to Java
		HKEY hKey = null;
		if ( t0 != 0L ) {
			hKey = new HKEY ();
			hKey.setPointer(new Pointer(t0));
		}
		char[] lpClass = null;
		if ( t1 != 0L ) lpClass = new char[(int) t2];
		for (int i = 0; i < lpClass.length; i++) {
			lpClass [i] = (char) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		IntByReference lpcClass = new IntByReference ((int) t2);
		IntByReference lpReserved = new IntByReference ((int) t3);
		IntByReference lpcSubKeys = new IntByReference ((int) t4);
		IntByReference lpcMaxSubKeyLen = new IntByReference ((int) t5);
		IntByReference lpcMaxClassLen = new IntByReference ((int) t6);
		IntByReference lpcValues = new IntByReference ((int) t7);
		IntByReference lpcMaxValueNameLen = new IntByReference ((int) t8);
		IntByReference lpcMaxValueLen = new IntByReference ((int) t9);
		IntByReference lpcbSecurityDescriptor = new IntByReference ((int) t10);
		FILETIME lpftLastWriteTime = new FILETIME ();

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.RegQueryInfoKey (hKey, lpClass, lpcClass, lpReserved, lpcSubKeys, lpcMaxSubKeyLen, lpcMaxClassLen, lpcValues, lpcMaxValueNameLen, lpcMaxValueLen, lpcbSecurityDescriptor, lpftLastWriteTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < lpClass.length; i++) {
			memory.setByteMemoryValue (t1, new LongValue(lpClass [i]));
			t1 += 1;
		}		memory.setDoubleWordMemoryValue(t4, new LongValue(lpcSubKeys.getValue()));

		
		memory.setDoubleWordMemoryValue(t5, new LongValue(lpcMaxSubKeyLen.getValue()));

		
		memory.setDoubleWordMemoryValue(t6, new LongValue(lpcMaxClassLen.getValue()));

		
		memory.setDoubleWordMemoryValue(t7, new LongValue(lpcValues.getValue()));

		
		memory.setDoubleWordMemoryValue(t8, new LongValue(lpcMaxValueNameLen.getValue()));

		
		memory.setDoubleWordMemoryValue(t9, new LongValue(lpcMaxValueLen.getValue()));

		
		memory.setDoubleWordMemoryValue(t10, new LongValue(lpcbSecurityDescriptor.getValue()));

		
		t11 = this.params.get(11);
		memory.setDoubleWordMemoryValue (t11, new LongValue(lpftLastWriteTime.dwLowDateTime));
		t11 += 4;
		memory.setDoubleWordMemoryValue (t11, new LongValue(lpftLastWriteTime.dwHighDateTime));
		t11 += 4;

	}
}