/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.avrt.functions
 * File name: AvSetMmThreadCharacteristics.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.avrt.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.avrt.AvrtAPI;
import v2.org.analysis.apihandle.winapi.avrt.AvrtDLL;
import v2.org.analysis.value.LongValue;
 
public class AvSetMmThreadCharacteristics extends AvrtAPI {
	public AvSetMmThreadCharacteristics () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		String TaskName = null;
		if ( t0 != 0L ) TaskName = memory.getText(this, t0);
		IntByReference TaskIndex = new IntByReference ((int) t1);

		// Step 3: call API function
		int ret = AvrtDLL.INSTANCE.AvSetMmThreadCharacteristics (TaskName, TaskIndex);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}