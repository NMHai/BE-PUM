/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.HICON;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;

/**
 * Loads the specified icon resource from the executable (.exe) file associated
 * with an application instance.
 * 
 * @param hInstance
 *            A handle to an instance of the module whose executable file
 *            contains the icon to be loaded. This parameter must be NULL when a
 *            standard icon is being loaded.
 * 
 * @param lpIconName
 *            The name of the icon resource to be loaded. Alternatively, this
 *            parameter can contain the resource identifier in the low-order
 *            word and zero in the high-order word. Use the MAKEINTRESOURCE
 *            macro to create this value.
 * 
 * @return If the function succeeds, the return value is a handle to the newly
 *         loaded icon. If the function fails, the return value is NULL. To get
 *         extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class LoadIcon extends User32API {

	public LoadIcon() {
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
		WString lpIconName = new WString(memory.getText(this, t2));
		HICON ret = User32DLL.INSTANCE.LoadIcon(hInstance, lpIconName);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
