/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetCurrentConsoleFontEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.structures.CONSOLE_FONT_INFOEX;
import v2.org.analysis.apihandle.structures.COORD;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;
 
public class SetCurrentConsoleFontEx extends Kernel32API {
	public SetCurrentConsoleFontEx () {
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
		CONSOLE_FONT_INFOEX lpConsoleCurrentFontEx = null;
		if ( t2 != 0L) {
			lpConsoleCurrentFontEx = new CONSOLE_FONT_INFOEX ();
			lpConsoleCurrentFontEx.cbSize = new ULONG ((int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lpConsoleCurrentFontEx.nFont = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lpConsoleCurrentFontEx.dwFontSize = new COORD ();
			// Nested Structure
			lpConsoleCurrentFontEx.dwFontSize.X = new SHORT (((LongValue)memory.getWordMemoryValue (t2 += 0)).getValue());
			lpConsoleCurrentFontEx.dwFontSize.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t2 += 2)).getValue());
			lpConsoleCurrentFontEx.FontFamily = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lpConsoleCurrentFontEx.FontWeight = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			for (int i = 0; i < lpConsoleCurrentFontEx.FaceName.length; i++) {
				lpConsoleCurrentFontEx.FaceName [i] = (char) ((LongValue) memory.getWordMemoryValue (t2)).getValue();
				t2 += 2;
			}
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetCurrentConsoleFontEx (hConsoleOutput, bMaximumWindow, lpConsoleCurrentFontEx);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}