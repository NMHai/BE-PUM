/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetNamedPipeHandleState.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetNamedPipeHandleState extends Kernel32API {
	public GetNamedPipeHandleState () {
		super();
		NUM_OF_PARMS = 7;
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
		
		// Step 2: type conversion from C++ to Java
		HANDLE hNamedPipe = null;
		if ( t0 != 0L ) {
			hNamedPipe = new HANDLE ();
			hNamedPipe.setPointer(new Pointer(t0));
		}
		IntByReference lpState = new IntByReference ((int) t1);
		IntByReference lpCurInstances = new IntByReference ((int) t2);
		IntByReference lpMaxCollectionCount = new IntByReference ((int) t3);
		IntByReference lpCollectDataTimeout = new IntByReference ((int) t4);
		char[] lpUserName = null;
		if ( t5 != 0L ) lpUserName = new char[(int) t6];
		for (int i = 0; i < lpUserName.length; i++) {
			lpUserName [i] = (char) ((LongValue) memory.getByteMemoryValue (t5)).getValue();
			t5 += 1;
		}
		DWORD nMaxUserNameSize = new DWORD (t6);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetNamedPipeHandleState (hNamedPipe, lpState, lpCurInstances, lpMaxCollectionCount, lpCollectDataTimeout, lpUserName, nMaxUserNameSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(lpState.getValue()));

		
		memory.setDoubleWordMemoryValue(t2, new LongValue(lpCurInstances.getValue()));

		
		memory.setDoubleWordMemoryValue(t3, new LongValue(lpMaxCollectionCount.getValue()));

		
		memory.setDoubleWordMemoryValue(t4, new LongValue(lpCollectDataTimeout.getValue()));

		
		t5 = this.params.get(5);
		for (int i = 0; i < lpUserName.length; i++) {
			memory.setByteMemoryValue (t5, new LongValue(lpUserName [i]));
			t5 += 1;
		}
	}
}