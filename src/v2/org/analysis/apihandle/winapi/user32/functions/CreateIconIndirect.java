/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: CreateIconIndirect.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.structures.ICONINFO;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
 
public class CreateIconIndirect extends User32API {
	public CreateIconIndirect () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		ICONINFO piconinfo = null;
		if ( t0 != 0L) {
			piconinfo = new ICONINFO ();
			piconinfo.fIcon = (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue() != 0L ) ? true : false;
			t0 += 4;
			piconinfo.xHotspot = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			piconinfo.yHotspot = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			piconinfo.hbmMask = new HBITMAP (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue()));
			t0 += 4;
			piconinfo.hbmColor = new HBITMAP (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue()));
			t0 += 4;
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.CreateIconIndirect (piconinfo);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}