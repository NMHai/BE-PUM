/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetWindowTextLength.java
 * Created date: Nov 4, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

/**
 * Retrieves the length, in characters, of the specified window's title bar text
 * (if the window has a title bar). If the specified window is a control, the
 * function retrieves the length of the text within the control. However,
 * GetWindowTextLength cannot retrieve the length of the text of an edit control
 * in another application.
 * 
 * @param hWnd
 *            A handle to the window or control.
 * 
 * @return If the function succeeds, the return value is the length, in
 *         characters, of the text. Under certain conditions, this value may
 *         actually be greater than the length of the text. For more
 *         information, see the following Remarks section. If the window has no
 *         text, the return value is zero. To get extended error information,
 *         call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetWindowTextLength extends User32API {

	public GetWindowTextLength() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = new HWND(new Pointer(t1));
		int ret = User32DLL.INSTANCE.GetWindowTextLength(hWnd);
		
		register.mov("eax", new LongValue(ret));
	}

}
