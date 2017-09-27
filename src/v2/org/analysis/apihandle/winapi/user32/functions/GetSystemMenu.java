/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: GetSystemMenu.java
 * Created date: Mar 21, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import v2.org.analysis.value.LongValue;

/**
 * Enables the application to access the window menu (also known as the system
 * menu or the control menu) for copying and modifying.
 * 
 * @param hWnd
 *            A handle to the window that will own a copy of the window menu.
 * 
 * @param bRevert
 *            The action to be taken. If this parameter is FALSE, GetSystemMenu
 *            returns a handle to the copy of the window menu currently in use.
 *            The copy is initially identical to the window menu, but it can be
 *            modified. If this parameter is TRUE, GetSystemMenu resets the
 *            window menu back to the default state. The previous window menu,
 *            if any, is destroyed.
 * 
 * @return If the bRevert parameter is FALSE, the return value is a handle to a
 *         copy of the window menu. If the bRevert parameter is TRUE, the return
 *         value is NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSystemMenu extends User32API {

	public GetSystemMenu() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HWND hWnd = new HWND(new Pointer(t1));
		BOOL bRevert = new BOOL(t2);
		HMENU ret = User32DLL.INSTANCE.GetSystemMenu(hWnd, bRevert);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}
}
