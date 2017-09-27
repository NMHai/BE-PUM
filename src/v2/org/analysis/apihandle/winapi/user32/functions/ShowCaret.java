/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: ShowCaret.java
 * Created date: Oct 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;

/**
 * Makes the caret visible on the screen at the caret's current position. When
 * the caret becomes visible, it begins flashing automatically.
 * 
 * @param hWnd
 *            A handle to the window that owns the caret. If this parameter is
 *            NULL, ShowCaret searches the current task for the window that owns
 *            the caret.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class ShowCaret extends User32API {

	public ShowCaret() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = (t1 == 0L) ? null : new HWND(new Pointer(t1));
		BOOL ret = User32DLL.INSTANCE.ShowCaret(hWnd);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
