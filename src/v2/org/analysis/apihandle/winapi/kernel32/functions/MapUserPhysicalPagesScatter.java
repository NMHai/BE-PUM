/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: MapUserPhysicalPagesScatter.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class MapUserPhysicalPagesScatter extends Kernel32API {
	public MapUserPhysicalPagesScatter () {
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
		PointerByReference VirtualAddresses = new PointerByReference (new Pointer(t0));
		ULONG_PTR NumberOfPages = new ULONG_PTR (t1);
		IntByReference PageArray = new IntByReference ((int) t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.MapUserPhysicalPagesScatter (VirtualAddresses, NumberOfPages, PageArray);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}