/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetStockObject.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The GetStockObject function retrieves a handle to one of the stock pens,
 * brushes, fonts, or palettes.
 * 
 * @param fnObject
 *            The type of stock object. This parameter can be one of the
 *            following values.
 * 
 * @return If the function succeeds, the return value is a handle to the
 *         requested logical object. If the function fails, the return value is
 *         NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class GetStockObject extends Gdi32API {

	public GetStockObject() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		int fnObject = (int) t1;
		HANDLE ret = Gdi32DLL.INSTANCE.GetStockObject(fnObject);

		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
