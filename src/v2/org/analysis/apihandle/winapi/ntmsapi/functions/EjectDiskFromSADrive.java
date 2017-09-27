/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ntmsapi.functions
 * File name: EjectDiskFromSADrive.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.ntmsapi.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.ntmsapi.NtmsapiAPI;
import v2.org.analysis.apihandle.winapi.ntmsapi.NtmsapiDLL;
import v2.org.analysis.value.LongValue;
 
public class EjectDiskFromSADrive extends NtmsapiAPI {
	public EjectDiskFromSADrive () {
		super();
		NUM_OF_PARMS = 7;
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
		
		// Step 2: type conversion from C++ to Java
		CHARByReference lpComputerName = new CHARByReference (new CHAR(t0));
		CHARByReference lpAppName = new CHARByReference (new CHAR(t1));
		CHARByReference lpDeviceName = new CHARByReference (new CHAR(t2));
		HWND hWnd = null;
		if ( t3 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t3));
		}
		CHARByReference lpTitle = new CHARByReference (new CHAR(t4));
		CHARByReference lpMessage = new CHARByReference (new CHAR(t5));
		DWORD dwOptions = new DWORD (t6);

		// Step 3: call API function
		int ret = NtmsapiDLL.INSTANCE.EjectDiskFromSADrive (lpComputerName, lpAppName, lpDeviceName, hWnd, lpTitle, lpMessage, dwOptions);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}