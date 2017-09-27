/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetConsoleCursorInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.CONSOLE_CURSOR_INFO;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetConsoleCursorInfo extends Kernel32API {
	public SetConsoleCursorInfo () {
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
		CONSOLE_CURSOR_INFO lpConsoleCursorInfo = null;
		if ( t1 != 0L) {
			lpConsoleCursorInfo = new CONSOLE_CURSOR_INFO ();
			lpConsoleCursorInfo.dwSize = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpConsoleCursorInfo.bVisible = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetConsoleCursorInfo (hConsoleOutput, lpConsoleCursorInfo);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}