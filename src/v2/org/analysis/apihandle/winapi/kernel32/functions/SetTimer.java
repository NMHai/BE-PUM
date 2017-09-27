/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetTimer.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import v2.org.analysis.value.LongValue;

/**
 * Creates a timer with the specified time-out value.
 * 
 * @param hWnd
 *            A handle to the window to be associated with the timer. This
 *            window must be owned by the calling thread. If a NULL value for
 *            hWnd is passed in along with an nIDEvent of an existing timer,
 *            that timer will be replaced in the same way that an existing
 *            non-NULL hWnd timer will be.
 * 
 * @param nIDEvent
 *            A nonzero timer identifier. If the hWnd parameter is NULL, and the
 *            nIDEvent does not match an existing timer then it is ignored and a
 *            new timer ID is generated. If the hWnd parameter is not NULL and
 *            the window specified by hWnd already has a timer with the value
 *            nIDEvent, then the existing timer is replaced by the new timer.
 *            When SetTimer replaces a timer, the timer is reset. Therefore, a
 *            message will be sent after the current time-out value elapses, but
 *            the previously set time-out value is ignored. If the call is not
 *            intended to replace an existing timer, nIDEvent should be 0 if the
 *            hWnd is NULL.
 * 
 * @param uElapse
 *            The time-out value, in milliseconds. If uElapse is less than
 *            USER_TIMER_MINIMUM (0x0000000A), the timeout is set to
 *            USER_TIMER_MINIMUM. If uElapse is greater than USER_TIMER_MAXIMUM
 *            (0x7FFFFFFF), the timeout is set to USER_TIMER_MAXIMUM.
 * 
 * @param lpTimerFunc
 *            A pointer to the function to be notified when the time-out value
 *            elapses. For more information about the function, see TimerProc.
 *            If lpTimerFunc is NULL, the system posts a WM_TIMER message to the
 *            application queue. The hwnd member of the message's MSG structure
 *            contains the value of the hWnd parameter.
 * 
 * @return If the function succeeds and the hWnd parameter is NULL, the return
 *         value is an integer identifying the new timer. An application can
 *         pass this value to the KillTimer function to destroy the timer. If
 *         the function succeeds and the hWnd parameter is not NULL, then the
 *         return value is a nonzero integer. An application can pass the value
 *         of the nIDEvent parameter to the KillTimer function to destroy the
 *         timer. If the function fails to create a timer, the return value is
 *         zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SetTimer extends Kernel32API {

	public SetTimer() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		HWND hWnd = new HWND(new Pointer(t1));
		UINT_PTR nIDEvent = new UINT_PTR(t2);
		UINT uElapse = new UINT(t3);
		// TODO: Can't assign proc pointer
		Callback lpTimerFunc = null;
		System.out.println("\t\t SPECIAL WINDOWS API: CALLBACK");
		
		UINT_PTR ret = Kernel32DLL.INSTANCE.SetTimer(hWnd, nIDEvent, uElapse, lpTimerFunc);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
