/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FileTimeToDosDateTime.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.ptr.ShortByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class FileTimeToDosDateTime extends Kernel32API {
	public FileTimeToDosDateTime () {
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
		FILETIME lpFileTime = null;
		if ( t0 != 0L) {
			lpFileTime = new FILETIME ();
			lpFileTime.dwLowDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
			lpFileTime.dwHighDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue();
			t0 += 4;
		}
		ShortByReference lpFatDate = new ShortByReference ((short) t1);
		ShortByReference lpFatTime = new ShortByReference ((short) t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.FileTimeToDosDateTime (lpFileTime, lpFatDate, lpFatTime);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(lpFatDate.getValue()));

		
		memory.setDoubleWordMemoryValue(t2, new LongValue(lpFatTime.getValue()));

		

	}
}