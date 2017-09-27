/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetDeviceCaps.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.WinDef.HDC;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.value.LongValue;

/**
 * The GetDeviceCaps function retrieves device-specific information for the
 * specified device.
 * 
 * @param hdc
 *            A handle to the DC.
 * 
 * @param nIndex
 *            The item to be returned.
 * 
 * @return The return value specifies the value of the desired item. When
 *         <i>nIndex</i> is <code>BITSPIXEL</code> and the device has 15bpp or
 *         16bpp, the return value is 16.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDeviceCaps extends Gdi32API {
	public GetDeviceCaps() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HDC hdc = new HDC(new Pointer(t1));
		int nIndex = (int) t2;

		int ret = GDI32.INSTANCE.GetDeviceCaps(hdc, nIndex);

		register.mov("eax", new LongValue(ret));
	}
}
