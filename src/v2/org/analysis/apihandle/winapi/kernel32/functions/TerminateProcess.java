/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: TerminateProcess.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Terminates the specified process and all of its threads.
 * 
 * @param hProcess
 *            A handle to the process to be terminated.
 * 
 * @param uExitCode
 *            The exit code to be used by the process and threads terminated as
 *            a result of this call. Use the GetExitCodeProcess function to
 *            retrieve a process's exit value. Use the GetExitCodeThread
 *            function to retrieve a thread's exit value.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class TerminateProcess extends Kernel32API {

	public TerminateProcess() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		
		HANDLE handle = Kernel32.INSTANCE.GetCurrentProcess();
		long value = Pointer.nativeValue(handle.getPointer());
		System.out.println("HandleProcess=" + t1 + ", CurrentID=" + value);
		
		if (t1 == value) {
			System.out.println("Stop current Process");
			curState.setFeasiblePath(false);
			return;
		}

		HANDLE hProcess = new HANDLE(new Pointer(t1));
		UINT uExitCode = new UINT(t2);

		BOOL ret = Kernel32DLL.INSTANCE.TerminateProcess(hProcess, uExitCode);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
