/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetConsoleCtrlHandler.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import com.sun.jna.Callback;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.value.LongValue;

/**
 * Adds or removes an application-defined HandlerRoutine function from the list
 * of handler functions for the calling process.
 * 
 * @param HandlerRoutine
 *            A pointer to the application-defined HandlerRoutine function to be
 *            added or removed. This parameter can be NULL.
 * 
 * @param Add
 *            If this parameter is TRUE, the handler is added; if it is FALSE,
 *            the handler is removed. If the HandlerRoutine parameter is NULL, a
 *            TRUE value causes the calling process to ignore CTRL+C input, and
 *            a FALSE value restores normal processing of CTRL+C input. This
 *            attribute of ignoring or processing CTRL+C is inherited by child
 *            processes.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SetConsoleCtrlHandler extends Kernel32API {

	public SetConsoleCtrlHandler() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		Callback HandlerRoutine = null;
		BOOL Add = new BOOL(t2);
		BOOL ret = Kernel32DLL.INSTANCE.SetConsoleCtrlHandler(HandlerRoutine, Add);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
