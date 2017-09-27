/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: EnumDependentServices.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.Winsvc.SC_HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.structures.ENUM_SERVICE_STATUS;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class EnumDependentServices extends Advapi32API {
	public EnumDependentServices () {
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
		SC_HANDLE hService = null;
		if ( t0 != 0L ) {
			hService = new SC_HANDLE ();
			hService.setPointer(new Pointer(t0));
		}
		DWORD dwServiceState = new DWORD (t1);
		ENUM_SERVICE_STATUS lpServices = new ENUM_SERVICE_STATUS ();
		DWORD cbBufSize = new DWORD (t3);
		int[] pcbBytesNeeded = null;
		if ( t4 != 0L ) pcbBytesNeeded = new int[(int) t3];
		for (int i = 0; i < pcbBytesNeeded.length; i++) {
			pcbBytesNeeded [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
		}
		IntByReference lpServicesReturned = new IntByReference ((int) t5);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.EnumDependentServices (hService, dwServiceState, lpServices, cbBufSize, pcbBytesNeeded, lpServicesReturned);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setText(this, t2, new String(lpServices.lpServiceName));
		memory.setText(this, t2, new String(lpServices.lpDisplayName));
		// Nested Structure
			lpServices.ServiceStatus.dwServiceType = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue();
			lpServices.ServiceStatus.dwCurrentState = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			lpServices.ServiceStatus.dwControlsAccepted = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			lpServices.ServiceStatus.dwWin32ExitCode = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			lpServices.ServiceStatus.dwServiceSpecificExitCode = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			lpServices.ServiceStatus.dwCheckPoint = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
			lpServices.ServiceStatus.dwWaitHint = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
		t4 = this.params.get(4);
		for (int i = 0; i < pcbBytesNeeded.length; i++) {
			memory.setDoubleWordMemoryValue (t4, new LongValue(pcbBytesNeeded [i]));
			t4 += 4;
		}		memory.setDoubleWordMemoryValue(t5, new LongValue(lpServicesReturned.getValue()));

		

	}
}