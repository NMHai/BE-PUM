/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mpr.functions
 * File name: WNetDisconnectDialog1.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.mpr.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.structures.DISCDLGSTRUCT;
import v2.org.analysis.apihandle.winapi.mpr.MprAPI;
import v2.org.analysis.apihandle.winapi.mpr.MprDLL;
import v2.org.analysis.value.LongValue;
 
public class WNetDisconnectDialog1 extends MprAPI {
	public WNetDisconnectDialog1 () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		DISCDLGSTRUCT lpConnDlgStruct = null;
		if ( t0 != 0L) {
			lpConnDlgStruct = new DISCDLGSTRUCT ();
			lpConnDlgStruct.cbStructure = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpConnDlgStruct.hwndOwner = new HWND (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue()));
			t0 += 4;
			lpConnDlgStruct.lpLocalName = memory.getText(this, t0);
			t0 += 4;
			lpConnDlgStruct.lpRemoteName = memory.getText(this, t0);
			t0 += 4;
			lpConnDlgStruct.dwFlags = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}

		// Step 3: call API function
		int ret = MprDLL.INSTANCE.WNetDisconnectDialog1 (lpConnDlgStruct);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}