/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetActiveWindow.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the window handle to the active window attached to the calling
 * thread's message queue.
 * 
 * @return The return value is the handle to the active window attached to the
 *         calling thread's message queue. Otherwise, the return value is NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class GetActiveWindow extends User32API {

	public GetActiveWindow() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		HWND ret = User32DLL.INSTANCE.GetActiveWindow();

		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
