/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetNamedPipeClientComputerName.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;
 
public class GetNamedPipeClientComputerName extends Kernel32API {
	public GetNamedPipeClientComputerName () {
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
		HANDLE Pipe = null;
		if ( t0 != 0L ) {
			Pipe = new HANDLE ();
			Pipe.setPointer(new Pointer(t0));
		}
		String ClientComputerName = null;
		if ( t1 != 0L ) {
			ClientComputerName = memory.getText(this, t1);
		}
		ULONG ClientComputerNameLength = new ULONG ((int) t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetNamedPipeClientComputerName (Pipe, ClientComputerName, ClientComputerNameLength);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t1, new String(ClientComputerName));
	}
}