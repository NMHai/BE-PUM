/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: SendMessage.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;

/**
 * Sends the specified message to a window or windows. The SendMessage function
 * calls the window procedure for the specified window and does not return until
 * the window procedure has processed the message.
 * 
 * @param hWnd
 *            : A handle to the window whose window procedure will receive the
 *            message. If this parameter is HWND_BROADCAST ((HWND)0xffff), the
 *            message is sent to all top-level windows in the system, including
 *            disabled or invisible unowned windows, overlapped windows, and
 *            pop-up windows; but the message is not sent to child windows.
 * 
 * @param Msg
 *            : The message to be sent.
 * 
 * @param wParam
 *            : Additional message-specific information.
 * 
 * @param lParam
 *            : Additional message-specific information.
 * 
 * @return The return value specifies the result of the message processing; it
 *         depends on the message sent.
 * 
 * @author Yen Nguyen
 *
 */
public class SendMessage extends User32API {

	public SendMessage() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		// String str = symbolValueMemoryOperand.getText(this, new
		// X86MemoryOperand(DataType.INT32, t2));
		String msg = memory.getText(this, t2);

		System.out.println("Window Handle:" + t1 + ", Message Sent:" + msg + ", First Param:" + t3 + ", Second Param:"
				+ t4);

		LRESULT ret = User32DLL.INSTANCE.SendMessage(new HWND(new Pointer(t1)), (int) t2, new WPARAM(t3),
				new LPARAM(t4));

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
