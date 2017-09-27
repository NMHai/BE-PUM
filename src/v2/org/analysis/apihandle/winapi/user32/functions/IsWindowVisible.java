/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: IsWindowVisible.java
 * Created date: Aug 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Determines the visibility state of the specified window.
 * 
 * @param hWnd
 *            A handle to the window to be tested.
 * 
 * @return If the specified window, its parent window, its parent's parent
 *         window, and so forth, have the WS_VISIBLE style, the return value is
 *         nonzero. Otherwise, the return value is zero. Because the return
 *         value specifies whether the window has the WS_VISIBLE style, it may
 *         be nonzero even if the window is totally obscured by other windows.
 * 
 * @author Yen Nguyen
 *
 */
public class IsWindowVisible extends User32API {

	public IsWindowVisible() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = new HWND(new Pointer(t1));
		BOOL ret = User32DLL.INSTANCE.IsWindowVisible(hWnd);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
