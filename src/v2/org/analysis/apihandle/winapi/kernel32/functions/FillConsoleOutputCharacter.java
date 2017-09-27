/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FillConsoleOutputCharacter.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.COORD;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class FillConsoleOutputCharacter extends Kernel32API {
	public FillConsoleOutputCharacter () {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hConsoleOutput = null;
		if ( t0 != 0L ) {
			hConsoleOutput = new HANDLE ();
			hConsoleOutput.setPointer(new Pointer(t0));
		}
		char cCharacter = (char) t1;
		DWORD nLength = new DWORD (t2);
		COORD dwWriteCoord = null;
		if ( t3 != 0L) {
			dwWriteCoord = new COORD ();
			dwWriteCoord.X = new SHORT (((LongValue)memory.getWordMemoryValue (t3)).getValue());
			t3 += 2;
			dwWriteCoord.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t3)).getValue());
			t3 += 2;
		}
		int[] lpNumberOfCharsWritten = null;
		if ( t4 != 0L ) lpNumberOfCharsWritten = new int[(int) t2];
		for (int i = 0; i < lpNumberOfCharsWritten.length; i++) {
			lpNumberOfCharsWritten [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.FillConsoleOutputCharacter (hConsoleOutput, cCharacter, nLength, dwWriteCoord, lpNumberOfCharsWritten);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t4 = this.params.get(4);
		for (int i = 0; i < lpNumberOfCharsWritten.length; i++) {
			memory.setDoubleWordMemoryValue (t4, new LongValue(lpNumberOfCharsWritten [i]));
			t4 += 4;
		}
	}
}