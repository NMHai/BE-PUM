package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HDC;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The GetBkColor function returns the current background color for the
 * specified device context.
 * 
 * @param hdc
 *            Handle to the device context whose background color is to be
 *            returned.
 * 
 * @return If the function succeeds, the return value is a COLORREF value for
 *         the current background color. If the function fails, the return value
 *         is CLR_INVALID.
 * 
 * @author Yen Nguyen
 *
 */
public class GetBkColor extends Gdi32API {

	public GetBkColor() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HDC hdc = new HDC(new Pointer(t1));
		DWORD ret = Gdi32DLL.INSTANCE.GetBkColor(hdc);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
