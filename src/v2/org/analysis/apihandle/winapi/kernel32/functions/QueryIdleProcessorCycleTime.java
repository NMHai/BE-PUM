/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: QueryIdleProcessorCycleTime.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class QueryIdleProcessorCycleTime extends Kernel32API {
	public QueryIdleProcessorCycleTime () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		IntByReference BufferLength = new IntByReference ((int) t0);
		LongByReference ProcessorIdleCycleTime = new LongByReference ((long) t1);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.QueryIdleProcessorCycleTime (BufferLength, ProcessorIdleCycleTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(ProcessorIdleCycleTime.getValue()));

		

	}
}