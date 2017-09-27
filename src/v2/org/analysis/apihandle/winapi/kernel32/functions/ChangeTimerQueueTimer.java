/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: ChangeTimerQueueTimer.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;
 
public class ChangeTimerQueueTimer extends Kernel32API {
	public ChangeTimerQueueTimer () {
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
		HANDLE TimerQueue = null;
		if ( t0 != 0L ) {
			TimerQueue = new HANDLE ();
			TimerQueue.setPointer(new Pointer(t0));
		}
		HANDLE Timer = null;
		if ( t1 != 0L ) {
			Timer = new HANDLE ();
			Timer.setPointer(new Pointer(t1));
		}
		ULONG DueTime = new ULONG ((int) t2);
		ULONG Period = new ULONG ((int) t3);

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.ChangeTimerQueueTimer (TimerQueue, Timer, DueTime, Period);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}