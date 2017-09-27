/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetLastActivePopup.java
 * Created date: Sep 18, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Determines which pop-up window owned by the specified window was most
 * recently active.
 * 
 * @param hWnd
 *            A handle to the owner window.
 * 
 * @return The return value identifies the most recently active pop-up window.
 *         The return value is the same as the hWnd parameter, if any of the
 *         following conditions are met: The window identified by hWnd was most
 *         recently active. The window identified by hWnd does not own any
 *         pop-up windows. The window identifies by hWnd is not a top-level
 *         window, or it is owned by another window.
 *
 * @author Yen Nguyen
 *
 */
public class GetLastActivePopup extends User32API {

	public GetLastActivePopup() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = new HWND(new Pointer(t1));
		HWND ret = User32DLL.INSTANCE.GetLastActivePopup(hWnd);

		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
