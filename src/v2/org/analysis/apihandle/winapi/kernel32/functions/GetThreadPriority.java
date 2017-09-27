/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetThreadPriority.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the priority value for the specified thread. This value, together
 * with the priority class of the thread's process, determines the thread's
 * base-priority level.
 * 
 * @param hThread
 *            A handle to the thread. The handle must have the
 *            THREAD_QUERY_INFORMATION or THREAD_QUERY_LIMITED_INFORMATION
 *            access right. For more information, see Thread Security and Access
 *            Rights.
 * 
 * @return If the function succeeds, the return value is the thread's priority
 *         level. If the function fails, the return value is
 *         THREAD_PRIORITY_ERROR_RETURN. To get extended error information, call
 *         GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetThreadPriority extends Kernel32API {

	public GetThreadPriority() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {

		long t1 = this.params.get(0);

		HANDLE hThread = new HANDLE(new Pointer(t1));
		int ret = Kernel32DLL.INSTANCE.GetThreadPriority(hThread);

		register.mov("eax", new LongValue(ret));
	}

}
