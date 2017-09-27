/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: VerifyVersionInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDLONG;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinNT.OSVERSIONINFOEX;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class VerifyVersionInfo extends Kernel32API {
	public VerifyVersionInfo () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		OSVERSIONINFOEX lpVersionInfo = null;
		if ( t0 != 0L) {
			lpVersionInfo = new OSVERSIONINFOEX ();
			lpVersionInfo.dwOSVersionInfoSize = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpVersionInfo.dwMajorVersion = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpVersionInfo.dwMinorVersion = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpVersionInfo.dwBuildNumber = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpVersionInfo.dwPlatformId = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			for (int i = 0; i < lpVersionInfo.szCSDVersion.length; i++) {
				lpVersionInfo.szCSDVersion [i] = (char) ((LongValue) memory.getWordMemoryValue (t0)).getValue();
				t0 += 2;
			}
			lpVersionInfo.wServicePackMajor = new WORD (((LongValue)memory.getWordMemoryValue (t0)).getValue());
			t0 += 2;
			lpVersionInfo.wServicePackMinor = new WORD (((LongValue)memory.getWordMemoryValue (t0)).getValue());
			t0 += 2;
			lpVersionInfo.wSuiteMask = new WORD (((LongValue)memory.getWordMemoryValue (t0)).getValue());
			t0 += 2;
			lpVersionInfo.wProductType = (byte) ((LongValue)memory.getByteMemoryValue (t0)).getValue();
			t0 += 1;
			lpVersionInfo.wReserved = (byte) ((LongValue)memory.getByteMemoryValue (t0)).getValue();
			t0 += 1;
		}
		DWORD dwTypeMask = new DWORD (t1);
		DWORDLONG dwlConditionMask = new DWORDLONG (t2);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.VerifyVersionInfo (lpVersionInfo, dwTypeMask, dwlConditionMask);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}