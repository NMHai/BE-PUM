/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: CreateNamedPipe.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateNamedPipe extends Kernel32API {
	public CreateNamedPipe () {
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
		String lpName = null;
		if ( t0 != 0L ) lpName = memory.getText(this, t0);
		DWORD dwOpenMode = new DWORD (t1);
		DWORD dwPipeMode = new DWORD (t2);
		DWORD nMaxInstances = new DWORD (t3);
		DWORD nOutBufferSize = new DWORD (t4);
		DWORD nInBufferSize = new DWORD (t5);
		DWORD nDefaultTimeOut = new DWORD (t6);
		SECURITY_ATTRIBUTES lpSecurityAttributes = null;
		if ( t7 != 0L) {
			lpSecurityAttributes = new SECURITY_ATTRIBUTES ();
			lpSecurityAttributes.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t7)).getValue());
			t7 += 4;
			lpSecurityAttributes.lpSecurityDescriptor = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t7)).getValue());
			t7 += 4;
			lpSecurityAttributes.bInheritHandle = (((LongValue)memory.getDoubleWordMemoryValue (t7)).getValue() != 0L ) ? true : false;
			t7 += 4;
		}

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.CreateNamedPipe (lpName, dwOpenMode, dwPipeMode, nMaxInstances, nOutBufferSize, nInBufferSize, nDefaultTimeOut, lpSecurityAttributes);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}