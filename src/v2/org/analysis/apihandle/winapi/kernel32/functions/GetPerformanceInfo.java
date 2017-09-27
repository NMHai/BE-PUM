/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetPerformanceInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.structures.PERFORMANCE_INFORMATION;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetPerformanceInfo extends Kernel32API {
	public GetPerformanceInfo () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		PERFORMANCE_INFORMATION pPerformanceInformation = new PERFORMANCE_INFORMATION ();
		DWORD cb = new DWORD (t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetPerformanceInfo (pPerformanceInformation, cb);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.cb.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.CommitTotal.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.CommitLimit.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.CommitPeak.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.PhysicalTotal.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.PhysicalAvailable.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.SystemCache.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.KernelTotal.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.KernelPaged.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.KernelNonpaged.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.PageSize.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.HandleCount.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.ProcessCount.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(pPerformanceInformation.ThreadCount.longValue()));
		t0 += 4;

	}
}