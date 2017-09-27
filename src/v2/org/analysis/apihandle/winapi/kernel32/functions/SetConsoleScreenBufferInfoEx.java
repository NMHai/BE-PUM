/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetConsoleScreenBufferInfoEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.structures.CONSOLE_SCREEN_BUFFER_INFOEX;
import v2.org.analysis.apihandle.structures.COORD;
import v2.org.analysis.apihandle.structures.SMALL_RECT;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
 
public class SetConsoleScreenBufferInfoEx extends Kernel32API {
	public SetConsoleScreenBufferInfoEx () {
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
		CONSOLE_SCREEN_BUFFER_INFOEX lpConsoleScreenBufferInfoEx = null;
		if ( t1 != 0L) {
			lpConsoleScreenBufferInfoEx = new CONSOLE_SCREEN_BUFFER_INFOEX ();
			lpConsoleScreenBufferInfoEx.cbSize = new ULONG ((int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpConsoleScreenBufferInfoEx.dwSize = new COORD ();
			// Nested Structure
			lpConsoleScreenBufferInfoEx.dwSize.X = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfoEx.dwSize.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
			lpConsoleScreenBufferInfoEx.dwCursorPosition = new COORD ();
			// Nested Structure
			lpConsoleScreenBufferInfoEx.dwCursorPosition.X = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfoEx.dwCursorPosition.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
			lpConsoleScreenBufferInfoEx.wAttributes = new WORD (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpConsoleScreenBufferInfoEx.srWindow = new SMALL_RECT ();
			// Nested Structure
			lpConsoleScreenBufferInfoEx.srWindow.Left = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfoEx.srWindow.Top = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
			lpConsoleScreenBufferInfoEx.srWindow.Right = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
			lpConsoleScreenBufferInfoEx.srWindow.Bottom = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
			lpConsoleScreenBufferInfoEx.dwMaximumWindowSize = new COORD ();
			// Nested Structure
			lpConsoleScreenBufferInfoEx.dwMaximumWindowSize.X = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfoEx.dwMaximumWindowSize.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
			lpConsoleScreenBufferInfoEx.wPopupAttributes = new WORD (((LongValue)memory.getWordMemoryValue (t1)).getValue());
			t1 += 2;
			lpConsoleScreenBufferInfoEx.bFullscreenSupported = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			for (int i = 0; i < lpConsoleScreenBufferInfoEx.ColorTable.length; i++) {
				lpConsoleScreenBufferInfoEx.ColorTable [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t1)).getValue();
				t1 += 4;
			}
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetConsoleScreenBufferInfoEx (hConsoleOutput, lpConsoleScreenBufferInfoEx);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}