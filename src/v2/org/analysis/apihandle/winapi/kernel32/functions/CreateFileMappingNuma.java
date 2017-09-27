/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: CreateFileMappingNuma.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class CreateFileMappingNuma extends Kernel32API {
	public CreateFileMappingNuma () {
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
		HANDLE hFile = null;
		if ( t0 != 0L ) {
			hFile = new HANDLE ();
			hFile.setPointer(new Pointer(t0));
		}
		SECURITY_ATTRIBUTES lpFileMappingAttributes = null;
		if ( t1 != 0L) {
			lpFileMappingAttributes = new SECURITY_ATTRIBUTES ();
			lpFileMappingAttributes.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpFileMappingAttributes.lpSecurityDescriptor = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
			lpFileMappingAttributes.bInheritHandle = (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue() != 0L ) ? true : false;
			t1 += 4;
		}
		DWORD flProtect = new DWORD (t2);
		DWORD dwMaximumSizeHigh = new DWORD (t3);
		DWORD dwMaximumSizeLow = new DWORD (t4);
		String lpName = null;
		if ( t5 != 0L ) lpName = memory.getText(this, t5);
		DWORD nndPreferred = new DWORD (t6);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.CreateFileMappingNuma (hFile, lpFileMappingAttributes, flProtect, dwMaximumSizeHigh, dwMaximumSizeLow, lpName, nndPreferred);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}