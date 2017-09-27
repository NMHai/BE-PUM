/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: Wow64DisableWow64FsRedirection.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class Wow64DisableWow64FsRedirection extends Kernel32API {
	public Wow64DisableWow64FsRedirection () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		PointerByReference OldValue = new PointerByReference (new Pointer(t0));

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.Wow64DisableWow64FsRedirection (OldValue);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t0, new LongValue(Pointer.nativeValue(OldValue.getValue())));

		

	}
}