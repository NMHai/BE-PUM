/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SendNotifyMessage.java
 * Created date: Sep 13, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Sends the specified message to a window or windows. If the window was created
 * by the calling thread, SendNotifyMessage calls the window procedure for the
 * window and does not return until the window procedure has processed the
 * message. If the window was created by a different thread, SendNotifyMessage
 * passes the message to the window procedure and returns immediately; it does
 * not wait for the window procedure to finish processing the message.
 * 
 * @param hWnd
 *            A handle to the window whose window procedure will receive the
 *            message. If this parameter is HWND_BROADCAST ((HWND)0xffff), the
 *            message is sent to all top-level windows in the system, including
 *            disabled or invisible unowned windows, overlapped windows, and
 *            pop-up windows; but the message is not sent to child windows.
 * 
 * @param Msg
 *            The message to be sent.
 * 
 * @param wParam
 *            Additional message-specific information.
 * 
 * @param lParam
 *            Additional message-specific information.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SendNotifyMessage extends User32API {

	public SendNotifyMessage() {
		super();
		NUM_OF_PARMS = 4;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see v2.org.analysis.apihandle.winapi.API#execute()
	 */
	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		HWND hWnd = new HWND(new Pointer(t1));
		UINT Msg = new UINT(t2);
		WPARAM wParam = new WPARAM(t3);
		LPARAM lParam = new LPARAM(t4);
		BOOL ret = User32DLL.INSTANCE.SendNotifyMessage(hWnd, Msg, wParam, lParam);

		register.mov("eax", new LongValue(ret.longValue()));
	}
}
