/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.rstrtmgr.functions
 * File name: RmAddFilter.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.rstrtmgr.functions;

import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.structures.RM_UNIQUE_PROCESS;
import v2.org.analysis.apihandle.winapi.rstrtmgr.RstrtmgrAPI;
import v2.org.analysis.apihandle.winapi.rstrtmgr.RstrtmgrDLL;
import v2.org.analysis.value.LongValue;
 
public class RmAddFilter extends RstrtmgrAPI {
	public RmAddFilter () {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		
		// Step 2: type conversion from C++ to Java
		DWORD dwSessionHandle = new DWORD (t0);
		CHARByReference strFilename = new CHARByReference (new CHAR(t1));
		RM_UNIQUE_PROCESS Application = null;
		if ( t2 != 0L) {
			Application = new RM_UNIQUE_PROCESS ();
			Application.dwProcessId = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			Application.ProcessStartTime = new FILETIME ();
			// Nested Structure
			Application.ProcessStartTime.dwLowDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue();
			Application.ProcessStartTime.dwHighDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
		}
		CHARByReference strShortServiceName = new CHARByReference (new CHAR(t3));
		int ActionType = (int) t4;

		// Step 3: call API function
		int ret = RstrtmgrDLL.INSTANCE.RmAddFilter (dwSessionHandle, strFilename, Application, strShortServiceName, ActionType);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}