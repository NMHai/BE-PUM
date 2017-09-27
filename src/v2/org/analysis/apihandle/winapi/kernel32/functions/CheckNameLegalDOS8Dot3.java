/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: CheckNameLegalDOS8Dot3.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class CheckNameLegalDOS8Dot3 extends Kernel32API {
	public CheckNameLegalDOS8Dot3 () {
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
		String lpName = null;
		if ( t0 != 0L ) lpName = memory.getText(this, t0);
		byte[] lpOemName = null;
		if ( t1 != 0L ) lpOemName = new byte[(int) t2];
		for (int i = 0; i < lpOemName.length; i++) {
			lpOemName [i] = (byte) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		DWORD OemNameSize = new DWORD (t2);
		IntByReference pbNameContainsSpaces = new IntByReference ((int) t3);
		IntByReference pbNameLegal = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.CheckNameLegalDOS8Dot3 (lpName, lpOemName, OemNameSize, pbNameContainsSpaces, pbNameLegal);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		for (int i = 0; i < lpOemName.length; i++) {
			memory.setByteMemoryValue (t1, new LongValue(lpOemName [i]));
			t1 += 1;
		}		memory.setDoubleWordMemoryValue(t3, new LongValue(pbNameContainsSpaces.getValue()));

		
		memory.setDoubleWordMemoryValue(t4, new LongValue(pbNameLegal.getValue()));

		

	}
}