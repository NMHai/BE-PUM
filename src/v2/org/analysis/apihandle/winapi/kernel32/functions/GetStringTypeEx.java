/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetStringTypeEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LCID;
import com.sun.jna.ptr.ShortByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetStringTypeEx extends Kernel32API {
	public GetStringTypeEx () {
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
		LCID Locale = new LCID (t0);
		DWORD dwInfoType = new DWORD (t1);
		String lpSrcStr = null;
		if ( t2 != 0L ) lpSrcStr = memory.getText(this, t2);
		int cchSrc = (int) t3;
		ShortByReference lpCharType = new ShortByReference ((short) t4);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetStringTypeEx (Locale, dwInfoType, lpSrcStr, cchSrc, lpCharType);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t4, new LongValue(lpCharType.getValue()));

		

	}
}