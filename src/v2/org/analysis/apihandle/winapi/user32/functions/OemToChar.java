/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: OemToChar.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.ptr.ByteByReference;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class OemToChar extends User32API {
	public OemToChar () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		ByteByReference lpszSrc = new ByteByReference ((byte) t0);
		String lpszDst = null;
		if ( t1 != 0L ) lpszDst = memory.getText(this, t1);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.OemToChar (lpszSrc, lpszDst);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t1, new String(lpszDst));
	}
}