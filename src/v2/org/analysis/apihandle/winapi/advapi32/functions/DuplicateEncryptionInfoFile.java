/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: DuplicateEncryptionInfoFile.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class DuplicateEncryptionInfoFile extends Advapi32API {
	public DuplicateEncryptionInfoFile () {
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
		String SrcFileName = null;
		if ( t0 != 0L ) SrcFileName = memory.getText(this, t0);
		String DstFileName = null;
		if ( t1 != 0L ) DstFileName = memory.getText(this, t1);
		DWORD dwCreationDistribution = new DWORD (t2);
		DWORD dwAttributes = new DWORD (t3);
		SECURITY_ATTRIBUTES lpSecurityAttributes = null;
		if ( t4 != 0L) {
			lpSecurityAttributes = new SECURITY_ATTRIBUTES ();
			lpSecurityAttributes.dwLength = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue());
			t4 += 4;
			lpSecurityAttributes.lpSecurityDescriptor = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue());
			t4 += 4;
			lpSecurityAttributes.bInheritHandle = (((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue() != 0L ) ? true : false;
			t4 += 4;
		}

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.DuplicateEncryptionInfoFile (SrcFileName, DstFileName, dwCreationDistribution, dwAttributes, lpSecurityAttributes);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}