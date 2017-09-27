/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CreateDialogParam.java
 * Created date: Mar 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.structures.WinUser.DLGPROC;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.INT_PTR;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;

/**
 * Creates a modal dialog box from a dialog box template resource. Before
 * displaying the dialog box, the function passes an application-defined value
 * to the dialog box procedure as the lParam parameter of the WM_INITDIALOG
 * message. An application can use this value to initialize dialog box controls.
 * 
 * @param hInstance
 *            A handle to the module which contains the dialog box template. If
 *            this parameter is NULL, then the current executable is used.
 * 
 * @param lpTemplateName
 *            The dialog box template. This parameter is either the pointer to a
 *            null-terminated character string that specifies the name of the
 *            dialog box template or an integer value that specifies the
 *            resource identifier of the dialog box template. If the parameter
 *            specifies a resource identifier, its high-order word must be zero
 *            and its low-order word must contain the identifier. You can use
 *            the MAKEINTRESOURCE macro to create this value.
 * 
 * @param hWndParent
 *            A handle to the window that owns the dialog box.
 * 
 * @param lpDialogFunc
 *            A pointer to the dialog box procedure. For more information about
 *            the dialog box procedure, see DialogProc.
 * 
 * @param dwInitParam
 *            The value to pass to the dialog box in the lParam parameter of the
 *            WM_INITDIALOG message.
 * 
 * @return If the function succeeds, the return value is the value of the
 *         nResult parameter specified in the call to the EndDialog function
 *         used to terminate the dialog box. If the function fails because the
 *         hWndParent parameter is invalid, the return value is zero. The
 *         function returns zero in this case for compatibility with previous
 *         versions of Windows. If the function fails for any other reason, the
 *         return value is 1. To get extended error information, call
 *         GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class DialogBoxParam extends User32API {

	public DialogBoxParam() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		HINSTANCE hInstance = new HINSTANCE();
		hInstance.setPointer(new Pointer(t1));
		String lpTemplateName = memory.getText(this, t2);
		HWND hWndParent = new HWND(new Pointer(t3));
		DLGPROC lpDialogFunc = new DLGPROC() {
			@Override
			public INT_PTR invoke(HWND hwnd, UINT unit, WPARAM wparam, LPARAM lparam) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		LPARAM dwInitParam = new LPARAM(t5);

		System.out.println("\t\tSPECIAL WINDOWS API: CALLBACK");

		INT_PTR ret = User32DLL.INSTANCE.DialogBoxParam(hInstance, lpTemplateName, hWndParent, lpDialogFunc,
				dwInitParam);
		long value = ret.longValue();

		register.mov("eax", new LongValue(value));
	}
}
