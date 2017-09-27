package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Determines whether a window is maximized.
 * 
 * @param hWnd
 *            A handle to the window to be tested.
 * 
 * @return If the window is zoomed, the return value is nonzero. If the window
 *         is not zoomed, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class IsZoomed extends User32API {

	public IsZoomed() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = new HWND(new Pointer(t1));
		BOOL ret = User32DLL.INSTANCE.IsZoomed(hWnd);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
