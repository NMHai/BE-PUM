/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: RegisterClass.java
 * Created date: Mar 27, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.structures.WinUser.WNDCLASS;
import v2.org.analysis.apihandle.winapi.structures.WinUser.WNDCLASS.WNDPROC;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
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

/**
 * Registers a window class for subsequent use in calls to the CreateWindow or
 * CreateWindowEx function.
 * 
 * @param lpWndClass
 *            A pointer to a WNDCLASS structure. You must fill the structure
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
public class RegisterClass extends User32API {

	public RegisterClass() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		// public UINT style;
		// public WNDPROC lpfnWndProc;
		// public int cbClsExtra;
		// public int cbWndExtra;
		// public HINSTANCE hInstance;
		// public HICON hIcon;
		// public HCURSOR hCursor;
		// public HBRUSH hbrBackground;
		// public WString lpszMenuName;
		// public WString lpszClassName;

		System.out.println("\t\tSPECIAL WINDOWS API: CALLBACK");
		WNDCLASS lpWndClass = new WNDCLASS();
		lpWndClass.style = new UINT(((LongValue) memory.getDoubleWordMemoryValue(t1)).getValue());
		lpWndClass.lpfnWndProc = new WNDPROC() {
			@Override
			public LRESULT invoke(HWND hwnd, UINT unit, WPARAM wparam, LPARAM lparam) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		t1 += 4;
		lpWndClass.cbClsExtra = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue();
		lpWndClass.cbWndExtra = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue();
		lpWndClass.hInstance = new HINSTANCE();
		lpWndClass.hInstance
				.setPointer(new Pointer((((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue())));
		lpWndClass.hIcon = new HICON(new Pointer((((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue())));
		lpWndClass.hCursor = new HCURSOR(new Pointer(((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue()));
		lpWndClass.hbrBackground = new HBRUSH(new Pointer(
				((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue()));
		lpWndClass.lpszMenuName = new WString(memory.getText(this, ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4))
				.getValue()));
		lpWndClass.lpszClassName = new WString(memory.getText(this, ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4))
				.getValue()));

		ATOM ret = User32DLL.INSTANCE.RegisterClass(lpWndClass);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
