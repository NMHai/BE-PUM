/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetFileInformationByHandle.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.BY_HANDLE_FILE_INFORMATION;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetFileInformationByHandle extends Kernel32API {
	public GetFileInformationByHandle () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hFile = null;
		if ( t0 != 0L ) {
			hFile = new HANDLE ();
			hFile.setPointer(new Pointer(t0));
		}
		BY_HANDLE_FILE_INFORMATION lpFileInformation = new BY_HANDLE_FILE_INFORMATION ();

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetFileInformationByHandle (hFile, lpFileInformation);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t1 = this.params.get(1);
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpFileInformation.dwFileAttributes.longValue()));
		t1 += 4;
		// Nested Structure
			lpFileInformation.ftCreationTime.dwLowDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue();
			lpFileInformation.ftCreationTime.dwHighDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
		// Nested Structure
			lpFileInformation.ftLastAccessTime.dwLowDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue();
			lpFileInformation.ftLastAccessTime.dwHighDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
		// Nested Structure
			lpFileInformation.ftLastWriteTime.dwLowDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 0)).getValue();
			lpFileInformation.ftLastWriteTime.dwHighDateTime = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1 += 4)).getValue();
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpFileInformation.dwVolumeSerialNumber.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpFileInformation.nFileSizeHigh.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpFileInformation.nFileSizeLow.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpFileInformation.nNumberOfLinks.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpFileInformation.nFileIndexHigh.longValue()));
		t1 += 4;
		memory.setDoubleWordMemoryValue (t1, new LongValue(lpFileInformation.nFileIndexLow.longValue()));
		t1 += 4;

	}
}