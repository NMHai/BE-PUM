/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetTimeZoneInformation.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.TIME_ZONE_INFORMATION;
import v2.org.analysis.value.LongValue;
 
public class GetTimeZoneInformation extends Kernel32API {
	public GetTimeZoneInformation () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		TIME_ZONE_INFORMATION lpTimeZoneInformation = new TIME_ZONE_INFORMATION ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetTimeZoneInformation (lpTimeZoneInformation);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t0 = this.params.get(0);
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpTimeZoneInformation.Bias.longValue()));
		t0 += 4;
		memory.setText(this, t0, new String(lpTimeZoneInformation.StandardName));
		// Nested Structure
			lpTimeZoneInformation.StandardDate.wYear = (short) ((LongValue)memory.getWordMemoryValue (t0 += 0)).getValue();
			lpTimeZoneInformation.StandardDate.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wDay = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wHour = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpTimeZoneInformation.StandardBias.longValue()));
		t0 += 4;
		memory.setText(this, t0, new String(lpTimeZoneInformation.DaylightName));
		// Nested Structure
			lpTimeZoneInformation.DaylightDate.wYear = (short) ((LongValue)memory.getWordMemoryValue (t0 += 0)).getValue();
			lpTimeZoneInformation.DaylightDate.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wDay = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wHour = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
		memory.setDoubleWordMemoryValue (t0, new LongValue(lpTimeZoneInformation.DaylightBias.longValue()));
		t0 += 4;

	}
}