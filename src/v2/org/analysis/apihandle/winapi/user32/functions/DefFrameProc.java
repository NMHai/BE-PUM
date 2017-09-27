/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DefFrameProc.java
 * Created date: Aug 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Provides default processing for any window messages that the window procedure
 * of a multiple-document interface (MDI) frame window does not process. All
 * window messages that are not explicitly processed by the window procedure
 * must be passed to the DefFrameProc function, not the DefWindowProc function.
 * 
 * @param hWnd
 *            A handle to the MDI frame window.
 * 
 * @param hWndMDIClient
 *            A handle to the MDI client window.
 * 
 * @param uMsg
 *            The message to be processed.
 * 
 * @param wParam
 *            Additional message-specific information.
 * 
 * @param lParam
 *            Additional message-specific information.
 * 
 * @return The return value specifies the result of the message processing and
 *         depends on the message. If the hWndMDIClient parameter is NULL, the
 *         return value is the same as for the DefWindowProc function.
 * 
 * @author Yen Nguyen
 *
 */
public class DefFrameProc extends User32API {
	public DefFrameProc() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		HWND hWnd = new HWND(new Pointer(t1));
		HWND hWndMDIClient = new HWND(new Pointer(t2));
		UINT uMsg = new UINT(t3);
		WPARAM wParam = new WPARAM(t4);
		LPARAM lParam = new LPARAM(t5);
		LRESULT ret = User32DLL.INSTANCE.DefFrameProc(hWnd, hWndMDIClient, uMsg, wParam, lParam);

		System.out.println("Return value:" + ret.intValue());
		register.mov("eax", new LongValue(ret.intValue()));
	}

}
