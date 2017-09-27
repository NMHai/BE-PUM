/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetThreadPriority.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.value.LongValue;

/**
 * Sets the priority value for the specified thread. This value, together with
 * the priority class of the thread's process, determines the thread's base
 * priority level.
 * 
 * @param hThread
 *            A handle to the thread whose priority value is to be set.
 * 
 * @param nPriority
 *            The priority value for the thread. This parameter can be one of
 *            the following values.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SetThreadPriority extends Kernel32API {

	public SetThreadPriority() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HANDLE hThread = new HANDLE(new Pointer(t1));
		int nPriority = (int) t2;
		BOOL ret = Kernel32DLL.INSTANCE.SetThreadPriority(hThread, nPriority);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
