/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: ReadConsoleOutputAttribute.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.structures.COORD;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class ReadConsoleOutputAttribute extends Kernel32API {
	public ReadConsoleOutputAttribute () {
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
		short[] lpAttribute = null;
		if ( t1 != 0L ) lpAttribute = new short[(int) t2];
		for (int i = 0; i < lpAttribute.length; i++) {
			lpAttribute [i] = (short) ((LongValue) memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
		}
		DWORD nLength = new DWORD (t2);
		COORD dwReadCoord = null;
		if ( t3 != 0L) {
			dwReadCoord = new COORD ();
			dwReadCoord.X = new SHORT (((LongValue)memory.getWordMemoryValue (t3)).getValue());
			t3 += 2;
			dwReadCoord.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t3)).getValue());
			t3 += 2;
		}
		IntByReference lpNumberOfAttrsRead = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.ReadConsoleOutputAttribute (hConsoleOutput, lpAttribute, nLength, dwReadCoord, lpNumberOfAttrsRead);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < lpAttribute.length; i++) {
			memory.setWordMemoryValue (t1, new LongValue(lpAttribute [i]));
			t1 += 2;
		}		memory.setDoubleWordMemoryValue(t4, new LongValue(lpNumberOfAttrsRead.getValue()));

		

	}
}