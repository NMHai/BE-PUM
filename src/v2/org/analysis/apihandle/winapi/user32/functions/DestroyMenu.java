/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CloseWindow.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HMENU;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import v2.org.analysis.value.LongValue;

/**
 * Destroys the specified menu and frees any memory that the menu occupies.
 * 
 * @param hMenu
 *            [in] A handle to the menu to be destroyed.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class DestroyMenu extends User32API {

	public DestroyMenu() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HMENU hMenu = new HMENU(new Pointer(t1));
		BOOL ret = User32DLL.INSTANCE.DestroyMenu(hMenu);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
