/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mincore.functions
 * File name: VerFindFile.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mincore.functions;

import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.mincore.MincoreAPI;
import v2.org.analysis.apihandle.winapi.mincore.MincoreDLL;
import v2.org.analysis.value.LongValue;
 
public class VerFindFile extends MincoreAPI {
	public VerFindFile () {
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
		DWORD dwFlags = new DWORD (t0);
		String szFileName = null;
		if ( t1 != 0L ) szFileName = memory.getText(this, t1);
		String szWinDir = null;
		if ( t2 != 0L ) szWinDir = memory.getText(this, t2);
		String szAppDir = null;
		if ( t3 != 0L ) szAppDir = memory.getText(this, t3);
		CHARByReference szCurDir = new CHARByReference (new CHAR(t4));
		IntByReference lpuCurDirLen = new IntByReference ((int) t5);
		String szDestDir = null;
		if ( t6 != 0L ) szDestDir = memory.getText(this, t6);
		IntByReference lpuDestDirLen = new IntByReference ((int) t7);

		// Step 3: call API function
		int ret = MincoreDLL.INSTANCE.VerFindFile (dwFlags, szFileName, szWinDir, szAppDir, szCurDir, lpuCurDirLen, szDestDir, lpuDestDirLen);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t4, new LongValue(szCurDir.getValue().longValue()));

		
		memory.setText(this, t6, new String(szDestDir));
	}
}