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
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;

import v2.org.analysis.value.LongValue;

/**
 * This function retrieves a handle to a display device context (DC) for the
 * client area of the specified window. The display device context can be used
 * in subsequent graphics display interface (GDI) functions to draw in the
 * client area of the window.
 * 
 * @param hWnd
 *            Handle to the window whose device context is to be retrieved. If
 *            this value is NULL, GetDC retrieves the device context for the
 *            entire screen.
 * 
 * @return The handle the device context for the specified window's client area
 *         indicates success. NULL indicates failure. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDC extends User32API {

	public GetDC() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = new HWND(new Pointer(t1));
		HDC ret = User32.INSTANCE.GetDC(hWnd);

		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
