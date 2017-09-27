/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetConsoleScreenBufferSize.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.COORD;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetConsoleScreenBufferSize extends Kernel32API {
	public SetConsoleScreenBufferSize () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hConsoleOutput = null;
		if ( t0 != 0L ) {
			hConsoleOutput = new HANDLE ();
			hConsoleOutput.setPointer(new Pointer(t0));
		}
		COORD dwSize = null;
		if ( t1 != 0L) {
			dwSize = new COORD ();
			dwSize.X = new SHORT (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			dwSize.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetConsoleScreenBufferSize (hConsoleOutput, dwSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}