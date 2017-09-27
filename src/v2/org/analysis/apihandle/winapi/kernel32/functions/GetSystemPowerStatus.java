/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetSystemPowerStatus.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.structures.SYSTEM_POWER_STATUS;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetSystemPowerStatus extends Kernel32API {
	public GetSystemPowerStatus () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		SYSTEM_POWER_STATUS lpSystemPowerStatus = new SYSTEM_POWER_STATUS ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetSystemPowerStatus (lpSystemPowerStatus);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		memory.setByteMemoryValue (t0, new LongValue(lpSystemPowerStatus.ACLineStatus.longValue()));
		t0 += 1;
		memory.setByteMemoryValue (t0, new LongValue(lpSystemPowerStatus.BatteryFlag.longValue()));
		t0 += 1;
		memory.setByteMemoryValue (t0, new LongValue(lpSystemPowerStatus.BatteryLifePercent.longValue()));
		t0 += 1;
		memory.setByteMemoryValue (t0, new LongValue(lpSystemPowerStatus.SystemStatusFlag.longValue()));
		t0 += 1;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpSystemPowerStatus.BatteryLifeTime.longValue()));
		t0 += 4;
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpSystemPowerStatus.BatteryFullLifeTime.longValue()));
		t0 += 4;

	}
}