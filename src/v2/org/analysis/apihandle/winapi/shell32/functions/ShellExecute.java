/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: ShellExecute.java
 * Created date: May 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.shell32.functions;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Shell32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.INT_PTR;

/**
 * Performs an operation on a specified file.
 * 
 * @param hwnd
 *            A handle to the owner window used for displaying a UI or error
 *            messages. This value can be NULL if the operation is not
 *            associated with a window.
 *
 * @param lpOperation
 *            A pointer to a null-terminated string, referred to in this case as
 *            a verb, that specifies the action to be performed. The set of
 *            available verbs depends on the particular file or folder.
 *            Generally, the actions available from an object's shortcut menu
 *            are available verbs. The following verbs are commonly used:
 *
 *            edit Launches an editor and opens the document for editing. If
 *            lpFile is not a document file, the function will fail. explore
 *            Explores a folder specified by lpFile. find Initiates a search
 *            beginning in the directory specified by lpDirectory. open Opens
 *            the item specified by the lpFile parameter. The item can be a file
 *            or folder. print Prints the file specified by lpFile. If lpFile is
 *            not a document file, the function fails. NULL In systems prior to
 *            Windows 2000, the default verb is used if it is valid and
 *            available in the registry. If not, the "open" verb is used. In
 *            Windows 2000 and later, the default verb is used if available. If
 *            not, the "open" verb is used. If neither verb is available, the
 *            system uses the first verb listed in the registry.
 * 
 * @param lpFile
 *            A pointer to a null-terminated string that specifies the file or
 *            object on which to execute the specified verb. To specify a Shell
 *            namespace object, pass the fully qualified parse name. Note that
 *            not all verbs are supported on all objects. For example, not all
 *            document types support the "print" verb. If a relative path is
 *            used for the lpDirectory parameter do not use a relative path for
 *            lpFile.
 *
 * @param lpParameters
 *            If lpFile specifies an executable file, this parameter is a
 *            pointer to a null-terminated string that specifies the parameters
 *            to be passed to the application. The format of this string is
 *            determined by the verb that is to be invoked. If lpFile specifies
 *            a document file, lpParameters should be NULL.
 *
 * @param lpDirectory
 *            A pointer to a null-terminated string that specifies the default
 *            (working) directory for the action. If this value is NULL, the
 *            current working directory is used. If a relative path is provided
 *            at lpFile, do not use a relative path for lpDirectory.
 *
 * @param nShowCmd
 *            The flags that specify how an application is to be displayed when
 *            it is opened. If lpFile specifies a document file, the flag is
 *            simply passed to the associated application. It is up to the
 *            application to decide how to handle it.
 *
 * @return If the function succeeds, it returns a value greater than 32. If the
 *         function fails, it returns an error value that indicates the cause of
 *         the failure. The return value is cast as an HINSTANCE for backward
 *         compatibility with 16-bit Windows applications. It is not a true
 *         HINSTANCE, however. It can be cast only to an int and compared to
 *         either 32 or the following error codes below.
 *         <p/>
 *         NOTE: {@link WinDef.INT_PTR} is used instead of HINSTANCE here, since
 *         the former fits the reutrn type's actual usage more closely.
 *
 *         0 The operating system is out of memory or resources.
 *         ERROR_FILE_NOT_FOUND The specified file was not found.
 *         ERROR_PATH_NOT_FOUND The specified path was not found.
 *         ERROR_BAD_FORMAT The .exe file is invalid (non-Win32 .exe or error in
 *         .exe image). SE_ERR_ACCESSDENIED The operating system denied access
 *         to the specified file. SE_ERR_ASSOCINCOMPLETE The file name
 *         association is incomplete or invalid. SE_ERR_DDEBUSY The DDE
 *         transaction could not be completed because other DDE transactions
 *         were being processed. SE_ERR_DDEFAIL The DDE transaction failed.
 *         SE_ERR_DDETIMEOUT The DDE transaction could not be completed because
 *         the request timed out. SE_ERR_DLLNOTFOUND The specified DLL was not
 *         found. SE_ERR_FNF The specified file was not found. SE_ERR_NOASSOC
 *         There is no application associated with the given file name
 *         extension. This error will also be returned if you attempt to print a
 *         file that is not printable. SE_ERR_OOM There was not enough memory to
 *         complete the operation. SE_ERR_PNF The specified path was not found.
 *         SE_ERR_SHARE A sharing violation occurred.
 * 
 * @author Yen Nguyen
 *
 */
public class ShellExecute extends Shell32API {

	public ShellExecute() {
		super();
		NUM_OF_PARMS = 6;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);

		HWND hwnd = (t1 == 0L) ? null : new HWND(new Pointer(t1));
		String lpOperation = (t2 == 0L) ? null : memory.getText(this, t2);
		String lpFile = (t3 == 0L) ? null : memory.getText(this, t3);
		String lpParameters = (t4 == 0L) ? null : memory.getText(this, t4);
		String lpDirectory = (t5 == 0L) ? null : memory.getText(this, t5);
		int nShowCmd = (int) t6;

		System.out.println("hwnd: " + t1 + ", lpOperation: " + lpOperation + ", lpFile: " + lpFile + ", lpParameters: "
				+ lpParameters + ", lpDirectory: " + lpDirectory + ", nShowCmd: " + nShowCmd);

		INT_PTR ret = Shell32.INSTANCE.ShellExecute(hwnd, lpOperation, lpFile, lpParameters, lpDirectory, nShowCmd);

		System.out.println("Return value:" + ret.intValue());
		register.mov("eax", new LongValue(ret.intValue()));
	}

}
