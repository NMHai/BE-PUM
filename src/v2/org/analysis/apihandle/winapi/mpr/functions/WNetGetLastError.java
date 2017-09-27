/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mpr.functions
 * File name: WNetGetLastError.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mpr.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.mpr.MprAPI;
import v2.org.analysis.apihandle.winapi.mpr.MprDLL;
import v2.org.analysis.value.LongValue;
 
public class WNetGetLastError extends MprAPI {
	public WNetGetLastError () {
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
		IntByReference lpError = new IntByReference ((int) t0);
		char[] lpErrorBuf = null;
		if ( t1 != 0L ) lpErrorBuf = new char[(int) t2];
		for (int i = 0; i < lpErrorBuf.length; i++) {
			lpErrorBuf [i] = (char) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		DWORD nErrorBufSize = new DWORD (t2);
		char[] lpNameBuf = null;
		if ( t3 != 0L ) lpNameBuf = new char[(int) t4];
		for (int i = 0; i < lpNameBuf.length; i++) {
			lpNameBuf [i] = (char) ((LongValue) memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
		}
		DWORD nNameBufSize = new DWORD (t4);

		// Step 3: call API function
		int ret = MprDLL.INSTANCE.WNetGetLastError (lpError, lpErrorBuf, nErrorBufSize, lpNameBuf, nNameBufSize);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t0, new LongValue(lpError.getValue()));

		
		t1 = this.params.get(1);
		for (int i = 0; i < lpErrorBuf.length; i++) {
			memory.setByteMemoryValue (t1, new LongValue(lpErrorBuf [i]));
			t1 += 1;
		}		t3 = this.params.get(3);
		for (int i = 0; i < lpNameBuf.length; i++) {
			memory.setByteMemoryValue (t3, new LongValue(lpNameBuf [i]));
			t3 += 1;
		}
	}
}