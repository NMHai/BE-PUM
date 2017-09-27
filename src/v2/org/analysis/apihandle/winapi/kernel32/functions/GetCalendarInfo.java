/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetCalendarInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.LCID;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetCalendarInfo extends Kernel32API {
	public GetCalendarInfo () {
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
		LCID Locale = new LCID (t0);
		int Calendar = (int) t1;
		int CalType = (int) t2;
		char[] lpCalData = null;
		if ( t3 != 0L ) lpCalData = new char[(int) t4];
		for (int i = 0; i < lpCalData.length; i++) {
			lpCalData [i] = (char) ((LongValue) memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
		}
		int cchData = (int) t4;
		IntByReference lpValue = new IntByReference ((int) t5);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetCalendarInfo (Locale, Calendar, CalType, lpCalData, cchData, lpValue);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		for (int i = 0; i < lpCalData.length; i++) {
			memory.setByteMemoryValue (t3, new LongValue(lpCalData [i]));
			t3 += 1;
		}		memory.setDoubleWordMemoryValue(t5, new LongValue(lpValue.getValue()));

		

	}
}