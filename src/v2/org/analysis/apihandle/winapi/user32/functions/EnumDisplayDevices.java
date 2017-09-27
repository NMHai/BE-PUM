/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: EnumDisplayDevices.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.structures.DISPLAY_DEVICE;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class EnumDisplayDevices extends User32API {
	public EnumDisplayDevices () {
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
		String lpDevice = null;
		if ( t0 != 0L ) lpDevice = memory.getText(this, t0);
		DWORD iDevNum = new DWORD (t1);
		DISPLAY_DEVICE lpDisplayDevice = new DISPLAY_DEVICE ();
		DWORD dwFlags = new DWORD (t3);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.EnumDisplayDevices (lpDevice, iDevNum, lpDisplayDevice, dwFlags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpDisplayDevice.cb.longValue()));
		t2 += 4;
		for (int i = 0; i < lpDisplayDevice.DeviceName.length; i++) {
			memory.setWordMemoryValue (t2, new LongValue(lpDisplayDevice.DeviceName [i]));
			t2 += 2;
		}
		for (int i = 0; i < lpDisplayDevice.DeviceString.length; i++) {
			memory.setWordMemoryValue (t2, new LongValue(lpDisplayDevice.DeviceString [i]));
			t2 += 2;
		}
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpDisplayDevice.StateFlags.longValue()));
		t2 += 4;
		for (int i = 0; i < lpDisplayDevice.DeviceID.length; i++) {
			memory.setWordMemoryValue (t2, new LongValue(lpDisplayDevice.DeviceID [i]));
			t2 += 2;
		}
		for (int i = 0; i < lpDisplayDevice.DeviceKey.length; i++) {
			memory.setWordMemoryValue (t2, new LongValue(lpDisplayDevice.DeviceKey [i]));
			t2 += 2;
		}

	}
}