/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: CreateScalableFontResource.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateScalableFontResource extends Gdi32API {
	public CreateScalableFontResource () {
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
		DWORD fdwHidden = new DWORD (t0);
		String lpszFontRes = null;
		if ( t1 != 0L ) lpszFontRes = memory.getText(this, t1);
		String lpszFontFile = null;
		if ( t2 != 0L ) lpszFontFile = memory.getText(this, t2);
		String lpszCurrentPath = null;
		if ( t3 != 0L ) lpszCurrentPath = memory.getText(this, t3);

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.CreateScalableFontResource (fdwHidden, lpszFontRes, lpszFontFile, lpszCurrentPath);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}