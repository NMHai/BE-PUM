/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SetForegroundWindow.java
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
 * Brings the thread that created the specified window into the foreground and
 * activates the window. Keyboard input is directed to the window, and various
 * visual cues are changed for the user. The system assigns a slightly higher
 * priority to the thread that created the foreground window than it does to
 * other threads.
 * 
 * @param hWnd
 *            A handle to the window that should be activated and brought to the
 *            foreground.
 * 
 * @return If the window was brought to the foreground, the return value is
 *         nonzero. If the window was not brought to the foreground, the return
 *         value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class SetForegroundWindow extends User32API {

	public SetForegroundWindow() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = new HWND(new Pointer(t1));
		BOOL ret = User32DLL.INSTANCE.SetForegroundWindow(hWnd );
		
		register.mov("eax", new LongValue(ret.longValue()));
	}
}
