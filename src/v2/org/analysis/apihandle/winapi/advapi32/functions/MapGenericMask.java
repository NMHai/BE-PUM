/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: MapGenericMask.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.GENERIC_MAPPING;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class MapGenericMask extends Advapi32API {
	public MapGenericMask () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		IntByReference AccessMask = new IntByReference ((int) t0);
		GENERIC_MAPPING GenericMapping = null;
		if ( t1 != 0L) {
			GenericMapping = new GENERIC_MAPPING ();
			GenericMapping.genericRead = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			GenericMapping.genericWrite = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			GenericMapping.genericExecute = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			GenericMapping.genericAll = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
		}

		// Step 3: call API function
		Advapi32DLL.INSTANCE.MapGenericMask (AccessMask, GenericMapping);
		
		// Step 4: update environment (memory & eax register)

	}
}