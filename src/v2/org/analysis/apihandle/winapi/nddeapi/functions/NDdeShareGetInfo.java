/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.nddeapi.functions
 * File name: NDdeShareGetInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.nddeapi.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;

import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiAPI;
import v2.org.analysis.apihandle.winapi.nddeapi.NddeapiDLL;
import v2.org.analysis.value.LongValue;
 
public class NDdeShareGetInfo extends NddeapiAPI {
	public NDdeShareGetInfo () {
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
		String lpszServer = null;
		if ( t0 != 0L ) lpszServer = memory.getText(this, t0);
		String lpszShareName = null;
		if ( t1 != 0L ) lpszShareName = memory.getText(this, t1);
		UINT nLevel = new UINT (t2);
		byte[] lpBuffer = null;
		if ( t3 != 0L ) lpBuffer = new byte[(int) t4];
		for (int i = 0; i < lpBuffer.length; i++) {
			lpBuffer [i] = (byte) ((LongValue) memory.getByteMemoryValue (t3)).getValue();
			t3 += 1;
		}
		DWORD cBufSize = new DWORD (t4);
		IntByReference lpnTotalAvailable = new IntByReference ((int) t5);
		ShortByReference lpnItems = new ShortByReference ((short) t6);

		// Step 3: call API function
		int ret = NddeapiDLL.INSTANCE.NDdeShareGetInfo (lpszServer, lpszShareName, nLevel, lpBuffer, cBufSize, lpnTotalAvailable, lpnItems);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t3 = this.params.get(3);
		for (int i = 0; i < lpBuffer.length; i++) {
			memory.setByteMemoryValue (t3, new LongValue(lpBuffer [i]));
			t3 += 1;
		}		memory.setDoubleWordMemoryValue(t5, new LongValue(lpnTotalAvailable.getValue()));

		

	}
}