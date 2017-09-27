/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetWindowLong.java
 * Created date: Apr 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LONG;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves information about the specified window. The function also retrieves
 * the 32-bit (DWORD) value at the specified offset into the extra window
 * memory.
 * 
 * @param hWnd
 *            A handle to the window and, indirectly, the class to which the
 *            window belongs.
 * 
 * @param nIndex
 *            The zero-based offset to the value to be retrieved. Valid values
 *            are in the range zero through the number of bytes of extra window
 *            memory, minus four; for example, if you specified 12 or more bytes
 *            of extra memory, a value of 8 would be an index to the third
 *            32-bit integer. To retrieve any other value, specify one of the
 *            following values.
 * 
 * @return If the function succeeds, the return value is the requested value. If
 *         the function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetWindowLong extends User32API {

	public GetWindowLong() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HWND hWnd = (t1 == 0L) ? null : new HWND(new Pointer(t1));
		int nIndex = (int) t2;
		LONG ret = User32DLL.INSTANCE.GetWindowLong(hWnd, nIndex);

		long value = ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
