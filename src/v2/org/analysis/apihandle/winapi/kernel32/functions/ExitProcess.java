/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: ExitProcess.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

/**
 * Ends the calling process and all its threads.
 * 
 * @param uExitCode
 *            : The exit code for the process and all threads.
 * 
 * @author Yen Nguyen
 *
 */
public class ExitProcess extends Kernel32API {

	public ExitProcess() {
		super();
		NUM_OF_PARMS = 1;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);

		Kernel32DLL.INSTANCE.ExitProcess((int) t1);
	}

}
