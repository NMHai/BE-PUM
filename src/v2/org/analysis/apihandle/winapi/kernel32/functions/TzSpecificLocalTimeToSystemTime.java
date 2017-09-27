/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: TzSpecificLocalTimeToSystemTime.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.TIME_ZONE_INFORMATION;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinDef.LONG;
 
public class TzSpecificLocalTimeToSystemTime extends Kernel32API {
	public TzSpecificLocalTimeToSystemTime () {
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
		TIME_ZONE_INFORMATION lpTimeZoneInformation = null;
		if ( t0 != 0L) {
			lpTimeZoneInformation = new TIME_ZONE_INFORMATION ();
			lpTimeZoneInformation.Bias = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpTimeZoneInformation.StandardName = memory.getText(this, t0);
			t0 += 4;
			lpTimeZoneInformation.StandardDate = new SYSTEMTIME ();
			// Nested Structure
			lpTimeZoneInformation.StandardDate.wYear = (short) ((LongValue)memory.getWordMemoryValue (t0 += 0)).getValue();
			lpTimeZoneInformation.StandardDate.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wDay = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wHour = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardDate.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.StandardBias = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpTimeZoneInformation.DaylightName = memory.getText(this, t0);
			t0 += 4;
			lpTimeZoneInformation.DaylightDate = new SYSTEMTIME ();
			// Nested Structure
			lpTimeZoneInformation.DaylightDate.wYear = (short) ((LongValue)memory.getWordMemoryValue (t0 += 0)).getValue();
			lpTimeZoneInformation.DaylightDate.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wDay = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wHour = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightDate.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZoneInformation.DaylightBias = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}
		SYSTEMTIME lpLocalTime = null;
		if ( t1 != 0L) {
			lpLocalTime = new SYSTEMTIME ();
			lpLocalTime.wYear = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpLocalTime.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpLocalTime.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpLocalTime.wDay = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpLocalTime.wHour = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpLocalTime.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpLocalTime.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpLocalTime.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
		}
		SYSTEMTIME lpUniversalTime = new SYSTEMTIME ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.TzSpecificLocalTimeToSystemTime (lpTimeZoneInformation, lpLocalTime, lpUniversalTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setWordMemoryValue (t2, new LongValue(lpUniversalTime.wYear));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpUniversalTime.wMonth));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpUniversalTime.wDayOfWeek));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpUniversalTime.wDay));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpUniversalTime.wHour));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpUniversalTime.wMinute));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpUniversalTime.wSecond));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpUniversalTime.wMilliseconds));
		t2 += 2;

	}
}