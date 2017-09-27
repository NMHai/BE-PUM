/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: LookupAccountSid.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.PSID;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class LookupAccountSid extends Advapi32API {
	public LookupAccountSid () {
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
		String lpSystemName = null;
		if ( t0 != 0L ) lpSystemName = memory.getText(this, t0);
		PSID lpSid = null;
		if ( t1 != 0L) {
			lpSid = new PSID ();
			lpSid.sid = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue());
			t1 += 4;
		}
		char[] lpName = null;
		if ( t2 != 0L ) lpName = new char[(int) t3];
		for (int i = 0; i < lpName.length; i++) {
			lpName [i] = (char) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		IntByReference cchName = new IntByReference ((int) t3);
		char[] lpReferencedDomainName = null;
		if ( t4 != 0L ) lpReferencedDomainName = new char[(int) t5];
		for (int i = 0; i < lpReferencedDomainName.length; i++) {
			lpReferencedDomainName [i] = (char) ((LongValue) memory.getByteMemoryValue (t4)).getValue();
			t4 += 1;
		}
		IntByReference cchReferencedDomainName = new IntByReference ((int) t5);
		PointerByReference peUse = new PointerByReference (new Pointer(t6));

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.LookupAccountSid (lpSystemName, lpSid, lpName, cchName, lpReferencedDomainName, cchReferencedDomainName, peUse);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < lpName.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(lpName [i]));
			t2 += 1;
		}		t4 = this.params.get(4);
		for (int i = 0; i < lpReferencedDomainName.length; i++) {
			memory.setByteMemoryValue (t4, new LongValue(lpReferencedDomainName [i]));
			t4 += 1;
		}		memory.setDoubleWordMemoryValue(t6, new LongValue(Pointer.nativeValue(peUse.getValue())));

		

	}
}