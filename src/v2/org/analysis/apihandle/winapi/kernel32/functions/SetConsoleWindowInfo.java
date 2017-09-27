/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetConsoleWindowInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.SMALL_RECT;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetConsoleWindowInfo extends Kernel32API {
	public SetConsoleWindowInfo () {
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
		HANDLE hConsoleOutput = null;
		if ( t0 != 0L ) {
			hConsoleOutput = new HANDLE ();
			hConsoleOutput.setPointer(new Pointer(t0));
		}
		BOOL bAbsolute = new BOOL (t1);
		SMALL_RECT lpConsoleWindow = null;
		if ( t2 != 0L) {
			lpConsoleWindow = new SMALL_RECT ();
			lpConsoleWindow.Left = new SHORT (((LongValue)memory.getWordMemoryValue (t2)).getValue());
			t2 += 2;
			lpConsoleWindow.Top = new SHORT (((LongValue)memory.getWordMemoryValue (t2)).getValue());
			t2 += 2;
			lpConsoleWindow.Right = new SHORT (((LongValue)memory.getWordMemoryValue (t2)).getValue());
			t2 += 2;
			lpConsoleWindow.Bottom = new SHORT (((LongValue)memory.getWordMemoryValue (t2)).getValue());
			t2 += 2;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetConsoleWindowInfo (hConsoleOutput, bAbsolute, lpConsoleWindow);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}