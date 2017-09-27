package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Determines whether a window is a child window or descendant window of a
 * specified parent window. A child window is the direct descendant of a
 * specified parent window if that parent window is in the chain of parent
 * windows; the chain of parent windows leads from the original overlapped or
 * pop-up window to the child window.
 * 
 * @param hWndParent
 *            A handle to the parent window.
 * 
 * @param hWnd
 *            A handle to the window to be tested.
 * 
 * @return If the window is a child or descendant window of the specified parent
 *         window, the return value is nonzero. If the window is not a child or
 *         descendant window of the specified parent window, the return value is
 *         zero.
 * 
 * @author Yen Nguyen
 *
 */
public class IsChild extends User32API {

	public IsChild() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HWND hWndParent = new HWND(new Pointer(t1));
		HWND hWnd = new HWND(new Pointer(t2));

		BOOL ret = User32DLL.INSTANCE.IsChild(hWndParent, hWnd);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
