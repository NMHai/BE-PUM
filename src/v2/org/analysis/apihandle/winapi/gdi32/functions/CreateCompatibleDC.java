/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: CreateCompatibleDC.java
 * Created date: Oct 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32.functions;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;

/**
 * The CreateCompatibleDC function creates a memory device context (DC)
 * compatible with the specified device.
 * 
 * @param hdc
 *            A handle to an existing DC. If this handle is NULL, the function
 *            creates a memory DC compatible with the application's current
 *            screen.
 * 
 * @return If the function succeeds, the return value is the handle to a memory
 *         DC. If the function fails, the return value is NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateCompatibleDC extends Gdi32API {

	public CreateCompatibleDC() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		
		HDC hdc = new HDC(new Pointer(t1));
		HDC ret = Gdi32DLL.INSTANCE.CreateCompatibleDC(hdc);
		
		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
