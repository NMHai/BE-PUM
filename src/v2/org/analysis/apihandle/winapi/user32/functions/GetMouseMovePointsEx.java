/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetMouseMovePointsEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.MOUSEMOVEPOINT;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetMouseMovePointsEx extends User32API {
	public GetMouseMovePointsEx () {
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
		UINT cbSize = new UINT (t0);
		MOUSEMOVEPOINT lppt = null;
		if ( t1 != 0L) {
			lppt = new MOUSEMOVEPOINT ();
			lppt.x = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lppt.y = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			lppt.time = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lppt.dwExtraInfo = new ULONG_PTR (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
		}
		MOUSEMOVEPOINT lpptBuf = new MOUSEMOVEPOINT ();
		int nBufPoints = (int) t3;
		DWORD resolution = new DWORD (t4);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.GetMouseMovePointsEx (cbSize, lppt, lpptBuf, nBufPoints, resolution);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpptBuf.x));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpptBuf.y));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpptBuf.time.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpptBuf.dwExtraInfo.longValue()));
		t2 += 4;

	}
}