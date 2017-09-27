/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: BroadcastSystemMessage.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class BroadcastSystemMessage extends User32API {
	public BroadcastSystemMessage () {
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
		DWORD dwFlags = new DWORD (t0);
		IntByReference lpdwRecipients = new IntByReference ((int) t1);
		UINT uiMessage = new UINT (t2);
		WPARAM wParam = new WPARAM (t3);
		LPARAM lParam = new LPARAM (t4);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.BroadcastSystemMessage (dwFlags, lpdwRecipients, uiMessage, wParam, lParam);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}