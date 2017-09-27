/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SetCaretBlinkTime.java
 * Created date: Aug 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Sets the caret blink time to the specified number of milliseconds. The
 * blink time is the elapsed time, in milliseconds, required to invert the
 * caret's pixels.
 * 
 * @param uMSeconds
 *            The new blink time, in milliseconds.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 *         
 * @author Yen Nguyen
 *
 */
public class SetCaretBlinkTime extends User32API {
	public SetCaretBlinkTime() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		UINT uMSeconds = new UINT(t1);
		BOOL ret = User32DLL.INSTANCE.SetCaretBlinkTime(uMSeconds);
		
		System.out.println("Return value:" + ret.intValue());
		register.mov("eax", new LongValue(ret.intValue()));
	}

}
