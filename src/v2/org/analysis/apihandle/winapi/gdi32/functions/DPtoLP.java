/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: DPtoLP.java
 * Created date: Dec 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32.functions;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.POINT;

/**
 * The DPtoLP function converts device coordinates into logical coordinates. The
 * conversion depends on the mapping mode of the device context, the settings of
 * the origins and extents for the window and viewport, and the world
 * transformation.
 * 
 * @param hdc
 *            A handle to the device context.
 * 
 * @param lpPoints
 *            A pointer to an array of POINT structures. The x- and
 *            y-coordinates contained in each POINT structure will be
 *            transformed.
 * 
 * @param nCount
 *            The number of points in the array.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class DPtoLP extends Gdi32API {

	public DPtoLP() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {

		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HDC hdc = new HDC(new Pointer(t1));
		POINT lpPoints = new POINT();
		lpPoints.x = (int) ((LongValue) memory.getDoubleWordMemoryValue(t2)).getValue();
		lpPoints.y = (int) ((LongValue) memory.getDoubleWordMemoryValue(t2 + 4)).getValue();
		int nCount = (int) t3;
		BOOL ret = Gdi32DLL.INSTANCE.DPtoLP(hdc, lpPoints, nCount);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(t2, new LongValue(lpPoints.x));
		memory.setDoubleWordMemoryValue(t2 + 4, new LongValue(lpPoints.y));
	}

}
