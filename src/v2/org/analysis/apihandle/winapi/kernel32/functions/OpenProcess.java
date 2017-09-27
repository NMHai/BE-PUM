/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import v2.org.analysis.value.LongValue;

/**
 * Opens an existing local process object.
 * 
 * @param dwDesiredAccess
 *            The access to the process object. This access right is checked
 *            against the security descriptor for the process. This parameter
 *            can be one or more of the process access rights.
 * 
 * @param bInheritHandle
 *            If this value is TRUE, processes created by this process will
 *            inherit the handle. Otherwise, the processes do not inherit this
 *            handle.
 * 
 * @param dwProcessId
 *            The identifier of the local process to be opened.
 * 
 * @return If the function succeeds, the return value is an open handle to the
 *         specified process. If the function fails, the return value is NULL.
 *         To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class OpenProcess extends Kernel32API {

	public OpenProcess() {
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
		DWORD dwProcessId = new DWORD(t3);
		HANDLE ret = Kernel32DLL.INSTANCE.OpenProcess(dwDesiredAccess, bInheritHandle, dwProcessId);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
