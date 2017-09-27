/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: TerminateThread.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Terminates a thread.
 * 
 * @param hThread
 *            A handle to the thread to be terminated. The handle must have the
 *            THREAD_TERMINATE access right. For more information, see Thread
 *            Security and Access Rights.
 * 
 * @param dwExitCode
 *            The exit code for the thread. Use the GetExitCodeThread function
 *            to retrieve a thread's exit value.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class TerminateThread extends Kernel32API {

	public TerminateThread() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HANDLE hThread = new HANDLE(new Pointer(t1));
		DWORD dwExitCode = new DWORD(t2);

		BOOL ret = Kernel32DLL.INSTANCE.TerminateThread(hThread, dwExitCode);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
