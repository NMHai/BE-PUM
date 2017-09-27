/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: OpenEncryptedFileRaw.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.ptr.PointerByReference;
 
public class OpenEncryptedFileRaw extends Advapi32API {
	public OpenEncryptedFileRaw () {
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
		String lpFileName = null;
		if ( t0 != 0L ) {
			lpFileName = memory.getText(this, t0);
		}
		ULONG ulFlags = new ULONG ((int) t1);
		PointerByReference pvContext = new PointerByReference (new Pointer(t2));

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.OpenEncryptedFileRaw (lpFileName, ulFlags, pvContext);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t2, new LongValue(Pointer.nativeValue(pvContext.getValue())));

		

	}
}