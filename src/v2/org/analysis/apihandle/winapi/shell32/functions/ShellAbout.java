/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: ShellAbout.java
 * Created date: Sep 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.shell32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HICON;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Displays a ShellAbout dialog box.
 * 
 * @param hWnd
 *            A window handle to a parent window. This parameter can be NULL.
 * 
 * @param szApp
 *            A pointer to a null-terminated string that contains text to be
 *            displayed in the title bar of the ShellAbout dialog box and on the
 *            first line of the dialog box after the text "Microsoft". If the
 *            text contains a separator (#) that divides it into two parts, the
 *            function displays the first part in the title bar and the second
 *            part on the first line after the text "Microsoft".
 * 
 * @param szOtherStuff
 *            A pointer to a null-terminated string that contains text to be
 *            displayed in the dialog box after the version and copyright
 *            information. This parameter can be NULL.
 * 
 * @param hIcon
 *            The handle of an icon that the function displays in the dialog
 *            box. This parameter can be NULL, in which case the function
 *            displays the Windows icon.
 * 
 * @return TRUE if successful; otherwise, FALSE.
 * 
 * @author Yen Nguyen
 *
 */
public class ShellAbout extends Shell32API {

	public ShellAbout() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		HWND hWnd = (t1 == 0L) ? null : new HWND(new Pointer(t1));
		String szApp = (t2 == 0L) ? null : memory.getText(this, t2);
		String szOtherStuff = (t3 == 0L) ? null : memory.getText(this, t3);
		HICON hIcon = (t4 == 0L) ? null : new HICON(new Pointer(t4));
		
		int ret = Shell32DLL.INSTANCE.ShellAbout(hWnd, szApp, szOtherStuff, hIcon);

		register.mov("eax", new LongValue(ret));
	}

}
