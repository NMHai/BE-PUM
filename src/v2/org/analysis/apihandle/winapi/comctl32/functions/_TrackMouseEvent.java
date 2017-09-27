/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.comctl32.functions
 * File name: _TrackMouseEvent.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.comctl32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.structures.TRACKMOUSEEVENT;
import v2.org.analysis.apihandle.winapi.comctl32.Comctl32API;
import v2.org.analysis.apihandle.winapi.comctl32.Comctl32DLL;
import v2.org.analysis.value.LongValue;
 
public class _TrackMouseEvent extends Comctl32API {
	public _TrackMouseEvent () {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		
		// Step 2: type conversion from C++ to Java
		TRACKMOUSEEVENT lpEventTrack = null;
		if ( t0 != 0L) {
			lpEventTrack = new TRACKMOUSEEVENT ();
			lpEventTrack.cbSize = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpEventTrack.dwFlags = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
			lpEventTrack.hwndTrack = new HWND (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue()));
			t0 += 4;
			lpEventTrack.dwHoverTime = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t0)).getValue());
			t0 += 4;
		}

		// Step 3: call API function
		int ret = Comctl32DLL.INSTANCE._TrackMouseEvent (lpEventTrack);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}