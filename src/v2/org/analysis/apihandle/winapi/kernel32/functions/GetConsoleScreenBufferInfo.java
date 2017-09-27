/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetConsoleScreenBufferInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.CONSOLE_SCREEN_BUFFER_INFO;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetConsoleScreenBufferInfo extends Kernel32API {
	public GetConsoleScreenBufferInfo () {
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
		CONSOLE_SCREEN_BUFFER_INFO lpConsoleScreenBufferInfo = new CONSOLE_SCREEN_BUFFER_INFO ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetConsoleScreenBufferInfo (hConsoleOutput, lpConsoleScreenBufferInfo);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		// Nested Structure
			lpConsoleScreenBufferInfo.dwSize.X = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfo.dwSize.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
		// Nested Structure
			lpConsoleScreenBufferInfo.dwCursorPosition.X = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfo.dwCursorPosition.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
		memory.setWordMemoryValue (t1, new LongValue(lpConsoleScreenBufferInfo.wAttributes.longValue()));
		t1 += 2;
		// Nested Structure
			lpConsoleScreenBufferInfo.srWindow.Left = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfo.srWindow.Top = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
			lpConsoleScreenBufferInfo.srWindow.Right = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
			lpConsoleScreenBufferInfo.srWindow.Bottom = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());
		// Nested Structure
			lpConsoleScreenBufferInfo.dwMaximumWindowSize.X = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 0)).getValue());
			lpConsoleScreenBufferInfo.dwMaximumWindowSize.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t1 += 2)).getValue());

	}
}