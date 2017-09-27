/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: AllocateUserPhysicalPagesNuma.java
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
 
public class AllocateUserPhysicalPagesNuma extends Kernel32API {
	public AllocateUserPhysicalPagesNuma () {
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
		HANDLE hProcess = null;
		if ( t0 != 0L ) {
			hProcess = new HANDLE ();
			hProcess.setPointer(new Pointer(t0));
		}
		IntByReference NumberOfPages = new IntByReference ((int) t1);
		int[] PageArray = null;
		if ( t2 != 0L ) PageArray = new int[(int) t3];
		for (int i = 0; i < PageArray.length; i++) {
			PageArray [i] = (int) ((LongValue) memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
		}
		DWORD nndPreferred = new DWORD (t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.AllocateUserPhysicalPagesNuma (hProcess, NumberOfPages, PageArray, nndPreferred);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < PageArray.length; i++) {
			memory.setDoubleWordMemoryValue (t2, new LongValue(PageArray [i]));
			t2 += 4;
		}
	}
}