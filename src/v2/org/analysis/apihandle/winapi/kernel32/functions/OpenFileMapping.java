/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: OpenFileMapping.java
 * Created date: Aug 31, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Opens a named file mapping object.
 * 
 * @param dwDesiredAccess
 *            The access to the file mapping object. This access is checked
 *            against any security descriptor on the target file mapping object.
 *            For a list of values, see File Mapping Security and Access Rights.
 * 
 * @param bInheritHandle
 *            If this parameter is TRUE, a process created by the CreateProcess
 *            function can inherit the handle; otherwise, the handle cannot be
 *            inherited.
 * 
 * @param lpName
 *            The name of the file mapping object to be opened. If there is an
 *            open handle to a file mapping object by this name and the security
 *            descriptor on the mapping object does not conflict with the
 *            dwDesiredAccess parameter, the open operation succeeds. The name
 *            can have a "Global\" or "Local\" prefix to explicitly open an
 *            object in the global or session namespace. The remainder of the
 *            name can contain any character except the backslash character (\).
 *            For more information, see Kernel Object Namespaces. Fast user
 *            switching is implemented using Terminal Services sessions. The
 *            first user to log on uses session 0, the next user to log on uses
 *            session 1, and so on. Kernel object names must follow the
 *            guidelines outlined for Terminal Services so that applications can
 *            support multiple users.
 * 
 * @return If the function succeeds, the return value is an open handle to the
 *         specified file mapping object. If the function fails, the return
 *         value is NULL. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class OpenFileMapping extends Kernel32API {

	public OpenFileMapping() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		DWORD dwDesiredAccess = new DWORD(t1);
		BOOL bInheritHandle = new BOOL(t2);
		String lpName = memory.getText(this, t3);
		HANDLE ret = Kernel32DLL.INSTANCE.OpenFileMapping(dwDesiredAccess, bInheritHandle, lpName);

		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
