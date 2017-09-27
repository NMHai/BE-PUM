/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mincore.functions
 * File name: VerInstallFile.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mincore.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.mincore.MincoreAPI;
import v2.org.analysis.apihandle.winapi.mincore.MincoreDLL;
import v2.org.analysis.value.LongValue;
 
public class VerInstallFile extends MincoreAPI {
	public VerInstallFile () {
		super();
		NUM_OF_PARMS = 8;
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
		long t7 = this.params.get(7);
		
		// Step 2: type conversion from C++ to Java
		DWORD uFlags = new DWORD (t0);
		String szSrcFileName = null;
		if ( t1 != 0L ) szSrcFileName = memory.getText(this, t1);
		String szDestFileName = null;
		if ( t2 != 0L ) szDestFileName = memory.getText(this, t2);
		String szSrcDir = null;
		if ( t3 != 0L ) szSrcDir = memory.getText(this, t3);
		String szDestDir = null;
		if ( t4 != 0L ) szDestDir = memory.getText(this, t4);
		String szCurDir = null;
		if ( t5 != 0L ) szCurDir = memory.getText(this, t5);
		String szTmpFile = null;
		if ( t6 != 0L ) szTmpFile = memory.getText(this, t6);
		IntByReference lpuTmpFileLen = new IntByReference ((int) t7);

		// Step 3: call API function
		int ret = MincoreDLL.INSTANCE.VerInstallFile (uFlags, szSrcFileName, szDestFileName, szSrcDir, szDestDir, szCurDir, szTmpFile, lpuTmpFileLen);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setText(this, t6, new String(szTmpFile));
	}
}