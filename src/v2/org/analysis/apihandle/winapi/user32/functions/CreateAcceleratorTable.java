/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CreateAcceleratorTable.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.WORD;

import v2.org.analysis.apihandle.structures.ACCEL;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateAcceleratorTable extends User32API {
	public CreateAcceleratorTable () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		ACCEL lpaccl = null;
		if ( t0 != 0L) {
			lpaccl = new ACCEL ();
			lpaccl.fVirt = new BYTE (((LongValue)memory.getByteMemoryValue (t0)).getValue());
			t0 += 1;
			lpaccl.key = new WORD (((LongValue)memory.getWordMemoryValue (t0)).getValue());
			t0 += 2;
			lpaccl.cmd = new WORD (((LongValue)memory.getWordMemoryValue (t0)).getValue());
			t0 += 2;
		}
		int cEntries = (int) t1;

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CreateAcceleratorTable (lpaccl, cEntries);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}