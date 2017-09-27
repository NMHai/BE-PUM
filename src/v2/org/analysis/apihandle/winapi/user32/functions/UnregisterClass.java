/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: UnregisterClass.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;

/**
 * Unregisters a window class, freeing the memory required for the class.
 * 
 * @param lpClassName
 *            A null-terminated string or a class atom. If lpClassName is a
 *            string, it specifies the window class name. This class name must
 *            have been registered by a previous call to the RegisterClass or
 *            RegisterClassEx function. System classes, such as dialog box
 *            controls, cannot be unregistered. If this parameter is an atom, it
 *            must be a class atom created by a previous call to the
 *            RegisterClass or RegisterClassEx function. The atom must be in the
 *            low-order word of lpClassName; the high-order word must be zero.
 * 
 * @param hInstance
 *            A handle to the instance of the module that created the class.
 * 
 * @return If the function succeeds, the return value is nonzero. If the class
 *         could not be found or if a window still exists that was created with
 *         the class, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class UnregisterClass extends User32API {

	public UnregisterClass() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String lpClassName = memory.getText(this, t1);
		HINSTANCE hInstance = null;
		if (t2 != 0L) {
			hInstance = new HINSTANCE();
			hInstance.setPointer(new Pointer(t2));
		}
		BOOL ret = User32DLL.INSTANCE.UnregisterClass(lpClassName, hInstance);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
