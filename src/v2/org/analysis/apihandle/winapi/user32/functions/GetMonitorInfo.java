/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetMonitorInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.structures.HMONITOR;
import v2.org.analysis.apihandle.winapi.structures.MONITORINFO;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
 
public class GetMonitorInfo extends User32API {
	public GetMonitorInfo () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HMONITOR hMonitor = null;
		if ( t0 != 0L ) {
			hMonitor = new HMONITOR ();
			hMonitor.setPointer(new Pointer(t0));
		}
		MONITORINFO lpmi = new MONITORINFO ();

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetMonitorInfo (hMonitor, lpmi);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpmi.cbSize));
		t1 += 4;
		// Nested Structure
			lpmi.rcMonitor.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue();
			lpmi.rcMonitor.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpmi.rcMonitor.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpmi.rcMonitor.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
		// Nested Structure
			lpmi.rcWork.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue();
			lpmi.rcWork.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpmi.rcWork.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
			lpmi.rcWork.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpmi.dwFlags));
		t1 += 4;

	}
}