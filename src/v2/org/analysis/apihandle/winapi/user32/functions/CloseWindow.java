/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CloseWindow.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;

import v2.org.analysis.value.LongValue;

/**
 * Minimizes (but does not destroy) the specified window.
 * 
 * @param hWnd
 *            A handle to the window to be minimized.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 *         If the function fails, the return value is zero. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CloseWindow extends User32API {

	public CloseWindow() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = new HWND(new Pointer(t1));
		boolean ret = User32.INSTANCE.CloseWindow(hWnd);

		register.mov("eax", new LongValue(ret ? 1 : 0));
	}

}
