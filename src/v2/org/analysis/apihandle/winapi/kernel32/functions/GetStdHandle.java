/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetStdHandle.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Retrieves a handle to the specified standard device (standard input, standard
 * output, or standard error).
 * 
 * @param nStdHandle
 *            : The standard device. This parameter can be one of the following
 *            values.
 * @return <p>
 *         If the function succeeds, the return value is a handle to the
 *         specified device, or a redirected handle set by a previous call to
 *         SetStdHandle. The handle has GENERIC_READ and GENERIC_WRITE access
 *         rights, unless the application has used SetStdHandle to set a
 *         standard handle with lesser access.
 *         </p>
 *         <p>
 *         If the function fails, the return value is INVALID_HANDLE_VALUE. To
 *         get extended error information, call GetLastError.
 *         </p>
 *         <p>
 *         If an application does not have associated standard handles, such as
 *         a service running on an interactive desktop, and has not redirected
 *         them, the return value is NULL.
 *         </p>
 * 
 * @author Yen Nguyen
 *
 */
public class GetStdHandle extends Kernel32API {

	/**
	 * 
	 */
	public GetStdHandle() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t = this.params.get(0);
		HANDLE ret = Kernel32DLL.INSTANCE.GetStdHandle(new DWORD(t));

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));

//		GetFileType.stdHandleList.add((int) value);
	}

}
