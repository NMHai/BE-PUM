/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetTimeFormatEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetTimeFormatEx extends Kernel32API {
	public GetTimeFormatEx () {
		super();
		NUM_OF_PARMS = 6;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		
		// Step 2: type conversion from C++ to Java
		CHARByReference lpLocaleName = new CHARByReference (new CHAR(t0));
		DWORD dwFlags = new DWORD (t1);
		SYSTEMTIME lpTime = null;
		if ( t2 != 0L) {
			lpTime = new SYSTEMTIME ();
			lpTime.wYear = (short) ((LongValue)memory.getWordMemoryValue (t2)).getValue();
			t2 += 2;
			lpTime.wMonth = (short) ((LongValue)memory.getWordMemoryValue (t2)).getValue();
			t2 += 2;
			lpTime.wDayOfWeek = (short) ((LongValue)memory.getWordMemoryValue (t2)).getValue();
			t2 += 2;
			lpTime.wDay = (short) ((LongValue)memory.getWordMemoryValue (t2)).getValue();
			t2 += 2;
			lpTime.wHour = (short) ((LongValue)memory.getWordMemoryValue (t2)).getValue();
			t2 += 2;
			lpTime.wMinute = (short) ((LongValue)memory.getWordMemoryValue (t2)).getValue();
			t2 += 2;
			lpTime.wSecond = (short) ((LongValue)memory.getWordMemoryValue (t2)).getValue();
			t2 += 2;
			lpTime.wMilliseconds = (short) ((LongValue)memory.getWordMemoryValue (t2)).getValue();
			t2 += 2;
		}
		CHARByReference lpFormat = new CHARByReference (new CHAR(t3));
		char[] lpTimeStr = null;
		if ( t4 != 0L ) lpTimeStr = new char[(int) t5];
		for (int i = 0; i < lpTimeStr.length; i++) {
			lpTimeStr [i] = (char) ((LongValue) memory.getWordMemoryValue (t4)).getValue();
			t4 += 2;
		}
		int cchTime = (int) t5;

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetTimeFormatEx (lpLocaleName, dwFlags, lpTime, lpFormat, lpTimeStr, cchTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t4 = this.params.get(4);
		for (int i = 0; i < lpTimeStr.length; i++) {
			memory.setWordMemoryValue (t4, new LongValue(lpTimeStr [i]));
			t4 += 2;
		}
	}
}