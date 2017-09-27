/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DdePostAdvise.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DdePostAdvise extends User32API {
	public DdePostAdvise () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		DWORD idInst = new DWORD (t0);
		HANDLE hszTopic = null;
		if ( t1 != 0L ) {
			hszTopic = new HANDLE ();
			hszTopic.setPointer(new Pointer(t1));
		}
		HANDLE hszItem = null;
		if ( t2 != 0L ) {
			hszItem = new HANDLE ();
			hszItem.setPointer(new Pointer(t2));
		}

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DdePostAdvise (idInst, hszTopic, hszItem);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}