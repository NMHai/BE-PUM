/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;

/**
 * [LoadBitmap is available for use in the operating systems specified in the
 * Requirements section. It may be altered or unavailable in subsequent
 * versions. Instead, use LoadImage and DrawFrameControl.] The LoadBitmap
 * function loads the specified bitmap resource from a module's executable file.
 * 
 * @param hInstance
 *            A handle to the instance of the module whose executable file
 *            contains the bitmap to be loaded.
 * 
 * @param lpBitmapName
 *            A pointer to a null-terminated string that contains the name of
 *            the bitmap resource to be loaded. Alternatively, this parameter
 *            can consist of the resource identifier in the low-order word and
 *            zero in the high-order word. The MAKEINTRESOURCE macro can be used
 *            to create this value.
 * 
 * @return If the function succeeds, the return value is the handle to the
 *         specified bitmap. If the function fails, the return value is NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class LoadBitmap extends User32API {

	public LoadBitmap() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HINSTANCE hInstance = new HINSTANCE();
		hInstance.setPointer(new Pointer(t1));
		WString lpBitmapName = new WString(memory.getText(this, t2));
		HBITMAP ret = User32DLL.INSTANCE.LoadBitmap(hInstance, lpBitmapName);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
