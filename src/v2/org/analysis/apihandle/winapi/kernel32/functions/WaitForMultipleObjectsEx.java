/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: WaitForMultipleObjectsEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class WaitForMultipleObjectsEx extends Kernel32API {
	public WaitForMultipleObjectsEx () {
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
		DWORD nCount = new DWORD (t0);
		HANDLEByReference lpHandles = new HANDLEByReference (new HANDLE(new Pointer(t1)));
		BOOL bWaitAll = new BOOL (t2);
		DWORD dwMilliseconds = new DWORD (t3);
		BOOL bAlertable = new BOOL (t4);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.WaitForMultipleObjectsEx (nCount, lpHandles, bWaitAll, dwMilliseconds, bAlertable);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}