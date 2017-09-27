/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetLocaleInfoEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetLocaleInfoEx extends Kernel32API {
	public GetLocaleInfoEx () {
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
		CHARByReference lpLocaleName = new CHARByReference (new CHAR(t0));
		int LCType = (int) t1;
		char[] lpLCData = null;
		if ( t2 != 0L ) lpLCData = new char[(int) t3];
		for (int i = 0; i < lpLCData.length; i++) {
			lpLCData [i] = (char) ((LongValue) memory.getWordMemoryValue (t2)).getValue();
			t2 += 2;
		}
		int cchData = (int) t3;

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetLocaleInfoEx (lpLocaleName, LCType, lpLCData, cchData);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < lpLCData.length; i++) {
			memory.setWordMemoryValue (t2, new LongValue(lpLCData [i]));
			t2 += 2;
		}
	}
}