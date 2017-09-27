/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FindNextVolume.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class FindNextVolume extends Kernel32API {
	public FindNextVolume () {
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
		HANDLE hFindVolume = null;
		if ( t0 != 0L ) {
			hFindVolume = new HANDLE ();
			hFindVolume.setPointer(new Pointer(t0));
		}
		String lpszVolumeName = null;
		if ( t1 != 0L ) lpszVolumeName = memory.getText(this, t1);
		DWORD cchBufferLength = new DWORD (t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.FindNextVolume (hFindVolume, lpszVolumeName, cchBufferLength);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t1, new String(lpszVolumeName));
	}
}