/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetConsoleSelectionInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.SHORT;

import v2.org.analysis.apihandle.structures.CONSOLE_SELECTION_INFO;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetConsoleSelectionInfo extends Kernel32API {
	public GetConsoleSelectionInfo () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		CONSOLE_SELECTION_INFO lpConsoleSelectionInfo = new CONSOLE_SELECTION_INFO ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetConsoleSelectionInfo (lpConsoleSelectionInfo);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpConsoleSelectionInfo.dwFlags.longValue()));
		t0 += 4;
		// Nested Structure
			lpConsoleSelectionInfo.dwSelectionAnchor.X = new SHORT (((LongValue)memory.getWordMemoryValue (t0 += 0)).getValue());
			lpConsoleSelectionInfo.dwSelectionAnchor.Y = new SHORT (((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue());
		// Nested Structure
			lpConsoleSelectionInfo.srSelection.Left = new SHORT (((LongValue)memory.getWordMemoryValue (t0 += 0)).getValue());
			lpConsoleSelectionInfo.srSelection.Top = new SHORT (((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue());
			lpConsoleSelectionInfo.srSelection.Right = new SHORT (((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue());
			lpConsoleSelectionInfo.srSelection.Bottom = new SHORT (((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue());

	}
}