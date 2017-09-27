/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: CreatePipe.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreatePipe extends Kernel32API {
	public CreatePipe () {
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
		HANDLEByReference hReadPipe = new HANDLEByReference (new HANDLE(new Pointer(t0)));
		HANDLEByReference hWritePipe = new HANDLEByReference (new HANDLE(new Pointer(t1)));
		SECURITY_ATTRIBUTES lpPipeAttributes = null;
		if ( t2 != 0L) {
			lpPipeAttributes = new SECURITY_ATTRIBUTES ();
			lpPipeAttributes.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lpPipeAttributes.lpSecurityDescriptor = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			lpPipeAttributes.bInheritHandle = (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue() != 0L ) ? true : false;
			t2 += 4;
		}
		DWORD nSize = new DWORD (t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.CreatePipe (hReadPipe, hWritePipe, lpPipeAttributes, nSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t0, new LongValue(Pointer.nativeValue(hReadPipe.getValue().getPointer())));

		
		memory.setDoubleWordMemoryValue(t1, new LongValue(Pointer.nativeValue(hWritePipe.getValue().getPointer())));

		

	}
}