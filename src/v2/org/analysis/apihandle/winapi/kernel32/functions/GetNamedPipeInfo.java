/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetNamedPipeInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetNamedPipeInfo extends Kernel32API {
	public GetNamedPipeInfo () {
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
		HANDLE hNamedPipe = null;
		if ( t0 != 0L ) {
			hNamedPipe = new HANDLE ();
			hNamedPipe.setPointer(new Pointer(t0));
		}
		IntByReference lpFlags = new IntByReference ((int) t1);
		int[] lpOutBufferSize = null;
		if ( t2 != 0L ) lpOutBufferSize = new int[(int) t3];
		for (int i = 0; i < lpOutBufferSize.length; i++) {
			lpOutBufferSize [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
		}
		int[] lpInBufferSize = null;
		if ( t3 != 0L ) lpInBufferSize = new int[(int) t2];
		for (int i = 0; i < lpInBufferSize.length; i++) {
			lpInBufferSize [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
		}
		IntByReference lpMaxInstances = new IntByReference ((int) t4);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetNamedPipeInfo (hNamedPipe, lpFlags, lpOutBufferSize, lpInBufferSize, lpMaxInstances);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(lpFlags.getValue()));

		
		t2 = this.params.get(2);
		for (int i = 0; i < lpOutBufferSize.length; i++) {
			memory.setDoubleWordMemoryValue (t2, new LongValue(lpOutBufferSize [i]));
			t2 += 4;
		}		t3 = this.params.get(3);
		for (int i = 0; i < lpInBufferSize.length; i++) {
			memory.setDoubleWordMemoryValue (t3, new LongValue(lpInBufferSize [i]));
			t3 += 4;
		}		memory.setDoubleWordMemoryValue(t4, new LongValue(lpMaxInstances.getValue()));

		

	}
}