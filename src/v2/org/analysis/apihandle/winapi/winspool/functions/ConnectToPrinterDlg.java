/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: ConnectToPrinterDlg.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.winspool.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.apihandle.winapi.winspool.WinspoolDLL;
import v2.org.analysis.value.LongValue;
 
public class ConnectToPrinterDlg extends WinspoolAPI {
	public ConnectToPrinterDlg () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HWND hwnd = null;
		if ( t0 != 0L ) {
			hwnd = new HWND ();
			hwnd.setPointer(new Pointer(t0));
		}
		DWORD Flags = new DWORD (t1);

		// Step 3: call API function
		int ret = WinspoolDLL.INSTANCE.ConnectToPrinterDlg (hwnd, Flags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}