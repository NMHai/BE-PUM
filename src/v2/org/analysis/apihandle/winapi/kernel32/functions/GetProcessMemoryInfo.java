/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetProcessMemoryInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.PROCESS_MEMORY_COUNTERS;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetProcessMemoryInfo extends Kernel32API {
	public GetProcessMemoryInfo () {
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
		HANDLE Process = null;
		if ( t0 != 0L ) {
			Process = new HANDLE ();
			Process.setPointer(new Pointer(t0));
		}
		PROCESS_MEMORY_COUNTERS ppsmemCounters = new PROCESS_MEMORY_COUNTERS ();
		DWORD cb = new DWORD (t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetProcessMemoryInfo (Process, ppsmemCounters, cb);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(ppsmemCounters.cb.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(ppsmemCounters.PageFaultCount.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(ppsmemCounters.PeakWorkingSetSize.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(ppsmemCounters.WorkingSetSize.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(ppsmemCounters.QuotaPeakPagedPoolUsage.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(ppsmemCounters.QuotaPagedPoolUsage.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(ppsmemCounters.QuotaPeakNonPagedPoolUsage.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(ppsmemCounters.QuotaNonPagedPoolUsage.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(ppsmemCounters.PagefileUsage.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(ppsmemCounters.PeakPagefileUsage.longValue()));
		t1 += 4;

	}
}