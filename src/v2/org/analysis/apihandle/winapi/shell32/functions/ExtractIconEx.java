/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: ExtractIconEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;
 
public class ExtractIconEx extends Shell32API {
	public ExtractIconEx () {
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
		String lpszFile = null;
		if ( t0 != 0L ) lpszFile = memory.getText(this, t0);
		int nIconIndex = (int) t1;
		HANDLEByReference phiconLarge = new HANDLEByReference (new HANDLE(new Pointer(t2)));
		HANDLEByReference phiconSmall = new HANDLEByReference (new HANDLE(new Pointer(t3)));
		UINT nIcons = new UINT (t4);

		// Step 3: call API function
		int ret = Shell32DLL.INSTANCE.ExtractIconEx (lpszFile, nIconIndex, phiconLarge, phiconSmall, nIcons);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t2, new LongValue(Pointer.nativeValue(phiconLarge.getValue().getPointer())));

		
		memory.setDoubleWordMemoryValue(t3, new LongValue(Pointer.nativeValue(phiconSmall.getValue().getPointer())));

		

	}
}