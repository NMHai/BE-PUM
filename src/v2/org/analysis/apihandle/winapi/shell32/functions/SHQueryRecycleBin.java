/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHQueryRecycleBin.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.structures.SHQUERYRBINFO;
import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class SHQueryRecycleBin extends Shell32API {
	public SHQueryRecycleBin () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String pszRootPath = null;
		if ( t0 != 0L ) pszRootPath = memory.getText(this, t0);
		SHQUERYRBINFO pSHQueryRBInfo = null;
		if ( t1 != 0L) {
			pSHQueryRBInfo = new SHQUERYRBINFO ();
			pSHQueryRBInfo.cbSize = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			pSHQueryRBInfo.i64Size = (long) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			pSHQueryRBInfo.i64NumItems = (long) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
		}

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.SHQueryRecycleBin (pszRootPath, pSHQueryRBInfo);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}