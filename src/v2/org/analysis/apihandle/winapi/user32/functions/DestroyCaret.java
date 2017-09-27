/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DestroyCaret.java
 * Created date: Oct 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;

/**
 * Destroys the caret's current shape, frees the caret from the window, and
 * removes the caret from the screen.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class DestroyCaret extends User32API {

	public DestroyCaret() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		BOOL ret = User32DLL.INSTANCE.DestroyCaret();

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
