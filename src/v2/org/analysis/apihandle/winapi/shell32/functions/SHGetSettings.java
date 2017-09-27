/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHGetSettings.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.SHELLFLAGSTATE;
import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class SHGetSettings extends Shell32API {
	public SHGetSettings () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		SHELLFLAGSTATE lpsfs = null;
		if ( t0 != 0L) {
			lpsfs = new SHELLFLAGSTATE ();
			lpsfs.fShowAllObjects = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fShowExtensions = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fNoConfirmRecycle = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fShowSysFiles = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fShowCompColor = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fDoubleClickInWebView = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fDesktopHTML = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fWin95Classic = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fDontPrettyPath = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fShowAttribCol = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fMapNetDrvBtn = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fShowInfoTip = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fHideIcons = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fAutoCheckSelect = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fIconsOnly = new BOOL (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpsfs.fRestFlags = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}
		DWORD dwMask = new DWORD (t1);

		// Step 3: call API function
		Shell32DLL.INSTANCE.SHGetSettings (lpsfs, dwMask);
		
		// Step 4: update environment (memory & eax register)

	}
}