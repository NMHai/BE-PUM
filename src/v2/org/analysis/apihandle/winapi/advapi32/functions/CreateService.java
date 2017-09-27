/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CreateService.java
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
 
public class CreateService extends Advapi32API {
	public CreateService () {
		super();
		NUM_OF_PARMS = 13;
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
		long t11 = this.params.get(11);
		long t12 = this.params.get(12);
		
		// Step 2: type conversion from C++ to Java
		SC_HANDLE hSCManager = null;
		if ( t0 != 0L ) {
			hSCManager = new SC_HANDLE ();
			hSCManager.setPointer(new Pointer(t0));
		}
		String lpServiceName = null;
		if ( t1 != 0L ) lpServiceName = memory.getText(this, t1);
		String lpDisplayName = null;
		if ( t2 != 0L ) lpDisplayName = memory.getText(this, t2);
		DWORD dwDesiredAccess = new DWORD (t3);
		DWORD dwServiceType = new DWORD (t4);
		DWORD dwStartType = new DWORD (t5);
		DWORD dwErrorControl = new DWORD (t6);
		String lpBinaryPathName = null;
		if ( t7 != 0L ) lpBinaryPathName = memory.getText(this, t7);
		String lpLoadOrderGroup = null;
		if ( t8 != 0L ) lpLoadOrderGroup = memory.getText(this, t8);
		IntByReference lpdwTagId = new IntByReference ((int) t9);
		char[] lpDependencies = null;
		if ( t10 != 0L ) lpDependencies = new char[(int) t11];
		for (int i = 0; i < lpDependencies.length; i++) {
			lpDependencies [i] = (char) ((LongValue) memory.getByteMemoryValue (t10)).getValue();
			t10 += 1;
		}
		String lpServiceStartName = null;
		if ( t11 != 0L ) lpServiceStartName = memory.getText(this, t11);
		String lpPassword = null;
		if ( t12 != 0L ) lpPassword = memory.getText(this, t12);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.CreateService (hSCManager, lpServiceName, lpDisplayName, dwDesiredAccess, dwServiceType, dwStartType, dwErrorControl, lpBinaryPathName, lpLoadOrderGroup, lpdwTagId, lpDependencies, lpServiceStartName, lpPassword);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t9, new LongValue(lpdwTagId.getValue()));

		

	}
}