/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetTopWindow.java
 * Created date: Apr 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Examines the Z order of the child windows associated with the specified
 * parent window and retrieves a handle to the child window at the top of the Z
 * order.
 * 
 * @param hWnd
 *            A handle to the parent window whose child windows are to be
 *            examined. If this parameter is NULL, the function returns a handle
 *            to the window at the top of the Z order.
 * 
 * @return If the function succeeds, the return value is a handle to the child
 *         window at the top of the Z order. If the specified window has no
 *         child windows, the return value is NULL. To get extended error
 *         information, use the GetLastError function.
 * 
 * @author Yen Nguyen
 *
 */
public class GetTopWindow extends User32API {

	public GetTopWindow() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = (t1 == 0) ? null : new HWND(new Pointer(t1));
		HWND ret = User32DLL.INSTANCE.GetTopWindow(hWnd);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
