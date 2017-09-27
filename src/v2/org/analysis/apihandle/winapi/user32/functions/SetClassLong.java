/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SetClassLong.java
 * Created date: Oct 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LONG;

/**
 * Replaces the specified 32-bit (long) value at the specified offset into the
 * extra class memory or the WNDCLASSEX structure for the class to which the
 * specified window belongs.
 * 
 * @param hWnd
 *            A handle to the window and, indirectly, the class to which the
 *            window belongs.
 * 
 * @param nIndex
 *            The value to be replaced. To set a 32-bit value in the extra class
 *            memory, specify the positive, zero-based byte offset of the value
 *            to be set. Valid values are in the range zero through the number
 *            of bytes of extra class memory, minus four; for example, if you
 *            specified 12 or more bytes of extra class memory, a value of 8
 *            would be an index to the third 32-bit integer. To set any other
 *            value from the WNDCLASSEX structure, specify one of the following
 *            values.
 * 
 * @param dwNewLong
 *            The replacement value.
 * 
 * @return If the function succeeds, the return value is the previous value of
 *         the specified 32-bit integer. If the value was not previously set,
 *         the return value is zero. If the function fails, the return value is
 *         zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SetClassLong extends User32API {

	public SetClassLong() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HWND hWnd = new HWND(new Pointer(t1));
		int nIndex = (int) t2;
		LONG dwNewLong = new LONG(t3);
		
		DWORD ret = User32DLL.INSTANCE.SetClassLong(hWnd, nIndex, dwNewLong);
		
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
