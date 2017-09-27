/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FoldString.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class FoldString extends Kernel32API {
	public FoldString () {
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
		DWORD dwMapFlags = new DWORD (t0);
		String lpSrcStr = null;
		if ( t1 != 0L ) lpSrcStr = memory.getText(this, t1);
		int cchSrc = (int) t2;
		char[] lpDestStr = null;
		if ( t3 != 0L ) lpDestStr = new char[(int) t4];
		for (int i = 0; i < lpDestStr.length; i++) {
			lpDestStr [i] = (char) ((LongValue) memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
		}
		int cchDest = (int) t4;

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.FoldString (dwMapFlags, lpSrcStr, cchSrc, lpDestStr, cchDest);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		for (int i = 0; i < lpDestStr.length; i++) {
			memory.setByteMemoryValue (t3, new LongValue(lpDestStr [i]));
			t3 += 1;
		}
	}
}