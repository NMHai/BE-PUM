/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetCurrentConsoleFontEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.CONSOLE_FONT_INFOEX;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetCurrentConsoleFontEx extends Kernel32API {
	public GetCurrentConsoleFontEx () {
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
		CONSOLE_FONT_INFOEX lpConsoleCurrentFontEx = new CONSOLE_FONT_INFOEX ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetCurrentConsoleFontEx (hConsoleOutput, bMaximumWindow, lpConsoleCurrentFontEx);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpConsoleCurrentFontEx.cbSize.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpConsoleCurrentFontEx.nFont.longValue()));
		t2 += 4;
		// Nested Structure
			lpConsoleCurrentFontEx.dwFontSize.X = new SHORT (((LongValue)memory.getWordMemoryValue (t2 += 0)).getValue());
			lpConsoleCurrentFontEx.dwFontSize.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t2 += 2)).getValue());
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpConsoleCurrentFontEx.FontFamily.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpConsoleCurrentFontEx.FontWeight.longValue()));
		t2 += 4;
		for (int i = 0; i < lpConsoleCurrentFontEx.FaceName.length; i++) {
			memory.setWordMemoryValue (t2, new LongValue(lpConsoleCurrentFontEx.FaceName [i]));
			t2 += 2;
		}

	}
}