/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: PostMessage.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.WPARAM;

/**
 * This function places a message in the message queue associated with the
 * thread that created the specified window and then returns without waiting for
 * the thread to process the message. Messages in a message queue are retrieved
 * by calls to the GetMessage or PeekMessage function.
 * 
 * @param hWnd
 *            : Handle to the window whose window procedure is to receive the
 *            message.
 * @param msg
 *            : Specifies the message to be posted.
 * @param wParam
 *            : Specifies additional message-specific information.
 * @param lParam
 *            : Specifies additional message-specific information.
 *
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class PostMessage extends User32API {

	public PostMessage() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		/*
		 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
		 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
		 */
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		String msg = memory.getText(this, t2);
		String str1 = memory.getText(this, t1);
		System.out.println("Handle Window:" + t1 + " " + str1 + ", Post Message:" + msg + ", First Param:" + t3
				+ ", Second Param:" + t4);

		BOOL ret = User32DLL.INSTANCE.PostMessage(new HWND(new Pointer(t1)), (int) t2, new WPARAM(t3), new LPARAM(t4));

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
