/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: ExitThread.java
 * Created date: Apr 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

/**
 * Ends the calling thread.
 * 
 * @param dwExitCode
 *            The exit code for the thread.
 * 
 * @author Yen Nguyen
 *
 */
public class ExitThread extends Kernel32API {

	public ExitThread() {
		super();
		NUM_OF_PARMS = 1;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		Kernel32DLL.INSTANCE.ExitThread(new DWORD(t1));
	}
}
