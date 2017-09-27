/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: DeleteBoundaryDescriptor.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
 
public class DeleteBoundaryDescriptor extends Kernel32API {
	public DeleteBoundaryDescriptor () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		HANDLE BoundaryDescriptor = null;
		if ( t0 != 0L ) {
			BoundaryDescriptor = new HANDLE ();
			BoundaryDescriptor.setPointer(new Pointer(t0));
		}

		// Step 3: call API function
		Kernel32DLL.INSTANCE.DeleteBoundaryDescriptor (BoundaryDescriptor);
		
		// Step 4: update environment (memory & eax register)

	}
}