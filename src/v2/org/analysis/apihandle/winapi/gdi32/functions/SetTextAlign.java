/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: SetTextAlign.java
 * Created date: Dec 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32.functions;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.UINT;

/**
 * The SetTextAlign function sets the text-alignment flags for the specified
 * device context.
 * 
 * @param hdc
 *            A handle to the device context.
 * 
 * @param fMode
 *            The text alignment by using a mask of the values in the following
 *            list. Only one flag can be chosen from those that affect
 *            horizontal and vertical alignment. In addition, only one of the
 *            two flags that alter the current position can be chosen.
 * 
 * @return If the function succeeds, the return value is the previous
 *         text-alignment setting. If the function fails, the return value is
 *         GDI_ERROR.
 * 
 * @author Yen Nguyen
 *
 */
public class SetTextAlign extends Gdi32API {

	public SetTextAlign() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {

		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HDC hdc = new HDC(new Pointer(t1));
		UINT fMode = new UINT(t2);
		UINT ret = Gdi32DLL.INSTANCE.SetTextAlign(hdc, fMode);
		
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
