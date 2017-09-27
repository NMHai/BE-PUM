/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetLastError.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

/**
 * The SetLastError function sets the last-error code for the calling thread.
 * 
 * @param dwErrCode
 *            Last-error code for the thread.
 * 
 * @author Yen Nguyen
 *
 */
public class SetLastError extends Kernel32API {

	public SetLastError() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		int dwErrCode = (int) t1;
		Kernel32.INSTANCE.SetLastError(dwErrCode);
	}

}
