/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: MessageBox.java
 * Created date: Feb 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;

/**
 * Displays a modal dialog box that contains a system icon, a set of buttons,
 * and a brief application-specific message, such as status or error
 * information. The message box returns an integer value that indicates which
 * button the user clicked.
 * 
 * @param hWnd
 *            : A handle to the owner window of the message box to be created.
 *            If this parameter is NULL, the message box has no owner window.
 * 
 * @param lpText
 *            : The message to be displayed. If the string consists of more than
 *            one line, you can separate the lines using a carriage return
 *            and/or linefeed character between each line.
 * 
 * @param lpCaption
 *            : The dialog box title. If this parameter is NULL, the default
 *            title is Error.
 * 
 * @param uType
 *            : The contents and behavior of the dialog box. This parameter can
 *            be a combination of flags from the following groups of flags.
 * 
 * @return If a message box has a Cancel button, the function returns the
 *         IDCANCEL value if either the ESC key is pressed or the Cancel button
 *         is selected. If the message box has no Cancel button, pressing ESC
 *         has no effect.
 * 
 * @author Yen Nguyen
 *
 */
public class MessageBox extends User32API {

	private Integer apiCallReturn = null;

	public MessageBox() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		
		if (t2 == 0 && t3 == 0) {
			System.out.println("Special trick by Malware: set the wrong paramters.");
			register.mov("eax", new LongValue(0));
			return;
		}
		
		HWND hWnd = new HWND(new Pointer(t1));
		String lpText = memory.getText(this, t2);
		String lpCaption = memory.getText(this, t3);
		UINT uType = new UINT(t4);

		System.out.print("Handle:" + t1);
		System.out.print(", Address of Text:" + t2 + ", Text:" + lpText);
		System.out.print(", Address of Title Text:" + t3 + ", Title Text:" + lpCaption);
		System.out.println(", Style:" + t4);

		MBThread thread = new MBThread(hWnd, lpText, lpCaption, uType);
		try {
			thread.start();
			Thread.sleep(500);
			thread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Return code/value Description
		// IDABORT 3 The Abort button was selected.
		// IDCANCEL 2 The Cancel button was selected.
		// IDCONTINUE 11 The Continue button was selected.
		// IDIGNORE 5 The Ignore button was selected.
		// IDNO 7 The No button was selected.
		// IDOK 1 The OK button was selected.
		// IDRETRY 4 The Retry button was selected.
		// IDTRYAGAIN 10 The Try Again button was selected.
		// IDYES 6 The Yes button was selected.

		register.mov("eax", new LongValue(1));

		System.out
				.println("\t\tSPECIAL WINDOWS API: The return code must be based on user interaction! Simulator always return 0!");
	}

	class MBThread extends Thread {
		HWND _hWnd;
		String _lpText;
		String _lpCaption;
		UINT _uType;

		public MBThread(HWND hWnd, String lpText, String lpCaption, UINT uType) {
			this._hWnd = hWnd;
			this._lpText = lpText;
			this._lpCaption = lpCaption;
			this._uType = uType;
		}

		@Override
		public void run() {
			apiCallReturn = User32DLL.INSTANCE.MessageBox(this._hWnd, this._lpText, this._lpCaption, this._uType);
		}
	}
}
