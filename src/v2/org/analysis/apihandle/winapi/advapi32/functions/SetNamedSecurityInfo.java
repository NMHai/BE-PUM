/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: SetNamedSecurityInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.ACL;
import com.sun.jna.platform.win32.WinNT.PSID;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetNamedSecurityInfo extends Advapi32API {
	public SetNamedSecurityInfo () {
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
		String pObjectName = null;
		if ( t0 != 0L ) pObjectName = memory.getText(this, t0);
		int ObjectType = (int) t1;
		int SecurityInfo = (int) t2;
		PSID psidOwner = null;
		if ( t3 != 0L) {
			psidOwner = new PSID ();
			psidOwner.sid = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
		}
		PSID psidGroup = null;
		if ( t4 != 0L) {
			psidGroup = new PSID ();
			psidGroup.sid = new Pointer (((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue());
			t4 += 4;
		}
		ACL pDacl = null;
		if ( t5 != 0L) {
			pDacl = new ACL ();
			pDacl.AclRevision = (byte) ((LongValue)memory.getByteMemoryValue (t5)).getValue();
			t5 += 1;
			pDacl.Sbz1 = (byte) ((LongValue)memory.getByteMemoryValue (t5)).getValue();
			t5 += 1;
			pDacl.AclSize = (short) ((LongValue)memory.getWordMemoryValue (t5)).getValue();
			t5 += 2;
			pDacl.AceCount = (short) ((LongValue)memory.getWordMemoryValue (t5)).getValue();
			t5 += 2;
			pDacl.Sbz2 = (short) ((LongValue)memory.getWordMemoryValue (t5)).getValue();
			t5 += 2;
		}
		ACL pSacl = null;
		if ( t6 != 0L) {
			pSacl = new ACL ();
			pSacl.AclRevision = (byte) ((LongValue)memory.getByteMemoryValue (t6)).getValue();
			t6 += 1;
			pSacl.Sbz1 = (byte) ((LongValue)memory.getByteMemoryValue (t6)).getValue();
			t6 += 1;
			pSacl.AclSize = (short) ((LongValue)memory.getWordMemoryValue (t6)).getValue();
			t6 += 2;
			pSacl.AceCount = (short) ((LongValue)memory.getWordMemoryValue (t6)).getValue();
			t6 += 2;
			pSacl.Sbz2 = (short) ((LongValue)memory.getWordMemoryValue (t6)).getValue();
			t6 += 2;
		}

		// Step 3: call API function
		int ret = Advapi32DLL.INSTANCE.SetNamedSecurityInfo (pObjectName, ObjectType, SecurityInfo, psidOwner, psidGroup, pDacl, pSacl);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}