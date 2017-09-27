/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetDCEx.java
 * Created date: Dec 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HRGN;
import com.sun.jna.platform.win32.WinDef.HWND;

/**
 * The GetDCEx function retrieves a handle to a device context (DC) for the
 * client area of a specified window or for the entire screen. You can use the
 * returned handle in subsequent GDI functions to draw in the DC. The device
 * context is an opaque data structure, whose values are used internally by GDI.
 * 
 * @param hWnd
 *            A handle to the window whose DC is to be retrieved. If this value
 *            is NULL, GetDCEx retrieves the DC for the entire screen.
 * 
 * @param hrgnClip
 *            A clipping region that may be combined with the visible region of
 *            the DC. If the value of flags is DCX_INTERSECTRGN or
 *            DCX_EXCLUDERGN, then the operating system assumes ownership of the
 *            region and will automatically delete it when it is no longer
 *            needed. In this case, the application should not use or delete the
 *            region after a successful call to GetDCEx.
 * 
 * @param flags
 *            Specifies how the DC is created. This parameter can be one or more
 *            of the following values.
 * 
 * @return If the function succeeds, the return value is the handle to the DC
 *         for the specified window. If the function fails, the return value is
 *         NULL. An invalid value for the hWnd parameter will cause the function
 *         to fail.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDCEx extends User32API {

	public GetDCEx() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HWND hWnd = new HWND(new Pointer(t1));
		HRGN hrgnClip = new HRGN(new Pointer(t2));
		int flags = (int) t3;
		HDC ret = User32DLL.INSTANCE.GetDCEx(hWnd, hrgnClip, flags);

		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
