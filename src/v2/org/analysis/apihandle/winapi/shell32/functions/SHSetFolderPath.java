/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHSetFolderPath.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class SHSetFolderPath extends Shell32API {
	public SHSetFolderPath () {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		
		// Step 2: type conversion from C++ to Java
		int csidl = (int) t0;
		HANDLE hToken = null;
		if ( t1 != 0L ) {
			hToken = new HANDLE ();
			hToken.setPointer(new Pointer(t1));
		}
		DWORD dwFlags = new DWORD (t2);
		String pszPath = null;
		if ( t3 != 0L ) pszPath = memory.getText(this, t3);

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.SHSetFolderPath (csidl, hToken, dwFlags, pszPath);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}