/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetSystemTime.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetSystemTime extends Kernel32API {
	public SetSystemTime () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		SYSTEMTIME lpSystemTime = null;
		if ( t0 != 0L) {
			lpSystemTime = new SYSTEMTIME ();
			lpSystemTime.wYear = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
			lpSystemTime.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
			lpSystemTime.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
			lpSystemTime.wDay = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
			lpSystemTime.wHour = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
			lpSystemTime.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
			lpSystemTime.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
			lpSystemTime.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t0)).getValue();
			t0 += 2;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetSystemTime (lpSystemTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}