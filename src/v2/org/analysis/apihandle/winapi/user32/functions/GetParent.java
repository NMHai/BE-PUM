/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetParent.java
 * Created date: Oct 14, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

/**
 * Retrieves a handle to the specified window's parent or owner. To retrieve a
 * handle to a specified ancestor, use the GetAncestor function.
 * 
 * @param hWnd
 *            A handle to the window whose parent window handle is to be
 *            retrieved.
 * 
 * @return If the window is a child window, the return value is a handle to the
 *         parent window. If the window is a top-level window with the WS_POPUP
 *         style, the return value is a handle to the owner window. If the
 *         function fails, the return value is NULL. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetParent extends User32API {

	public GetParent() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = new HWND(new Pointer(t1));
		HWND ret = User32DLL.INSTANCE.GetParent(hWnd);

		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
