/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DdeClientTransaction.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DdeClientTransaction extends User32API {
	public DdeClientTransaction () {
		super();
		NUM_OF_PARMS = 8;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		long t6 = this.params.get(6);
		long t7 = this.params.get(7);
		
		// Step 2: type conversion from C++ to Java
		byte pData = (byte) t0;
		DWORD cbData = new DWORD (t1);
		HANDLE hConv = null;
		if ( t2 != 0L ) {
			hConv = new HANDLE ();
			hConv.setPointer(new Pointer(t2));
		}
		HANDLE hszItem = null;
		if ( t3 != 0L ) {
			hszItem = new HANDLE ();
			hszItem.setPointer(new Pointer(t3));
		}
		UINT wFmt = new UINT (t4);
		UINT wType = new UINT (t5);
		DWORD dwTimeout = new DWORD (t6);
		IntByReference pdwResult = new IntByReference ((int) t7);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DdeClientTransaction (pData, cbData, hConv, hszItem, wFmt, wType, dwTimeout, pdwResult);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t7, new LongValue(pdwResult.getValue()));

		

	}
}