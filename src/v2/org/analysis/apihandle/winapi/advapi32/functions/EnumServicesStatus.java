/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: EnumServicesStatus.java
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
 
public class EnumServicesStatus extends Advapi32API {
	public EnumServicesStatus () {
		super();
		NUM_OF_PARMS = 8;
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
		
		// Step 2: type conversion from C++ to Java
		SC_HANDLE hSCManager = null;
		if ( t0 != 0L ) {
			hSCManager = new SC_HANDLE ();
			hSCManager.setPointer(new Pointer(t0));
		}
		DWORD dwServiceType = new DWORD (t1);
		DWORD dwServiceState = new DWORD (t2);
		ENUM_SERVICE_STATUS lpServices = new ENUM_SERVICE_STATUS ();
		DWORD cbBufSize = new DWORD (t4);
		int[] pcbBytesNeeded = null;
		if ( t5 != 0L ) pcbBytesNeeded = new int[(int) t4];
		for (int i = 0; i < pcbBytesNeeded.length; i++) {
			pcbBytesNeeded [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t5)).getValue();
			t5 += 4;
		}
		IntByReference lpServicesReturned = new IntByReference ((int) t6);
		IntByReference lpResumeHandle = new IntByReference ((int) t7);

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.EnumServicesStatus (hSCManager, dwServiceType, dwServiceState, lpServices, cbBufSize, pcbBytesNeeded, lpServicesReturned, lpResumeHandle);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		memory.setText(this, t3, new String(lpServices.lpServiceName));
		memory.setText(this, t3, new String(lpServices.lpDisplayName));
		// Nested Structure
			lpServices.ServiceStatus.dwServiceType = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3 += 0)).getValue();
			lpServices.ServiceStatus.dwCurrentState = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3 += 4)).getValue();
			lpServices.ServiceStatus.dwControlsAccepted = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3 += 4)).getValue();
			lpServices.ServiceStatus.dwWin32ExitCode = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3 += 4)).getValue();
			lpServices.ServiceStatus.dwServiceSpecificExitCode = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3 += 4)).getValue();
			lpServices.ServiceStatus.dwCheckPoint = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3 += 4)).getValue();
			lpServices.ServiceStatus.dwWaitHint = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3 += 4)).getValue();
		t5 = this.params.get(5);
		for (int i = 0; i < pcbBytesNeeded.length; i++) {
			memory.setDoubleWordMemoryValue (t5, new LongValue(pcbBytesNeeded [i]));
			t5 += 4;
		}		memory.setDoubleWordMemoryValue(t6, new LongValue(lpServicesReturned.getValue()));

		

	}
}