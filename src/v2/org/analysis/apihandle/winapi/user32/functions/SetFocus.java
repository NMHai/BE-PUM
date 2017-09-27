/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SetFocus.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.value.LongValue;

/**
 * Sets the keyboard focus to the specified window. The window must be attached
 * to the calling thread's message queue.
 * 
 * @param hWnd
 *            A handle to the window that will receive the keyboard input. If
 *            this parameter is NULL, keystrokes are ignored.
 * 
 * @return If the function succeeds, the return value is the handle to the
 *         window that previously had the keyboard focus. If the hWnd parameter
 *         is invalid or the window is not attached to the calling thread's
 *         message queue, the return value is NULL. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SetFocus extends User32API {

	public SetFocus() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = (t1 == 0L) ? null : new HWND(new Pointer(t1));
		HWND ret = User32.INSTANCE.SetFocus(hWnd);

		long value = Pointer.nativeValue(ret.getPointer());

		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
