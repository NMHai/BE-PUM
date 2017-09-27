/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CloseWindow.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves the handle to the window that has the keyboard focus, if the
 * window is attached to the calling thread's message queue.
 * 
 * @return The return value is the handle to the window with the keyboard
 *         focus. If the calling thread's message queue does not have an
 *         associated window with the keyboard focus, the return value is
 *         NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class GetFocus extends User32API {

	public GetFocus() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		HWND ret = User32DLL.INSTANCE.GetFocus();
		
		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
	}

}
