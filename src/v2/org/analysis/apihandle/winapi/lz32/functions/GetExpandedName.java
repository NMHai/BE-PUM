/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.lz32.functions
 * File name: GetExpandedName.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.lz32.functions;

import v2.org.analysis.apihandle.winapi.lz32.Lz32API;
import v2.org.analysis.apihandle.winapi.lz32.Lz32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetExpandedName extends Lz32API {
	public GetExpandedName () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String lpszSource = null;
		if ( t0 != 0L ) lpszSource = memory.getText(this, t0);
		String lpszBuffer = null;
		if ( t1 != 0L ) lpszBuffer = memory.getText(this, t1);

		// Step 3: call API function
		int ret = Lz32DLL.INSTANCE.GetExpandedName (lpszSource, lpszBuffer);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t1, new String(lpszBuffer));
	}
}