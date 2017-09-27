/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: Sleep.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.DWORD;

/**
 * Suspends the execution of the current thread until the time-out interval
 * elapses.
 * 
 * @param dwMilliseconds
 *            The time interval for which execution is to be suspended, in
 *            milliseconds. A value of zero causes the thread to relinquish the
 *            remainder of its time slice to any other thread that is ready to
 *            run. If there are no other threads ready to run, the function
 *            returns immediately, and the thread continues execution. Windows
 *            XP: A value of zero causes the thread to relinquish the remainder
 *            of its time slice to any other thread of equal priority that is
 *            ready to run. If there are no other threads of equal priority
 *            ready to run, the function returns immediately, and the thread
 *            continues execution. This behavior changed starting with Windows
 *            Server 2003. A value of INFINITE indicates that the suspension
 *            should not time out.
 * 
 * @author Yen Nguyen
 *
 */
public class Sleep extends Kernel32API {
	public static int offset = 0;

	public Sleep() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		if (t1 > 1000L) {
			offset += (int) (t1 - 1000L);
			t1 = 1000L;
		}

		DWORD dwMilliseconds = new DWORD(t1);
		Kernel32DLL.INSTANCE.Sleep(dwMilliseconds);
		
		register.mov("eax", new LongValue(0));
	}

}
