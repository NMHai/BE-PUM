/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SetCaretPos.java
 * Created date: Oct 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;

/**
 * Moves the caret to the specified coordinates. If the window that owns the
 * caret was created with the CS_OWNDC class style, then the specified
 * coordinates are subject to the mapping mode of the device context associated
 * with that window.
 * 
 * @param X
 *            The new x-coordinate of the caret.
 * 
 * @param Y
 *            The new y-coordinate of the caret.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SetCaretPos extends User32API {

	public SetCaretPos() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		int X = (int) t1;
		int Y = (int) t2;
		BOOL ret = User32DLL.INSTANCE.SetCaretPos(X, Y);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
