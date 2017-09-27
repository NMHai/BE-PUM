/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: StrokePath.java
 * Created date: Dec 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32.functions;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HDC;

/**
 * The StrokePath function renders the specified path by using the current pen.
 * 
 * @param hdc
 *            Handle to a device context that contains the completed path.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class StrokePath extends Gdi32API {

	public StrokePath() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HDC hdc = new HDC(new Pointer(t1));
		BOOL ret = Gdi32DLL.INSTANCE.StrokePath(hdc);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
