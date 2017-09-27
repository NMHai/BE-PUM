/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHInvokePrinterCommand.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class SHInvokePrinterCommand extends Shell32API {
	public SHInvokePrinterCommand () {
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
		HWND hwnd = null;
		if ( t0 != 0L ) {
			hwnd = new HWND ();
			hwnd.setPointer(new Pointer(t0));
		}
		UINT uAction = new UINT (t1);
		String lpBuf1 = null;
		if ( t2 != 0L ) lpBuf1 = memory.getText(this, t2);
		String lpBuf2 = null;
		if ( t3 != 0L ) lpBuf2 = memory.getText(this, t3);
		BOOL fModal = new BOOL (t4);

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.SHInvokePrinterCommand (hwnd, uAction, lpBuf1, lpBuf2, fModal);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}