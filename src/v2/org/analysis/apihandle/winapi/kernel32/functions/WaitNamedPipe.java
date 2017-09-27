/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: WaitNamedPipe.java
 * Created date: Aug 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;

/**
 * Waits until either a time-out interval elapses or an instance of the
 * specified named pipe is available for connection (that is, the pipe's server
 * process has a pending ConnectNamedPipe operation on the pipe).
 * 
 * @param lpNamedPipeName
 *            The name of the named pipe. The string must include the name of
 *            the computer on which the server process is executing. A period
 *            may be used for the servername if the pipe is local. The following
 *            pipe name format is used: \\servername\pipe\pipename
 * 
 * @param nTimeOut
 *            The number of milliseconds that the function will wait for an
 *            instance of the named pipe to be available. You can used one of
 *            the following values instead of specifying a number of
 *            milliseconds.
 * 
 * @return If an instance of the pipe is available before the time-out interval
 *         elapses, the return value is nonzero. If an instance of the pipe is
 *         not available before the time-out interval elapses, the return value
 *         is zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class WaitNamedPipe extends Kernel32API {

	public WaitNamedPipe() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String lpNamedPipeName = memory.getText(this, t1);
		DWORD nTimeOut = new DWORD(t2);

		BOOL ret = Kernel32DLL.INSTANCE.WaitNamedPipe(lpNamedPipeName, nTimeOut);

		register.mov("eax", new LongValue(ret.intValue()));
	}

}
