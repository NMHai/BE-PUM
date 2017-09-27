/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetNumaAvailableMemoryNode.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.UCHAR;
import com.sun.jna.ptr.DoubleByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetNumaAvailableMemoryNode extends Kernel32API {
	public GetNumaAvailableMemoryNode () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		UCHAR Node = new UCHAR (t0);
		DoubleByReference AvailableBytes = new DoubleByReference ((double) t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetNumaAvailableMemoryNode (Node, AvailableBytes);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(AvailableBytes.getValue()));

		

	}
}