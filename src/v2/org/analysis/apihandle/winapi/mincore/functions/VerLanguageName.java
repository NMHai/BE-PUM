/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mincore.functions
 * File name: VerLanguageName.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mincore.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.mincore.MincoreAPI;
import v2.org.analysis.apihandle.winapi.mincore.MincoreDLL;
import v2.org.analysis.value.LongValue;
 
public class VerLanguageName extends MincoreAPI {
	public VerLanguageName () {
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
		DWORD wLang = new DWORD (t0);
		String szLang = null;
		if ( t1 != 0L ) szLang = memory.getText(this, t1);
		DWORD cchLang = new DWORD (t2);

		// Step 3: call API function
		int ret = MincoreDLL.INSTANCE.VerLanguageName (wLang, szLang, cchLang);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t1, new String(szLang));
	}
}