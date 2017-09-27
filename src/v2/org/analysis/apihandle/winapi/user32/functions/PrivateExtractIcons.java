/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: PrivateExtractIcons.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class PrivateExtractIcons extends User32API {
	public PrivateExtractIcons () {
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
		String lpszFile = null;
		if ( t0 != 0L ) lpszFile = memory.getText(this, t0);
		int nIconIndex = (int) t1;
		int cxIcon = (int) t2;
		int cyIcon = (int) t3;
		HANDLEByReference phicon = new HANDLEByReference (new HANDLE(new Pointer(t4)));
		IntByReference piconid = new IntByReference ((int) t5);
		UINT nIcons = new UINT (t6);
		UINT flags = new UINT (t7);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.PrivateExtractIcons (lpszFile, nIconIndex, cxIcon, cyIcon, phicon, piconid, nIcons, flags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t4, new LongValue(Pointer.nativeValue(phicon.getValue().getPointer())));

		
		memory.setDoubleWordMemoryValue(t5, new LongValue(piconid.getValue()));

		

	}
}