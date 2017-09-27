/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetDynamicTimeZoneInformation.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.structures.DYNAMIC_TIME_ZONE_INFORMATION;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetDynamicTimeZoneInformation extends Kernel32API {
	public GetDynamicTimeZoneInformation () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		DYNAMIC_TIME_ZONE_INFORMATION pTimeZoneInformation = new DYNAMIC_TIME_ZONE_INFORMATION ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetDynamicTimeZoneInformation (pTimeZoneInformation);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		memory.setDoubleWordMemoryValue (t0, new LongValue(pTimeZoneInformation.Bias.longValue()));
		t0 += 4;
		for (int i = 0; i < pTimeZoneInformation.StandardName.length; i++) {
			memory.setWordMemoryValue (t0, new LongValue(pTimeZoneInformation.StandardName [i]));
			t0 += 2;
		}
		// Nested Structure
			pTimeZoneInformation.StandardDate.wYear = (short) ((LongValue)memory.getWordMemoryValue (t0 += 0)).getValue();
			pTimeZoneInformation.StandardDate.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.StandardDate.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.StandardDate.wDay = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.StandardDate.wHour = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.StandardDate.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.StandardDate.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.StandardDate.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
		memory.setDoubleWordMemoryValue (t0, new LongValue(pTimeZoneInformation.StandardBias.longValue()));
		t0 += 4;
		for (int i = 0; i < pTimeZoneInformation.DaylightName.length; i++) {
			memory.setWordMemoryValue (t0, new LongValue(pTimeZoneInformation.DaylightName [i]));
			t0 += 2;
		}
		// Nested Structure
			pTimeZoneInformation.DaylightDate.wYear = (short) ((LongValue)memory.getWordMemoryValue (t0 += 0)).getValue();
			pTimeZoneInformation.DaylightDate.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.DaylightDate.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.DaylightDate.wDay = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.DaylightDate.wHour = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.DaylightDate.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.DaylightDate.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			pTimeZoneInformation.DaylightDate.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
		memory.setDoubleWordMemoryValue (t0, new LongValue(pTimeZoneInformation.DaylightBias.longValue()));
		t0 += 4;
		for (int i = 0; i < pTimeZoneInformation.TimeZoneKeyName.length; i++) {
			memory.setWordMemoryValue (t0, new LongValue(pTimeZoneInformation.TimeZoneKeyName [i]));
			t0 += 2;
		}
		memory.setByteMemoryValue (t0, new LongValue(pTimeZoneInformation.DynamicDaylightTimeDisabled));
		t0 += 1;

	}
}