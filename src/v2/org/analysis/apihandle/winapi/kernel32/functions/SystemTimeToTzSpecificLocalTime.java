/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SystemTimeToTzSpecificLocalTime.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.TIME_ZONE_INFORMATION;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinDef.LONG;
 
public class SystemTimeToTzSpecificLocalTime extends Kernel32API {
	public SystemTimeToTzSpecificLocalTime () {
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
		TIME_ZONE_INFORMATION lpTimeZone = null;
		if ( t0 != 0L) {
			lpTimeZone = new TIME_ZONE_INFORMATION ();
			lpTimeZone.Bias = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpTimeZone.StandardName = memory.getText(this, t0);
			t0 += 4;
			lpTimeZone.StandardDate = new SYSTEMTIME ();
			// Nested Structure
			lpTimeZone.StandardDate.wYear = (short) ((LongValue)memory.getWordMemoryValue (t0 += 0)).getValue();
			lpTimeZone.StandardDate.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.StandardDate.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.StandardDate.wDay = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.StandardDate.wHour = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.StandardDate.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.StandardDate.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.StandardDate.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.StandardBias = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpTimeZone.DaylightName = memory.getText(this, t0);
			t0 += 4;
			lpTimeZone.DaylightDate = new SYSTEMTIME ();
			// Nested Structure
			lpTimeZone.DaylightDate.wYear = (short) ((LongValue)memory.getWordMemoryValue (t0 += 0)).getValue();
			lpTimeZone.DaylightDate.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.DaylightDate.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.DaylightDate.wDay = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.DaylightDate.wHour = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.DaylightDate.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.DaylightDate.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.DaylightDate.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t0 += 2)).getValue();
			lpTimeZone.DaylightBias = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}
		SYSTEMTIME lpUniversalTime = null;
		if ( t1 != 0L) {
			lpUniversalTime = new SYSTEMTIME ();
			lpUniversalTime.wYear = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpUniversalTime.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpUniversalTime.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpUniversalTime.wDay = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpUniversalTime.wHour = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpUniversalTime.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpUniversalTime.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			lpUniversalTime.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
		}
		SYSTEMTIME lpLocalTime = new SYSTEMTIME ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SystemTimeToTzSpecificLocalTime (lpTimeZone, lpUniversalTime, lpLocalTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setWordMemoryValue (t2, new LongValue(lpLocalTime.wYear));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpLocalTime.wMonth));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpLocalTime.wDayOfWeek));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpLocalTime.wDay));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpLocalTime.wHour));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpLocalTime.wMinute));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpLocalTime.wSecond));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpLocalTime.wMilliseconds));
		t2 += 2;

	}
}