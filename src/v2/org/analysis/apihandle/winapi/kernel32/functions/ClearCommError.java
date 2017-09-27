/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: ClearCommError.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.structures.COMSTAT;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class ClearCommError extends Kernel32API {
	public ClearCommError () {
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
		HANDLE hFile = null;
		if ( t0 != 0L ) {
			hFile = new HANDLE ();
			hFile.setPointer(new Pointer(t0));
		}
		IntByReference lpErrors = new IntByReference ((int) t1);
		COMSTAT lpStat = new COMSTAT ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.ClearCommError (hFile, lpErrors, lpStat);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(lpErrors.getValue()));

		
		t2 = this.params.get(2);
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpStat.fCtsHold.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpStat.fDsrHold.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpStat.fRlsdHold.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpStat.fXoffHold.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpStat.fXoffSent.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpStat.fEof.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpStat.fTxim.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpStat.fReserved.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpStat.cbInQue.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpStat.cbOutQue.longValue()));
		t2 += 4;

	}
}