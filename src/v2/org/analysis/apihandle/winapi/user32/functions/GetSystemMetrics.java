/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetSystemMetrics.java
 * Created date: Sep 22, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The OpenProcessToken function opens the access token associated with a
 * process.
 * 
 * @param ProcessHandle
 *            A handle to the process whose access token is opened. The process
 *            must have the PROCESS_QUERY_INFORMATION access permission.
 * 
 * @param DesiredAccess
 *            dAccess [in] Specifies an access mask that specifies the requested
 *            types of access to the access token. These requested access types
 *            are compared with the discretionary access control list (DACL) of
 *            the token to determine which accesses are granted or denied. For a
 *            list of access rights for access tokens, see Access Rights for
 *            Access-Token Objects.
 * 
 * @param TokenHandle
 *            A pointer to a handle that identifies the newly opened access
 *            token when the function returns.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSystemMetrics extends User32API {

	public GetSystemMetrics() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		int ret = User32DLL.INSTANCE.GetSystemMetrics((int) t1);

		register.mov("eax", new LongValue(ret));
	}

}
