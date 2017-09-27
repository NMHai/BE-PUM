/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.value.LongValue;

/**
 * Opens the clipboard for examination and prevents other applications from
 * modifying the clipboard content.
 * 
 * @param hWndNewOwner
 *            A handle to the window to be associated with the open clipboard.
 *            If this parameter is NULL, the open clipboard is associated with
 *            the current task.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class OpenClipboard extends User32API {

	public OpenClipboard() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWndNewOwner = (t1 != 0L) ? new HWND(new Pointer(t1)) : null;
		BOOL ret = User32DLL.INSTANCE.OpenClipboard(hWndNewOwner);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
