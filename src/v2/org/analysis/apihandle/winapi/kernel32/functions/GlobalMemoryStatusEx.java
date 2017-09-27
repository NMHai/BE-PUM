/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GlobalMemoryStatusEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinBase.MEMORYSTATUSEX;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDLONG;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GlobalMemoryStatusEx extends Kernel32API {
	public GlobalMemoryStatusEx () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		MEMORYSTATUSEX lpBuffer = null;
		if ( t0 != 0L) {
			lpBuffer = new MEMORYSTATUSEX ();
			lpBuffer.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpBuffer.dwMemoryLoad = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpBuffer.ullTotalPhys = new DWORDLONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpBuffer.ullAvailPhys = new DWORDLONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpBuffer.ullTotalPageFile = new DWORDLONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpBuffer.ullAvailPageFile = new DWORDLONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpBuffer.ullTotalVirtual = new DWORDLONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpBuffer.ullAvailVirtual = new DWORDLONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpBuffer.ullAvailExtendedVirtual = new DWORDLONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GlobalMemoryStatusEx (lpBuffer);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}