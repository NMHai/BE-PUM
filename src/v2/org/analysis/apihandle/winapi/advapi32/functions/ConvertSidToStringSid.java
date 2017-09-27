/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: ConvertSidToStringSid.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.PSID;
import com.sun.jna.ptr.PointerByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class ConvertSidToStringSid extends Advapi32API {
	public ConvertSidToStringSid () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		PSID Sid = null;
		if ( t0 != 0L) {
			Sid = new PSID ();
			Sid.sid = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}
		PointerByReference StringSid = new PointerByReference (new Pointer(t1));

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.ConvertSidToStringSid (Sid, StringSid);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t1, new LongValue(Pointer.nativeValue(StringSid.getValue())));

		

	}
}