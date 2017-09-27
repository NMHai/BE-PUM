/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HBRUSH;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import v2.org.analysis.value.LongValue;

/**
 * The GetSysColorBrush function retrieves a handle identifying a logical brush
 * that corresponds to the specified color index.
 * 
 * @param nIndex
 *            A color index. This value corresponds to the color used to paint
 *            one of the window elements. See GetSysColor for system color index
 *            values.
 * 
 * @return The return value identifies a logical brush if the nIndex parameter
 *         is supported by the current platform. Otherwise, it returns NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSysColorBrush extends User32API {

	public GetSysColorBrush() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long x = this.params.get(0);

		int nIndex = (int) x;
		HBRUSH ret = User32DLL.INSTANCE.GetSysColorBrush(nIndex);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
