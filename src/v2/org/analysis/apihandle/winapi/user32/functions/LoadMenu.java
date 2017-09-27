/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.HMENU;

/**
 * Loads the specified menu resource from the executable (.exe) file associated
 * with an application instance.
 * 
 * @param hInstance
 *            A handle to the module containing the menu resource to be loaded.
 * 
 * @param lpMenuName
 *            The name of the menu resource. Alternatively, this parameter can
 *            consist of the resource identifier in the low-order word and zero
 *            in the high-order word. To create this value, use the
 *            MAKEINTRESOURCE macro.
 * 
 * @return If the function succeeds, the return value is a handle to the menu
 *         resource. If the function fails, the return value is NULL. To get
 *         extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class LoadMenu extends User32API {

	public LoadMenu() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HINSTANCE hInstance = null;
		if (t1 != 0L) {
			hInstance = new HINSTANCE();
			hInstance.setPointer(new Pointer(t1));
		}
		WString lpMenuName = new WString(memory.getText(this, t2));
		HMENU ret = User32DLL.INSTANCE.LoadMenu(hInstance, lpMenuName);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);

	}

}
