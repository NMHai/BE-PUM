/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetConsoleScreenBufferInfoEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.CONSOLE_SCREEN_BUFFER_INFOEX;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetConsoleScreenBufferInfoEx extends Kernel32API {
	public GetConsoleScreenBufferInfoEx () {
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
		CONSOLE_SCREEN_BUFFER_INFOEX lpConsoleScreenBufferInfoEx = new CONSOLE_SCREEN_BUFFER_INFOEX ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetConsoleScreenBufferInfoEx (hConsoleOutput, lpConsoleScreenBufferInfoEx);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpConsoleScreenBufferInfoEx.cbSize.longValue()));
		t1 += 4;
		// Nested Structure
			lpConsoleScreenBufferInfoEx.dwSize.X = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfoEx.dwSize.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
		// Nested Structure
			lpConsoleScreenBufferInfoEx.dwCursorPosition.X = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfoEx.dwCursorPosition.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
		memory.setWordMemoryValue (t1, new LongValue(lpConsoleScreenBufferInfoEx.wAttributes.longValue()));
		t1 += 2;
		// Nested Structure
			lpConsoleScreenBufferInfoEx.srWindow.Left = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfoEx.srWindow.Top = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
			lpConsoleScreenBufferInfoEx.srWindow.Right = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
			lpConsoleScreenBufferInfoEx.srWindow.Bottom = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
		// Nested Structure
			lpConsoleScreenBufferInfoEx.dwMaximumWindowSize.X = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfoEx.dwMaximumWindowSize.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
		memory.setWordMemoryValue (t1, new LongValue(lpConsoleScreenBufferInfoEx.wPopupAttributes.longValue()));
		t1 += 2;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpConsoleScreenBufferInfoEx.bFullscreenSupported.longValue()));
		t1 += 4;
		for (int i = 0; i < lpConsoleScreenBufferInfoEx.ColorTable.length; i++) {
			memory.setDoubleWordMemoryValue (t1, new LongValue(lpConsoleScreenBufferInfoEx.ColorTable [i]));
			t1 += 4;
		}

	}
}