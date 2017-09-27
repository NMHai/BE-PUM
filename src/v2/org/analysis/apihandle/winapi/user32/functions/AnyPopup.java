/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: AnyPopup.java
 * Created date: Sep 17, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Indicates whether an owned, visible, top-level pop-up, or overlapped window
 * exists on the screen. The function searches the entire screen, not just the
 * calling application's client area.
 * 
 * This function is provided only for compatibility with 16-bit versions of
 * Windows. It is generally not useful.
 * 
 * @return If a pop-up window exists, the return value is nonzero, even if the
 *         pop-up window is completely covered by other windows. If a pop-up
 *         window does not exist, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class AnyPopup extends User32API {

	public AnyPopup() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		BOOL ret = User32DLL.INSTANCE.AnyPopup();

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
