/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.avrt.functions
 * File name: AvRtJoinThreadOrderingGroup.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.avrt.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid.GUID;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

import v2.org.analysis.apihandle.winapi.avrt.AvrtAPI;
import v2.org.analysis.apihandle.winapi.avrt.AvrtDLL;
import v2.org.analysis.value.LongValue;
 
public class AvRtJoinThreadOrderingGroup extends AvrtAPI {
	public AvRtJoinThreadOrderingGroup () {
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
		HANDLEByReference Context = new HANDLEByReference (new HANDLE(new Pointer(t0)));
		GUID ThreadOrderingGuid = null;
		if ( t1 != 0L) {
			ThreadOrderingGuid = new GUID ();
			ThreadOrderingGuid.Data1 = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			ThreadOrderingGuid.Data2 = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			ThreadOrderingGuid.Data3 = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			for (int i = 0; i < ThreadOrderingGuid.Data4.length; i++) {
				ThreadOrderingGuid.Data4 [i] = (byte) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
				t1 += 1;
			}
		}
		BOOL Before = new BOOL (t2);

		// Step 3: call API function
		int ret = AvrtDLL.INSTANCE.AvRtJoinThreadOrderingGroup (Context, ThreadOrderingGuid, Before);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t0, new LongValue(Pointer.nativeValue(Context.getValue().getPointer())));

		

	}
}