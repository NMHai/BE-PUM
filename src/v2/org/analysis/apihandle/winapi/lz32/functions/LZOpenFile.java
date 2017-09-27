/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.lz32.functions
 * File name: LZOpenFile.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.lz32.functions;

import com.sun.jna.platform.win32.WinDef.WORD;

import v2.org.analysis.apihandle.structures.OFSTRUCT;
import v2.org.analysis.apihandle.winapi.lz32.Lz32API;
import v2.org.analysis.apihandle.winapi.lz32.Lz32DLL;
import v2.org.analysis.value.LongValue;
 
public class LZOpenFile extends Lz32API {
	public LZOpenFile () {
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
		String lpFileName = null;
		if ( t0 != 0L ) lpFileName = memory.getText(this, t0);
		OFSTRUCT lpReOpenBuf = new OFSTRUCT ();
		WORD wStyle = new WORD (t2);

		// Step 3: call API function
		int ret = Lz32DLL.INSTANCE.LZOpenFile (lpFileName, lpReOpenBuf, wStyle);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setByteMemoryValue (t1, new LongValue(lpReOpenBuf.cBytes.longValue()));
		t1 += 1;
		memory.setByteMemoryValue (t1, new LongValue(lpReOpenBuf.fFixedDisk.longValue()));
		t1 += 1;
		memory.setWordMemoryValue (t1, new LongValue(lpReOpenBuf.nErrCode.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpReOpenBuf.Reserved1.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpReOpenBuf.Reserved2.longValue()));
		t1 += 2;
		for (int i = 0; i < lpReOpenBuf.szPathName.length; i++) {
			memory.setByteMemoryValue (t1, new LongValue(lpReOpenBuf.szPathName [i].longValue()));
			t1 += 1;
		}

	}
}