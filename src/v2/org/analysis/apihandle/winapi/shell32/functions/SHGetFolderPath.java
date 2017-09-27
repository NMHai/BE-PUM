/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHGetFolderPath.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class SHGetFolderPath extends Shell32API {
	public SHGetFolderPath () {
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
		HWND hwndOwner = null;
		if ( t0 != 0L ) {
			hwndOwner = new HWND ();
			hwndOwner.setPointer(new Pointer(t0));
		}
		int nFolder = (int) t1;
		HANDLE hToken = null;
		if ( t2 != 0L ) {
			hToken = new HANDLE ();
			hToken.setPointer(new Pointer(t2));
		}
		DWORD dwFlags = new DWORD (t3);
		String pszPath = null;
		if ( t4 != 0L ) pszPath = memory.getText(this, t4);

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.SHGetFolderPath (hwndOwner, nFolder, hToken, dwFlags, pszPath);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t4, new String(pszPath));
	}
}