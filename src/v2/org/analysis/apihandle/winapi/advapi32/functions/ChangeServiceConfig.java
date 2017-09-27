/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: ChangeServiceConfig.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.Winsvc.SC_HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class ChangeServiceConfig extends Advapi32API {
	public ChangeServiceConfig () {
		super();
		NUM_OF_PARMS = 11;
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
		long t6 = this.params.get(6);
		long t7 = this.params.get(7);
		long t8 = this.params.get(8);
		long t9 = this.params.get(9);
		long t10 = this.params.get(10);
		
		// Step 2: type conversion from C++ to Java
		SC_HANDLE hService = null;
		if ( t0 != 0L ) {
			hService = new SC_HANDLE ();
			hService.setPointer(new Pointer(t0));
		}
		DWORD dwServiceType = new DWORD (t1);
		DWORD dwStartType = new DWORD (t2);
		DWORD dwErrorControl = new DWORD (t3);
		String lpBinaryPathName = null;
		if ( t4 != 0L ) lpBinaryPathName = memory.getText(this, t4);
		String lpLoadOrderGroup = null;
		if ( t5 != 0L ) lpLoadOrderGroup = memory.getText(this, t5);
		IntByReference lpdwTagId = new IntByReference ((int) t6);
		char[] lpDependencies = null;
		if ( t7 != 0L ) lpDependencies = new char[(int) t8];
		for (int i = 0; i < lpDependencies.length; i++) {
			lpDependencies [i] = (char) ((LongValue) memory.getByteMemoryValue (t7)).getValue();
			t7 += 1;
		}
		String lpServiceStartName = null;
		if ( t8 != 0L ) lpServiceStartName = memory.getText(this, t8);
		String lpPassword = null;
		if ( t9 != 0L ) lpPassword = memory.getText(this, t9);
		String lpDisplayName = null;
		if ( t10 != 0L ) lpDisplayName = memory.getText(this, t10);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.ChangeServiceConfig (hService, dwServiceType, dwStartType, dwErrorControl, lpBinaryPathName, lpLoadOrderGroup, lpdwTagId, lpDependencies, lpServiceStartName, lpPassword, lpDisplayName);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t6, new LongValue(lpdwTagId.getValue()));

		

	}
}