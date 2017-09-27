/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.avrt.functions
 * File name: AvSetMmMaxThreadCharacteristics.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.avrt.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.avrt.AvrtAPI;
import v2.org.analysis.apihandle.winapi.avrt.AvrtDLL;
import v2.org.analysis.value.LongValue;
 
public class AvSetMmMaxThreadCharacteristics extends AvrtAPI {
	public AvSetMmMaxThreadCharacteristics () {
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
		String FirstTask = null;
		if ( t0 != 0L ) FirstTask = memory.getText(this, t0);
		String SecondTask = null;
		if ( t1 != 0L ) SecondTask = memory.getText(this, t1);
		IntByReference TaskIndex = new IntByReference ((int) t2);

		// Step 3: call API function
		int ret = AvrtDLL.INSTANCE.AvSetMmMaxThreadCharacteristics (FirstTask, SecondTask, TaskIndex);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}