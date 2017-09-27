/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetCommProperties.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.COMMPROP;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetCommProperties extends Kernel32API {
	public GetCommProperties () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hFile = null;
		if ( t0 != 0L ) {
			hFile = new HANDLE ();
			hFile.setPointer(new Pointer(t0));
		}
		COMMPROP lpCommProp = new COMMPROP ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetCommProperties (hFile, lpCommProp);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setWordMemoryValue (t1, new LongValue(lpCommProp.wPacketLength.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpCommProp.wPacketVersion.longValue()));
		t1 += 2;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwServiceMask.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwReserved1.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwMaxTxQueue.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwMaxRxQueue.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwMaxBaud.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwProvSubType.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwProvCapabilities.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwSettableParams.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwSettableBaud.longValue()));
		t1 += 4;
		memory.setWordMemoryValue (t1, new LongValue(lpCommProp.wSettableData.longValue()));
		t1 += 2;
		memory.setWordMemoryValue (t1, new LongValue(lpCommProp.wSettableStopParity.longValue()));
		t1 += 2;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwCurrentTxQueue.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwCurrentRxQueue.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwProvSpec1.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpCommProp.dwProvSpec2.longValue()));
		t1 += 4;
		for (int i = 0; i < lpCommProp.wcProvChar.length; i++) {
			memory.setWordMemoryValue (t1, new LongValue(lpCommProp.wcProvChar [i]));
			t1 += 2;
		}

	}
}