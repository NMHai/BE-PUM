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
 * Retrieves a handle to the foreground window (the window with which the user
 * is currently working). The system assigns a slightly higher priority to the
 * thread that creates the foreground window than it does to other threads.
 * 
 * @return The return value is a handle to the foreground window. The foreground
 *         window can be NULL in certain circumstances, such as when a window is
 *         losing activation.
 * 
 * @author Yen Nguyen
 *
 */
public class GetForegroundWindow extends User32API {

	public GetForegroundWindow() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		HWND ret = User32.INSTANCE.GetForegroundWindow();

		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
