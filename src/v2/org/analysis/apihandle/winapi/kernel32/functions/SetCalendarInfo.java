/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetCalendarInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.LCID;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetCalendarInfo extends Kernel32API {
	public SetCalendarInfo () {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		
		// Step 2: type conversion from C++ to Java
		LCID Locale = new LCID (t0);
		int Calendar = (int) t1;
		int CalType = (int) t2;
		String lpCalData = null;
		if ( t3 != 0L ) lpCalData = memory.getText(this, t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.SetCalendarInfo (Locale, Calendar, CalType, lpCalData);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}