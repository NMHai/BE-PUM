/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: IsWindowUnicode.java
 * Created date: Nov 4, 2015
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
 * Determines whether the specified window is a native Unicode window.
 * 
 * @param hWnd
 *            A handle to the window to be tested.
 * 
 * @return If the window is a native Unicode window, the return value is
 *         nonzero. If the window is not a native Unicode window, the return
 *         value is zero. The window is a native ANSI window.
 * 
 * @author Yen Nguyen
 *
 */
public class IsWindowUnicode extends User32API {

	public IsWindowUnicode() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = new HWND(new Pointer(t1));
		BOOL ret = User32DLL.INSTANCE.IsWindowUnicode(hWnd);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
