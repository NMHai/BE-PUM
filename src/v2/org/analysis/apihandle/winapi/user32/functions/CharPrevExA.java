/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CharPrevExA.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.ptr.ByteByReference;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CharPrevExA extends User32API {
	public CharPrevExA () {
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
		WORD CodePage = new WORD (t0);
		ByteByReference lpStart = new ByteByReference ((byte) t1);
		ByteByReference lpCurrentChar = new ByteByReference ((byte) t2);
		DWORD dwFlags = new DWORD (t3);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CharPrevExA (CodePage, lpStart, lpCurrentChar, dwFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}