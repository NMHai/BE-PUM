/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: WaitForSingleObject.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Waits until the specified object is in the signaled state or the time-out
 * interval elapses. To enter an alertable wait state, use the
 * WaitForSingleObjectEx function. To wait for multiple objects, use the
 * WaitForMultipleObjects.
 * 
 * @param hHandle
 *            A handle to the object. For a list of the object types whose
 *            handles can be specified, see the following Remarks section. If
 *            this handle is closed while the wait is still pending, the
 *            function's behavior is undefined. The handle must have the
 *            SYNCHRONIZE access right. For more information, see Standard
 *            Access Rights.
 * 
 * @param dwMilliseconds
 *            The time-out interval, in milliseconds. If a nonzero value is
 *            specified, the function waits until the object is signaled or the
 *            interval elapses. If dwMilliseconds is zero, the function does not
 *            enter a wait state if the object is not signaled; it always
 *            returns immediately. If dwMilliseconds is INFINITE, the function
 *            will return only when the object is signaled.
 * 
 * @return If the function succeeds, the return value indicates the event that
 *         caused the function to return.
 * 
 * @author Yen Nguyen
 *
 */
public class WaitForSingleObject extends Kernel32API {

	public WaitForSingleObject() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		int ret = Kernel32.INSTANCE.WaitForSingleObject(new HANDLE(new Pointer(t1)), (int) t2);
		
		if (t2 == 0xffffffffL) {
			t2 = 0xfffL;
		}

		// http://stackoverflow.com/questions/897614/how-do-i-know-if-a-thread-is-suspended-under-windows-ce
		// Return the state of a suspended thread
		if (SuspendThread.suspendedThreadList.contains(t1)) {
			ret = 258; // 0x102
		}
		register.mov("eax", new LongValue(ret));
	}
}
