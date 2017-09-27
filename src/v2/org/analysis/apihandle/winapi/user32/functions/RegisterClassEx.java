/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: RegisterClassEx.java
 * Created date: Mar 27, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.structures.WinUser.WNDCLASS.WNDPROC;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.ATOM;
import com.sun.jna.platform.win32.WinDef.HBRUSH;
import com.sun.jna.platform.win32.WinDef.HCURSOR;
import com.sun.jna.platform.win32.WinDef.HICON;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.WNDCLASSEX;

/**
 * Registers a window class for subsequent use in calls to the CreateWindow or
 * CreateWindowEx function.
 * 
 * @param lpwcx
 *            A pointer to a WNDCLASSEX structure. You must fill the structure
 *            with the appropriate class attributes before passing it to the
 *            function.
 * 
 * @return If the function succeeds, the return value is a class atom that
 *         uniquely identifies the class being registered. This atom can only be
 *         used by the CreateWindow, CreateWindowEx, GetClassInfo,
 *         GetClassInfoEx, FindWindow, FindWindowEx, and UnregisterClass
 *         functions and the IActiveIMMap::FilterClientWindows method. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class RegisterClassEx extends User32API {

	public RegisterClassEx() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		// UINT cbSize;
		// /* Win 3.x */
		// UINT style;
		// WNDPROC lpfnWndProc;
		// int cbClsExtra;
		// int cbWndExtra;
		// HINSTANCE hInstance;
		// HICON hIcon;
		// HCURSOR hCursor;
		// HBRUSH hbrBackground;
		// LPCWSTR lpszMenuName;
		// LPCWSTR lpszClassName;
		// /* Win 4.0 */
		// HICON hIconSm;

		System.out.println("\t\tSPECIAL WINDOWS API: CALLBACK");

		WNDCLASSEX lpwcx = new WNDCLASSEX();
		lpwcx.cbSize = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1)).getValue();
		lpwcx.style = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue();
		lpwcx.lpfnWndProc = new WNDPROC() {
			@Override
			public LRESULT invoke(HWND hwnd, UINT unit, WPARAM wparam, LPARAM lparam) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		t1 += 4;
		lpwcx.cbClsExtra = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue();
		lpwcx.cbWndExtra = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue();
		lpwcx.hInstance = new HINSTANCE();
		lpwcx.hInstance.setPointer(new Pointer((((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue())));
		lpwcx.hIcon = new HICON(new Pointer((((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue())));
		lpwcx.hCursor = new HCURSOR(new Pointer(((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue()));
		lpwcx.hbrBackground = new HBRUSH(new Pointer(((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue()));
		lpwcx.lpszMenuName = memory.getText(this, ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue());
		lpwcx.lpszClassName = new WString(memory.getText(this, ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4))
				.getValue()));
		lpwcx.hIconSm = new HICON(new Pointer(((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue()));

		ATOM ret = User32.INSTANCE.RegisterClassEx(lpwcx);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
