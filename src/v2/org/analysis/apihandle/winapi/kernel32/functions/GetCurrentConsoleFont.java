/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetCurrentConsoleFont.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.CONSOLE_FONT_INFO;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetCurrentConsoleFont extends Kernel32API {
	public GetCurrentConsoleFont () {
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
		BOOL bMaximumWindow = new BOOL (t1);
		CONSOLE_FONT_INFO lpConsoleCurrentFont = new CONSOLE_FONT_INFO ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetCurrentConsoleFont (hConsoleOutput, bMaximumWindow, lpConsoleCurrentFont);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpConsoleCurrentFont.nFont.longValue()));
		t2 += 4;
		// Nested Structure
			lpConsoleCurrentFont.dwFontSize.X = new SHORT (((LongValue)memory.getWordMemoryValue (t2 += 0)).getValue());
			lpConsoleCurrentFont.dwFontSize.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t2 += 2)).getValue());

	}
}