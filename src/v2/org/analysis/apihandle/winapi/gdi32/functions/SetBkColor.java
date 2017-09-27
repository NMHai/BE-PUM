/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: SetBkColor.java
 * Created date: Oct 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32.functions;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HDC;

/**
 * The SetBkColor function sets the current background color to the specified
 * color value, or to the nearest physical color if the device cannot represent
 * the specified color value.
 * 
 * @param hdc
 *            A handle to the device context.
 * 
 * @param crColor
 *            The new background color. To make a COLORREF value, use the RGB
 *            macro.
 * 
 * @return If the function succeeds, the return value specifies the previous
 *         background color as a COLORREF value. If the function fails, the
 *         return value is CLR_INVALID.
 * 
 * @author Yen Nguyen
 *
 */
public class SetBkColor extends Gdi32API {

	public SetBkColor() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HDC hdc = new HDC(new Pointer(t1));
		DWORD crColor = new DWORD(t2);
		DWORD ret = Gdi32DLL.INSTANCE.SetBkColor(hdc, crColor);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
