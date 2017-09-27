/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: CommandLineToArgvW.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class CommandLineToArgvW extends Shell32API {
	public CommandLineToArgvW () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		CHARByReference lpCmdLine = new CHARByReference (new CHAR(t0));
		IntByReference pNumArgs = new IntByReference ((int) t1);

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.CommandLineToArgvW (lpCmdLine, pNumArgs);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(pNumArgs.getValue()));

		

	}
}